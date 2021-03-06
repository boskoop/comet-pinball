package ch.m02.comet.pinball.presentation.screens;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.logic.command.SavePlayerNameCommand;
import ch.m02.comet.pinball.core.presentation.screen.PlayerNameScreen;
import ch.m02.comet.pinball.presentation.graphics.GraphicsDisplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class PlayerNameScreenImpl extends ManagedScreen implements
		PlayerNameScreen {

	private static final Logger log = LoggerFactory
			.getLogger(PlayerNameScreenImpl.class);

	@Inject
	private ApplicationContext context;

	@Inject
	private GraphicsDisplay display;

	private static final String DRAWABLE_BUTTON = "buttonnormal";
	private static final String DRAWABLE_BUTTON_PRESSED = "buttonpressed";

	private Stage stage;
	private BitmapFont blackFont;
	private BitmapFont whiteFont;
	private TextureAtlas atlas;
	private Skin skin;
	private SpriteBatch batch;
	private TextButton saveButton;
	private Label gameNameLabel;

	private TextField playernameTextField;

	private int scoreValue;

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

		display.registerPlayerNameScreen(this);

		BitmapFont font = new BitmapFont();
		font.setColor(Color.YELLOW);
		// Drawable playernameTextFieldBackground = new BaseDrawable();

		TextFieldStyle playernameTextFieldStyle = new TextFieldStyle();
		playernameTextFieldStyle.font = font;
		playernameTextFieldStyle.fontColor = Color.YELLOW;

		playernameTextField = new TextField("your name",
				playernameTextFieldStyle);
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

		String text = "Save";
		saveButton = new TextButton(text, style);
		saveButton.setWidth(blackFont.getBounds(text).width + width / 20);
		saveButton.setHeight(blackFont.getBounds(text).height + height / 20);

		saveButton.setX(width / 2f - saveButton.getWidth() / 2f);
		saveButton.setY(2 * height / 10f - saveButton.getHeight() / 2f);

		saveButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				log.info("'Save' button pressed");
				String playerName = playernameTextField.getText();
				SavePlayerNameCommand command = context.getComponentContainer()
						.getComponent(SavePlayerNameCommand.class);
				command.setPlayerName(playerName);
				command.execute();
			}
		});
		LabelStyle ls = new LabelStyle(whiteFont, Color.WHITE);
		gameNameLabel = new Label("Enter playername", ls);
		gameNameLabel.setX(0f);
		gameNameLabel.setY(9 * height / 10);
		gameNameLabel.setWidth(width);
		gameNameLabel.setAlignment(Align.center);

		playernameTextField.setPosition(
				width / 2 - playernameTextField.getWidth() / 2, 4 * height / 10
						- playernameTextField.getHeight() / 2);

		stage.addActor(playernameTextField);
		stage.addActor(saveButton);
		stage.addActor(gameNameLabel);
		drawHighscore(stage);
	}

	public void drawHighscore(Stage stage) {
		float x;
		float y;
		String text = "Your score is: " + Integer.toString(scoreValue);
		LabelStyle highscoreLabelStyle = new LabelStyle(whiteFont, Color.WHITE);

		x = Gdx.graphics.getWidth() / 2f
				- whiteFont.getBounds(text).width / 2;
		y = 5 * Gdx.graphics.getHeight() / 10f
				- whiteFont.getBounds(text).height / 2;

		Label highscore = new Label(text, highscoreLabelStyle);
		highscore.setPosition(x, y);

		stage.addActor(highscore);
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

	public void setHighscore(int scoreValue) {
		this.scoreValue = scoreValue;
	}

}
