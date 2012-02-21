package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class ConditionFactory {

	public static boolean isConditionValid(String rawCondition) {
		//TODO parse the raw condition looking for errors
		return true;
	}

	public static Condition createCondition(DataModel dataModel, String condInString) throws BlendedWorkflowException {
		return new ConditionParser(dataModel, condInString).parseCondition();
	}

}