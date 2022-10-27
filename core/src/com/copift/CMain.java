package com.copift;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class CMain extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Gmap gmap;

	@Override
	public void create () {
		batch = new SpriteBatch();


		gmap=new Gmap(batch);

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 1, 1);
		batch.begin();

		gmap.draw();

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
