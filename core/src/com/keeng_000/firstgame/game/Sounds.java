package com.keeng_000.firstgame.game;

import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Selcuk on 27.10.2015.
 */
public class Sounds {
    private static HashMap<String, Sound> sounds;

    static{
        sounds = new HashMap<String, Sound>();
    }
    public static void load(String path,String name){
        Sound mp3Sound = Gdx.audio.newSound(Gdx.files.internal(path));
        sounds.put(name,mp3Sound);
    }
    public static void play(String name){
        sounds.get(name).play();
    }
    public static void loop(String name){
        sounds.get(name).loop(0);
    }
    public static void stop(String name){
        sounds.get(name).stop();
    }
    public static void stopAll(){
        for(Sound s : sounds.values()){
            s.stop();
        }
    }

}
