package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.util.HashMap;
import java.util.Map;

import jvstm.Transaction;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class ActivateGoalForm extends VerticalLayout {

	// Variables
	private final long _bwInstanceOID;
	private final long _goalOID;
	private final HashMap<Long, Long> _entities = new HashMap<Long, Long>();
	private static Logger log = Logger.getLogger("????????????");

	// Interface
	private static final long NEW_DATA = -1;
	private static final String NEW_DATA_CAPTION = "New Data...";

	private final NativeSelect entityInstanceContext = new NativeSelect(
			"Select the Goal Context:");
	private final CheckBox conditions = new CheckBox("Disable Conditions?");
	private final VerticalLayout keyRelationsVL = new VerticalLayout();
	private final VerticalLayout subGoalContextVL = new VerticalLayout();
	private final HorizontalLayout submitPanel = new HorizontalLayout();

	public ActivateGoalForm(long bwInstanceOID, long goalOID) {
		_bwInstanceOID = bwInstanceOID;
		_goalOID = goalOID;

		// Properties
		setMargin(true);
		setSpacing(false);
		setHeight("150px");
		setWidth("220px");
		submitPanel.setSpacing(true);
		entityInstanceContext.setNullSelectionAllowed(false);
		entityInstanceContext.setImmediate(true);
		entityInstanceContext.addItem(NEW_DATA);
		entityInstanceContext.setItemCaption(NEW_DATA, NEW_DATA_CAPTION);

		entityInstanceContext.addListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (entityInstanceContext.getValue().equals(NEW_DATA)) {
					getKeyEntities();
				} else {
					keyRelationsVL.removeAllComponents();
				}
			}
		});

		Button submit = new Button("Activate");
		submit.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Boolean activate = true;

				// Get Goal Context
				Long entityInstanceOID = (Long) entityInstanceContext
						.getValue();
				Transaction.begin();
				if (entityInstanceOID == -1) {
					AchieveGoal goal = FenixFramework.getDomainObject(_goalOID);
					_entities.put(goal.getEntityContext().getOID(), null);
				} else {
					EntityInstance entityInstance = FenixFramework
							.getDomainObject(entityInstanceOID);
					Long entityOID = entityInstance.getEntity().getOid();
					_entities.put(entityOID, entityInstanceOID);
				}
				Transaction.commit();

				// Get Keys
				for (int i = 0; i < keyRelationsVL.getComponentCount(); i++) {
					NativeSelect selec = (NativeSelect) keyRelationsVL
							.getComponent(i);
					if (selec.getValue() == null) {
						activate = false;
					}
					long keyEntityInstanceOID = (Long) selec.getValue();

					Transaction.begin();
					EntityInstance keyEntityInstance = FenixFramework
							.getDomainObject(keyEntityInstanceOID);
					Long keyEntityOID = keyEntityInstance.getEntity().getOid();
					_entities.put(keyEntityOID, keyEntityInstanceOID);
					Transaction.commit();
				}

				// Get SubGoals Context
				for (int i = 0; i < subGoalContextVL.getComponentCount(); i++) {
					NativeSelect selec = (NativeSelect) subGoalContextVL
							.getComponent(i);
					long subEntityInstanceOID = (Long) selec.getValue();

					Transaction.begin();
					BWInstance bwInstance = FenixFramework
							.getDomainObject(_bwInstanceOID);
					DataModelInstance dataModelInstance = bwInstance
							.getDataModelInstance();
					log.info("|" + selec.getCaption() + "|");
					Entity subGoalEntity = dataModelInstance.getEntity(selec
							.getCaption());
					if (subEntityInstanceOID == -1) {
						_entities.put(subGoalEntity.getOID(), null);
					} else {
						EntityInstance subEntityInstance = FenixFramework
								.getDomainObject(subEntityInstanceOID);
						Long subEntityOID = subEntityInstance.getEntity()
								.getOid();
						_entities.put(subEntityOID, subEntityInstanceOID);
					}

					Transaction.commit();
				}

				for (Map.Entry<Long, Long> entry : _entities.entrySet()) {
					log.debug("E:" + entry.getKey() + " EI:" + entry.getValue());
				}

				// Show Disable Form depending on its checkbox value
				if (activate) {
					if (conditions.getValue().equals(true)) {
						showDisableConditionsWindow(_bwInstanceOID, _goalOID,
								_entities);
					} else {
						Transaction.begin();
						BlendedWorkflow
								.getInstance()
								.getWorkListManager()
								.createGoalInstance(_bwInstanceOID, _goalOID,
										null, null, _entities);
						Transaction.commit();
					}
					getApplication().getMainWindow().removeWindow(
							ActivateGoalForm.this.getWindow());
				} else {
					getApplication()
							.getMainWindow()
							.showNotification(
									"The Goal cannot be Activated due to missing key data!",
									Notification.TYPE_ERROR_MESSAGE); // "[GOAL] "
																		// +
				}

			}
		});

		Button cancel = new Button("Cancel");
		cancel.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(
						ActivateGoalForm.this.getWindow());
			}
		});

		// Layout
		addComponent(entityInstanceContext);
		addComponent(conditions);
		addComponent(keyRelationsVL);
		addComponent(subGoalContextVL);
		submitPanel.addComponent(submit);
		submitPanel.addComponent(cancel);
		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.BOTTOM_CENTER);

		// Populate
		getEntityInstances();
		getSubGoalsEntities();
	}

	/******************************
	 * Support Methods
	 ******************************/
	private void getEntityInstances() {
		Transaction.begin();

		BWInstance bwInstance = FenixFramework.getDomainObject(_bwInstanceOID);
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		AchieveGoal goal = FenixFramework.getDomainObject(_goalOID);
		Entity goalContext = goal.getEntityContext();

		for (Entity entity : dataModelInstance.getEntities()) {
			if (entity.equals(goalContext)) {
				for (EntityInstance entityInstance : entity
						.getEntityInstances()) {
					this.entityInstanceContext.addItem(entityInstance.getOID());
					this.entityInstanceContext.setItemCaption(
							entityInstance.getOID(), entityInstance.getID());
				}
			}
		}

		Transaction.commit();
	}

	private void getKeyEntities() {
		Transaction.begin();

		AchieveGoal goal = FenixFramework.getDomainObject(_goalOID);
		Entity goalContext = goal.getEntityContext();
		for (Relation relation : goalContext.getRelations()) {
			Entity one = relation.getEntityOne();
			Entity two = relation.getEntityTwo();
			if (goalContext.equals(one) && relation.getIsTwoKeyEntity()) {
				addNativeSelect(keyRelationsVL, two);
			}
			if (goalContext.equals(two) && relation.getIsOneKeyEntity()) {
				addNativeSelect(keyRelationsVL, one);
			}
		}

		if (keyRelationsVL.getComponentCount() > 0) {
			setHeight("250px");
		}

		Transaction.commit();
	}

	private void getSubGoalsEntities() {
		Transaction.begin();

		AchieveGoal goal = FenixFramework.getDomainObject(_goalOID);
		for (Entity entity : goal.getSubGoalsContext()) {
			addNativeSelect(subGoalContextVL, entity);
			_entities.put(entity.getOID(), null);
		}

		if (subGoalContextVL.getComponentCount() > 0) {
			setHeight("250px");
		}

		Transaction.commit();
	}

	protected void addNativeSelect(Layout l, Entity entity) {
		NativeSelect ns = new NativeSelect(entity.getName());
		ns.setImmediate(true);
		ns.addStyleName("h2");
		if (!l.equals(keyRelationsVL)) {
			ns.setNullSelectionAllowed(false);
			ns.addItem(NEW_DATA);
			ns.setItemCaption(NEW_DATA, NEW_DATA_CAPTION);
		}
		l.addComponent(ns);

		for (EntityInstance entityInstance : entity.getEntityInstances()) {
			ns.addItem(entityInstance.getOID());
			ns.setItemCaption(entityInstance.getOID(), entityInstance.getID());
		}
	}

	protected void showDisableConditionsWindow(long bwInstanceOID,
			long goalOID, HashMap<Long, Long> entitiesOID) {
		Window dataModel = new Window("Disable Conditions Form");
		dataModel.setContent(new ManageAchieveGoalsConditionsForm(this,
				bwInstanceOID, goalOID, entitiesOID));
		dataModel.center();
		dataModel.setClosable(false);
		dataModel.setResizable(false);
		getApplication().getMainWindow().addWindow(dataModel);
	}

}
