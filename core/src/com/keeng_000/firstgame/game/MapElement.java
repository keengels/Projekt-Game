package com.keeng_000.firstgame.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by keeng_000 on 20.10.2015.
 */
public class MapElement {

    private int width = 200;
    private int height = 100;
    private int xPos, yPos;
    private Texture img;

    public MapElement(int xPos, int yPos, String texture){
        this.xPos = xPos;
        this.yPos = yPos;
        img = new Texture(texture);
    }

    public Texture getTexture(){
        return this.img;
    }

    public int getXPos(){
        return this.xPos;
    }

    public int getYpos(){
        return this.yPos;
    }

    public void setXPos(){
        this.xPos -= 5;
    }   //Gamespeed noch hinzufügen

    public void setYPos(){
        this.setYPos();
    }
}
