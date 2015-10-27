package com.keeng_000.firstgame.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.keeng_000.firstgame.game.GameDraw;
import com.keeng_000.firstgame.game.MyGdxGame;

public class AndroidLauncher extends AndroidApplication {
	GameDraw gameDraw;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGdxGame(), config);
	}
}
