package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class AbstractCondition extends AbstractCondition_Base {
    
//	public TripleStateBool evaluate(DataObservable otherData) {
//		return TripleStateBool.FALSE;
//	}
//	
//	public TripleStateBool evaluate() {
//		return TripleStateBool.FALSE;
//	}
	
	public Condition and(Condition one, Condition other) {
		return new AndCondition(one, other);
	}
	
	public Condition or(Condition one, Condition other) {
		return new OrCondition(one, other);
	}
	
	public Condition not(Condition condition) {
		return new NotCondition(condition);
	}

}
