package pt.ist.socialsoftware.blendedworkflow.domain.dependence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateDependenceMethodTest extends TeardownRollbackTest {
	private static final String ENTITY_NAME_ONE = "Entity name one";
	private static final String ENTITY_NAME_TWO = "Entity name two";
	private static final String ROLENAME_ONE = "rolenameOne";
	private static final String ROLENAME_TWO = "rolenameTwo";

	private static String DEPENDENCE = ENTITY_NAME_ONE + "." + ROLENAME_TWO;

	private Entity entityOne = null;
	private Entity entityTwo = null;
	private AttributeBasic attributeBasic = null;
	private AttributeGroup attributeGroup = null;

	@Override
	public void populate4Test() throws BWException {
		Specification spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		entityOne = new Entity(spec.getDataModel(), ENTITY_NAME_ONE, false);
		attributeGroup = new AttributeGroup(spec.getDataModel(), entityOne, "att1", true);
		attributeBasic = new AttributeBasic(spec.getDataModel(), entityOne, null, "att2", AttributeType.BOOLEAN, true,
				false, false);
		entityTwo = new Entity(spec.getDataModel(), ENTITY_NAME_TWO, false);

		new RelationBW(spec.getDataModel(), "name", entityOne, ROLENAME_ONE, Cardinality.ONE, false, entityTwo,
				ROLENAME_TWO, Cardinality.ZERO_MANY, false);
	}

	@Test
	public void successEntity() {
		entityOne.createDependence(DEPENDENCE);

		assertEquals(1, entityOne.getDependenceSet().size());
		assertEquals(DEPENDENCE, entityOne.getDependenceSet().stream().findFirst().get().getPath().getValue());
	}

	@Test
	public void successAttributeGroup() {
		attributeGroup.createDependence(DEPENDENCE);

		assertEquals(1, attributeGroup.getDependenceSet().size());
		assertEquals(DEPENDENCE, attributeGroup.getDependenceSet().stream().findFirst().get().getPath().getValue());
	}

	@Test
	public void successAttribute() {
		attributeBasic.createDependence(DEPENDENCE);

		assertEquals(1, attributeBasic.getDependenceSet().size());
		assertEquals(DEPENDENCE, attributeBasic.getDependenceSet().stream().findFirst().get().getPath().getValue());
	}

}
