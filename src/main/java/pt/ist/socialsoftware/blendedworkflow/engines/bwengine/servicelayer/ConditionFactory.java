package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class ConditionFactory {
	
	public static boolean isConditionValid(String rawCondition) {
		 //TODO parse the raw condition looking for errors
		return true;
	}
	
	public static Condition createCondition(String condInString, String dataModelURI, String dataModelInstanceID) throws BlendedWorkflowException {
		return new ConditionParser(condInString, dataModelURI, dataModelInstanceID).parseCondition();
	}
	
}
