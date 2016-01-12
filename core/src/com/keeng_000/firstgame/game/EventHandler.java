package com.keeng_000.firstgame.game;

/**
 * Created by keeng_000 on 15.10.2015.
 */


import com.badlogic.gdx.Gdx;


public class EventHandler {

    Hero hero;
    float oldAccel;
    boolean turnL = true;
    boolean turnR = true;

    public EventHandler(Hero hero){
        this.hero = hero;
        this.oldAccel = 0;
    }
    public void handleInput(){

        if(Gdx.input.isTouched()){
            if(MyGdxGame.gameRunning) {
                if (!this.hero.setState("jump")) {
                    System.out.println("Eventhandler: Konnte HeroState nicht setzen.");
                }
            }else{
                //Hier kommt der Reset
                MyGdxGame.newGame= true;
                System.out.println("Test");
            }
        }else if(Gdx.input.getAccelerometerZ()>5 && this.oldAccel <2){
            //Hier wird etwas ausgeführt, wenn das Handy geneigt wird
            /*if(!this.hero.setState("jump")){
                System.out.println("Eventhandler: Konnte HeroState nicht setzen.");
            }*/
        }else if((Gdx.input.getAccelerometerY() > 2)&&turnR){
            System.out.println("Rechts.");
            turnR = false;
            turnL = true;
            this.hero.lowerHeat();
        }else if((Gdx.input.getAccelerometerY() < -2)&&turnL){
            System.out.println("Links.");
            turnR = true;
            turnL = false;
            this.hero.lowerHeat();
        }
        this.oldAccel = Gdx.input.getAccelerometerZ();

    }
}
