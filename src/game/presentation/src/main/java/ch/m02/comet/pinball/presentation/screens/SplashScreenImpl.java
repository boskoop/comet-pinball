package ch.m02.comet.pinball.presentation.screens;

import javax.inject.Inject;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.logic.command.ShowMainMenuCommand;
import ch.m02.comet.pinball.core.presentation.screen.SplashScreen;
import ch.m02.comet.pinball.presentation.tween.SpriteTween;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreenImpl extends ManagedScreen implements SplashScreen {
	
	@Inject
	private ApplicationContext context;

	Texture splashTexture;
	Sprite splashSprite;
	SpriteBatch batch;
	TweenManager tweenManager;
	
	@Override
	public void init() {
		splashTexture = new Texture("data/splash.png");
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		batch = new SpriteBatch();
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
		splashSprite = new Sprite(splashTexture);
		splashSprite.setColor(1, 1, 1, 0);

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
		ShowMainMenuCommand command = context.getComponentContainer().getComponent(ShowMainMenuCommand.class);
		command.execute();
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
		batch.dispose();
		splashTexture.dispose();
	}

}
