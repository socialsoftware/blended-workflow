package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class ForAllCondition extends ForAllCondition_Base {
    
    public  ForAllCondition() {
        super();
    }
    
	@Override
	public TripleStateBool evaluate(GoalWorkItem goalWorkItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void assignAttributeInstances(GoalWorkItem goalWorkItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void assignAttributeInstances(TaskWorkItem taskWorkItem,
			String conditionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Entity> getEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Attribute> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrTrueCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrFalseCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrSkippedCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrUndefinedConditionNEW() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrSkippedConditionNEW() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrTrueConditionNEW() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrFalseConditionNEW() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
