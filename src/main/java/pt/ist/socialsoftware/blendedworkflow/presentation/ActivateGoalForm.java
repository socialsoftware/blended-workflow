package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.util.ArrayList;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
import jvstm.Transaction;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ActivateGoalForm extends VerticalLayout {

	private static final Long NEW_DATA = (long) -1;
	private static final String NEW_DATA_Caption = "New Data...";
	private NativeSelect entityInstanceContext = new NativeSelect("Select the Goal Context:");
	private CheckBox conditions = new CheckBox("Disable Conditions?");
	
	private VerticalLayout relationsVL = new VerticalLayout();

	public ActivateGoalForm(final long bwInstanceOID, final long goalOID) {
		HorizontalLayout submitPanel = new HorizontalLayout();

		// Properties
		setMargin(true);
		setSpacing(false);
		setHeight("150px");
		setWidth("220px");

		submitPanel.setSpacing(true);

		Button submit = new Button("Activate");
		submit.addListener(new Button.ClickListener() {
			//NOTE: Goals cannot be activated if some of its key entities are not created
			@Override
			public void buttonClick(ClickEvent event) {
				Long entityInstanceOID = (Long) entityInstanceContext.getValue();
				ArrayList<Long> relationsEntityInstancesOID = new ArrayList<Long>();
				Boolean activate = true;
				
				//Gather Relation EntityInstance
				for (int i = 0; i < relationsVL.getComponentCount(); i++) {
					NativeSelect selec = (NativeSelect) relationsVL.getComponent(i);
					if (selec.getValue() == null) {
						activate = false;
					}
					long e2OID = (Long) selec.getValue();
					relationsEntityInstancesOID.add(e2OID);
				}

				//Show Disable Form depending on its checkbox value
				if (conditions.getValue().equals(true)) {
					if (entityInstanceOID.equals(NEW_DATA)) {
						entityInstanceOID = (long) 0;
					}
					if (activate) {
						showDisableConditionsWindow(bwInstanceOID, goalOID, entityInstanceOID, relationsEntityInstancesOID);
					} else {
						getApplication().getMainWindow().showNotification("The Goal cannot be Activated due to missing key data!", Notification.TYPE_ERROR_MESSAGE); //"[GOAL] " + 
					}
				} else {
					if (entityInstanceOID.equals(NEW_DATA)) {
						entityInstanceOID = null;
					}
					if (activate) {
						Transaction.begin();
						BlendedWorkflow.getInstance().getWorkListManager().createGoalInstance(bwInstanceOID, goalOID, entityInstanceOID, null, null, relationsEntityInstancesOID);
						Transaction.commit();
					} else {
						getApplication().getMainWindow().showNotification("The Goal cannot be Activated due to missing key data!", Notification.TYPE_ERROR_MESSAGE); //"[GOAL] " + 
					}
				}
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
		addComponent(conditions);
		addComponent(relationsVL);
		
		submitPanel.addComponent(submit);
		submitPanel.addComponent(cancel);
		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.BOTTOM_CENTER);

		// Populate
		updateEntityInstancesInfo(bwInstanceOID, goalOID);
		
		entityInstanceContext.setNullSelectionAllowed(false);
		entityInstanceContext.setImmediate(true);
		entityInstanceContext.addItem(NEW_DATA);
		entityInstanceContext.setItemCaption(NEW_DATA, NEW_DATA_Caption);
		entityInstanceContext.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				if (entityInstanceContext.getValue().equals(NEW_DATA)) {
					getRelations(bwInstanceOID, goalOID);
				} else {
					relationsVL.removeAllComponents();
				}
			}
		});
		
		//TODO:
//		final Label test = new Label("TEST2");
//		conditions.addListener(new Property.ValueChangeListener() {
//			public void valueChange(ValueChangeEvent event) {
//				if ((Boolean) conditions.getValue()) {
//					addComponent(test);
//					setHeight("250px");
//				} else {
//					removeComponent(test);
//					setHeight("150px");
//				}
//			}
//		});
	}
	
	private void getRelations(long bwInstanceOID, long goalOID) {
		Transaction.begin();
		AchieveGoal goal = AbstractDomainObject.fromOID(goalOID);
		Entity goalContext = goal.getEntityContext();
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
		
		if (relationsVL.getComponentCount() > 0) {
			setHeight("200px");
		}
		
		Transaction.commit();
	}
	
	protected void addNativeSelect(Entity entity) {
		NativeSelect ns = new NativeSelect(entity.getName());
		relationsVL.addComponent(ns);

		ns.addStyleName("h2");
		for (EntityInstance entityInstance : entity.getEntityInstances()) {
			ns.addItem(entityInstance.getOID());
			ns.setItemCaption(entityInstance.getOID(), entityInstance.getID());
		}
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
		
		//TODO:Relations?
		Transaction.commit();
	}

	protected void showDisableConditionsWindow(long bwInstanceOID, long goalOID, long entityInstanceOID, ArrayList<Long> relations) {
		Window dataModel = new Window("Disable Conditions Form");
		dataModel.setContent(new ManageAchieveGoalsConditionsForm(this, bwInstanceOID, goalOID, entityInstanceOID, relations));
		dataModel.center();
		dataModel.setClosable(false);
		dataModel.setResizable(false);
		getApplication().getMainWindow().addWindow(dataModel);
	}

}
