package com.keeng_000.firstgame.game;


import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {

	private FreeTypeFontGenerator freeTypeFont;
	private FreeTypeFontGenerator.FreeTypeFontParameter freeTypePar;
	private BitmapFont loserFont;
	static boolean gameRunning = true;
	static boolean newGame = false;
	private Music music_level1;
	Actor actor;
	SpriteBatch batch;
	public EventHandler eventHandler;
	public Map map;
	public Hero hero;
	private GameDraw gameDraw;
	private Score score;

	long endtime;
	float elapsedTime;

	public SoundManager sm;
	@Override
	public void create () {
		freeTypeFont = new FreeTypeFontGenerator(Gdx.files.internal("font1.ttf"));
		freeTypePar = new FreeTypeFontGenerator.FreeTypeFontParameter();
		freeTypePar.size = (int) (50* Gdx.graphics.getDensity());
		freeTypePar.color = Color.RED;
		loserFont = freeTypeFont.generateFont(freeTypePar);
		freeTypeFont.dispose();

		map = new Map();
		actor = new Actor();
		hero = new Hero(map);
		sm = new SoundManager();
		eventHandler = new EventHandler(hero);
		endtime = System.currentTimeMillis();
		batch = new SpriteBatch();
		score = new Score();


		gameDraw = new GameDraw(batch, hero, map, score);
		elapsedTime = 0f;


		music_level1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/fever.mp3"));
		music_level1.setLooping(true);
		music_level1.play();
	}


	private void engine(){
		map.updateBackgroundSky(hero.getXpos());
		eventHandler.handleInput();
		hero.heroEngine();
	}


	@Override
	public void render () {

		elapsedTime += Gdx.graphics.getDeltaTime();

		if (elapsedTime > 50f) {
			elapsedTime = 0f;
		}

		if(MyGdxGame.gameRunning) {
			this.engine();
			score.updateScore();


			Gdx.gl.glClearColor(0.0f, 0.0f, 0.75f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			gameDraw.render(elapsedTime);
		}else
		{
			eventHandler.handleInput();

			ArrayList<MapElement> tmpMapElements = map.getMapElements();
			batch.begin();
			if(hero.getMolten()) {
				batch.draw(hero.getCurAnimation().getKeyFrame(elapsedTime, true), hero.getXpos(), hero.getYpos());
			//for(int i = 0; i < tmpMapElements.size(); i++){
			//	if(tmpMapElements.get(i) != null)
			//		batch.draw(tmpMapElements.get(i).getTexture(), tmpMapElements.get(i).getXPos(), tmpMapElements.get(i).getYpos());
			//}
			}

			loserFont.draw(batch, "Click for Restart", (hero.getXpos() - (Gdx.graphics.getWidth() / 3))+300, hero.getYpos());
			loserFont.draw(batch, "Points: " + this.score.getScore(), ((hero.getXpos() + 50) - (Gdx.graphics.getWidth() / 3))+300, hero.getYpos() - 140);
			batch.end();
			music_level1.stop();

			if(MyGdxGame.newGame){
				this.reset();
			}
		}
	}

	public void reset(){
		MyGdxGame.gameRunning = true;
		MyGdxGame.newGame = false;

		map = new Map();
		actor = new Actor();
		hero = new Hero(map);
		score = new Score();
		eventHandler = new EventHandler(hero);
		endtime = System.currentTimeMillis();
		batch = new SpriteBatch();

		gameDraw = new GameDraw(batch, hero, map, score);
		elapsedTime = 0f;

		music_level1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/fever.mp3"));
		music_level1.setLooping(true);
		music_level1.play();

	}
}
