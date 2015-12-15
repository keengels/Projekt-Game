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
    public boolean invisible;

    public MapElement(int xPos, int yPos, String texture){
        this.xPos = xPos;
        this.yPos = yPos;
        img = new Texture(texture);
    }

    public MapElement(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        img = new Texture("traeger.png");

    }

    public Texture getTexture(){
        return this.img;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getXPos(){
        return this.xPos;
    }

    public int getYpos(){
        return this.yPos;
    }


    public void setYPos(){
        this.setYPos();
    }
}
