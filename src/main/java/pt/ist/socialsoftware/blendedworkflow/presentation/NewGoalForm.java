package pt.ist.socialsoftware.blendedworkflow.presentation;

//import com.vaadin.ui.Alignment;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.Button.ClickEvent;
//import com.vaadin.ui.CheckBox;
//import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.TextArea;
//import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
//import com.vaadin.ui.Window;
//import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class NewGoalForm extends VerticalLayout {

//	private String instanceSpecification;
//	private TextField goalName = new TextField("Goal Name");
//	private CheckBox isMandatory = new CheckBox("Mandatory goal");
//	private TextArea goalDefinition = new TextArea();
//	
//	private BlendedworkflowserviceApplication parentWindow = null;
//
//	public NewGoalForm(BlendedworkflowserviceApplication parent, String goalCaseInstance) {
//		
//		this.parentWindow = parent;
//		this.instanceSpecification = goalCaseInstance;
//		
//		setMargin(true);
//		setSpacing(true);
//		setWidth(null);
//		
//		addComponent(goalName);
//		addComponent(isMandatory);
//		
//		VerticalLayout goalDefPanel = new VerticalLayout();
//		Button addExistsCondition = new Button("Exists Condition");
//		addExistsCondition.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				addTextToGoalDef("exists(");
//			}
//		});
//		Button addEqualToCondition = new Button("EqualTo Condition");
//		addEqualToCondition.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				addTextToGoalDef("equalTo(");
//			}
//		});
//		
//		Button addTrueCondition = new Button("TRUE");
//		addTrueCondition.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				addTextToGoalDef("true");	
//			}
//		});
//		
//		Button addAndCondition = new Button("AND");
//		addAndCondition.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				addTextToGoalDef(" and ");
//			}
//		});
//
//		Button addOrCondition = new Button("OR");
//		addOrCondition.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				addTextToGoalDef(" or ");
//			}
//		});
//		Button addNotCondition = new Button("NOT");
//		addNotCondition.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				addTextToGoalDef(".not()");
//			}
//		});
//		
//		Button addData = new Button("Specify data...");
//		addData.addListener(new Button.ClickListener() {
//
//			@Override
//			public void buttonClick(ClickEvent event) {
//				if(goalDefinition.getValue().toString().endsWith("equalTo(")) {
//					showDataModelTreeWindow(true);
//				} else if(goalDefinition.getValue().toString().endsWith("exists(")){
//					showDataModelTreeWindow(false);
//				} else {
//					getApplication().getMainWindow().showNotification("You must specify a condition before", Notification.TYPE_WARNING_MESSAGE);
//				}
//			}
//		});
//
//		HorizontalLayout buttonsPanel = new HorizontalLayout();
//		buttonsPanel.addComponent(addExistsCondition);
//		buttonsPanel.addComponent(addEqualToCondition);
//		buttonsPanel.addComponent(addAndCondition);
//		buttonsPanel.addComponent(addOrCondition);
//		buttonsPanel.addComponent(addNotCondition);
//		buttonsPanel.addComponent(addTrueCondition);
//		buttonsPanel.addComponent(addData);
//		//buttonsPanel.setMargin(true);
//		buttonsPanel.setSpacing(true);
//		
//		goalDefPanel.addComponent(buttonsPanel);
//		goalDefinition.setWidth("500px");
//		goalDefPanel.addComponent(goalDefinition);
//		
//		goalDefPanel.setComponentAlignment(goalDefinition, Alignment.TOP_CENTER);
//		
//		goalDefPanel.setCaption("Goal Definition");
//		addComponent(goalDefPanel);
//		
//		HorizontalLayout submitPanel = new HorizontalLayout();
//		Button ok = new Button("OK");
//		ok.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				if(goalName.getValue().equals("") ||
//						goalDefinition.getValue().equals("")) {
//					getApplication().getMainWindow().showNotification("Please fill all the fields", Notification.TYPE_WARNING_MESSAGE);
//				}
//				// submit form to BWGoalManager
//				try {
//					String parentGoalName = null;
//					if(!parentWindow.getSelectedGoalID().equals("")) {
//						parentGoalName = (parentWindow.getSelectedGoalID().contains("(Mandatory)")) ?
//								parentWindow.getSelectedGoalID().replace(" (Mandatory)", "") : 
//									parentWindow.getSelectedGoalID().replace(" (Optional)", "");
//					}
//					WorklistManager.get().createGoal(instanceSpecification,
//							goalName.getValue().toString(), 
//							isMandatory.booleanValue(), goalDefinition.getValue().toString(),
//							parentGoalName);
//				} catch (WrongConditionException e) {
//					getApplication().getMainWindow().showNotification("The specified condition contains errors", Notification.TYPE_ERROR_MESSAGE);
//					return;
//				}
//				//close the window
//				getApplication().getMainWindow().removeWindow(NewGoalForm.this.getWindow());
//			}
//		});
//		
//		Button cancel = new Button("Cancel");
//		cancel.addListener(new Button.ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				getApplication().getMainWindow().removeWindow(NewGoalForm.this.getWindow());
//			}
//		});
//		
//		submitPanel.addComponent(cancel);
//		submitPanel.addComponent(ok);
//		//submitPanel.setMargin(true);
//		submitPanel.setSpacing(true);
//		
//		addComponent(submitPanel);
//		setComponentAlignment(submitPanel, Alignment.BOTTOM_RIGHT);
//	}
//	
//	public String getInstanceSpecification() {
//		return this.instanceSpecification;
//	}
//	
//	public void setData(String data, String value) {
//		if(value == null) {
//			addTextToGoalDef(data + ")");
//		} else {
//			addTextToGoalDef(data + ", " + value + ")");
//		}
//	}
//	
//	protected void addTextToGoalDef(String text) {
//		this.goalDefinition.setValue(this.goalDefinition.getValue() + text);
//	}
//	
//	protected void showDataModelTreeWindow(boolean specifyValue) {
//		
//		Window dataModel = new Window("Choose a data element");
//		dataModel.setContent(new DataModelTree(this, instanceSpecification, specifyValue));
//		dataModel.center();
//		
//		getApplication().getMainWindow().addWindow(dataModel);
//	}

}
