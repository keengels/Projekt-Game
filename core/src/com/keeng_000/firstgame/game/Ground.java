package com.keeng_000.firstgame.game;

/**
 * Created by follnoob on 27.10.15.
 */
public class Ground extends MapElement {
    private boolean isVisible;

    public Ground(int xPos, int yPos,boolean isVisible) {
        super(xPos, yPos, "traeger.png");
        this.isVisible = isVisible;
    }

    public boolean getVisible(){return this.isVisible;}
}
