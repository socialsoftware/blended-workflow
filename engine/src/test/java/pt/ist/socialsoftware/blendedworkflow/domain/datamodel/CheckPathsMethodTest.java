package pt.ist.socialsoftware.blendedworkflow.domain.datamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CheckPathsMethodTest extends TeardownRollbackTest {

	private static final String SPEC_ID = "SpecId";
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ENT_TWO_NAME = "EntTwo";
	private static final String ENT_THREE_NAME = "EntThree";
	private static final String GROUP_ONE_NAME = "GroupOne";
	private static final String ATT_ONE_NAME = "AttOne";
	private static final String ATT_TWO_NAME = "AttTwo";
	private static final String ATT_THREE_NAME = "AttThree";
	private static final String ROLENAME_ENT_ONE = "entOne";
	private static final String ROLENAME_ENT_TWO = "entTwo";
	private static final String ROLENAME_ENT_THREE = "entThree";

	Specification spec;
	DataModel dataModel;
	Entity entOne;
	Entity entTwo;
	Entity entThree;
	Attribute attThree;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification(SPEC_ID, "name", "author", "description", "version", "UID");
		dataModel = spec.getDataModel();

		entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		Attribute attOne = new Attribute(dataModel, entOne, ATT_ONE_NAME, AttributeType.NUMBER, true, false,
				false);
		new Attribute(dataModel, entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

		attThree = new Attribute(dataModel, entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true, false, false);

		new RelationBW(dataModel, "relOne", entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false, entThree,
				ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

		new RelationBW(dataModel, "relThree", entThree, ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false, entTwo,
				ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

	}

	@Test
	public void successDependence() throws BWException {
		new Dependence(dataModel, entOne,
				ENT_ONE_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);

		dataModel.checkPaths();
	}

	@Test
	public void failPrefixMissingDependence() throws BWException {
		try {
			new Dependence(dataModel, entTwo,
					ENT_ONE_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
			dataModel.checkPaths();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_PATH, bwe.getError());
		}
	}

	@Test
	public void successRule() throws BWException {
		new Rule(entOne, "myRule", new Comparison(new AttributeValueExpression(spec, ENT_ONE_NAME + "." + ATT_ONE_NAME),
				new AttributeValueExpression(spec, ENT_ONE_NAME + "." + ATT_TWO_NAME), ComparisonOperator.EQUAL));

		dataModel.checkPaths();
	}

	@Test
	public void failPrefixMissingRule() throws BWException {
		try {
			new Rule(entOne, "myRule",
					new Comparison(new AttributeValueExpression(spec, ENT_ONE_NAME + "." + ATT_ONE_NAME),
							new AttributeValueExpression(spec, ENT_TWO_NAME + "." + ATT_THREE_NAME),
							ComparisonOperator.EQUAL));
			dataModel.checkPaths();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_PATH, bwe.getError());
		}
	}

}
