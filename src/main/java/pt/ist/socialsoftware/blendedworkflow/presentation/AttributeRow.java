package pt.ist.socialsoftware.blendedworkflow.presentation;

import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class AttributeRow extends HorizontalLayout {

//	private AttributesPanel parent = null;
//	
//	private TextField attributeName = new TextField();
//	private NativeSelect attributeType = new NativeSelect();
//	private CheckBox isKey = new CheckBox("Key Attribute");
//	
//	private Button addButton = new Button("+");
//	private Button removeButton = new Button("-");
//	
//	public AttributeRow(AttributesPanel parent) {
//		this.parent = parent;
//		
//		//fill the attribute type
//		this.attributeType.addItem("Select...");
//		this.attributeType.addItem("String");
//		this.attributeType.addItem("Number");
//		this.attributeType.addItem("Boolean");
//		
//		this.attributeType.setValue("Select...");
//		
//		// add to the panel
//		this.addComponent(attributeName);
//		this.addComponent(attributeType);
//		this.addComponent(isKey);
//		
//		this.addComponent(addButton);
//		this.addComponent(removeButton);
//		
//		this.addButton.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				addRow();
//			}
//		});
//		
//		this.removeButton.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				removeRow();
//			}
//		});
//	}
//	
//	private void addRow() {
//		parent.addRow();
//	}
//	
//	private void removeRow() {
//		parent.removeRow(this);
//	}
//	
//	public String getAttributeName() {
//		return (String) this.attributeName.getValue();
//	}
//	
//	public String getAttributeType() throws BlendedWorkflowException {
//		String value = (String) this.attributeType.getValue(); 
//		if(value.equals("Select...")) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.BWPRESENTATION, "Form not filled");
//		}
//		
//		return value;
//	}
//	
//	public boolean getIsKey() {
//		return (Boolean) this.isKey.getValue();
//	}
}
