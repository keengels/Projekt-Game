package com.keeng_000.firstgame.game;

/**
 * Created by Selcuk on 09.12.2015.
 */
public class SoundManager {

    public SoundManager(){
        Sounds.load("sounds/extralife.ogg", "extralife");
        Sounds.load("sounds/shoot.ogg","shoot");
        Sounds.load("sounds/thruster.ogg", "thruster");
        Sounds.load("sounds/fever.mp3", "fever");
        Sounds.load("sounds/powerup.wav", "powerup");
        Sounds.load("sounds/jump.wav", "jump");
        Sounds.load("sounds/gameover.wav", "gameover");
        Sounds.load("sounds/fireball.wav", "fireball");
    }
}
