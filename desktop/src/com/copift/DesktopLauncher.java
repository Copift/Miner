package com.copift;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.copift.CMain;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(Gmap.left*2+ Gmap.widthBlock* Gmap.MaxX+ Gmap.MaxX* (Gmap.distance), 600);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new CMain(), config);
	}
}
