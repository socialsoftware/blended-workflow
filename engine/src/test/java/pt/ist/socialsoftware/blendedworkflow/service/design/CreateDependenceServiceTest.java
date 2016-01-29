package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DependenceDTO;

public class CreateDependenceServiceTest extends TeardownRollbackTest {
	private static final String SPEC_ID = "ID1";
	private static final String SPEC_NAME = "Doctor Appointment";
	private static final String ENTITY_NAME = "Patient";
	private static final String ATTRIBUTE_GROUP_NAME = "Medication";
	private static final String ATTRIBUTE_NAME = "Portion";
	private static final String DEPENDENCE_ONE = ENTITY_NAME + "." + "dependenceOne";
	private static final String DEPENDENCE_TWO = ENTITY_NAME + "." + "dependenceTwo";

	DesignInterface designInterface;
	DataModel dataModel;
	Entity entity;
	AttributeGroup group;
	AttributeBasic att;

	@Override
	public void populate4Test() throws BWException {
		designInterface = DesignInterface.getInstance();

		new Specification(SPEC_ID, SPEC_NAME, "author", "description", "version", "UID");
		dataModel = getBlendedWorkflow().getSpecById(SPEC_ID).get().getDataModel();

		entity = new Entity(dataModel, ENTITY_NAME, false);
		group = new AttributeGroup(dataModel, entity, ATTRIBUTE_GROUP_NAME, false);
		att = new AttributeBasic(dataModel, entity, group, ATTRIBUTE_NAME, AttributeType.NUMBER, true, false, false);
		entity.createDependence(DEPENDENCE_ONE);
	}

	@Test
	public void successCreateEntityDependence() {
		designInterface.createDependence(new DependenceDTO(entity.getExternalId(), DEPENDENCE_TWO));

		assertEquals(2, dataModel.getEntity(ENTITY_NAME).get().getDependenceSet().size());
		assertEquals(DEPENDENCE_ONE + "," + DEPENDENCE_TWO, dataModel.getDependenceSet().stream()
				.map(dep -> dep.getPath().getValue()).sorted().collect(Collectors.joining(",")));
	}

	@Test
	public void successCreateAttributeGroupDependence() {
		designInterface.createDependence(new DependenceDTO(group.getExternalId(), DEPENDENCE_TWO));

		assertEquals(1, dataModel.getEntity(ENTITY_NAME).get().getDependenceSet().size());
		assertEquals(1, dataModel.getEntity(ENTITY_NAME).get().getAttributeGroup(ATTRIBUTE_GROUP_NAME).get()
				.getDependenceSet().size());
		assertEquals(DEPENDENCE_ONE + "," + DEPENDENCE_TWO, dataModel.getDependenceSet().stream()
				.map(dep -> dep.getPath().getValue()).sorted().collect(Collectors.joining(",")));
	}

	@Test
	public void successCreateAttributeDependence() {
		designInterface.createDependence(new DependenceDTO(att.getExternalId(), DEPENDENCE_TWO));

		assertEquals(1, dataModel.getEntity(ENTITY_NAME).get().getDependenceSet().size());
		assertEquals(1,
				dataModel.getEntity(ENTITY_NAME).get().getAttribute(ATTRIBUTE_NAME).get().getDependenceSet().size());
		assertEquals(DEPENDENCE_ONE + "," + DEPENDENCE_TWO, dataModel.getDependenceSet().stream()
				.map(dep -> dep.getPath().getValue()).sorted().collect(Collectors.joining(",")));
	}

}
