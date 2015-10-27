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

/**
 * Created by keeng_000 on 15.10.2015.
 */
public class Map {

    /*
        0000000000000000000000000000000000000000000000000000000000000000000000
        0000000000000000000000000000000000000000000000000000000000000000000000
        00000000000000000000000#########00000000000000000000000000000000000000
        0000000000000000000000000000000000000000000000000000000000000000000000
        ########################000000000#####################################
     */

    private int groundPos = 200;
    Texture backGroundSky;
    Texture backGroundOlaf;
    ArrayList<MapElement> mapElements = new ArrayList<MapElement>();

    private float backGroundSkyYPos = 0, getBackGroundSkyXPos = 0;


    public Map(){
        backGroundSky = new Texture("wolken.jpg");
        int xPos = 0;
        int yPos = 100;

        for(int i = 0; i<10; i++){
            mapElements.add(new MapElement(xPos, yPos, "traeger.png"));
            xPos += 200;
        }
    }

    public ArrayList getMapElements(){
        return this.mapElements;
    }

    public void updateBackgroundSky(){
        this.getBackGroundSkyXPos-=0.1;
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

    public ArrayList createMapFromString(String map){
        //Erstellt eine Map aus einem String
        ArrayList<MapElement> mapElements = new ArrayList<MapElement>();

        return mapElements;
    }
}


//hallo miteinander