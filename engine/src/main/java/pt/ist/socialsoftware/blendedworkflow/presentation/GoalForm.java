package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.util.ArrayList;
import java.util.List;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem;
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
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class GoalForm extends VerticalLayout {

	private final String goalWorkItemOID;
	// VerticalLayout preData = new VerticalLayout();
	VerticalLayout data = new VerticalLayout();
	VerticalLayout entitiesInstances = new VerticalLayout();

	public GoalForm(final String workItemOID) {
		setMargin(true);
		setSpacing(true);

		this.goalWorkItemOID = workItemOID;

		// addComponent(preData);
		addComponent(data);
		addComponent(entitiesInstances);
		// getInputData();
		getOutputData();
		// getRelationEntities();

		HorizontalLayout footer = new HorizontalLayout();
		footer.setMargin(true);
		footer.setSpacing(true);

		Button submitButton = new Button("Submit");
		submitButton.addListener(new ClickListener() {
			@Atomic(mode = TxMode.WRITE)
			@Override
			public void buttonClick(ClickEvent event) {
				GoalWorkItem goalWorkItem = FenixFramework
						.getDomainObject(workItemOID);
				String e1OID = goalWorkItem.getEntityInstanceContext()
						.getExternalId();
				String bwInstanceOID = goalWorkItem.getBwInstance()
						.getExternalId();

				for (int i = 0; i < entitiesInstances.getComponentCount(); i++) {
					NativeSelect selec = (NativeSelect) entitiesInstances
							.getComponent(i);
					String e2OID = (String) selec.getValue();
					BlendedWorkflow.getInstance().getBwManager()
							.addRelationInstance(bwInstanceOID, e1OID, e2OID);
				}

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

				User activeUser = BlendedWorkflow.getInstance()
						.getOrganizationalManager().getActiveUser();
				goalWorkItem.setUser(activeUser);
				BlendedWorkflow.getInstance().getWorkListManager()
						.checkInWorkItem(goalWorkItemOID);

				getApplication().getMainWindow().removeWindow(
						GoalForm.this.getWindow());
			}
		});
		footer.addComponent(submitButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(
						GoalForm.this.getWindow());
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
				goalWorkItem.getOutputWorkItemArgumentsSet());
		arguments.get(index).setValue(value);
		arguments.get(index).setState(DataState.DEFINED);
	}

	@Atomic(mode = TxMode.WRITE)
	private void getOutputData() {
		GoalWorkItem goalWorkItem = FenixFramework
				.getDomainObject(goalWorkItemOID);

		BWEntity previousEntity = null;
		Boolean first = true;
		for (WorkItemArgument workItemArgument : goalWorkItem
				.getOutputWorkItemArgumentsSet()) {
			BWAttribute attribute = workItemArgument.getAttributeInstance()
					.getAttribute();
			BWEntity entity = attribute.getEntity();

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

	// private void getRelationEntities() {
	// Transaction.begin();
	// GoalWorkItem goalWorkItem =
	// FenixFramework.getDomainObject(goalWorkItemOID);
	// Entity goalContext = goalWorkItem.getAchieveGoal().getEntityContext();
	//
	// if (goalWorkItem.getSucessCondition().existExistEntity()) {
	//
	// for (Relation relation : goalContext.getRelations()) {
	// Entity one = relation.getEntityOne();
	// Entity two = relation.getEntityTwo();
	// if (goalContext.equals(one) && relation.getIsTwoKeyEntity()) {
	// addNativeSelect(two);
	// }
	// if (goalContext.equals(two) && relation.getIsOneKeyEntity()) {
	// addNativeSelect(one);
	// }
	// }
	// }
	//
	// Transaction.commit();
	// }

	protected void addCheckBox(String attributeName) {
		CheckBox checkBox = new CheckBox(attributeName);
		data.addComponent(checkBox);
	}

	protected void addTextBox(String attributeName) {
		TextField tf = new TextField(attributeName);
		data.addComponent(tf);
	}

	protected void addNativeSelect(BWEntity entity) {
		NativeSelect ns = new NativeSelect(entity.getName());
		entitiesInstances.addComponent(ns);

		ns.addStyleName("h2");
		for (EntityInstance entityInstance : entity.getEntityInstancesSet()) {
			ns.addItem(entityInstance.getExternalId());
			ns.setItemCaption(entityInstance.getExternalId(),
					entityInstance.getID());
		}
	}

	protected void addLabel(String entityName) {
		Label l = new Label(entityName);
		l.addStyleName("h2");
		data.addComponent(l);
	}

	// private void getInputData() {
	// Transaction.begin();
	// TaskWorkItem taskWorkItem =
	// FenixFramework.getDomainObject(taskWorkItemOID);
	//
	// Entity previousEntity = null;
	// Boolean first = true;
	// Boolean posAttribute = false;
	// for (WorkItemArgument preWorkItemArgument :
	// taskWorkItem.getInputWorkItemArguments()) {
	// AttributeInstance preAttributeInstance =
	// preWorkItemArgument.getAttributeInstance();
	// Attribute attribute = preAttributeInstance.getAttribute();
	// Entity entity = attribute.getEntity();
	// String value = preWorkItemArgument.getValue();
	//
	// for (WorkItemArgument posWorkItemArgument:
	// taskWorkItem.getOutputWorkItemArguments()) {
	// AttributeInstance posAttributeInstance =
	// posWorkItemArgument.getAttributeInstance();
	// if (preAttributeInstance.equals(posAttributeInstance)) {
	// posAttribute = true;
	// }
	// }
	//
	// if (!posAttribute) {
	//
	// if (first) {
	// previousEntity = entity;
	// addLabel(entity.getName(), true);
	// first = false;
	// }
	// if (!entity.equals(previousEntity)) {
	// addLabel(entity.getName(), true);
	// }
	//
	// if (attribute.getType().equals(AttributeType.BOOLEAN)) {
	// addCheckBox(attribute.getName(), true, value);
	// } else {
	// addTextBox(attribute.getName(), true, value);
	// }
	// }
	// posAttribute = false;
	// previousEntity = entity;
	// }
	// Transaction.commit();
	// }

}
