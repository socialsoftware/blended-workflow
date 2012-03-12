package pt.ist.socialsoftware.blendedworkflow.presentation;

//import com.vaadin.ui.Alignment;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.CheckBox;
//import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.NativeSelect;
//import com.vaadin.ui.TextField;
//import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
//import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class NewAttributeForm extends Window {

//	private DataModelTree parent;
//	private VerticalLayout content;
//	private String entityName; 
//	
//	private TextField attributeName = new TextField("AttributeName");
//	private NativeSelect type = new NativeSelect();
//	private CheckBox isKey = new CheckBox("Key Attribute");
//	
//	public NewAttributeForm(String entityName, DataModelTree parent) {
//		this.parent = parent;
//		this.entityName = entityName;
//		
//		setCaption("Add attribute to " + entityName);
//		
//		content.addComponent(attributeName);
//		
//		this.type.addItem("Select...");
//		this.type.addItem("STRING");
//		this.type.addItem("NUMBER");
//		this.type.addItem("BOOLEAN");
//		this.type.setValue("Select...");
//		content.addComponent(type);
//		
//		content.addComponent(this.isKey);
//		
//		HorizontalLayout submitPanel = new HorizontalLayout();
//		Button cancelBtn = new Button("Cancel");
//		cancelBtn.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				NewAttributeForm.this.close();
//			}
//		});
//		submitPanel.addComponent(cancelBtn);
//		
//		Button ok = new Button("Submit");
//		ok.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//					try {
//						submitForm();
//					} catch (FormNotFilledException e) {
//						getApplication().getMainWindow().showNotification("Please fill all the fields");
//					}
//					getParentWindow().refreshTree();
//					NewAttributeForm.this.close();
//			}
//		});
//		submitPanel.addComponent(ok);
//		
//		content.addComponent(submitPanel);
//		content.setComponentAlignment(submitPanel, Alignment.BOTTOM_RIGHT);
//		
//		content.setMargin(true);
//		this.center();
//		this.setContent(this.content);
//	}
//	
//	protected DataModelTree getParentWindow() {
//		return this.parent;
//	}
//	
//	protected void submitForm() throws FormNotFilledException {
//		String elementURI = this.getAttributeName();
//		String goalCaseInstanceID = parent.getGoalCaseInstanceID();
//		String dataModelURI = WorklistManager.get().getDataModelURI(goalCaseInstanceID);
//		String dataModelInstanceID = WorklistManager.get().getDataModelInstanceID(goalCaseInstanceID);
//		
//		ArrayList<AddDataMessage> att = new ArrayList<AddDataMessage>();
//		AddDataMessage attribute = new AddDataMessage();
//		attribute.setDataName(elementURI);
//		attribute.setDataType(getAttributeType());
//		attribute.setKey(getIsKey());
//		
//		att.add(attribute);
//		
//		WorklistManager.get().addData(dataModelURI, dataModelInstanceID, elementURI, att, null);
//	}
//	
//	public String getAttributeName() {
//		return this.entityName + "." + this.attributeName.getValue().toString();
//	}
//	
//	public String getAttributeType() throws FormNotFilledException {
//		String value = this.type.getValue().toString();
//		if(value.equals("Select...")) {
//			throw new FormNotFilledException();
//		}
//		return value;
//	}
//	
//	public boolean getIsKey() {
//		return (Boolean) this.isKey.getValue();
//	}
}

