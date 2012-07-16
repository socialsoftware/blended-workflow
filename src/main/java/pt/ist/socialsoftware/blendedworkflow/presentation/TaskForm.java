package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.text.DateFormat;

import jvstm.Transaction;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.User;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TaskForm extends VerticalLayout {
	DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);

	private long taskWorkItemOID;
	VerticalLayout preData = new VerticalLayout();
	VerticalLayout data = new VerticalLayout();
	HorizontalLayout footer = new HorizontalLayout();
	 
	public TaskForm(final long workItemOID) {
		setMargin(true);
		setSpacing(true);

		this.taskWorkItemOID = workItemOID;

		addComponent(preData);
		addComponent(data);

		getInputData();
		getOutputData();
		
		footer.setMargin(true);
		footer.setSpacing(true);

		Button submitButton = new Button("Submit");
		submitButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				
				//New
				Transaction.begin();
				TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(workItemOID);
				Transaction.commit();
					
				int workItemAttributeIndex = 0;
				for (int y = 0; y < data.getComponentCount(); y++) {
					AbstractField field;
					String value;
					if (!data.getComponent(y).getClass().equals(Label.class)) {
						if(data.getComponent(y).getClass().equals(CheckBox.class)) {
							field = (CheckBox) data.getComponent(y);
							value = field.getValue().toString();
						} else if(data.getComponent(y).getClass().equals(TextField.class)){
							field = (TextField) data.getComponent(y);
							value = field.getValue().toString();
						} else {
							field = (PopupDateField) data.getComponent(y);
							value = dateFormatter.format(field.getValue());
						}

						setWorkItemArgumentValue(workItemAttributeIndex, value);
						workItemAttributeIndex++;
					}
				}

				Transaction.begin();
				User activeUser = BlendedWorkflow.getInstance().getOrganizationalManager().getActiveUser();
				taskWorkItem.setUser(activeUser);
				Transaction.commit();
				Transaction.begin();
				BlendedWorkflow.getInstance().getWorkListManager().checkInWorkItem(taskWorkItemOID);
				Transaction.commit();

				getApplication().getMainWindow().removeWindow(TaskForm.this.getWindow());
			}
		});
		footer.addComponent(submitButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(TaskForm.this.getWindow());
			}
		});
		footer.addComponent(cancelButton);

		addComponent(footer);
		setComponentAlignment(footer, Alignment.MIDDLE_CENTER);
	}

	private void setWorkItemArgumentValue(int index, String value) {
		Transaction.begin();
		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);
		taskWorkItem.getOutputWorkItemArguments().get(index).setValue(value);
		taskWorkItem.getOutputWorkItemArguments().get(index).setState(DataState.DEFINED);
		Transaction.commit();
	}

	private void getInputData() {
		Transaction.begin();
		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);

		Entity previousEntity = null;
		Boolean first = true;
		Boolean posAttribute = false;
		for (WorkItemArgument preWorkItemArgument : taskWorkItem.getInputWorkItemArguments()) {
			AttributeInstance preAttributeInstance = preWorkItemArgument.getAttributeInstance();
			Attribute attribute = preAttributeInstance.getAttribute();
			Entity entity = attribute.getEntity();
			String value = preWorkItemArgument.getValue();

			for (WorkItemArgument posWorkItemArgument: taskWorkItem.getOutputWorkItemArguments()) {
				AttributeInstance posAttributeInstance = posWorkItemArgument.getAttributeInstance();
				if (preAttributeInstance.equals(posAttributeInstance)) {
					posAttribute = true;
				}
			}

			if (!posAttribute) {

				if (first) {
					previousEntity = entity;
					addLabel(entity.getName(), true);
					first = false;
				}
				if (!entity.equals(previousEntity)) {
					addLabel(entity.getName(), true);
				}

				if (attribute.getType().equals(AttributeType.BOOLEAN)) {
					addCheckBox(attribute.getName(), true, value);
				} else {
					addTextBox(attribute.getName(), true, value);
				}
			}
			posAttribute = false;
			previousEntity = entity;
		}
		Transaction.commit();
	}

	private void getOutputData() {
		Transaction.begin();

		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);

		Entity previousEntity = null;
		Boolean first = true;
		for (WorkItemArgument workItemArgument : taskWorkItem.getOutputWorkItemArguments()) {
			Attribute attribute = workItemArgument.getAttributeInstance().getAttribute();
			Entity entity = attribute.getEntity();

			if (first) {
				previousEntity = entity;
				addLabel(entity.getName(), false);
				first = false;
			}
			if (!entity.equals(previousEntity)) {
				addLabel(entity.getName(), false);
			}

			if (attribute.getName().contains("Date")) {
				//TODO:
				PopupDateField datetime;
				datetime = new PopupDateField(attribute.getName());
		        datetime.setValue(new java.util.Date());
		        datetime.setResolution(PopupDateField.RESOLUTION_DAY);
		        datetime.setImmediate(true);
		        data.addComponent(datetime);
		        //TODO:
			}
			else if (attribute.getType().equals(AttributeType.BOOLEAN)) {
				addCheckBox(attribute.getName(), false, null);
			} else {
				addTextBox(attribute.getName(), false, null);
			}
			previousEntity = entity;
		}
		Transaction.commit();

	}
	
	protected void addCheckBox(String attributeName, Boolean isPreData, String value) {
		CheckBox checkBox = new CheckBox(attributeName);

		if (value != null) {
			if (value.equals("true"))
				checkBox.setValue(true);
			else
				checkBox.setValue(false);
		}
		checkBox.setReadOnly(isPreData);
		if (isPreData) {
			preData.addComponent(checkBox);
		} else {
			data.addComponent(checkBox);
		}
	}

	protected void addTextBox(String attributeName, Boolean isPreData, String value) {
		TextField tf = new TextField(attributeName);
		if (value != null) {
			tf.setValue(value);
		}
		tf.setEnabled(!isPreData);
		if (isPreData) {
			preData.addComponent(tf);
		} else {
			data.addComponent(tf);
		}
	}

	protected void addLabel(String entityName, Boolean isPreData) {
		Label l= new Label(entityName);
		l.addStyleName("h2");

		if (isPreData) {
			preData.addComponent(l);
		} else {
			data.addComponent(l);
		}
	}
}

