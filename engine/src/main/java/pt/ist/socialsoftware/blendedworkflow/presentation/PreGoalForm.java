package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.util.ArrayList;
import java.util.List;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel.DataState;
import pt.ist.fenixframework.FenixFramework;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class PreGoalForm extends VerticalLayout {

	private final String goalWorkItemOID;
	VerticalLayout data = new VerticalLayout();

	public PreGoalForm(final String workItemOID) {
		setMargin(true);
		setSpacing(true);

		this.goalWorkItemOID = workItemOID;

		addComponent(data);
		getInputData();

		HorizontalLayout footer = new HorizontalLayout();
		footer.setMargin(true);
		footer.setSpacing(true);

		Button submitButton = new Button("Submit");
		submitButton.addListener(new ClickListener() {
			@Atomic(mode = TxMode.WRITE)
			@Override
			public void buttonClick(ClickEvent event) {
				int workItemAttributeIndex = 0;
				for (int y = 0; y < data.getComponentCount(); y++) {
					AbstractField field;

					if (!data.getComponent(y).getClass().equals(Label.class)) {
						if (data.getComponent(y).getClass()
								.equals(CheckBox.class)) {
							field = (CheckBox) data.getComponent(y);
						} else {
							field = (TextField) data.getComponent(y);
						}
						String value = field.getValue().toString();
						setWorkItemArgumentValue(workItemAttributeIndex, value);
						workItemAttributeIndex++;
					}
				}

				generateGoalForm(workItemOID);
				getApplication().getMainWindow().showNotification(
						"PreGoal accomplished",
						Notification.TYPE_TRAY_NOTIFICATION);
				getApplication().getMainWindow().removeWindow(
						PreGoalForm.this.getWindow());

			}
		});
		footer.addComponent(submitButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(
						PreGoalForm.this.getWindow());
			}
		});
		footer.addComponent(cancelButton);

		addComponent(footer);
		setComponentAlignment(footer, Alignment.MIDDLE_CENTER);
	}

	private void setWorkItemArgumentValue(int index, String value) {
		GoalWorkItem goalWorkItem = FenixFramework
				.getDomainObject(goalWorkItemOID);
		List<WorkItemArgument> arguments = new ArrayList<WorkItemArgument>(
				goalWorkItem.getInputWorkItemArgumentsSet());
		arguments.get(index).setValue(value);
		arguments.get(index).setState(DataState.DEFINED);
	}

	@Atomic(mode = TxMode.WRITE)
	private void getInputData() {
		GoalWorkItem goalWorkItem = FenixFramework
				.getDomainObject(goalWorkItemOID);

		Entity previousEntity = null;
		Boolean first = true;
		for (WorkItemArgument workItemArgument : goalWorkItem
				.getInputWorkItemArgumentsSet()) {
			Attribute attribute = workItemArgument.getAttributeInstance()
					.getAttribute();
			Entity entity = attribute.getEntity();

			if (first) {
				previousEntity = entity;
				addLabel(entity.getName());
				first = false;
			}
			if (!entity.equals(previousEntity)) {
				addLabel(entity.getName());
			}

			if (attribute.getType().equals(AttributeType.BOOLEAN)) {
				addCheckBox(attribute.getName());
			} else {
				addTextBox(attribute.getName());
			}
			previousEntity = entity;
		}
	}

	protected void addCheckBox(String attributeName) {
		CheckBox checkBox = new CheckBox(attributeName);
		data.addComponent(checkBox);
	}

	protected void addTextBox(String attributeName) {
		TextField tf = new TextField(attributeName);
		data.addComponent(tf);
	}

	protected void addLabel(String entityName) {
		Label l = new Label(entityName);
		l.addStyleName("h2");
		data.addComponent(l);
	}

	public void generateGoalForm(String workItemOID) {
		Window goalWindow = new Window("Goal Form");
		goalWindow.setContent(new GoalForm(workItemOID));
		goalWindow.setWidth("30%");
		goalWindow.center();
		getApplication().getMainWindow().addWindow(goalWindow);
	}
}
