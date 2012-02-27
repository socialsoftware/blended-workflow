package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.StringUtils;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateBWInstanceService {

	private String xml;
	private BlendedWorkflow blendedWorkflow;
	private BWSpecification bwSpecification;

	public CreateBWInstanceService(String xml) {
		this.xml = xml;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		// Get BWSpecification and clone it
		inputDataConverter();
		BWInstance bwInstance = new BWInstance(this.bwSpecification);

		// Create DataModel Instances
//		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
//		createEntityAndAttributesInstances(dataModelInstance);
//		createRelationInstances(dataModelInstance);

		bwInstance.getGoalModelInstance().getEnabledWorkItems();
	}

	private void inputDataConverter() throws BlendedWorkflowException {
		this.blendedWorkflow = BlendedWorkflow.getInstance();
		Document doc = StringUtils.stringToDoc(this.xml);

		Element root = doc.getRootElement();
		Namespace bwNamespace = root.getNamespace();
		Element caseInfo = root.getChild("CaseInfo", bwNamespace);

		this.bwSpecification = blendedWorkflow.getBWSpecification(caseInfo.getChildText("specificationName", bwNamespace));
	}

//	private void createEntityAndAttributesInstances(DataModelInstance dataModelInstance) {
//		EntityInstance newEntityInstance = null;
//
//		for (Entity entity : dataModelInstance.getEntities()) {
//			newEntityInstance = new EntityInstance(dataModelInstance, entity);
//			for (Attribute attribute : entity.getAttributes()) {
//				new AttributeInstance(attribute, newEntityInstance);
//			}
//		}
//	}
//
//	private void createRelationInstances(DataModelInstance dataModelInstance) {
//		Entity relationEntityOne;
//		Entity relationEntityTwo;
//		EntityInstance relationEntityInstanceOne;
//		EntityInstance relationEntityInstanceTwo;
//
//		for (Relation relation : dataModelInstance.getRelations()) {
//			relationEntityOne = dataModelInstance.getEntity(relation.getEntityOne().getName()); // Relation EntityOne
//			relationEntityTwo = dataModelInstance.getEntity(relation.getEntityTwo().getName()); // Relation EntityTwo
//
//			relationEntityInstanceOne = relationEntityOne.getFirstEntityInstance(); // EntityInstanceOne
//			relationEntityInstanceTwo = relationEntityTwo.getFirstEntityInstance(); // EntityInstanceTwo
//
//			new RelationInstance(relation, relationEntityInstanceOne, relationEntityInstanceTwo, relationEntityInstanceOne.getNewRelationInstanceId());
//		}
//	}

}