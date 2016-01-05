package com.keeng_000.firstgame.game;

/**
 * Created by Florian on 17.11.2015.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu {
    private Texture background;
    private Texture start;
    private Texture option;
    private SpriteBatch batch;

    public Menu(){
        batch = new SpriteBatch();
        background = new Texture("baum.png");
        start = new Texture("start.png");
        option = new Texture("optionen.png");
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }
    public void render() {
       batch.begin();
       Gdx.gl.glClearColor(1, 1, 1, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       batch.draw(background, 10, 10);
       batch.draw(start, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/3 + 100, Gdx.graphics.getWidth() /3, Gdx.graphics.getHeight()/4 - 100);
       batch.draw(option, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/3 - 100, Gdx.graphics.getWidth() /3, Gdx.graphics.getHeight()/4 -100);
       batch.end();


    }

}
