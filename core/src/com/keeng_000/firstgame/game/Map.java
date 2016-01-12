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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by keeng_000 on 15.10.2015.
 */
public class Map {
    private int groundPos = 200;
    Animation waterAnimation;
    ArrayList<MapElement> mapElementStack;

    private float backGroundSkyYPos = 0, getBackGroundSkyXPos = 0;


    public Map(){

        mapElementStack = new ArrayList() {
        };

        for(int i = 0; i < 15; i++)
            mapElementStack.add(new MapElement(i * 200, 100));
        this.prepareWater();

        int xPos = 0;
        int yPos = 100;

        //mapElements = this.createMap();
    }

    public Animation getWaterAnimation(){return this.waterAnimation;}
    public ArrayList getMapElements(){
        return this.mapElementStack;
    }

    public void updateBackgroundSky(int xPos){
        this.getBackGroundSkyXPos =(float)(xPos*(-0.1));
    }

    public float getBackGroundSkyXPos(){
        return this.getBackGroundSkyXPos;
    }

    public float getBackGroundSkyYPos(){
        return this.backGroundSkyYPos;
    }

    public int getGroundPos(){return this.groundPos;}

    public void setGroundPos(int ground){this.groundPos = ground;}

    private void prepareWater() {
        Texture img;
        img = new Texture("wasser.png");
        TextureRegion[] animationFrames;

        animationFrames = new TextureRegion[2];

        TextureRegion[][] tmpFrames = TextureRegion.split(img, 1064, 322);

        int index = 0;

        for (int i = 0; i < 1; i++){
            for(int j = 0; j < 2; j++) {
                animationFrames[index++] = tmpFrames[j][i];
            }
        }

        this.waterAnimation = new Animation( 0.25f, animationFrames);
    }

    public void addNewMapElement(){
        int size =  mapElementStack.size();
        if(mapElementStack.get(size - 1).getXPos() == mapElementStack.get(size - 2).getXPos() + 200) {

            //Lücke
            Random rand = new Random();
            int i = rand.nextInt();
            if(i % 10 > 5){

                mapElementStack.add(new MapElement(mapElementStack.get(size - 1).getXPos() + 400, 100));
            } else {
                //normales Element eventuell in der Höhe versetzt
                if (rand.nextInt() % 10 > 2) {
                    mapElementStack.add(new MapElement(mapElementStack.get(size - 1).getXPos() + 200, 400));
                } else {

                    mapElementStack.add(new MapElement(mapElementStack.get(size - 1).getXPos() + 200, 100));
                }
            }

        } else {
            mapElementStack.add(new MapElement(mapElementStack.get(size - 1).getXPos() + 200, 100));
        }
    }

    public void removeMapElement(){

        mapElementStack.remove(0);

    }

/*
    public void addNewMapElement(){
        if(mapElementStack.getLast() == null){

            System.out.println("letztes Element ist null");
            mapElementStack.add(new MapElement());
        } else{
            System.out.println("Map Element");
            Random rand = new Random();
            int i = rand.nextInt();
            System.out.println(i%10);
            if(i%10 > 5){
                System.out.println("NULL ELEMENT");
                mapElementStack.add(null);
            } else{
                System.out.println("NORMALES ELEMENT");
                mapElementStack.add(new MapElement());
            }
        }
    }
*/
    public ArrayList createMap(){
        int xpos = 0;
        ArrayList<MapElement> mapElements = new ArrayList<MapElement>();
        for(int i = 0; i<100;i++){
            if(i%10 == 3){
                mapElements.add(new Ground(xpos, 400));
                xpos += 200;
            }else{
                mapElements.add(new Ground(xpos, 100));
                xpos += 200;
            }
        }
        return mapElements;
    }
}


//hallo miteinander