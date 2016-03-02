package pt.ist.socialsoftware.blendedworkflow.domain.path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Path;
import pt.ist.socialsoftware.blendedworkflow.domain.Product.ProductType;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class GetTargetOfPathMethodTest extends TeardownRollbackTest {
	private static final String ROLE_TWO = "role2";
	private static final String ROLE_ONE = "role1";
	private static final String SPEC_ID = "ID0";
	private static final String SPEC_NAME = "Old Doctor Appointment Specification";
	private static final String ENTITY_NAME_TWO = "Entity Name";
	private static final String ENTITY_NAME_ONE = "Exists Entity Name";
	private static final String ATTRIBUTE_NAME_ONE = "Exists Attribute Name";
	private static final String ATTRIBUTE_NAME_TWO = "Exists Attribute Name String";

	Specification spec;
	Entity entityOne;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification(SPEC_ID, SPEC_NAME, "author", "description", "version", "UID");

		entityOne = new Entity(spec.getDataModel(), ENTITY_NAME_ONE, false);
		Entity entityTwo = new Entity(spec.getDataModel(), ENTITY_NAME_TWO, false);
		new AttributeBasic(spec.getDataModel(), entityOne, null, ATTRIBUTE_NAME_ONE, AttributeType.NUMBER, true, false,
				false);
		new AttributeBasic(spec.getDataModel(), entityOne, null, ATTRIBUTE_NAME_TWO, AttributeType.STRING, false, false,
				false);

		new RelationBW(spec.getDataModel(), "relation", entityOne, ROLE_ONE, Cardinality.ZERO_OR_ONE, false, entityTwo,
				ROLE_TWO, Cardinality.ONE, false);
	}

	@Test
	public void successAttribute() throws BWException {
		Path path = new Path(spec.getDataModel(), ENTITY_NAME_TWO + "." + ROLE_ONE + "." + ATTRIBUTE_NAME_ONE);

		assertEquals(ProductType.ATTRIBUTE_BASIC, path.getTarget().getProductType());

		AttributeBasic attribute = (AttributeBasic) path.getTarget();

		assertEquals(SPEC_ID, attribute.getDataModel().getSpecification().getSpecId());
		assertEquals(entityOne.getExternalId(), attribute.getEntity().getExternalId());
		assertEquals(ATTRIBUTE_NAME_ONE, attribute.getName());
	}

	@Test
	public void successEntity() throws BWException {
		Path path = new Path(spec.getDataModel(), ENTITY_NAME_TWO + "." + ROLE_ONE);

		assertEquals(ProductType.ENTITY, path.getTarget().getProductType());

		Entity entity = (Entity) path.getTarget();

		assertEquals(SPEC_ID, entity.getDataModel().getSpecification().getSpecId());
		assertEquals(ENTITY_NAME_ONE, entity.getName());
	}

	@Test
	public void failNoAttribute() throws BWException {
		try {
			Path path = new Path(spec.getDataModel(), ENTITY_NAME_TWO + "." + ROLE_ONE + "." + "blablabla");
			path.check();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_PATH, bwe.getError());
		}
	}

}
