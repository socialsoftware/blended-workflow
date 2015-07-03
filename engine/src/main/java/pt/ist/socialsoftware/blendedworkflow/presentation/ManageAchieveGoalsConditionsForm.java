package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.MaintainGoal;
import pt.ist.fenixframework.FenixFramework;

import com.vaadin.event.Action;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ManageAchieveGoalsConditionsForm extends VerticalLayout {

	private String _bwInstanceOID = null;
	// private String _entityInstanceOID = null;
	private String _goalOID = null;
	private final Set<String> activateConditionsOID = new HashSet<String>();
	private final Set<String> maitainGoalsOID = new HashSet<String>();

	protected final Tree acTreetable = new Tree("Activate Conditions:");
	protected final Tree mcTreetable = new Tree("Maintain Conditions:");
	protected Label goalNameLabel = new Label();

	private static final Action DISABLE_CONDITION_ACTION = new Action(
			"Disable Condition");

	public ManageAchieveGoalsConditionsForm(final ActivateGoalForm parent,
			final String bwInstanceOID, String goalOID,
			final HashMap<String, String> entitiesOID) {
		HorizontalLayout footer = new HorizontalLayout();

		_bwInstanceOID = bwInstanceOID;
		// if (entityInstanceOID != 0) {
		// _entityInstanceOID = entityInstanceOID;
		// }
		_goalOID = goalOID;

		// Properties
		setMargin(true);
		setWidth("1000px");
		setSpacing(true);

		acTreetable.setSelectable(true);
		mcTreetable.setSelectable(true);

		footer.setSpacing(true);

		acTreetable.addActionHandler(new Action.Handler() {
			@Override
			public void handleAction(Action action, Object sender, Object target) {
				if (action == DISABLE_CONDITION_ACTION) {
					String ConditionOID = (String) target;
					activateConditionsOID.remove(ConditionOID);
					acTreetable.removeItem(ConditionOID);
				}
			}

			@Override
			public Action[] getActions(Object target, Object sender) {
				if (!acTreetable.areChildrenAllowed(target)) {
					return new Action[] { DISABLE_CONDITION_ACTION };
				} else {
					return new Action[] {};
				}
			}
		});

		mcTreetable.addActionHandler(new Action.Handler() {
			@Override
			public void handleAction(Action action, Object sender, Object target) {
				if (action == DISABLE_CONDITION_ACTION) {
					String ConditionOID = (String) target;
					maitainGoalsOID.remove(ConditionOID);
					mcTreetable.removeItem(ConditionOID);
				}
			}

			@Override
			public Action[] getActions(Object target, Object sender) {
				if (!mcTreetable.areChildrenAllowed(target)) {
					return new Action[] { DISABLE_CONDITION_ACTION };
				} else {
					return new Action[] {};
				}
			}
		});

		Button submitButton = new Button("Finish");
		submitButton.addListener(new ClickListener() {
			@Atomic(mode = TxMode.WRITE)
			@Override
			public void buttonClick(ClickEvent event) {
				// FIXME: new parameters from previous screen
				BlendedWorkflow
						.getInstance()
						.getWorkListManager()
						.createGoalInstance(bwInstanceOID, _goalOID,
								activateConditionsOID, maitainGoalsOID,
								entitiesOID);
				getApplication().getMainWindow().removeWindow(
						ManageAchieveGoalsConditionsForm.this.getWindow());
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

	@Atomic(mode = TxMode.WRITE)
	public void getGoalConditions() {
		BWInstance bwInstance = FenixFramework.getDomainObject(_bwInstanceOID);
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();

		AchieveGoal newGoal = FenixFramework.getDomainObject(_goalOID);
		goalNameLabel.setCaption(newGoal.getName());

		for (Condition activateCondition : newGoal.getActivateConditionsSet()) {
			String ID = activateCondition.getExternalId();
			String caption = activateCondition.toString();

			acTreetable.addItem(ID);
			acTreetable.setItemCaption(ID, caption);
			acTreetable.setChildrenAllowed(ID, false);
			activateConditionsOID.add(ID);
		}

		for (MaintainGoal mg : goalModelInstance
				.getAchieveGoalAssociatedMaintainGoals(newGoal)) {
			String ID = mg.getExternalId();
			String caption = mg.getMaintainCondition().toString();

			mcTreetable.addItem(ID);
			mcTreetable.setItemCaption(ID, caption);
			mcTreetable.setChildrenAllowed(ID, false);

			this.maitainGoalsOID.add(ID);
		}
	}
}
