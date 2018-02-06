package pt.ist.socialsoftware.blendedworkflow.core.domain.dependence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class CreateDependenceMethodTest extends TeardownRollbackTest {
	private static final String ENTITY_NAME_ONE = "Entity name one";
	private static final String ENTITY_NAME_TWO = "Entity name two";
	private static final String ROLENAME_ONE = "rolenameOne";
	private static final String ROLENAME_TWO = "rolenameTwo";

	private static String DEPENDENCE = ENTITY_NAME_ONE + "." + ROLENAME_TWO;

	private Entity entityOne = null;
	private Entity entityTwo = null;
	private Attribute attributeBasic = null;

	@Override
	public void populate4Test() throws BWException {
		Specification spec = new Specification("SpecId", "My spec");
		this.entityOne = new Entity(spec.getDataModel(), ENTITY_NAME_ONE, false);
		this.attributeBasic = new Attribute(spec.getDataModel(), this.entityOne, "att2", AttributeType.BOOLEAN, true,
				false, false);
		this.entityTwo = new Entity(spec.getDataModel(), ENTITY_NAME_TWO, false);

		new RelationBW(spec.getDataModel(), "name", this.entityOne, ROLENAME_ONE, Cardinality.ONE, false,
				this.entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);
	}

	@Test
	public void successEntity() {
		this.entityOne.createDependence(DEPENDENCE);

		assertEquals(1, this.entityOne.getDependenceSet().size());
		assertEquals(DEPENDENCE, this.entityOne.getDependenceSet().stream().findFirst().get().getPath().getValue());
	}

	@Test
	public void successAttribute() {
		this.attributeBasic.createDependence(DEPENDENCE);

		assertEquals(1, this.attributeBasic.getDependenceSet().size());
		assertEquals(DEPENDENCE,
				this.attributeBasic.getDependenceSet().stream().findFirst().get().getPath().getValue());
	}

}
