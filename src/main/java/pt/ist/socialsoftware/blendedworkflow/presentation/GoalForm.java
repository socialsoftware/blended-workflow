package pt.ist.socialsoftware.blendedworkflow.presentation;

import jvstm.Transaction;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.User;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class GoalForm extends VerticalLayout {

	private long goalWorkItemOID;
	VerticalLayout data = new VerticalLayout();

	public GoalForm(final long workItemOID) {
		setMargin(true);
		setSpacing(true);

		this.goalWorkItemOID = workItemOID;

		addComponent(data);
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

				Transaction.begin();
				GoalWorkItem goalWorkItem = AbstractDomainObject.fromOID(goalWorkItemOID);
				User activeUser = BlendedWorkflow.getInstance().getOrganizationalManager().getActiveUser();
				goalWorkItem.setUser(activeUser);
				Transaction.commit();
				Transaction.begin();
				BlendedWorkflow.getInstance().getWorkListManager().checkInWorkItem(goalWorkItemOID);
				Transaction.commit();

				getApplication().getMainWindow().showNotification("Goal accomplished", Notification.TYPE_TRAY_NOTIFICATION);
				getApplication().getMainWindow().removeWindow(GoalForm.this.getWindow());
			}
		});
		footer.addComponent(submitButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(GoalForm.this.getWindow());
			}
		});
		footer.addComponent(cancelButton);

		addComponent(footer);
		setComponentAlignment(footer, Alignment.MIDDLE_CENTER);
	}

	private void setWorkItemArgumentValue(int index, String value) {
		Transaction.begin();
		GoalWorkItem goalWorkItem = AbstractDomainObject.fromOID(goalWorkItemOID);
		goalWorkItem.getConstrainViolationWorkItemArguments().get(index).setValue(value);
		goalWorkItem.getConstrainViolationWorkItemArguments().get(index).setState(DataState.DEFINED);
		Transaction.commit();
	}

	private void getOutputData() {
		Transaction.begin();
		GoalWorkItem goalWorkItem = AbstractDomainObject.fromOID(goalWorkItemOID);

		Entity previousEntity = null;
		Boolean first = true;
		for (WorkItemArgument workItemArgument : goalWorkItem.getConstrainViolationWorkItemArguments()) {
			Attribute attribute = workItemArgument.getAttributeInstance().getAttribute();
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
		Transaction.commit();
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
		Label l= new Label(entityName);
		l.addStyleName("h2");
		data.addComponent(l);
	}
}

