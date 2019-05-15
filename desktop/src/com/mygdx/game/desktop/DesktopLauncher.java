package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Star2DGame;
import com.mygdx.game.StarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		float aspect = 3f/4f;
//		config.height = 500;
//		config.width = (int)(config.height*aspect);

		new LwjglApplication(new Star2DGame(), config);
	}
}
