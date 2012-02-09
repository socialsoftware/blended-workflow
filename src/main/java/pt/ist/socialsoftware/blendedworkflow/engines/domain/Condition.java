package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public abstract class Condition extends Condition_Base {

//	public Condition() {
//		super();
//	}

//	public abstract TripleStateBool evaluate();
//	public abstract ArrayList<DataInfo> getKeyData();
//	public abstract ArrayList<DataInfo> getData();
	public abstract Condition and(Condition one, Condition other);
	public abstract Condition or(Condition one, Condition other);
	public abstract Condition not(Condition condition);

}
