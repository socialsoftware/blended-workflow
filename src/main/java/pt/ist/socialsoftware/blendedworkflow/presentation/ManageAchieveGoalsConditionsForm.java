package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.util.ArrayList;

import jvstm.Transaction;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.MaintainGoal;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.event.Action;

@SuppressWarnings("serial")
public class ManageAchieveGoalsConditionsForm extends VerticalLayout {

	private Long _bwInstanceOID = null;
	private Long _entityInstanceOID = null;
	private Long _goalOID = null;
	private ArrayList<Long> activateConditionsOID = new ArrayList<Long>();
	private ArrayList<Long> maitainGoalsOID = new ArrayList<Long>();
	
	protected final Tree acTreetable = new Tree("Activate Conditions:");
	protected final Tree mcTreetable = new Tree("Maintain Conditions:");
	protected Label goalNameLabel = new Label();

	private static final Action DISABLE_CONDITION_ACTION = new Action("Disable Condition");
	
	public ManageAchieveGoalsConditionsForm(final ActivateGoalForm parent, final long bwInstanceOID, long goalOID, long entityInstanceOID) {
		HorizontalLayout footer = new HorizontalLayout();
		
		_bwInstanceOID = bwInstanceOID;
		if (entityInstanceOID != 0) {
			_entityInstanceOID = entityInstanceOID;
		}
		_goalOID = goalOID;
		
		// Properties
		setMargin(true);
		setWidth("1000px");
		setSpacing(true);

		acTreetable.setSelectable(true);
		mcTreetable.setSelectable(true);

		footer.setSpacing(true);

		acTreetable.addActionHandler(new Action.Handler() {
			public void handleAction(Action action, Object sender, Object target) {
				if (action == DISABLE_CONDITION_ACTION) {
					Long ConditionOID = (Long) target;
					activateConditionsOID.remove(ConditionOID);
					acTreetable.removeItem(ConditionOID);
				} 
			}

			public Action[] getActions(Object target, Object sender) {
				if (!acTreetable.areChildrenAllowed(target)) {
					return new Action[]{DISABLE_CONDITION_ACTION};
				} else {
					return new Action[] {};
				}
			}
		});
		
		mcTreetable.addActionHandler(new Action.Handler() {
			public void handleAction(Action action, Object sender, Object target) {
				if (action == DISABLE_CONDITION_ACTION) {
					Long ConditionOID = (Long) target;
					maitainGoalsOID.remove(ConditionOID);
					mcTreetable.removeItem(ConditionOID);
				} 
			}

			public Action[] getActions(Object target, Object sender) {
				if (!mcTreetable.areChildrenAllowed(target)) {
					return new Action[]{DISABLE_CONDITION_ACTION};
				} else {
					return new Action[] {};
				}
			}
		});

		Button submitButton = new Button("Finish");
		submitButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Transaction.begin();
				BlendedWorkflow.getInstance().getWorkListManager().createGoalInstance(bwInstanceOID, _goalOID, _entityInstanceOID, activateConditionsOID, maitainGoalsOID);
				Transaction.commit();
				getApplication().getMainWindow().removeWindow(ManageAchieveGoalsConditionsForm.this.getWindow());
			}
		});

		goalNameLabel.addStyleName("h2");
		addComponent(goalNameLabel);
		addComponent(acTreetable);
		addComponent(mcTreetable);
		footer.addComponent(submitButton);
		addComponent(footer);
		setComponentAlignment(footer, Alignment.BOTTOM_CENTER);

		// Populate
		getGoalConditions();
	}

	public void getGoalConditions() {
		Transaction.begin();
		BWInstance bwInstance = AbstractDomainObject.fromOID(_bwInstanceOID);
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		
		AchieveGoal newGoal = AbstractDomainObject.fromOID(_goalOID);
		goalNameLabel.setCaption(newGoal.getName());
		
		for (Condition activateCondition : newGoal.getActivateConditions()) {
			long ID = activateCondition.getOID();
			String caption = activateCondition.toString();
			
			acTreetable.addItem(ID);
			acTreetable.setItemCaption(ID, caption);
			acTreetable.setChildrenAllowed(ID, false);
			activateConditionsOID.add(ID);
		}
		
		for (MaintainGoal mg : goalModelInstance.getAchieveGoalAssociatedMaintainGoals(newGoal)) {
			long ID = mg.getOID();
			String caption = mg.getMaintainCondition().toString();

			mcTreetable.addItem(ID);
			mcTreetable.setItemCaption(ID, caption);
			mcTreetable.setChildrenAllowed(ID, false);
			
			this.maitainGoalsOID.add(ID);
		}
		
		Transaction.commit();
	}
}
