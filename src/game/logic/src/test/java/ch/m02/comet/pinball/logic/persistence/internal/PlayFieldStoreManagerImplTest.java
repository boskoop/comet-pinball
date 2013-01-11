package ch.m02.comet.pinball.logic.persistence.internal;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldElementType;
import ch.m02.comet.pinball.logic.model.playfield.PlacementPdo;
import ch.m02.comet.pinball.logic.model.playfield.PlayFieldElementPdo;
import ch.m02.comet.pinball.logic.model.playfield.PlayFieldPdo;
import ch.m02.comet.pinball.logic.model.playfield.PlayFieldRulePdo;
import ch.m02.comet.pinball.logic.persistence.PlayFieldStore;

public class PlayFieldStoreManagerImplTest {

	@Test
	public void testPlayFieldGeneration() throws Exception {
		PlayFieldRulePdo rule = new PlayFieldRulePdo();
		rule.setClassName("java.lang.String");
		List<Integer> parameters = new ArrayList<Integer>();
		parameters.add(5);
		rule.setParameters(parameters);
		
		PlayFieldRulePdo rule2 = new PlayFieldRulePdo();
		rule2.setClassName("java.lang.Integer");
		List<Integer> parameters2 = new ArrayList<Integer>();
		parameters2.add(7);
		rule2.setParameters(parameters2);
		
		List<PlayFieldRulePdo> rules = new ArrayList<PlayFieldRulePdo>();
		rules.add(rule);
		rules.add(rule2);
		
		PlayFieldElementPdo element = new PlayFieldElementPdo();
		element.setId(1);
		PlacementPdo placement = new PlacementPdo();
		placement.setPositionX(50.0f);
		placement.setPositionY(40.0f);
		placement.setRotation(0.0f);
		placement.setScale(1.0f);
		element.setPlacement(placement);
		element.setType(PlayFieldElementType.BUMPER);

		PlayFieldElementPdo element2 = new PlayFieldElementPdo();
		element2.setId(2);
		PlacementPdo placement2 = new PlacementPdo();
		placement2.setPositionX(35.0f);
		placement2.setPositionY(20.0f);
		placement2.setRotation(0.0f);
		placement2.setScale(1.0f);
		element2.setPlacement(placement2);
		element2.setType(PlayFieldElementType.SLINGSHOT);
		
		List<PlayFieldElementPdo> elements = new ArrayList<PlayFieldElementPdo>();
		elements.add(element);
		elements.add(element2);
		
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

}
