package ch.m02.comet.pinball.presentation.screens;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.logic.command.NewSimulationCommand;
import ch.m02.comet.pinball.core.logic.command.ShowHighscoresCommand;
import ch.m02.comet.pinball.core.presentation.screen.MainMenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class MainMenuScreenImpl extends ManagedScreen implements MainMenuScreen {
	
	private static final Logger log = LoggerFactory.getLogger(MainMenuScreenImpl.class);
	
	@Inject
	private ApplicationContext context;

	private static final String DRAWABLE_BUTTON = "buttonnormal";
	private static final String DRAWABLE_BUTTON_PRESSED = "buttonpressed";

	private Stage stage;
	private BitmapFont blackFont;
	private BitmapFont whiteFont;
	private TextureAtlas atlas;
	private Skin skin;
	private SpriteBatch batch;
	private TextButton startButton;
	private TextButton highscoreButton;
	private Label gameNameLabel;
	
	public final static int BUTTON_OFFSET = 120;

	@Override
	public void init() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("data/menu/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		blackFont = new BitmapFont(
				Gdx.files.internal("data/menu/nueva_black.fnt"), false);
		whiteFont = new BitmapFont(
				Gdx.files.internal("data/menu/nueva_white.fnt"), false);
		stage = new Stage();
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);

		batch.begin();
		stage.draw();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null) {
			stage = new Stage(width, height, true);
		}
		stage.clear();

		Gdx.input.setInputProcessor(stage);

		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable(DRAWABLE_BUTTON);
		style.down = skin.getDrawable(DRAWABLE_BUTTON_PRESSED);
		style.font = blackFont;

		startButton = createStartGameButton(style);
		startButton.setX(Gdx.graphics.getWidth() / 2f - startButton.getWidth() / 2f);
		startButton.setY(Gdx.graphics.getHeight() / 2f - startButton.getHeight() / 2f);
		
		highscoreButton = createHighscoreButton(style);
		highscoreButton.setX(Gdx.graphics.getWidth() / 2f - highscoreButton.getWidth() / 2f);
		highscoreButton.setY(Gdx.graphics.getHeight() / 2f - highscoreButton.getHeight() / 2f - BUTTON_OFFSET);
		
		LabelStyle ls = new LabelStyle(whiteFont, Color.WHITE);
		gameNameLabel = new Label("Comet Pinball", ls);
		gameNameLabel.setX(0f);
		gameNameLabel.setY(Gdx.graphics.getHeight() / 2f + 100f);
		gameNameLabel.setWidth(width);
		gameNameLabel.setAlignment(Align.center);

		stage.addActor(startButton);
		stage.addActor(highscoreButton);
		stage.addActor(gameNameLabel);
	}
	
	private TextButton createStartGameButton(TextButtonStyle style){
		TextButton startButton = new TextButton("Start Game",style);
		startButton.setWidth(400);
		startButton.setHeight(100);

		startButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				log.info("'New game' button pressed");
				NewSimulationCommand command = context.getComponentContainer().getComponent(NewSimulationCommand.class);
				command.execute();
			}
		});
		
		return startButton;
	}
	
	private TextButton createHighscoreButton(TextButtonStyle style){
		TextButton startButton = new TextButton("Highscores",style);
		startButton.setWidth(400);
		startButton.setHeight(100);

		startButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				log.info("'Highscore' button pressed");
				ShowHighscoresCommand command = context.getComponentContainer().getComponent(ShowHighscoresCommand.class);
				command.execute();
			}
		});
		
		return startButton;
	}
	
	

	@Override
	public void show() {
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
		skin.dispose();
		atlas.dispose();
		blackFont.dispose();
		stage.dispose();
	}

}
