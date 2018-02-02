package pt.ist.socialsoftware.blendedworkflow.core.domain.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class GetEntityContextMethodTest extends TeardownRollbackTest {
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
	private static final String DESCRIPTION = "description";
	private static final String ACTIVITY_ONE = "activityOne";

	Specification spec;
	ActivityModel activityModel;
	Entity entOne;
	Entity entTwo;
	Entity entThree;
	Attribute attOne;
	Attribute attTwo;
	Attribute attThree;
	Attribute attFour;
	Activity activity;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec", "author", DESCRIPTION, "version", "UID");
		DataModel dataModel = this.spec.getDataModel();
		this.activityModel = this.spec.getActivityModel();

		this.entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		this.entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		this.entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		this.attOne = new Attribute(dataModel, this.entOne, ATT_ONE_NAME, AttributeType.NUMBER, true, false, false);
		this.attTwo = new Attribute(dataModel, this.entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

		this.attThree = new Attribute(dataModel, this.entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true, false, false);

		this.attFour = new Attribute(dataModel, this.entThree, ATT_FOUR_NAME, AttributeType.NUMBER, true, false, false);

		new RelationBW(dataModel, "relOneTwo", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY, false, this.entTwo,
				ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

		new RelationBW(dataModel, "relOneThree", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false, this.entThree,
				ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

		new RelationBW(dataModel, "relTwoThree", this.entThree, ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false,
				this.entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

		this.activity = new Activity(this.activityModel, ACTIVITY_ONE, DESCRIPTION);

	}

	@Test
	public void emptyContext() {
		this.activity.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entOne));

		Set<Entity> entityContext = this.activity.getEntityContext();

		assertTrue(entityContext.isEmpty());
	}

	@Test
	public void singleEntityContext() {
		this.activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME));
		this.activity.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));

		Set<Entity> entityContext = this.activity.getEntityContext();

		assertEquals(1, entityContext.size());
		assertEquals(this.entOne, entityContext.toArray(new Entity[entityContext.size()])[0]);
	}

	@Test
	public void longPreConditionPath() {
		this.activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		this.activity.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		this.activity.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));

		Set<Entity> entityContext = this.activity.getEntityContext();

		assertEquals(1, entityContext.size());
		assertEquals(this.entTwo, entityContext.toArray(new Entity[entityContext.size()])[0]);
	}

	@Test
	public void longPreConditionPathAndEntity() {
		this.activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME));
		this.activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		this.activity.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));

		Set<Entity> entityContext = this.activity.getEntityContext();

		assertEquals(1, entityContext.size());
		assertEquals(this.entOne, entityContext.toArray(new Entity[entityContext.size()])[0]);
	}

	@Test
	public void severalEntitiesInContext() {
		this.activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME));
		this.activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		this.activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_THREE_NAME));
		this.activity.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));
		this.activity.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attFour));

		Set<Entity> entityContext = this.activity.getEntityContext();

		assertEquals(2, entityContext.size());
		assertTrue(entityContext.contains(this.entOne));
		assertTrue(entityContext.contains(this.entThree));
	}

}
