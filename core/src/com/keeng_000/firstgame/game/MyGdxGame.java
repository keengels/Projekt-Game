package com.keeng_000.firstgame.game;

import com.badlogic.ashley.signals.Listener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

public class MyGdxGame extends ApplicationAdapter {

	private Music music_level1;
	Actor actor;
	SpriteBatch batch;
	public EventHandler eventHandler;
	public Map map;
	public Hero hero;
	private GameDraw gameDraw;
	private InputListener inputListener;

	long endtime;
	float elapsedTime;



	@Override
	public void create () {
		map = new Map();
		actor = new Actor();
		hero = new Hero(map);
		eventHandler = new EventHandler(hero);
		endtime = System.currentTimeMillis();
		batch = new SpriteBatch();
		gameDraw = new GameDraw(batch, hero, map);
		elapsedTime = 0f;

		//Sounds laden
		Sounds.load("sounds/extralife.ogg", "extralife");
		Sounds.load("sounds/shoot.ogg","shoot");
		Sounds.load("sounds/thruster.ogg", "thruster");
		Sounds.load("sounds/fever.mp3", "fever");

		music_level1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/fever.mp3"));
		music_level1.setLooping(true);
		music_level1.play();
	}


	private void engine(){
		map.updateBackgroundSky();
		eventHandler.handleInput();
		hero.heroEngine();
	}


	@Override
	public void render () {
		this.engine();
		//update();
		elapsedTime += Gdx.graphics.getDeltaTime();

		if(elapsedTime > 50f){
			elapsedTime = 0f;
		}

		//Sounds.play("extralife");

		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameDraw.render(elapsedTime);
	}

}
