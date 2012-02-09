package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class NotCondition extends NotCondition_Base {

	public NotCondition(Condition condition) {
		setCondition(condition);
	}

//	private Condition condition;
//
//	public NotCondition(Condition cond) {
//		this.condition = cond;	
//	}

//	@Override
//	public ArrayList<DataInfo> getData() {
//		return condition.getData();
//	}
//
//	@Override
//	public TripleStateBool evaluate() {
//		return this.condition.evaluate().NOT();
//	}
//
//	@Override
//	public ArrayList<DataInfo> getKeyData() {
//		return condition.getKeyData();
//	}

}
