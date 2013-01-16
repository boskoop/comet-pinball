package ch.m02.comet.pinball.persistence.internal;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;

import ch.m02.comet.pinball.core.model.playfield.Rule;
import ch.m02.comet.pinball.persistence.model.playfield.PlayFieldBumperElementPdo;
import ch.m02.comet.pinball.persistence.model.playfield.PlayFieldElementPdo;
import ch.m02.comet.pinball.persistence.model.playfield.PlayFieldObstacleElementPdo;
import ch.m02.comet.pinball.persistence.model.playfield.PlayFieldPdo;
import ch.m02.comet.pinball.persistence.model.playfield.PlayFieldRulePdo;
import ch.m02.comet.pinball.persistence.model.playfield.PlayFieldSlingshotElementPdo;
import ch.m02.comet.pinball.persistence.model.playfield.PlayFieldStore;
import ch.m02.comet.pinball.persistence.model.playfield.VectorPdo;

public class PlayFieldStoreDaoImplTest {

	private static class Rule1 implements Rule {

		@Override
		public void handleHit(int id) {
		}

		@Override
		public void init(List<Integer> paramters) {
		} 
	}
	
	private static class Rule2 implements Rule {

		@Override
		public void handleHit(int id) {
		}

		@Override
		public void init(List<Integer> paramters) {
		}
	}
	
	@Test
	public void testPlayFieldGeneration() throws Exception {
		PlayFieldRulePdo rule = new PlayFieldRulePdo();
		rule.setClassName(Rule1.class);
		List<Integer> parameters = new ArrayList<Integer>();
		parameters.add(5);
		rule.setParameters(parameters);
		
		PlayFieldRulePdo rule2 = new PlayFieldRulePdo();
		rule2.setClassName(Rule2.class);
		List<Integer> parameters2 = new ArrayList<Integer>();
		parameters2.add(7);
		rule2.setParameters(parameters2);
		
		List<PlayFieldRulePdo> rules = new ArrayList<PlayFieldRulePdo>();
		rules.add(rule);
		rules.add(rule2);
		
		PlayFieldBumperElementPdo bumperElement = new PlayFieldBumperElementPdo();
		bumperElement.setId(1);
		VectorPdo v = new VectorPdo();
		v.setX(50f);
		v.setY(40f);
		bumperElement.setPosition(v);
		bumperElement.setRadius(5f);

		PlayFieldSlingshotElementPdo slingshotElement = new PlayFieldSlingshotElementPdo();
		slingshotElement.setId(2);
		v = new VectorPdo();
		v.setX(20f);
		v.setY(15f);
		slingshotElement.setPosition(v);
		v = new VectorPdo();
		v.setX(2f);
		v.setY(0f);
		slingshotElement.setCornerA(v);
		v = new VectorPdo();
		v.setX(0f);
		v.setY(2f);
		slingshotElement.setCornerB(v);
		
		PlayFieldObstacleElementPdo obstacleElement = new PlayFieldObstacleElementPdo();
		obstacleElement.setId(3);
		v = new VectorPdo();
		v.setX(60f);
		v.setY(60f);
		obstacleElement.setPosition(v);
		List<VectorPdo> vertices = new ArrayList<VectorPdo>();
		vertices.add(new VectorPdo(1, 1));
		vertices.add(new VectorPdo(-1, 1));
		vertices.add(new VectorPdo(-1, -1));
		vertices.add(new VectorPdo(1, -1));
		obstacleElement.setVertices(vertices);
		
		List<PlayFieldElementPdo> elements = new ArrayList<PlayFieldElementPdo>();
		elements.add(bumperElement);
		elements.add(slingshotElement);
		elements.add(obstacleElement);
		
		PlayFieldPdo field = new PlayFieldPdo();
		field.setName("testfield");
		field.setElements(elements);
		field.setRules(rules);
		PlayFieldStore store = new PlayFieldStore();
		List<PlayFieldPdo> playfields = new ArrayList<PlayFieldPdo>();
		playfields.add(field);
		store.setPlayFields(playfields);
		
		JAXBContext context = JAXBContext.newInstance(PlayFieldStore.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	    // Write to System.out
	    m.marshal(store, System.out);
	    
	    @SuppressWarnings("unused")
		SchemaOutputResolver sor = new SchemaOutputResolver() {
	    	@Override
	    	public Result createOutput(String namespaceURI, String suggestedFileName)
	    			throws IOException {
	    		File file = new File(suggestedFileName);
	            StreamResult result = new StreamResult(file);
	            result.setSystemId(file.toURI().toURL().toString());
	            return result;
	    	}
	    };
//	    context.generateSchema(sor);
	}

	@Test
	public void testLoadPlayField() throws Exception {
		InputStream playfieldStream = getClass().getResourceAsStream(
				getClass().getSimpleName() + ".xml");
		PlayFieldStore store = null;
		try {
			JAXBContext context = JAXBContext.newInstance(PlayFieldStore.class);
			Unmarshaller u = context.createUnmarshaller();

			store = (PlayFieldStore) u.unmarshal(playfieldStream);
		} finally {
			playfieldStream.close();
		}
		System.out.println(store.getPlayFields().get(0));
		
		PlayFieldPdo playField = store.getPlayFields().get(0);
		assertEquals("testfield", playField.getName());
		assertEquals(3, playField.getElements().size());
		assertEquals(2, playField.getGameRules().size());
	}
	

}
