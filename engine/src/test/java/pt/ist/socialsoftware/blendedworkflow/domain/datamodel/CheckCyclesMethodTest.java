package pt.ist.socialsoftware.blendedworkflow.domain.datamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CheckCyclesMethodTest extends TeardownRollbackTest {

	private static final String SPEC_ID = "SpecId";
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ENT_TWO_NAME = "EntTwo";
	private static final String ENT_THREE_NAME = "EntThree";
	private static final String ATT_ONE_NAME = "AttOne";
	private static final String ATT_TWO_NAME = "AttTwo";
	private static final String ATT_THREE_NAME = "AttThree";
	private static final String ATT_FOUR_NAME = "AttFour";
	private static final String ROLENAME_ENT_ONE = "entOne";
	private static final String ROLENAME_ENT_TWO = "entTwo";
	private static final String ROLENAME_ENT_THREE = "entThree";

	Specification spec;
	DataModel dataModel;
	Entity entOne;
	Entity entTwo;
	Entity entThree;
	Attribute attOne;
	Attribute attTwo;
	Attribute attThree;
	Attribute attFour;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification(SPEC_ID, "name", "author", "description", "version", "UID");
		dataModel = spec.getDataModel();

		entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		attOne = new Attribute(dataModel, entOne, ATT_ONE_NAME, AttributeType.NUMBER, true, false, false);
		attTwo = new Attribute(dataModel, entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

		attThree = new Attribute(dataModel, entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true, false, false);

		attFour = new Attribute(dataModel, entThree, ATT_FOUR_NAME, AttributeType.NUMBER, true, false, false);

		new RelationBW(dataModel, "relOneTwo", entOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY, false, entTwo,
				ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

		new RelationBW(dataModel, "relOneThree", entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false, entThree,
				ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

		new RelationBW(dataModel, "relTwoThree", entThree, ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false, entTwo,
				ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

	}

	@Test
	public void successDependence() throws BWException {
		new Dependence(dataModel, entOne,
				ENT_ONE_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);

		dataModel.checkDependences();
	}

	@Test
	public void failAttributeToAttributeOneStep() throws BWException {
		try {
			new Dependence(dataModel, attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
			new Dependence(dataModel, attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME);
			dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failAttributeToAttributeTwoStep() throws BWException {
		try {
			new Dependence(dataModel, attOne,
					ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
			new Dependence(dataModel, attFour,
					ENT_THREE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME);
			dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failAttributeToAttributeTransitive() throws BWException {
		try {
			new Dependence(dataModel, attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
			new Dependence(dataModel, attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
			new Dependence(dataModel, attFour, ENT_THREE_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME);
			dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failEntityToEntity() throws BWException {
		try {
			new Dependence(dataModel, entOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO);
			new Dependence(dataModel, entTwo, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE);
			dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failEntityToEntityTwoStep() throws BWException {
		try {
			new Dependence(dataModel, entOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE);
			new Dependence(dataModel, entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_ONE);
			dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failEntityToEntityTransitive() throws BWException {
		try {
			new Dependence(dataModel, entOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO);
			new Dependence(dataModel, entTwo, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE);
			new Dependence(dataModel, entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_ONE);
			dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failEntityToEntityInverse() throws BWException {
		try {
			new Dependence(dataModel, entOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE);
			new Dependence(dataModel, entTwo, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE);
			dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void sucessAttributeToEntityInverse() throws BWException {
		new Dependence(dataModel, attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE);
		new Dependence(dataModel, entTwo, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE);
		dataModel.checkDependences();
	}

	@Test
	public void failEntityToAttributeInverse() throws BWException {
		try {
			new Dependence(dataModel, entOne,
					ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
			new Dependence(dataModel, entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_ONE);
			dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void successAttributeToAttributeInverse() throws BWException {
		new Dependence(dataModel, attOne,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
		new Dependence(dataModel, entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_ONE);
		dataModel.checkDependences();
	}

	@Test
	public void failEntityDependsOnItsAttributeDirect() throws BWException {
		try {
			new Dependence(dataModel, entOne, ENT_ONE_NAME + "." + ATT_ONE_NAME);
			dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failEntityDependsOnItsAttributeIndirect() throws BWException {
		try {
			new Dependence(dataModel, entOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO);
			new Dependence(dataModel, entTwo, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE);
			new Dependence(dataModel, entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME);
			dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void successAttributeDependenceCanCreateAllEntitiesOne() throws BWException {
		new Dependence(dataModel, attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
		new Dependence(dataModel, attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
		new Dependence(dataModel, entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_TWO_NAME);
		dataModel.checkDependences();
	}

	@Test
	public void successAttributeDependenceCanCreateAllEntitiesTwo() throws BWException {
		new Dependence(dataModel, attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
		new Dependence(dataModel, attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
		new Dependence(dataModel, entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_ONE);
		dataModel.checkDependences();
	}

	@Test
	public void sucessAttributeDependenceCanCreateAllEntitiesThree() throws BWException {
		new Dependence(dataModel, attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
		new Dependence(dataModel, attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
		new Dependence(dataModel, entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_ONE);
		dataModel.checkDependences();
	}

	@Test
	public void successIntermediatePathDependenceOne() throws BWException {
		new Dependence(dataModel, attOne,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
		new Dependence(dataModel, entTwo, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE);
		dataModel.checkDependences();
	}

	@Test
	public void successIntermediatePathDependenceTwo() throws BWException {
		new Dependence(dataModel, attOne,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
		new Dependence(dataModel, entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_TWO);
		dataModel.checkDependences();
	}

}
