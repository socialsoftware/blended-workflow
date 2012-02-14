package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class LoadBWSpecificationService {

	@Atomic
	public void execute() throws BlendedWorkflowException {
		// Blended Workflow
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();

		// BWSpecification
		BWSpecification bwSpecification = null;
		bwSpecification = new BWSpecification("Medical Appointment");
		blendedWorkflow.addBwSpecifications(bwSpecification);

		// Data Model
		DataModel dataModel = bwSpecification.getDataModel();
		Entity entityPatient = new Entity(dataModel, "Patient");
		Entity entityMedic = new Entity(dataModel, "Medic");
		new Attribute(dataModel, "Name", entityPatient, AttributeType.STRING, true);
		new Relation(dataModel, "has", entityPatient, entityMedic, Cardinality.MANY, Cardinality.ONE, true, true);

		// Goal Model
		GoalModel goalModel = bwSpecification.getGoalModel();
		String goalCureConditionString = "existsEntity(Nurse) or existsAttribute(Medic.Age.NUMBER.true)";
		Condition goalCureCondition = ConditionFactory.createCondition(dataModel, goalCureConditionString);
		Goal goalCure = new Goal(goalModel, "Cure", goalCureCondition); 

		String goalDiagnoseConditionString = "existsEntity(Medic)";
		Condition goalDiagnoseCondition = ConditionFactory.createCondition(dataModel, goalDiagnoseConditionString);
		new Goal(goalModel, goalCure, "Diagnose", goalDiagnoseCondition);

		String goalExamineConditionString = "existsAttribute(Medic.Age)";
		Condition goalExamineCondition = ConditionFactory.createCondition(dataModel, goalExamineConditionString);
		new Goal(goalModel, goalCure, "Examine", goalExamineCondition);

		// Print
		System.out.println("Created BWSpecification with the name \"" + bwSpecification.getName() + "\"");
		System.out.println("DataModel:");
		System.out.println("Entities");
		for (Entity entity : dataModel.getEntities()) {
			System.out.println("Entity \"" + entity.getName() + "\" created.");
			for (Attribute attribute : entity.getAttributes()) {
				System.out.println("Attribute \"" + attribute.getName() + "\" created.");
			}
		}
		for (Relation relation : dataModel.getRelations()) {
			System.out.println("Relation \"" + relation.getName() + "\" created.");
		}
		for (Goal goal : goalModel.getGoals()) {
			System.out.println("Goal \"" + goal.getName() + "\" has " + goal.getSubGoals().size() + " subgoals.");
		System.out.println("Condition " + goal.getCondition().getClass());
		}
	}

}