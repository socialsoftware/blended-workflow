package pt.ist.socialsoftware.blendedworkflow.domain.path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Path;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CheckPathMethodTest extends TeardownRollbackTest {

	private static final String SPEC_ID = "SpecId";
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ENT_TWO_NAME = "EntTwo";
	private static final String ENT_THREE_NAME = "EntThree";
	private static final String ATT_ONE_NAME = "AttOne";
	private static final String ATT_TWO_NAME = "AttTwo";
	private static final String ATT_THREE_NAME = "AttThree";
	private static final String ROLENAME_ENT_ONE = "entOne";
	private static final String ROLENAME_ENT_TWO = "entTwo";
	private static final String ROLENAME_ENT_THREE = "entThree";

	DataModel dataModel;
	Entity entOne;
	Entity entTwo;
	Entity entThree;
	Attribute attThree;

	@Override
	public void populate4Test() throws BWException {
		Specification spec = new Specification(SPEC_ID, "name", "author", "description", "version", "UID");
		dataModel = spec.getDataModel();

		entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		new Attribute(dataModel, entOne, ATT_ONE_NAME, AttributeType.BOOLEAN, true, false, false);
		new Attribute(dataModel, entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

		attThree = new Attribute(dataModel, entTwo, ATT_THREE_NAME, AttributeType.STRING, true, false, false);

		new RelationBW(dataModel, "relOne", entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false, entThree,
				ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

		new RelationBW(dataModel, "relThree", entThree, ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false, entTwo,
				ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

	}

	@Test
	public void successEntitytoExternalAttribute() throws BWException {
		Path path = new Path(dataModel,
				ENT_ONE_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);

		path.check();
	}

	@Test
	public void failPrefixMissing() throws BWException {
		try {
			Path path = new Path(dataModel, ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);
			path.check();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_PATH, bwe.getError());
		}
	}

	@Test
	public void successEntitytoExternalEntity() throws BWException {
		Path path = new Path(dataModel, ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_ONE);

		path.check();
	}

	@Test
	public void missingAttribute() throws BWException {
		Path path = new Path(dataModel,
				ENT_ONE_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO + "." + "notHere");

		try {
			path.check();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_PATH, bwe.getError());
			assertEquals(ENT_ONE_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO + "." + "notHere" + ":"
					+ "[notHere]", bwe.getMessage());
		}
	}

}
