package pt.ist.socialsoftware.blendedworkflow.core.service.design;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.DependenceDTO;

public class CreateDependenceServiceTest extends TeardownRollbackTest {
	private static final String SPEC_ID = "ID1";
	private static final String SPEC_NAME = "Doctor Appointment";
	private static final String ENTITY_NAME_ONE = "Entity name one";
	private static final String ENTITY_NAME_TWO = "Entity name two";
	private static final String ROLENAME_ONE = "rolenameOne";
	private static final String ROLENAME_TWO = "rolenameTwo";
	private static final String ATTRIBUTE_GROUP_NAME = "Medication";
	private static final String ATTRIBUTE_NAME_ONE = "Portion";
	private static final String DEPENDENCE_ONE = ENTITY_NAME_ONE + "." + ROLENAME_TWO;
	private static final String DEPENDENCE_TWO = ENTITY_NAME_TWO + "." + ROLENAME_ONE;

	DesignInterface designInterface;
	DataModel dataModel;
	Entity entityOne;
	Entity entityTwo;
	Attribute att;

	@Override
	public void populate4Test() throws BWException {
		this.designInterface = DesignInterface.getInstance();

		new Specification(SPEC_ID, SPEC_NAME, "author", "description", "version", "UID");
		this.dataModel = getBlendedWorkflow().getSpecById(SPEC_ID).get().getDataModel();

		this.entityOne = new Entity(this.dataModel, ENTITY_NAME_ONE, false);
		this.att = new Attribute(this.dataModel, this.entityOne, ATTRIBUTE_NAME_ONE, AttributeType.NUMBER, true, false,
				false);

		this.entityTwo = new Entity(this.dataModel, ENTITY_NAME_TWO, false);

		new RelationBW(this.dataModel, "name", this.entityOne, ROLENAME_ONE, Cardinality.ONE, false, this.entityTwo,
				ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		this.entityTwo.createDependence(DEPENDENCE_TWO);
	}

	@Test
	public void successCreateEntityDependence() {
		this.designInterface.createDependence(new DependenceDTO(SPEC_ID, this.entityOne.getFullPath(), DEPENDENCE_ONE));

		assertEquals(1, this.dataModel.getEntity(ENTITY_NAME_ONE).get().getDependenceSet().size());
		assertEquals(DEPENDENCE_ONE + "," + DEPENDENCE_TWO, this.dataModel.getDependenceSet().stream()
				.map(dep -> dep.getPath().getValue()).sorted().collect(Collectors.joining(",")));
	}

	@Test
	public void successCreateAttributeDependence() {
		this.designInterface.createDependence(new DependenceDTO(SPEC_ID, this.att.getFullPath(), DEPENDENCE_ONE));

		assertEquals(0, this.dataModel.getEntity(ENTITY_NAME_ONE).get().getDependenceSet().size());
		assertEquals(1, this.dataModel.getEntity(ENTITY_NAME_ONE).get().getAttribute(ATTRIBUTE_NAME_ONE).get()
				.getDependenceSet().size());
		assertEquals(DEPENDENCE_ONE + "," + DEPENDENCE_TWO, this.dataModel.getDependenceSet().stream()
				.map(dep -> dep.getPath().getValue()).sorted().collect(Collectors.joining(",")));
	}

}
