package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class ExistsEntityCondition extends ExistsEntityCondition_Base {
    
    public  ExistsEntityCondition() {
        super();
    }
    
//  //EXISTS.CLASS
//	private DataModelURI dataModelURI = null;
//	private DataNameURI data = null;
//	private DataModelInstanceID dataModelInstanceID;
//
//	/**
//	 * Creates the "exists" condition.
//	 * @param data can be an Attribute, an Entity or an instance of one of these.
//	 * @param caseID
//	 * @throws TypeMismatchException 
//	 */
//	public ExistsCondition(DataModelURI dataModelURI, DataNameURI data, DataModelInstanceID instanceID) {
//		this.dataModelURI = dataModelURI;
//		this.data = data;
//		this.dataModelInstanceID = instanceID;
//	}
//	
//	@Override
//	public TripleStateBool evaluate() {
//		
//		if(this.data.isDataEntity()) {
//			EntityInfo entityInfo = ConditionsInterpreter.get().getEntityInfo(data, dataModelURI, dataModelInstanceID);
//			if(entityInfo.isDefined()) {
//				return TripleStateBool.TRUE;
//			} else if(entityInfo.isSkipped()) {
//				return TripleStateBool.SKIPPED;
//			}
//			return TripleStateBool.FALSE;
//		} else {
//			ArrayList<DataInfo> dataInfo = ConditionsInterpreter.get().getDataInfo(this.data, this.dataModelURI, this.dataModelInstanceID);
//
//			for (DataInfo dataAtt : dataInfo) { //FIXME support various different instances
//				if(dataAtt.isSkipped()) {
//					return TripleStateBool.SKIPPED;  
//				} else if(!dataAtt.isDefined()) {
//					return TripleStateBool.FALSE;
//				}
//			}
//			return TripleStateBool.TRUE;
//		}
//	}
//	
//	@Override
//	public ArrayList<DataInfo> getKeyData() {
//		ArrayList<DataInfo> dataL = this.getData();
//		if(this.data.isDataEntity()) {
//			dataL = removeDataNotKey(dataL);
//		}
//
//		return dataL;
//	}
//	
//	@Override
//	public ArrayList<DataInfo> getData() {
//		ArrayList<DataInfo> dataL = new ArrayList<DataInfo>();
//		ArrayList<DataInfo> retreivedData = ConditionsInterpreter.get().getDataInfo(this.data, this.dataModelURI, this.dataModelInstanceID);
//		dataL.addAll(retreivedData);
//		
//		return dataL;
//	}
//	
//	protected ArrayList<DataInfo> removeDataNotKey(ArrayList<DataInfo> data) {
//		ArrayList<DataInfo> returnList = new ArrayList<DataInfo>();
//		for (DataInfo dataInfo : data) {
//			if(dataInfo.isKey()) {
//				returnList.add(dataInfo);
//			}
//		}
//		return returnList;
//	}
}
