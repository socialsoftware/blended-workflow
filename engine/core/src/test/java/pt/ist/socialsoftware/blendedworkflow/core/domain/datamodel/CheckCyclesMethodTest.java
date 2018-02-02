package pt.ist.socialsoftware.blendedworkflow.core.domain.datamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification(SPEC_ID, "name", "author", "description", "version", "UID");
		this.dataModel = this.spec.getDataModel();

		this.entOne = new Entity(this.dataModel, ENT_ONE_NAME, false);
		this.entTwo = new Entity(this.dataModel, ENT_TWO_NAME, false);
		this.entThree = new Entity(this.dataModel, ENT_THREE_NAME, false);

		this.attOne = new Attribute(this.dataModel, this.entOne, ATT_ONE_NAME, AttributeType.NUMBER, true, false,
				false);
		this.attTwo = new Attribute(this.dataModel, this.entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false,
				false);

		this.attThree = new Attribute(this.dataModel, this.entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true, false,
				false);

		this.attFour = new Attribute(this.dataModel, this.entThree, ATT_FOUR_NAME, AttributeType.NUMBER, true, false,
				false);

		new RelationBW(this.dataModel, "relOneTwo", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY, false,
				this.entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

		new RelationBW(this.dataModel, "relOneThree", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false,
				this.entThree, ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

		new RelationBW(this.dataModel, "relTwoThree", this.entThree, ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false,
				this.entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

	}

	@Test
	public void successDependence() throws BWException {
		new Dependence(this.dataModel, this.entOne,
				ENT_ONE_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);

		this.dataModel.checkDependences();
	}

	@Test
	public void failAttributeToAttributeOneStep() throws BWException {
		try {
			new Dependence(this.dataModel, this.attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
			new Dependence(this.dataModel, this.attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME);
			this.dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failAttributeToAttributeTwoStep() throws BWException {
		try {
			new Dependence(this.dataModel, this.attOne,
					ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
			new Dependence(this.dataModel, this.attFour,
					ENT_THREE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME);
			this.dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failAttributeToAttributeTransitive() throws BWException {
		try {
			new Dependence(this.dataModel, this.attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
			new Dependence(this.dataModel, this.attThree,
					ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
			new Dependence(this.dataModel, this.attFour, ENT_THREE_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME);
			this.dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failEntityToEntity() throws BWException {
		try {
			new Dependence(this.dataModel, this.entOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO);
			new Dependence(this.dataModel, this.entTwo, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE);
			this.dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failEntityToEntityTwoStep() throws BWException {
		try {
			new Dependence(this.dataModel, this.entOne,
					ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE);
			new Dependence(this.dataModel, this.entThree,
					ENT_THREE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_ONE);
			this.dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failEntityToEntityTransitive() throws BWException {
		try {
			new Dependence(this.dataModel, this.entOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO);
			new Dependence(this.dataModel, this.entTwo, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE);
			new Dependence(this.dataModel, this.entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_ONE);
			this.dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failEntityToEntityInverse() throws BWException {
		try {
			new Dependence(this.dataModel, this.entOne,
					ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE);
			new Dependence(this.dataModel, this.entTwo, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE);
			this.dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void sucessAttributeToEntityInverse() throws BWException {
		new Dependence(this.dataModel, this.attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE);
		new Dependence(this.dataModel, this.entTwo, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE);
		this.dataModel.checkDependences();
	}

	@Test
	public void failEntityToAttributeInverse() throws BWException {
		try {
			new Dependence(this.dataModel, this.entOne,
					ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
			new Dependence(this.dataModel, this.entThree,
					ENT_THREE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_ONE);
			this.dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void successAttributeToAttributeInverse() throws BWException {
		new Dependence(this.dataModel, this.attOne,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
		new Dependence(this.dataModel, this.entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_ONE);
		this.dataModel.checkDependences();
	}

	@Test
	public void failEntityDependsOnItsAttributeDirect() throws BWException {
		try {
			new Dependence(this.dataModel, this.entOne, ENT_ONE_NAME + "." + ATT_ONE_NAME);
			this.dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failEntityDependsOnItsAttributeIndirect() throws BWException {
		try {
			new Dependence(this.dataModel, this.entOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO);
			new Dependence(this.dataModel, this.entTwo, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE);
			new Dependence(this.dataModel, this.entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME);
			this.dataModel.checkDependences();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void successAttributeDependenceCanCreateAllEntitiesOne() throws BWException {
		new Dependence(this.dataModel, this.attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
		new Dependence(this.dataModel, this.attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
		new Dependence(this.dataModel, this.entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_TWO_NAME);
		this.dataModel.checkDependences();
	}

	@Test
	public void successAttributeDependenceCanCreateAllEntitiesTwo() throws BWException {
		new Dependence(this.dataModel, this.attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
		new Dependence(this.dataModel, this.attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
		new Dependence(this.dataModel, this.entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_ONE);
		this.dataModel.checkDependences();
	}

	@Test
	public void sucessAttributeDependenceCanCreateAllEntitiesThree() throws BWException {
		new Dependence(this.dataModel, this.attOne, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
		new Dependence(this.dataModel, this.attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
		new Dependence(this.dataModel, this.entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_ONE);
		this.dataModel.checkDependences();
	}

	@Test
	public void successIntermediatePathDependenceOne() throws BWException {
		new Dependence(this.dataModel, this.attOne,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
		new Dependence(this.dataModel, this.entTwo, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE);
		this.dataModel.checkDependences();
	}

	@Test
	public void successIntermediatePathDependenceTwo() throws BWException {
		new Dependence(this.dataModel, this.attOne,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME);
		new Dependence(this.dataModel, this.entThree, ENT_THREE_NAME + "." + ROLENAME_ENT_TWO);
		this.dataModel.checkDependences();
	}

}
