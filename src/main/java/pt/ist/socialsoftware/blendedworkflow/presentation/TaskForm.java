package pt.ist.socialsoftware.blendedworkflow.presentation;

import jvstm.Atomic;
import jvstm.Transaction;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CheckInWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TaskForm extends VerticalLayout {

	private long taskWorkItemOID;
	VerticalLayout preData = new VerticalLayout();
	VerticalLayout data = new VerticalLayout();

	public TaskForm(final long workItemOID) {
		setMargin(true);
		setSpacing(true);

		this.taskWorkItemOID = workItemOID;

		addComponent(preData);
		addComponent(data);
		getInputData();
		getOutputData();

		HorizontalLayout footer = new HorizontalLayout();
		footer.setMargin(true);
		footer.setSpacing(true);

		Button submitButton = new Button("Submit");
		submitButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				int workItemAttributeIndex = 0;
				for (int y = 0; y < data.getComponentCount(); y++) {
					AbstractField field;

					if (!data.getComponent(y).getClass().equals(Label.class)) {
						if(data.getComponent(y).getClass().equals(CheckBox.class)) {
							field = (CheckBox) data.getComponent(y);
						} else {
							field = (TextField) data.getComponent(y);
						}
						String value = field.getValue().toString();
						setWorkItemArgumentValue(workItemAttributeIndex, value);
						workItemAttributeIndex++;
					}
				}

				try {
					new CheckInWorkItemService(taskWorkItemOID).execute();
				} catch (BlendedWorkflowException bwe) {
					getApplication().getMainWindow().showNotification(bwe.getError().toString(), Notification.TYPE_ERROR_MESSAGE);
				}

				getApplication().getMainWindow().showNotification("Task accomplished", Notification.TYPE_TRAY_NOTIFICATION);
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
		setComponentAlignment(footer, Alignment.BOTTOM_RIGHT);
	}

	private void setWorkItemArgumentValue(int index, String value) {
		Transaction.begin();
		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);
		taskWorkItem.getConstrainViolationWorkItemArguments().get(index).setValue(value);
		taskWorkItem.getConstrainViolationWorkItemArguments().get(index).setState(DataState.DEFINED);
		Transaction.commit();
	}

	@Atomic
	private void getInputData() {
		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);

		Entity previousEntity = null;
		Boolean first = true;
		Boolean posAttribute = false;
		for (WorkItemArgument preWorkItemArgument : taskWorkItem.getPreConstrainWorkItemArguments()) {
			AttributeInstance preAttributeInstance = preWorkItemArgument.getAttributeInstance();
			Attribute attribute = preAttributeInstance.getAttribute();
			Entity entity = attribute.getEntity();
			String value = preWorkItemArgument.getValue();

			for (WorkItemArgument posWorkItemArgument: taskWorkItem.getConstrainViolationWorkItemArguments()) {
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
	}

	@Atomic
	private void getOutputData() {
		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);

		Entity previousEntity = null;
		Boolean first = true;
		for (WorkItemArgument workItemArgument : taskWorkItem.getConstrainViolationWorkItemArguments()) {
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

			if (attribute.getType().equals(AttributeType.BOOLEAN)) {
				addCheckBox(attribute.getName(), false, null);
			} else {
				addTextBox(attribute.getName(), false, null);
			}
			previousEntity = entity;
		}
	}

	protected void addCheckBox(String attributeName, Boolean isPreData, String value) {
		CheckBox checkBox = new CheckBox(attributeName);

		if (value != null) {
			if (value.equals(true))
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

