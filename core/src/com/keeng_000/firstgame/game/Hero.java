package com.keeng_000.firstgame.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.HashMap;

/**
 * Created by keeng_000 on 17.10.2015.
 */
public class Hero {

    private int width = 100;
    private int height = 100;
    private int maxJumpHeight = 500;
    private int gravity = 20;
    private String heroState="move";
    private int xPos, yPos;
    private HashMap<String, Animation> animationContainer = new HashMap<String, Animation>();

    Texture img;
    Animation animation;
    Map map;
    TextureRegion[] animationFrames;

    public Hero(Map map){
            this.map = map;

            this.xPos = 100;
            this.yPos = 200;

            if(this.prepareAnimations()){
                System.out.println("Hero-Class: Hero-Animations loaded.");
            }else{
                System.out.println("Hero-Class: Something went wrong loading the Animations.");
            }
    }

    public boolean setState(String state){

        if(animationContainer.get(state) == null){
            System.out.println("Hero-Class: Animation von Setstate Eventhandler ist nicht in animationContainer vorhanden.");
            return false;
        }else if(this.yPos == map.getGroundPos()){
            this.heroState = state;
            return true;
        }
        return true;
    }

    private void checkCollision(){

    }

    public void heroEngine(){

        //Aktualisierung des Helden
        if(this.heroState == "jump" && this.maxJumpHeight >= this.yPos){
            this.yPos +=40;

            if(this.yPos >= this.maxJumpHeight){
                this.heroState = "fall";
            }
        }

        if(this.yPos != map.getGroundPos() && this.heroState == "fall"){
            this.yPos -= this.gravity;

            if(this.yPos < map.getGroundPos()){
                this.yPos = map.getGroundPos();
                heroState = "move";
            }
        }
    }

    public Animation getCurAnimation(){
        return animationContainer.get(heroState);
    }

    private boolean prepareAnimations(){
/*
        //Move-Animation
        img = new Texture("runningcat.png");

        animationFrames = new TextureRegion[8];

        TextureRegion[][] tmpFrames = TextureRegion.split(img, 512, 256);

        int index = 0;

        for (int i = 0; i < 2; i++){
            for(int j = 0; j < 4; j++) {
                animationFrames[index++] = tmpFrames[j][i];
            }
        }
        animation = new Animation( 0.2f, animationFrames);
        */

        img = new Texture("ball.png");

        animationFrames = new TextureRegion[8];

        TextureRegion[][] tmpFrames = TextureRegion.split(img, 105, 110);

        int index = 0;

        for (int i = 0; i < 2; i++){
            for(int j = 0; j < 4; j++) {
                animationFrames[index++] = tmpFrames[j][i];
            }
        }

        animation = new Animation( 0.25f, animationFrames);

        animationContainer.put("move", animation);
        animationContainer.put("fall", animation);
        animationContainer.put("jump", animation);

        return true;
    }



    public void move(){

    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getXpos(){
        return this.xPos;
    }

    public int getYpos(){
        return this.yPos;
    }

    public boolean setYpos(int newPos){
        this.yPos = newPos;
        return true;
    }
}
