package ch.m02.comet.pinball.playfield;

import com.badlogic.gdx.physics.box2d.World;

public interface PlayfieldElement {

	public void init(World world);
	
	public void render();
	
}
