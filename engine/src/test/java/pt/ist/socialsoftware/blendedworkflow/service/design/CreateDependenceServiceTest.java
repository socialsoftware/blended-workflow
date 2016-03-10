package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DependenceDTO;

public class CreateDependenceServiceTest extends TeardownRollbackTest {
	private static final String SPEC_ID = "ID1";
	private static final String SPEC_NAME = "Doctor Appointment";
	private static final String ENTITY_NAME_ONE = "Entity name one";
	private static final String ENTITY_NAME_TWO = "Entity name two";
	private static final String ROLENAME_ONE = "rolenameOne";
	private static final String ROLENAME_TWO = "rolenameTwo";
	private static final String ATTRIBUTE_GROUP_NAME = "Medication";
	private static final String ATTRIBUTE_NAME_ONE = "Portion";
	private static final String DEPENDENCE_ONE = ENTITY_NAME_ONE + "." + ROLENAME_TWO;
	private static final String DEPENDENCE_TWO = ENTITY_NAME_TWO + "." + ROLENAME_ONE;

	DesignInterface designInterface;
	DataModel dataModel;
	Entity entityOne;
	Entity entityTwo;
	Attribute att;

	@Override
	public void populate4Test() throws BWException {
		designInterface = DesignInterface.getInstance();

		new Specification(SPEC_ID, SPEC_NAME, "author", "description", "version", "UID");
		dataModel = getBlendedWorkflow().getSpecById(SPEC_ID).get().getDataModel();

		entityOne = new Entity(dataModel, ENTITY_NAME_ONE, false);
		att = new Attribute(dataModel, entityOne, ATTRIBUTE_NAME_ONE, AttributeType.NUMBER, true, false, false);

		entityTwo = new Entity(dataModel, ENTITY_NAME_TWO, false);

		new RelationBW(dataModel, "name", entityOne, ROLENAME_ONE, Cardinality.ONE, false, entityTwo, ROLENAME_TWO,
				Cardinality.ZERO_MANY, false);

		entityTwo.createDependence(DEPENDENCE_TWO);
	}

	@Test
	public void successCreateEntityDependence() {
		designInterface.createDependence(new DependenceDTO(SPEC_ID, entityOne.getFullPath(), DEPENDENCE_ONE));

		assertEquals(1, dataModel.getEntity(ENTITY_NAME_ONE).get().getDependenceSet().size());
		assertEquals(DEPENDENCE_ONE + "," + DEPENDENCE_TWO, dataModel.getDependenceSet().stream()
				.map(dep -> dep.getPath().getValue()).sorted().collect(Collectors.joining(",")));
	}

	@Test
	public void successCreateAttributeDependence() {
		designInterface.createDependence(new DependenceDTO(SPEC_ID, att.getFullPath(), DEPENDENCE_ONE));

		assertEquals(0, dataModel.getEntity(ENTITY_NAME_ONE).get().getDependenceSet().size());
		assertEquals(1, dataModel.getEntity(ENTITY_NAME_ONE).get().getAttribute(ATTRIBUTE_NAME_ONE).get()
				.getDependenceSet().size());
		assertEquals(DEPENDENCE_ONE + "," + DEPENDENCE_TWO, dataModel.getDependenceSet().stream()
				.map(dep -> dep.getPath().getValue()).sorted().collect(Collectors.joining(",")));
	}

}
