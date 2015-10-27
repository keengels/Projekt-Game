package com.keeng_000.firstgame.game;

/**
 * Created by keeng_000 on 15.10.2015.
 */

import com.badlogic.ashley.signals.Listener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
public class EventHandler {

    Hero hero;

    public EventHandler(Hero hero){
        this.hero = hero;
    }
    public void handleInput(){

        if(Gdx.input.isTouched()){
            if(!this.hero.setState("jump")){
                System.out.println("Eventhandler: Konnte HeroState nicht setzen.");
            }
        }
    }
}
