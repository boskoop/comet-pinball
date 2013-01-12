package ch.m02.comet.pinball.logic.persistence.internal;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import ch.m02.comet.pinball.core.model.playfield.Rule;
import ch.m02.comet.pinball.logic.model.playfield.PlayFieldBumperElementPdo;
import ch.m02.comet.pinball.logic.model.playfield.PlayFieldElementPdo;
import ch.m02.comet.pinball.logic.model.playfield.PlayFieldObstacleElementPdo;
import ch.m02.comet.pinball.logic.model.playfield.PlayFieldPdo;
import ch.m02.comet.pinball.logic.model.playfield.PlayFieldRulePdo;
import ch.m02.comet.pinball.logic.model.playfield.PlayFieldSlingshotElementPdo;
import ch.m02.comet.pinball.logic.model.playfield.VectorPdo;
import ch.m02.comet.pinball.logic.persistence.PlayFieldStore;

public class PlayFieldStoreManagerImplTest {

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
	    
//	    SchemaOutputResolver sor = new SchemaOutputResolver() {
//	    	@Override
//	    	public Result createOutput(String namespaceURI, String suggestedFileName)
//	    			throws IOException {
//	    		File file = new File(suggestedFileName);
//	            StreamResult result = new StreamResult(file);
//	            result.setSystemId(file.toURI().toURL().toString());
//	            return result;
//	    	}
//	    };
//	    context.generateSchema(sor);
	}
	
	private static class Rule1 implements Rule { }
	private static class Rule2 implements Rule { }

}
