package ch.m02.comet.pinball.physics.util;

import java.util.List;

import ch.m02.comet.pinball.core.model.playfield.Vector;

import com.badlogic.gdx.math.Vector2;

public class VectorUtil {
	
	private VectorUtil(){
		// utility class 
	}
	
	public static Vector2[] convertToVertices(List<? extends Vector> vectors){
		if(vectors == null )
			throw new IllegalArgumentException("vectors can not be null");
		
		Vector2[] vertices = new Vector2[vectors.size()];
		for(int i = 0; i < vectors.size(); i++){
			vertices[i] = new Vector2(vectors.get(i).getX(),vectors.get(i).getY());
		}
		return vertices;
	}
}
