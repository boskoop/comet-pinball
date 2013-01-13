package ch.m02.comet.pinball.presentation.screens;


import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.model.simulation.Score;
import ch.m02.comet.pinball.core.presentation.screen.HighscoreScreen;
import ch.m02.comet.pinball.presentation.graphics.GraphicsDisplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class HighscoreScreenImpl extends ManagedScreen implements HighscoreScreen {
	
	private static final Logger log = LoggerFactory.getLogger(HighscoreScreenImpl.class);
	
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
	private TextButton button;
	private Label gameNameLabel;
	private List<? extends Score> highscores;
	
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
		
		display.registerHighscoreScreen(this);
	}

	public void setHighscores(List<? extends Score> highscores){
		this.highscores = highscores;
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);

		batch.begin();
		stage.draw();
		//drawHighscores(batch);
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

		String text = "Patrick scores!";
		button = new TextButton(text, style);
		button.setWidth( blackFont.getBounds(text).width + width/20);
		button.setHeight(blackFont.getBounds(text).height + height/20);
		//button.setHeight(100);
		
		button.setX(width/ 2f - button.getWidth() / 2f);
		button.setY(height / 2f - button.getHeight() / 2f);
		
		System.out.println(width + " " + height);
		System.out.println(width/ 2f - button.getWidth() / 2f);
		System.out.println(button.getWidth());
		
		LabelStyle ls = new LabelStyle(whiteFont, Color.WHITE);
		gameNameLabel = new Label("Highscores", ls);
		gameNameLabel.setX(0f);
		gameNameLabel.setY(9*height/10);
		gameNameLabel.setWidth(width);
		gameNameLabel.setAlignment(Align.center);

		//stage.addActor(button);
		stage.addActor(gameNameLabel);
		
		drawHighscores(stage);
		if(highscores != null){
			log.info("count of highscores:" + highscores.size());
			System.out.println("Highscores: ");
			for(Score score : highscores)
				System.out.println("Score:"+score.getScoreValue());
		} else {
			log.info("no highscores");
		} 
	}
	
	public void drawHighscores(Stage stage){
		String text;
		float x;
		float y;
		LabelStyle highscoreLabelStyle = new LabelStyle(whiteFont, Color.WHITE);
		for(int i = 0; i < 5; i++){
			if(highscores != null && highscores.size() > i){
				text = fillString(highscores.get(i).getSimulation().getPlayer().getName(),' ',10) + "\t - \t" + fillString(Integer.toString(highscores.get(i).getScoreValue()),' ',10);			
			} else {
				text = fillString("Patrick",' ',5) + "   -   " + fillString("1000000",' ',5);
			}
			x = Gdx.graphics.getWidth() /2f - whiteFont.getBounds(text).width/2;
			y = (8f-i)*Gdx.graphics.getHeight()/10f - whiteFont.getBounds(text).height/2;
			//whiteFont.draw(spriteBatch,text,x,y);
			
			Label highscore = new Label(text, highscoreLabelStyle);
			highscore.setPosition(x, y);
			stage.addActor(highscore);
			
			log.debug("highscoretext: "+ text + " Position:"+ x +" "+ y);
		}
		
	}
	
	private String fillString(String string,char fill,int size){
		if(string.length() > size)
			return string.substring(0, size);
		else if(string.length() == size)
			return string;
		else{
			while(string.length() <size){
				string = fill + string;
			}
			return string;
		}
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
