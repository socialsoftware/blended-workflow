package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.User;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel.DataState;
import pt.ist.fenixframework.FenixFramework;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TaskForm extends VerticalLayout {
	DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);

	private final String taskWorkItemOID;
	VerticalLayout preData = new VerticalLayout();
	VerticalLayout data = new VerticalLayout();
	HorizontalLayout footer = new HorizontalLayout();

	public TaskForm(final String workItemOID) {
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
			@Atomic(mode = TxMode.WRITE)
			@Override
			public void buttonClick(ClickEvent event) {

				// New
				TaskWorkItem taskWorkItem = FenixFramework
						.getDomainObject(workItemOID);

				int workItemAttributeIndex = 0;
				for (int y = 0; y < data.getComponentCount(); y++) {
					AbstractField field;
					String value;
					if (!data.getComponent(y).getClass().equals(Label.class)) {
						if (data.getComponent(y).getClass()
								.equals(CheckBox.class)) {
							field = (CheckBox) data.getComponent(y);
							value = field.getValue().toString();
						} else if (data.getComponent(y).getClass()
								.equals(TextField.class)) {
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

				User activeUser = BlendedWorkflow.getInstance()
						.getOrganizationalManager().getActiveUser();
				taskWorkItem.setUser(activeUser);
				BlendedWorkflow.getInstance().getWorkListManager()
						.checkInWorkItem(taskWorkItemOID);

				getApplication().getMainWindow().removeWindow(
						TaskForm.this.getWindow());
			}
		});
		footer.addComponent(submitButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(
						TaskForm.this.getWindow());
			}
		});
		footer.addComponent(cancelButton);

		addComponent(footer);
		setComponentAlignment(footer, Alignment.MIDDLE_CENTER);
	}

	private void setWorkItemArgumentValue(int index, String value) {
		TaskWorkItem taskWorkItem = FenixFramework
				.getDomainObject(taskWorkItemOID);
		List<WorkItemArgument> arguments = new ArrayList<WorkItemArgument>(
				taskWorkItem.getOutputWorkItemArgumentsSet());
		arguments.get(index).setValue(value);
		arguments.get(index).setState(DataState.DEFINED);
	}

	private void getInputData() {
		TaskWorkItem taskWorkItem = FenixFramework
				.getDomainObject(taskWorkItemOID);

		BWEntity previousEntity = null;
		Boolean first = true;
		Boolean posAttribute = false;
		for (WorkItemArgument preWorkItemArgument : taskWorkItem
				.getInputWorkItemArgumentsSet()) {
			AttributeInstance preAttributeInstance = preWorkItemArgument
					.getAttributeInstance();
			BWAttribute attribute = preAttributeInstance.getAttribute();
			BWEntity entity = attribute.getEntity();
			String value = preWorkItemArgument.getValue();

			for (WorkItemArgument posWorkItemArgument : taskWorkItem
					.getOutputWorkItemArgumentsSet()) {
				AttributeInstance posAttributeInstance = posWorkItemArgument
						.getAttributeInstance();
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

	private void getOutputData() {

		TaskWorkItem taskWorkItem = FenixFramework
				.getDomainObject(taskWorkItemOID);

		BWEntity previousEntity = null;
		Boolean first = true;
		for (WorkItemArgument workItemArgument : taskWorkItem
				.getOutputWorkItemArgumentsSet()) {
			BWAttribute attribute = workItemArgument.getAttributeInstance()
					.getAttribute();
			BWEntity entity = attribute.getEntity();

			if (first) {
				previousEntity = entity;
				addLabel(entity.getName(), false);
				first = false;
			}
			if (!entity.equals(previousEntity)) {
				addLabel(entity.getName(), false);
			}

			if (attribute.getName().contains("Date")) {
				// TODO:
				PopupDateField datetime;
				datetime = new PopupDateField(attribute.getName());
				datetime.setValue(new java.util.Date());
				datetime.setResolution(PopupDateField.RESOLUTION_DAY);
				datetime.setImmediate(true);
				data.addComponent(datetime);
				// TODO:
			} else if (attribute.getType().equals(AttributeType.BOOLEAN)) {
				addCheckBox(attribute.getName(), false, null);
			} else {
				addTextBox(attribute.getName(), false, null);
			}
			previousEntity = entity;
		}

	}

	protected void addCheckBox(String attributeName, Boolean isPreData,
			String value) {
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

	protected void addTextBox(String attributeName, Boolean isPreData,
			String value) {
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
		Label l = new Label(entityName);
		l.addStyleName("h2");

		if (isPreData) {
			preData.addComponent(l);
		} else {
			data.addComponent(l);
		}
	}
}
