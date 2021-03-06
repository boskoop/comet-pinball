package ch.m02.comet.pinball.prototype.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class MainMenu implements Screen {

	private static final String DRAWABLE_BUTTON = "buttonnormal";
	private static final String DRAWABLE_BUTTON_PRESSED = "buttonpressed";

	private Stage stage;
	private BitmapFont blackFont;
	private BitmapFont whiteFont;
	private TextureAtlas atlas;
	private Skin skin;
	private SpriteBatch batch;
	private TextButton button;
	private Label gameNameLabel;
	
	private Game game;

	public MainMenu(Game game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

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

		button = new TextButton("Play!", style);
		button.setWidth(400);
		button.setHeight(100);
		button.setX(Gdx.graphics.getWidth() / 2f - button.getWidth() / 2f);
		button.setY(Gdx.graphics.getHeight() / 2f - button.getHeight() / 2f);

		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new PinballPrototypeGame(game));
			}
		});
		
		LabelStyle ls = new LabelStyle(whiteFont, Color.WHITE);
		gameNameLabel = new Label("Comet Pinball", ls);
		gameNameLabel.setX(0f);
		gameNameLabel.setY(Gdx.graphics.getHeight() / 2f + 100f);
		gameNameLabel.setWidth(width);
		gameNameLabel.setAlignment(Align.center);

		stage.addActor(button);
		stage.addActor(gameNameLabel);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("data/menu/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		blackFont = new BitmapFont(Gdx.files.internal("data/menu/nueva_black.fnt"),
				false);
		whiteFont = new BitmapFont(Gdx.files.internal("data/menu/nueva_white.fnt"),
				false);
	}

	@Override
	public void hide() {
		dispose();
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
