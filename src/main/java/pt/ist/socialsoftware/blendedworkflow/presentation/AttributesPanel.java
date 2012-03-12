package pt.ist.socialsoftware.blendedworkflow.presentation;

import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AttributesPanel extends VerticalLayout {

//	private ArrayList<AttributeRow> attributes = new ArrayList<AttributeRow>();
//	private NewEntityForm parent = null;
//	
//	public AttributesPanel(NewEntityForm parent) {
//		this.parent = parent;
//		this.setSpacing(true);
//		addRow();
//	}
//	
//	public void addRow() {
//		AttributeRow newAttribute = new AttributeRow(this);
//		this.attributes.add(newAttribute);
//		this.addComponent(newAttribute);
//	}
//	
//	public void removeRow(AttributeRow row) {
//		int i = this.attributes.indexOf(row);
//		AttributeRow deletedRow = this.attributes.remove(i);
//		this.removeComponent(deletedRow);
//	}
//	
//	public ArrayList<AddDataMessage> getAttributes() throws FormNotFilledException {
//		ArrayList<AddDataMessage> atts = new ArrayList<AddDataMessage>();
//		
//		String entityName = parent.getEntityName() ;
//		for (AttributeRow att : this.attributes) {
//			AddDataMessage attData = new AddDataMessage();
//			attData.setDataName(att.getAttributeName());
//			attData.setDataType(att.getAttributeType());
//			attData.setKey(att.getIsKey());
//			atts.add(attData);
//		}
//		
//		return atts;
//	}
}
