package pt.ist.socialsoftware.blendedworkflow.domain.datamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CheckCircularitiesMethodTest extends TeardownRollbackTest {

	private static final String SPEC_ID = "SpecId";
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ENT_TWO_NAME = "EntTwo";
	private static final String ENT_THREE_NAME = "EntThree";
	private static final String GROUP_ONE_NAME = "GroupOne";
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
	AttributeGroup attGroupOne;
	AttributeBasic attOne;
	AttributeBasic attTwo;
	AttributeBasic attThree;
	AttributeBasic attFour;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification(SPEC_ID, "name", "author", "description", "version", "UID");
		dataModel = spec.getDataModel();

		entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		attGroupOne = new AttributeGroup(dataModel, entOne, GROUP_ONE_NAME, true);

		attOne = new AttributeBasic(dataModel, entOne, null, ATT_ONE_NAME, AttributeType.NUMBER, true, false, false);
		attGroupOne.addAttribute(attOne);
		attTwo = new AttributeBasic(dataModel, entOne, null, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

		attThree = new AttributeBasic(dataModel, entTwo, null, ATT_THREE_NAME, AttributeType.NUMBER, true, false,
				false);

		attFour = new AttributeBasic(dataModel, entThree, null, ATT_FOUR_NAME, AttributeType.NUMBER, true, false,
				false);

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

		dataModel.checkCircularities();
	}

	@Test
	public void failAttributeToAttributeOneStep() throws BWException {
		try {
			new Dependence(dataModel, attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
			new Dependence(dataModel, attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME);
			dataModel.checkCircularities();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failAttributeGroupToAttribute() throws BWException {
		try {
			new Dependence(dataModel, attGroupOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
			new Dependence(dataModel, attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME);
			dataModel.checkCircularities();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failAttributeToAttributeGroup() throws BWException {
		try {
			new Dependence(dataModel, attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
			new Dependence(dataModel, attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE + "." + GROUP_ONE_NAME);
			dataModel.checkCircularities();
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
			dataModel.checkCircularities();
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
			dataModel.checkCircularities();
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
			dataModel.checkCircularities();
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
			dataModel.checkCircularities();
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
			dataModel.checkCircularities();
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
			dataModel.checkCircularities();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failEntityToAttributeInverse() throws BWException {
		try {
			new Dependence(dataModel, entOne,
					ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
			new Dependence(dataModel, entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_ONE);
			dataModel.checkCircularities();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failEntityDependsOnItsAttributeDirect() throws BWException {
		try {
			new Dependence(dataModel, entOne, ENT_ONE_NAME + "." + ATT_ONE_NAME);
			dataModel.checkCircularities();
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
			dataModel.checkCircularities();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

}
