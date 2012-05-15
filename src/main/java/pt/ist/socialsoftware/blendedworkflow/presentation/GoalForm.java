package pt.ist.socialsoftware.blendedworkflow.presentation;

import jvstm.Transaction;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.User;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class GoalForm extends VerticalLayout {

	private long goalWorkItemOID;
//	VerticalLayout preData = new VerticalLayout();
	VerticalLayout data = new VerticalLayout();
	VerticalLayout entitiesInstances = new VerticalLayout();

	public GoalForm(final long workItemOID) {
		setMargin(true);
		setSpacing(true);

		this.goalWorkItemOID = workItemOID;

//		addComponent(preData);
		addComponent(data);
		addComponent(entitiesInstances);
//		getInputData();
		getOutputData();
		getRelationEntities();

		HorizontalLayout footer = new HorizontalLayout();
		footer.setMargin(true);
		footer.setSpacing(true);

		Button submitButton = new Button("Submit");
		submitButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Transaction.begin();
				GoalWorkItem goalWorkItem = AbstractDomainObject.fromOID(workItemOID);
				long e1OID = goalWorkItem.getEntityInstanceContext().getOID();
				long bwInstanceOID = goalWorkItem.getBwInstance().getOID();
				Transaction.commit();
				
				for (int i = 0; i < entitiesInstances.getComponentCount(); i++) {
					NativeSelect selec = (NativeSelect) entitiesInstances.getComponent(i);
					long e2OID = (Long) selec.getValue();
					Transaction.begin();
					BlendedWorkflow.getInstance().getBwManager().addRelationInstance(bwInstanceOID, e1OID, e2OID);
					Transaction.commit();
				}
				
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
		goalWorkItem.getOutputWorkItemArguments().get(index).setValue(value);
		goalWorkItem.getOutputWorkItemArguments().get(index).setState(DataState.DEFINED);
		Transaction.commit();
	}

	private void getOutputData() {
		Transaction.begin();
		GoalWorkItem goalWorkItem = AbstractDomainObject.fromOID(goalWorkItemOID);

		Entity previousEntity = null;
		Boolean first = true;
		for (WorkItemArgument workItemArgument : goalWorkItem.getOutputWorkItemArguments()) {
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
	
	private void getRelationEntities() {
		Transaction.begin();
		GoalWorkItem goalWorkItem = AbstractDomainObject.fromOID(goalWorkItemOID);
		Entity goalContext = goalWorkItem.getAchieveGoal().getEntityContext();
		
		if (goalWorkItem.getSucessCondition().existExistEntity()) {
			addLabel("Relations Entities:");
			for (Relation relation : goalContext.getRelations()) {
				Entity one = relation.getEntityOne();
				Entity two = relation.getEntityTwo();
				if (goalContext.equals(one) && relation.getIsTwoKeyEntity()) {
					addNativeSelect(two);
				}
				if (goalContext.equals(two) && relation.getIsOneKeyEntity()) {
					addNativeSelect(one);
				}
			}
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
	
	protected void addNativeSelect(Entity entity) {
		NativeSelect ns = new NativeSelect(entity.getName());
		entitiesInstances.addComponent(ns);

		for (EntityInstance entityInstance : entity.getEntityInstances()) {
			ns.addItem(entityInstance.getOID());
			ns.setItemCaption(entityInstance.getOID(), entityInstance.getID());
		}
	}

	protected void addLabel(String entityName) {
		Label l= new Label(entityName);
		l.addStyleName("h2");
		data.addComponent(l);
	}
	
//	private void getInputData() {
//		Transaction.begin();
//		TaskWorkItem taskWorkItem = AbstractDomainObject.fromOID(taskWorkItemOID);
//
//		Entity previousEntity = null;
//		Boolean first = true;
//		Boolean posAttribute = false;
//		for (WorkItemArgument preWorkItemArgument : taskWorkItem.getInputWorkItemArguments()) {
//			AttributeInstance preAttributeInstance = preWorkItemArgument.getAttributeInstance();
//			Attribute attribute = preAttributeInstance.getAttribute();
//			Entity entity = attribute.getEntity();
//			String value = preWorkItemArgument.getValue();
//
//			for (WorkItemArgument posWorkItemArgument: taskWorkItem.getOutputWorkItemArguments()) {
//				AttributeInstance posAttributeInstance = posWorkItemArgument.getAttributeInstance();
//				if (preAttributeInstance.equals(posAttributeInstance)) {
//					posAttribute = true;
//				}
//			}
//
//			if (!posAttribute) {
//
//				if (first) {
//					previousEntity = entity;
//					addLabel(entity.getName(), true);
//					first = false;
//				}
//				if (!entity.equals(previousEntity)) {
//					addLabel(entity.getName(), true);
//				}
//
//				if (attribute.getType().equals(AttributeType.BOOLEAN)) {
//					addCheckBox(attribute.getName(), true, value);
//				} else {
//					addTextBox(attribute.getName(), true, value);
//				}
//			}
//			posAttribute = false;
//			previousEntity = entity;
//		}
//		Transaction.commit();
//	}

}

