package com.keeng_000.firstgame.game;

/**
 * Created by follnoob on 10.11.15.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class TieledTest extends ApplicationAdapter implements InputProcessor {

    Texture img;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    World world;
    BodyDef bodyDef;
    FixtureDef fixtureDef;
    CircleShape circle;
    Fixture fixture;
    Body body;

    public TieledTest() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        //bodyDef.position.set(Gdx.graphics.getWidth()/2-, -100);


        world = new World(new Vector2(0,-10), true);

        body = world.createBody(bodyDef);

        circle = new CircleShape();
        circle.setRadius(60f);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        fixture = body.createFixture(fixtureDef);

        //tiledMap = new TmxMapLoader().load("test.tmx");
        //tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        //camera = new OrthographicCamera();
        //camera.setToOrtho(false);
        //camera.translate(-624f, 0);
        //camera.zoom = 3.2f;
        //camera.update();
        Gdx.input.setInputProcessor(this);
        circle.dispose();
    }
    //Ich liebe Florian
    @Override
    public void render() {

        //camera.translate(0.3f, 0f);


        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //camera.update();
        //tiledMapRenderer.setView(camera);
        //tiledMapRenderer.render();
        world.step(1/60f, 6, 2);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
