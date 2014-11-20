package pt.ist.socialsoftware.blendedworkflow.presentation;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem.GoalState;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class RedoGoalForm extends VerticalLayout {

	private final NativeSelect bwInstances = new NativeSelect("BWInstance");
	private final NativeSelect parentGoal = new NativeSelect("Goal:");
	private final NativeSelect workItems = new NativeSelect(
			"Completed or Skipped GoalWorkItems:");

	public RedoGoalForm() {

		HorizontalLayout dataHL = new HorizontalLayout();
		VerticalLayout dataVL = new VerticalLayout();
		HorizontalLayout submitPanel = new HorizontalLayout();

		// Properties
		setMargin(true);
		setSpacing(false);
		setHeight("200px");
		setWidth("200px");

		dataHL.setSpacing(true);
		submitPanel.setSpacing(true);

		bwInstances.setImmediate(true);
		bwInstances.addListener(new Property.ValueChangeListener() {
			@Atomic(mode = TxMode.WRITE)
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (bwInstances.getValue() == null) {
					parentGoal.removeAllItems();
					workItems.removeAllItems();
				} else {
					String bwInstanceOID = (String) bwInstances.getValue();
					BWInstance bwInstance = FenixFramework
							.getDomainObject(bwInstanceOID);
					getGoals(bwInstance);
				}
			}
		});

		parentGoal.setImmediate(true);
		parentGoal.addListener(new Property.ValueChangeListener() {
			@Atomic(mode = TxMode.WRITE)
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (parentGoal.getValue() == null) {
					workItems.removeAllItems();
				} else {
					String bwInstanceOID = (String) bwInstances.getValue();
					String goalOID = (String) parentGoal.getValue();
					updateWorkItemsInfo(bwInstanceOID, goalOID);
				}
			}
		});

		Button submit = new Button("Submit");
		submit.addListener(new Button.ClickListener() {
			@Atomic(mode = TxMode.WRITE)
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					String workItemlOID = (String) workItems.getValue();
					String activeUserID = BlendedWorkflow.getInstance()
							.getOrganizationalManager().getActiveUser().getID();
					BlendedWorkflow.getInstance().getWorkListManager()
							.redoGoal(workItemlOID, activeUserID);

					getApplication().getMainWindow().removeWindow(
							RedoGoalForm.this.getWindow());
				} catch (java.lang.NullPointerException jle) {
					getApplication().getMainWindow().showNotification(
							"Please fill all the fields");
				}

			}
		});

		Button cancel = new Button("Cancel");
		cancel.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(
						RedoGoalForm.this.getWindow());
			}
		});

		// Layout
		addComponent(bwInstances);
		addComponent(parentGoal);
		addComponent(workItems);

		dataHL.addComponent(dataVL);
		addComponent(dataHL);

		submitPanel.addComponent(submit);
		submitPanel.addComponent(cancel);
		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.BOTTOM_CENTER);

		// Populate
		getBWInstances();
	}

	private void getGoals(BWInstance bwInstance) {
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		for (AchieveGoal goal : goalModelInstance.getAchieveGoalsSet()) {
			if (goal.getGoalWorkItemsSet().size() > 0) {
				this.parentGoal.addItem(goal.getExternalId());
				this.parentGoal.setItemCaption(goal.getExternalId(),
						goal.getName());
			}
		}
	}

	private void updateWorkItemsInfo(String bwInstanceOID, String goalOID) {
		this.workItems.removeAllItems();
		AchieveGoal goal = FenixFramework.getDomainObject(goalOID);

		for (GoalWorkItem goalWorkItem : goal.getGoalWorkItemsSet()) {
			if (goalWorkItem.getState().equals(GoalState.ACHIEVED)
					|| goalWorkItem.getState().equals(GoalState.SKIPPED)) {
				this.workItems.addItem(goalWorkItem.getExternalId());
				this.workItems.setItemCaption(goalWorkItem.getExternalId(),
						goalWorkItem.getID());
				// TODO: Give workitem data information
			}
		}
	}

	@Atomic(mode = TxMode.WRITE)
	private void getBWInstances() {
		for (BWSpecification bwSpecification : BlendedWorkflow.getInstance()
				.getBwSpecificationsSet()) {
			for (BWInstance bwInstance : bwSpecification.getBwInstancesSet()) {
				this.bwInstances.addItem(bwInstance.getExternalId());
				this.bwInstances.setItemCaption(bwInstance.getExternalId(),
						bwInstance.getName());
			}
		}
	}

}
