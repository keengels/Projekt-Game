package com.keeng_000.firstgame.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

/**
 * Created by Selcuk on 09.12.2015.
 */
public class Score {
    public BitmapFont font = new BitmapFont( );
    SpriteBatch batch;
    public Hero hero;
    public int score = 0;
    //ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
   // executor.scheduleAtFixedRate(helloRunnable, 0, 3, TimeUnit.SECONDS);

    public Score(int score) {
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void updateEverySecond() {

        System.out.println("Hier:"+ score);

        try {
            Thread.sleep(2000);
            score += 2;
        } catch(InterruptedException ie) {}
        showScore();
    }

    public void showScore() {

        System.out.println("Ich bin:" + score);
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        System.out.println("aaa");
        font.getData().setScale(-4, 4);
        System.out.println(score);
        font.draw(batch, "Score: " + score, hero.getXpos() - 600, hero.getYpos() + 500);
        System.out.println("Hallo" + score);
        System.out.println("hierasdad");

    }


}

