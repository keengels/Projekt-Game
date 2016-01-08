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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by keeng_000 on 17.10.2015.
 */
public class Hero {

    private int heatLevel = 0;
    private float heat = 0f;
    private int movementSpeed = 5;
    private int posEndJump = 0;
    private int width = 100;
    private int height = 100;
    private int maxJumpHeight = 500;
    private int gravity = 10;
    private String heroState="fall";
    private int xPos, yPos;
    private HashMap<String, Animation> animationContainer = new HashMap<String, Animation>();
    private ArrayList<MapElement> allMapElements;
    Texture img;
    Animation animation;
    Map map;
    TextureRegion[] animationFrames;


    public Hero(Map map){
            this.map = map;

            this.allMapElements = map.getMapElements();
            this.xPos = 200;
            this.yPos = 400;

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
        }else{

            if(state == "jump"){

                if(this.heroState != "jump" && this.heroState != "fall") {
                    posEndJump = this.yPos + this.maxJumpHeight;
                    this.yPos += 20;
                    this.xPos += movementSpeed;
                    if (this.heroState == "move") {
                        this.heroState = state;

                    }
                }
            }
            else{
                this.heroState = state;
            }

            return true;
        }
    }

    private void checkCollision(){
        boolean kollisionUnten = false;
        for(int i = 0; i < this.allMapElements.size(); i++) {
            if ((this.xPos <= (allMapElements.get(i).getWidth() + allMapElements.get(i).getXPos())) && this.xPos + this.width >= allMapElements.get(i).getXPos()) {
                if (this.yPos == (allMapElements.get(i).getHeight() + allMapElements.get(i).getYpos())) {
                    //Kollision unten
                    kollisionUnten = true;
                    this.setState("move");
                } else if ((this.yPos + this.height) == (allMapElements.get(i).getYpos())) {
                    //Kollision oben
                    this.setState("fall");
                } else if ((this.xPos + this.width) == (allMapElements.get(i).getXPos()) && this.yPos < (allMapElements.get(i).getYpos()) + allMapElements.get(i).getHeight() && this.yPos > (allMapElements.get(i).getYpos())) {
                    //Kollision rechts
                    this.setState("fall");
                } else if (this.heroState.equals("move") && !kollisionUnten) {
                    this.setState("fall");
                }
            }
        }
    }


    public void heroEngine(){
        if(this.yPos < -100){
            /*
            this.xPos = 0;
            this.yPos = 300;
            */
            MyGdxGame.gameRunning = false;
        }
        this.checkCollision();
        //Aktualisierung des Helden
        if(this.heroState == "jump"){
            Sounds.play("jump");
            if(this.posEndJump > this.yPos) {
                this.yPos += 20;
                this.xPos += this.movementSpeed;
            }else{

                this.heroState = "fall";
            }
        }

        if(this.heroState == "move"){
            this.xPos += this.movementSpeed;
            this.heat += 0.002f;
            this.heatLevel = (int)this.heat;

            if(this.heatLevel >= 3) {
                this.heatLevel = 2;
            }
        }

        if(this.heroState == "fall"){
            //Sounds.play("gameover");
            this.yPos -= this.gravity;
            this.xPos += this.movementSpeed / 2;
        }
    }
    boolean einmal = true;
    public Animation getCurAnimation(){

        if(this.heatLevel == 0) {
            return animationContainer.get(heroState);
        }else{
            if(einmal) {
                Sounds.play("powerup");
                einmal = false;
            }

            return animationContainer.get(heroState+this.heatLevel);
        }

    }

    private boolean prepareAnimations(){

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

        img = new Texture("ball2.png");

        animationFrames = new TextureRegion[8];

        TextureRegion[][] tmpFrames2 = TextureRegion.split(img, 105, 110);

        index = 0;

        for (int i = 0; i < 2; i++){
            for(int j = 0; j < 4; j++) {
                animationFrames[index++] = tmpFrames2[j][i];
            }
        }

        animation = new Animation( 0.25f, animationFrames);

        animationContainer.put("move1", animation);


        animationContainer.put("fall1", animation);
        animationContainer.put("jump1", animation);

        img = new Texture("ball3.png");

        animationFrames = new TextureRegion[8];

        TextureRegion[][] tmpFrames3 = TextureRegion.split(img, 105, 110);

        index = 0;

        for (int i = 0; i < 2; i++){
            for(int j = 0; j < 4; j++) {
                animationFrames[index++] = tmpFrames3[j][i];
            }
        }

        animation = new Animation( 0.25f, animationFrames);

        animationContainer.put("move2", animation);
        animationContainer.put("fall2", animation);
        animationContainer.put("jump2", animation);



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

    public void lowerHeat(){
        this.heat = this.heat -  0.05f;
    }
}
