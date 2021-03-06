package com.keeng_000.firstgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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

    private FreeTypeFontGenerator freeTypeFont;
    private FreeTypeFontGenerator.FreeTypeFontParameter freeTypePar;
    private BitmapFont scoreFont;
    private int xOffset = 300;
    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();

    Score score;
    Map map;
    Hero hero;
    SpriteBatch batch;
    SpriteBatch batchfont;
    OrthographicCamera cam;
    int oldXpos;

    public GameDraw(SpriteBatch batch, Hero hero, Map map, Score score) {
        this.batch = batch;
        this.hero = hero;
        this.map = map;
        this.score = score;
        this.oldXpos = hero.getXpos();
        this.batchfont = new SpriteBatch();

        cam  = new OrthographicCamera(30, 30*(Gdx.graphics.getWidth()/Gdx.graphics.getHeight()));
        cam.position.set(hero.getXpos(), hero.getYpos(),0);
        cam.setToOrtho(false);
        cam.update();

        freeTypeFont = new FreeTypeFontGenerator(Gdx.files.internal("font1.ttf"));
        freeTypePar = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypePar.size = (int) (50 * Gdx.graphics.getDensity());
        freeTypePar.color = Color.RED;
        scoreFont = freeTypeFont.generateFont(freeTypePar);
        freeTypeFont.dispose();

    }

    public void render (float elapsedTime) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        ArrayList<MapElement> tmpMapElements = map.getMapElements();
        float currentYpos = hero.getYpos();

        batch.setProjectionMatrix(cam.combined);
        cam.position.set(hero.getXpos()+this.xOffset, currentYpos, 0);
        //Render Hero
        batch.draw(hero.getCurAnimation().getKeyFrame(elapsedTime, true), hero.getXpos(), currentYpos);

        //Render Mapelemente
        for(int i = 0; i < tmpMapElements.size(); i++){
            if(tmpMapElements.get(i) != null)
            batch.draw(tmpMapElements.get(i).getTexture(), tmpMapElements.get(i).getXPos(), tmpMapElements.get(i).getYpos());
        }
        //Wasser rendern
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                batch.draw(map.getWaterAnimation().getKeyFrame(elapsedTime, true), (hero.getXpos() - (Gdx.graphics.getWidth() / 2)) + (i * 1064), -290-(j*322));
            }
        }
        //Render Score
        batch.end();
        batchfont.begin();
        scoreFont.draw(batchfont, "Points:" + score.getScore(), 0, height - 50);
        batchfont.end();

        if(oldXpos + 1000 < hero.getXpos()){
            oldXpos += 200;
            map.addNewMapElement();
            //map.removeMapElement(hero.getXpos());

        }

        //cam.position.set(hero.getXpos()+this.xOffset, hero.getYpos(), 0);
        cam.update();
    }
    public void dispose(){
        batch.dispose();
    }
}
