package pt.ist.socialsoftware.blendedworkflow.presentation;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import jvstm.Transaction;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window.Notification;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class RedoGoalForm extends VerticalLayout implements Property.ValueChangeListener {

	private NativeSelect bwInstances = new NativeSelect("BWInstance");
	private NativeSelect parentGoal = new NativeSelect("Goal to REDO:");
	private NativeSelect workItems = new NativeSelect("Completed/Skipped GoalWorkItemss:");
//	private Logger log = Logger.getLogger("WorklistManager");
	
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

		bwInstances.setNullSelectionAllowed(false);
		bwInstances.setImmediate(true);
		bwInstances.addListener(this);

		Button submit = new Button("Submit");
		submit.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					long bwInstanceOID = (Long) bwInstances.getValue();
//					long parentGoalOID = (Long) parentGoal.getValue();
					long workItemlOID = (Long) parentGoal.getValue();
					Transaction.begin();
					String activeUserID = BlendedWorkflow.getInstance().getOrganizationalManager().getActiveUser().getID();
					BlendedWorkflow.getInstance().getWorkListManager().redoGoal(bwInstanceOID, workItemlOID, activeUserID);
					Transaction.commit();
					
					getApplication().getMainWindow().showNotification("Goal ReActivated successfully", Notification.TYPE_TRAY_NOTIFICATION);
					getApplication().getMainWindow().removeWindow(RedoGoalForm.this.getWindow());
				} catch (java.lang.NullPointerException jle) {
					getApplication().getMainWindow().showNotification("Please fill all the fields");
				} 

			}
		});

		Button cancel = new Button("Cancel");
		cancel.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(RedoGoalForm.this.getWindow());
			}
		});

		// Layout
		addComponent(bwInstances);
		addComponent(parentGoal);
		addComponent(workItems);
		
		parentGoal.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				if (parentGoal.getValue() == null) {
					workItems.removeAllItems();
				} else {
					long bwInstanceOID = (Long) bwInstances.getValue();
					long goalOID = (Long) parentGoal.getValue();
					updateWorkItemsInfo(bwInstanceOID, goalOID); 
				}
			}
		});

		dataHL.addComponent(dataVL);
		addComponent(dataHL);

		submitPanel.addComponent(submit);
		submitPanel.addComponent(cancel);
		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.BOTTOM_CENTER);

		// Populate
		getBWInstances();
	}

	// Update Goals depending on selected bwInstance
	public void valueChange(ValueChangeEvent event) {
		long bwInstanceOID = (Long) bwInstances.getValue();
		BWInstance bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		getGoals(bwInstance);
	}

	private void getGoals(BWInstance bwInstance) {
		Transaction.begin();
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		for (AchieveGoal goal : goalModelInstance.getAchieveGoals()) {
			if (goal.getGoalWorkItemsCount() > 0) {
				this.parentGoal.addItem(goal.getOID());
				this.parentGoal.setItemCaption(goal.getOID(), goal.getName());}
		}
		Transaction.commit();
	}
	
	private void updateWorkItemsInfo(long bwInstanceOID, long goalOIDs) {
//		BWInstance bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		AchieveGoal goal = AbstractDomainObject.fromOID(bwInstanceOID);
		
		Transaction.begin();
		for (GoalWorkItem goalWorkItem : goal.getGoalWorkItems()) {
			this.parentGoal.addItem(goalWorkItem.getOID());
			this.parentGoal.setItemCaption(goalWorkItem.getOID(), goalWorkItem.getID());
			//TODO: Give workitem data information
		}
		Transaction.commit();
	}
	

	private void getBWInstances() {
		Transaction.begin();
		for (BWSpecification bwSpecification : BlendedWorkflow.getInstance().getBwSpecifications()) {
			for (BWInstance bwInstance : bwSpecification.getBwInstances()) {
				this.bwInstances.addItem(bwInstance.getOID());
				this.bwInstances.setItemCaption(bwInstance.getOID(), bwInstance.getName());
			}
		}
		Transaction.commit();
	}

}
