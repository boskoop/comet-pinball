package ch.m02.comet.pinball.prototype.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import ch.m02.comet.pinball.prototype.tween.SpriteTween;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen {

	Texture splashTexture;
	Sprite splashSprite;
	SpriteBatch batch;
	TweenManager tweenManager;
	Game game;

	public SplashScreen(Game game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1); // black
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		tweenManager.update(delta);

		batch.begin();
		splashSprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		splashTexture = new Texture("data/splash.png");
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		splashSprite = new Sprite(splashTexture);
		splashSprite.setColor(1, 1, 1, 0);

		batch = new SpriteBatch();

		Tween.registerAccessor(Sprite.class, new SpriteTween());
		tweenManager = new TweenManager();

		TweenCallback cb = new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				tweenCompleted();
			}
		};

		Tween.to(splashSprite, SpriteTween.ALPHA, 2f).target(1)
				.ease(TweenEquations.easeInQuad).repeatYoyo(1, 1.5f)
				.setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
				.start(tweenManager);
	}

	private void tweenCompleted() {
		game.setScreen(new MainMenu(game));
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
