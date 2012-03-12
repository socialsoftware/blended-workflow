package pt.ist.socialsoftware.blendedworkflow.presentation;

//import com.vaadin.ui.AbstractField;
//import com.vaadin.ui.Alignment;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.Button.ClickEvent;
//import com.vaadin.ui.Button.ClickListener;
//import com.vaadin.ui.CheckBox;
//import com.vaadin.ui.Window.Notification;
//import com.vaadin.ui.FormLayout;
//import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")

/**
 * Represents a task form
 */
public class TaskForm extends VerticalLayout {
//
//	private TaskInfo taskInfo = null;
//	private FormLayout form = new FormLayout();
//	private ArrayList<FormLayout> groups = new ArrayList<FormLayout>();
//	
//	private boolean isActivityTask;
//	
//	public TaskForm(TaskInfo taskInfo, boolean isActivity) {
//		
//		this.taskInfo = taskInfo;
//		
//		this.isActivityTask = isActivity;
//		
//		this.form.setMargin(true);
//		this.setWidth(null);
//		
//		// set input values
//		for (GroupDataInfo group : taskInfo.getInputData()) {
//			//see if the group exists
//			FormLayout layout = null;
//			if(this.groups.isEmpty()) {
//				// if does not exist, create it
//				layout = new FormLayout();
//				layout.setCaption(group.getGroupName());
//				this.groups.add(layout);
//				form.addComponent(layout);
//			} else {
//				for (FormLayout fGroup : this.groups) {
//					if(fGroup.getCaption().equals(group.getGroupName())) {
//						layout = fGroup;
//						break;
//					}
//				}
//				
//				if(layout == null) {
//					layout = new FormLayout();
//					layout.setCaption(group.getGroupName());
//					this.groups.add(layout);
//					form.addComponent(layout);
//				}
//			}
//			
//			for (DataInfo field : group.getFields()) {
//				if(field.getDataType().equalsIgnoreCase("BOOLEAN")) {
//					addCheckBox(layout, field.getDataName(), field.getValue(), true);
//				} else {
//					addTextBox(layout, field.getDataName(), field.getValue(), true);
//				}
//			}
//		}
//		
//		//set the output values
//		for (GroupDataInfo group : taskInfo.getOutputData()) {
//			FormLayout layout = null;
//			for (FormLayout fGroup : this.groups) {
//				if(fGroup.getCaption().equals(group.getGroupName())) {
//					layout = fGroup;
//					break;
//				}
//			}
//				
//			if(layout == null) {
//				layout = new FormLayout();
//				layout.setCaption(group.getGroupName());
//				this.groups.add(layout);
//				form.addComponent(layout);
//			} 		
//			
//			for (DataInfo field : group.getFields()) {
//				// search if the field exists in the group
//				int i = 0;
//				for(; i<layout.getComponentCount(); i++) {
//					if(layout.getComponent(i).getCaption().equals(field.getDataName())) {
//						break;
//					}
//				}
//				if(i>=layout.getComponentCount()) {
//					if(field.getDataType().equalsIgnoreCase("BOOLEAN")) {
//						addCheckBox(layout, field.getDataName(), field.getValue(), false);
//					} else {
//						addTextBox(layout, field.getDataName(), field.getValue(), false);
//					}
//				} else {
//					if(layout.getComponent(i) instanceof CheckBox) {
//						((CheckBox) layout.getComponent(i)).setReadOnly(false);
//						((CheckBox) layout.getComponent(i)).setValue(("true".equals(field.getValue()) ? true : false));
//					} else {
//						((TextField) layout.getComponent(i)).setReadOnly(false);
//						String fieldValue = field.getValue();
//						if(fieldValue!= null && !"null".equals(fieldValue) && !"N/A".equals(fieldValue)) {
//							((TextField) layout.getComponent(i)).setValue(fieldValue);
//						}
//					}
//				}
//			}
//		}
//		
//		// add the form
//		this.addComponent(form);
//		
//		//add a footer
//		HorizontalLayout footer = new HorizontalLayout();
//		Button okButton = new Button("OK");
//		footer.addComponent(okButton);
//
//		footer.setMargin(true);
//		
//		this.addComponent(footer);
//		this.setComponentAlignment(footer, Alignment.BOTTOM_RIGHT);
//		
//		//set the buttons behavior
//		okButton.addListener(new ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				
//				for(int i=0; i<form.getComponentCount(); i++) {
//					// Get the values from the textfields
//					FormLayout layout = (FormLayout) form.getComponent(i); 
//					for(int j=0; j< layout.getComponentCount(); j++) {
//						AbstractField field;
//						if(layout.getComponent(j) instanceof CheckBox) {
//							field = (CheckBox) layout.getComponent(j);
//						} else {
//							field = (TextField) layout.getComponent(j);
//						}
//						if(!field.isReadOnly()) {
//							DataInfo dataInfo = TaskForm.this.taskInfo.getOutputData(layout.getCaption(), field.getCaption());
//							dataInfo.setValue(field.getValue().toString());
//						}
//					}
//				}
//				
//				if(WorklistManager.get().registerExecutedTask(TaskForm.this.taskInfo, TaskForm.this.isActivityTask)) {
//					if(TaskForm.this.isActivityTask) {
//						getApplication().getMainWindow().showNotification("Activity executed", Notification.TYPE_TRAY_NOTIFICATION);
//					} else {
//						getApplication().getMainWindow().showNotification("Goal accomplished", Notification.TYPE_TRAY_NOTIFICATION);
//					}
//				}
//								
//				getApplication().getMainWindow().removeWindow(TaskForm.this.getWindow());
//			}
//		});		
//	}
//	
//	public TaskInfo getTaskInfo() {
//		return this.taskInfo;
//	}
//	
//	protected void addCheckBox(FormLayout layout, String dataName, String value, boolean readOnly) {
//		CheckBox checkBox = new CheckBox(dataName);
//		if("true".equals(value)) {
//			checkBox.setValue(true);
//		}
//		checkBox.setReadOnly(readOnly);
//		layout.addComponent(checkBox);
//	}
//	
//	protected void addTextBox(FormLayout layout, String dataName, String value, boolean readOnly) {
//		TextField tf = new TextField(dataName);
//		if(value != null && !"null".equals(value) && !"N/A".equals(value)) {
//			tf.setValue(value);
//		}
//		tf.setReadOnly(readOnly);
//		layout.addComponent(tf);
//	}
}
