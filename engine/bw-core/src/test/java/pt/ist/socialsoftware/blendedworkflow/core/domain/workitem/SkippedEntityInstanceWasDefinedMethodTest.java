package pt.ist.socialsoftware.blendedworkflow.core.domain.workitem;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.AttributeInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.WorkItemDto;

public class SkippedEntityInstanceWasDefinedMethodTest extends TeardownRollbackTest {
	
	EntityInstance entityInstance;
	AttributeInstanceDto attributeInstanceDto;
	
	@Override
	public void populate4Test() throws BWException {
		Specification spec = new Specification("SpecId", "My spec");
		WorkflowInstance workflowInstance = new WorkflowInstance(spec, "name");
		Entity entity = new Entity(spec.getDataModel(), "EntityName", false);
		entityInstance = new EntityInstance(workflowInstance, entity, ProductInstanceState.SKIPPED);
		
		DataModel dataModel = new DataModel();
		Attribute attribute = new Attribute(dataModel, entity, "att1", AttributeType.STRING, false);
		AttributeInstance attributeInstance = new AttributeInstance(entityInstance, attribute, "valueABC",  ProductInstanceState.DEFINED);
		attributeInstanceDto = new AttributeInstanceDto(attributeInstance);
	}

	@Test
	public void success() {
		WorkItemDto workItemDto = new WorkItemDto();
			
		assertTrue(workItemDto.skippedEntityInstanceWasDefined(entityInstance.getState(), attributeInstanceDto.getState()));
	}
}