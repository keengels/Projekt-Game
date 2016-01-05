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
    Texture backGroundSky;
    // Texture backGroundOlaf;
    ArrayList<MapElement> mapElementStack;

    private float backGroundSkyYPos = 0, getBackGroundSkyXPos = 0;


    public Map(){

        mapElementStack = new ArrayList() {
        };

        for(int i = 0; i < 10; i++)
            mapElementStack.add(new MapElement(i * 200, 100));

        backGroundSky = new Texture("wolken.jpg");

        int xPos = 0;
        int yPos = 100;

        //mapElements = this.createMap();
    }


    public ArrayList getMapElements(){
        return this.mapElementStack;
    }

    public void updateBackgroundSky(int xPos){
        this.getBackGroundSkyXPos =(float)(xPos*(-0.1));
    }

    public Texture getBackGroundSky(){
        return this.backGroundSky;
    }

    public float getBackGroundSkyXPos(){
        return this.getBackGroundSkyXPos;
    }

    public float getBackGroundSkyYPos(){
        return this.backGroundSkyYPos;
    }

    public int getGroundPos(){return this.groundPos;}

    public void setGroundPos(int ground){this.groundPos = ground;}


    public void addNewMapElement(){
        int size =  mapElementStack.size();
        if(mapElementStack.get(size - 1) == null){

            System.out.println("letztes Element ist null");
            mapElementStack.add(new MapElement(mapElementStack.get(size - 2).getXPos() + 400, 100));
        } else {
            System.out.println("Map Element");
            Random rand = new Random();
            int i = rand.nextInt();
            System.out.println(i%10);
            if(i%10 > 5){
                System.out.println("NULL ELEMENT");
                mapElementStack.add(null);
            } else {
                System.out.println("NORMALES ELEMENT");
                mapElementStack.add(new MapElement(mapElementStack.get(size - 1).getXPos() + 200, 100));
            }
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