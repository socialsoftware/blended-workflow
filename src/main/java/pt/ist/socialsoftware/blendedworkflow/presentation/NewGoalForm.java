package pt.ist.socialsoftware.blendedworkflow.presentation;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateGoalService;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import jvstm.Atomic;

import com.vaadin.ui.AbstractSelect.Filtering;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class NewGoalForm extends VerticalLayout implements Property.ValueChangeListener {

	private ComboBox bwInstances = new ComboBox("BWInstances");
	private TextField name = new TextField("Goal Name");
	private ComboBox parentGoal = new ComboBox("Parent Goal");
	private TextArea condition = new TextArea("Goal Condition"); 

	public NewGoalForm() {

		setMargin(true);
		setSpacing(true);
		
		condition.setWidth("100%");
		bwInstances.setNullSelectionAllowed(false);
		parentGoal.setNullSelectionAllowed(false);
		
		getBWInstances();
		
		bwInstances.setFilteringMode(Filtering.FILTERINGMODE_OFF);
        bwInstances.setImmediate(true);
        bwInstances.addListener(this);
        
		addComponent(bwInstances);
		addComponent(name);
		addComponent(parentGoal);
		addComponent(condition);

		HorizontalLayout submitPanel = new HorizontalLayout();
		submitPanel.setSpacing(true);

		Button submit = new Button("Submit");
		submit.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					long bwInstanceOID = (Long) bwInstances.getValue();
					String goalName = (String) name.getValue();
					long parentGoalID = (Long) parentGoal.getValue();
					String goalCondition = (String) condition.getValue();
					
					new CreateGoalService(bwInstanceOID, goalName, parentGoalID, goalCondition).execute();
				}
				catch (java.lang.NullPointerException jle) {
					getApplication().getMainWindow().showNotification("Please fill all fields");
				}
				catch (BlendedWorkflowException bwe) {
					getApplication().getMainWindow().showNotification(bwe.getError().toString(), Notification.TYPE_ERROR_MESSAGE);
				}
				
				getApplication().getMainWindow().showNotification("Goal created successfully");
				getApplication().getMainWindow().removeWindow(NewGoalForm.this.getWindow());
			}
		});
		submitPanel.addComponent(submit);

		Button cancel = new Button("Cancel");
		cancel.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(NewGoalForm.this.getWindow());
			}
		});
		submitPanel.addComponent(cancel);

		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.BOTTOM_RIGHT);
	}

	// Update Goals depending on selected bwInstance
    public void valueChange(ValueChangeEvent event) {
		long bwInstanceOID = (Long) bwInstances.getValue();
		BWInstance bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		getGoals(bwInstance);
    }

    @Atomic
	private void getGoals(BWInstance bwInstance) {
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		for (Goal goal : goalModelInstance.getGoals()) {
			this.parentGoal.addItem(goal.getOID());
			this.parentGoal.setItemCaption(goal.getOID(), goal.getName());
		}
	}
    
    @Atomic
	private void getBWInstances() {
		for (BWSpecification bwSpecification : BlendedWorkflow.getInstance().getBwSpecifications()) {
			for (BWInstance bwInstance : bwSpecification.getBwInstances()) {
				this.bwInstances.addItem(bwInstance.getOID());
				this.bwInstances.setItemCaption(bwInstance.getOID(), bwInstance.getID());
			}
		}
	}
}
