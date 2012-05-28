package pt.ist.socialsoftware.blendedworkflow.presentation;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import jvstm.Transaction;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ActivateGoalForm extends VerticalLayout {

	private NativeSelect entityInstanceContext = new NativeSelect("Select the Goal Context:");

	public ActivateGoalForm(final long bwInstanceOID, final long goalOID) {
		HorizontalLayout dataHL = new HorizontalLayout();
		VerticalLayout dataVL = new VerticalLayout();
		HorizontalLayout submitPanel = new HorizontalLayout();

		// Properties
		setMargin(true);
		setSpacing(false);
		setHeight("100px");
		setWidth("220px");

		dataHL.setSpacing(true);
		submitPanel.setSpacing(true);

		Button submit = new Button("Activate");
		submit.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Long entityInstance = (Long) entityInstanceContext.getValue();

				Transaction.begin();
				BlendedWorkflow.getInstance().getWorkListManager().createGoalInstance(bwInstanceOID, goalOID, entityInstance);
				Transaction.commit();

				showDisableConditionsWindow(bwInstanceOID);
				getApplication().getMainWindow().removeWindow(ActivateGoalForm.this.getWindow());
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
		addComponent(entityInstanceContext);

		dataHL.addComponent(dataVL);
		addComponent(dataHL);

		submitPanel.addComponent(submit);
		submitPanel.addComponent(cancel);
		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.BOTTOM_CENTER);

		// Populate
		updateEntityInstancesInfo(bwInstanceOID, goalOID);
	}
	
	private void updateEntityInstancesInfo(long bwInstanceOID, long goalOID) {
		Transaction.begin();
		BWInstance bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		AchieveGoal goal = AbstractDomainObject.fromOID(goalOID);
		Entity goalContext = goal.getEntityContext();

		for (Entity entity : dataModelInstance.getEntities()) {
			if (entity.equals(goalContext)) {
				for (EntityInstance entityInstance : entity.getEntityInstances()) {
					this.entityInstanceContext.addItem(entityInstance.getOID());
					this.entityInstanceContext.setItemCaption(entityInstance.getOID(), entityInstance.getID());
				}
			}
		}
		Transaction.commit();
	}

	protected void showDisableConditionsWindow(long bwInstanceOID) {
		Window dataModel = new Window("Disable Conditions Form");
		dataModel.setContent(new ManageAchieveGoalsConditionsForm(this, bwInstanceOID));
		dataModel.center();
		dataModel.setClosable(false);
		dataModel.setResizable(false);
		getApplication().getMainWindow().addWindow(dataModel);
	}

}
