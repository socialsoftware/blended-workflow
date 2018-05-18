package pt.ist.socialsoftware.blendedworkflow.core.domain.datamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification(SPEC_ID, "name");
		this.dataModel = this.spec.getDataModel();

		this.entOne = new Entity(this.dataModel, ENT_ONE_NAME, false);
		this.entTwo = new Entity(this.dataModel, ENT_TWO_NAME, false);
		this.entThree = new Entity(this.dataModel, ENT_THREE_NAME, false);

		new Attribute(this.dataModel, this.entOne, ATT_ONE_NAME, AttributeType.NUMBER, true);
		new Attribute(this.dataModel, this.entOne, ATT_TWO_NAME, AttributeType.NUMBER, false);

		this.attThree = new Attribute(this.dataModel, this.entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true);

		new RelationBW(this.dataModel, "relOne", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false, this.entThree,
				ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

		new RelationBW(this.dataModel, "relThree", this.entThree, ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false,
				this.entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

	}

	@Test
	public void successDependence() throws BWException {
		new Dependence(this.dataModel, this.entOne,
				ENT_ONE_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);

		this.dataModel.checkPaths();
	}

	@Test
	public void failPrefixMissingDependence() throws BWException {
		try {
			new Dependence(this.dataModel, this.entTwo,
					ENT_ONE_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
			this.dataModel.checkPaths();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_PATH, bwe.getError());
		}
	}

	@Test
	public void successRule() throws BWException {
		new Rule(this.entOne, "myRule",
				new Comparison(new AttributeValueExpression(this.spec, ENT_ONE_NAME + "." + ATT_ONE_NAME),
						new AttributeValueExpression(this.spec, ENT_ONE_NAME + "." + ATT_TWO_NAME),
						ComparisonOperator.EQUAL));

		this.dataModel.checkPaths();
	}

	@Test
	public void failPrefixMissingRule() throws BWException {
		try {
			new Rule(this.entOne, "myRule",
					new Comparison(new AttributeValueExpression(this.spec, ENT_ONE_NAME + "." + ATT_ONE_NAME),
							new AttributeValueExpression(this.spec, ENT_TWO_NAME + "." + ATT_THREE_NAME),
							ComparisonOperator.EQUAL));
			this.dataModel.checkPaths();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_PATH, bwe.getError());
		}
	}

}
