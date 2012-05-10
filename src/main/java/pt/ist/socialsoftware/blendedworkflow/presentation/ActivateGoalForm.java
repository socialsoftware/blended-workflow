package pt.ist.socialsoftware.blendedworkflow.presentation;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
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
public class ActivateGoalForm extends VerticalLayout implements Property.ValueChangeListener {

	private NativeSelect bwInstances = new NativeSelect("BWInstance");
	private NativeSelect parentGoal = new NativeSelect("Goal to Activate:");
	
	public ActivateGoalForm() {

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
					long parentGoalOID = (Long) parentGoal.getValue();
//					String activeUserID = "";
					Transaction.begin();
					BlendedWorkflow.getInstance().getWorkListManager().createGoalInstance(bwInstanceOID, parentGoalOID);
//					activeUserID = BlendedWorkflow.getInstance().getOrganizationalManager().getActiveUser().getID();
					Transaction.commit();
					
					getApplication().getMainWindow().showNotification("Goal Activated successfully", Notification.TYPE_TRAY_NOTIFICATION);
					getApplication().getMainWindow().removeWindow(ActivateGoalForm.this.getWindow());
				} catch (java.lang.NullPointerException jle) {
					getApplication().getMainWindow().showNotification("Please fill all the fields");
				} 

			}
		});

		Button cancel = new Button("Cancel");
		cancel.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(ActivateGoalForm.this.getWindow());
			}
		});

		// Layout
		addComponent(bwInstances);
		addComponent(parentGoal);

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
			this.parentGoal.addItem(goal.getOID());
			this.parentGoal.setItemCaption(goal.getOID(), goal.getName());
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
