package com.keeng_000.firstgame.game;

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
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;


/**
 * Created by keeng_000 on 10.10.2015.
 */
public class GameDraw extends ApplicationAdapter{

    Map map;
    Texture img;
    Hero hero;
    SpriteBatch batch;
    Animation animation;
    TextureRegion[] animationFrames;

    public GameDraw(SpriteBatch batch, Hero hero, Map map) {
        this.batch = batch;
        this.hero = hero;
        this.map = map;
    }

    public void render (float elapsedTime) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        ArrayList<MapElement> tmpMapElements = map.getMapElements();




        //Render Sky
        batch.draw(map.getBackGroundSky(), map.getBackGroundSkyXPos(), 0, 2000, 3000);

        //Render Hero
        batch.draw(hero.getCurAnimation().getKeyFrame(elapsedTime, true), hero.getXpos(), hero.getYpos());

        //Render Mapelemente
        for(int i = 0; i < tmpMapElements.size(); i++){
            tmpMapElements.get(i).setXPos();
            batch.draw(tmpMapElements.get(i).getTexture(), tmpMapElements.get(i).getXPos(), tmpMapElements.get(i).getYpos());
        }


        batch.end();
    }

    public void dispose(){
        batch.dispose();
    }

}
