package pt.ist.socialsoftware.blendedworkflow.domain.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

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
		spec = new Specification("SpecId", "My spec", "author", DESCRIPTION, "version", "UID");
		DataModel dataModel = spec.getDataModel();
		activityModel = spec.getActivityModel();

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

		activity = new Activity(activityModel, ACTIVITY_ONE, DESCRIPTION);

	}

	@Test
	public void emptyContext() {
		activity.addPostCondition(DefEntityCondition.getDefEntityCondition(entOne));

		Set<Entity> entityContext = activity.getEntityContext();

		assertTrue(entityContext.isEmpty());
	}

	@Test
	public void singleEntityContext() {
		activity.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME));
		activity.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attOne));

		Set<Entity> entityContext = activity.getEntityContext();

		assertEquals(1, entityContext.size());
		assertEquals(entOne, entityContext.toArray(new Entity[entityContext.size()])[0]);
	}

	@Test
	public void longPreConditionPath() {
		activity.addPreCondition(DefPathCondition.getDefPathCondition(spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		activity.addPostCondition(DefEntityCondition.getDefEntityCondition(entOne));
		activity.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attOne));

		Set<Entity> entityContext = activity.getEntityContext();

		assertEquals(1, entityContext.size());
		assertEquals(entTwo, entityContext.toArray(new Entity[entityContext.size()])[0]);
	}

	@Test
	public void longPreConditionPathAndEntity() {
		activity.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME));
		activity.addPreCondition(DefPathCondition.getDefPathCondition(spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		activity.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attOne));

		Set<Entity> entityContext = activity.getEntityContext();

		assertEquals(1, entityContext.size());
		assertEquals(entOne, entityContext.toArray(new Entity[entityContext.size()])[0]);
	}

	@Test
	public void severalEntitiesInContext() {
		activity.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME));
		activity.addPreCondition(DefPathCondition.getDefPathCondition(spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		activity.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENT_THREE_NAME));
		activity.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attOne));
		activity.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attFour));

		Set<Entity> entityContext = activity.getEntityContext();

		assertEquals(2, entityContext.size());
		assertTrue(entityContext.contains(entOne));
		assertTrue(entityContext.contains(entThree));
	}

}
