package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.util.ArrayList;
import java.util.Iterator;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import jvstm.Transaction;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class NewGoalForm extends VerticalLayout implements Property.ValueChangeListener {

	private NativeSelect bwInstances = new NativeSelect("BWInstances");
	private TextField name = new TextField("Goal Name");
	private NativeSelect parentGoal = new NativeSelect("Parent Goal");
	private TextField description = new TextField("Description");
	private NativeSelect conditionType = new NativeSelect("Condition Type");
	private NativeSelect relationType = new NativeSelect("Relation Type");
	private NativeSelect entityContext = new NativeSelect("Entity Context");
	private Table requiredData = new Table("Create Condition:");
	private Table conditionsTable= new Table("Conditions Created");
	
	private String sucessCondition = "";
	private ArrayList<String> activateCondition = new ArrayList<String>();

	public NewGoalForm() {

		
		HorizontalLayout dataHL = new HorizontalLayout();
		VerticalLayout dataVL = new VerticalLayout();
		HorizontalLayout submitPanel = new HorizontalLayout();

		// Properties
		setMargin(true);
		setSpacing(false);
		setHeight("600px");
		setWidth("570px");

		dataHL.setSpacing(true);
		submitPanel.setSpacing(true);

		bwInstances.setNullSelectionAllowed(false);
		bwInstances.setImmediate(true);
		bwInstances.addListener(this);

		parentGoal.setNullSelectionAllowed(false);
		parentGoal.setEnabled(true);
		
		conditionType.setNullSelectionAllowed(false);
		conditionType.setEnabled(true);
		
		conditionType.addItem("Activate");
		conditionType.addItem("Success");
		conditionType.setEnabled(true);
		
		relationType.setNullSelectionAllowed(false);
		relationType.setImmediate(true);
		relationType.addItem("and");
		relationType.addItem("or");
		relationType.setEnabled(false);

		requiredData.setWidth("400px");
		requiredData.setHeight("130px");
		requiredData.addContainerProperty("Relation", String.class,  null);
		requiredData.addContainerProperty("Data",  String.class,  null);
		requiredData.addContainerProperty("Type",  String.class,  null);
		requiredData.addContainerProperty("Constrain",  String.class,  null);
		requiredData.addContainerProperty("Not?",  Boolean.class,  null);
		
		conditionsTable.setWidth("400px");
		conditionsTable.setHeight("130px");
		conditionsTable.addContainerProperty("Type", String.class,  null);
		conditionsTable.addContainerProperty("Condition",  String.class,  null);

		Button addData = new Button("Add Data");
		addData.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					long bwInstanceOID = (Long) bwInstances.getValue();
					if (!relationType.isEnabled()) {
						showDataModelTreeWindow(bwInstanceOID, "");
					}
					else {
						String relation = (String) relationType.getValue();
						showDataModelTreeWindow(bwInstanceOID, relation);
					}
				} catch (java.lang.NullPointerException jle) {
					getApplication().getMainWindow().showNotification("Please select a BW Instance");
				}

			}
		});
		
		Button finishCondition = new Button("Finish Condition");
		finishCondition.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				String type = (String) conditionType.getValue();
				if (type.equals("Success")) {
					sucessCondition = createCondition();
					addDataToConditionTable("Sucess", sucessCondition);
				} else {
					String newActivateCondition = createCondition();
					activateCondition.add(createCondition());
					addDataToConditionTable("Activate", newActivateCondition);
				}
				requiredData.removeAllItems();
			}
		});

		Button submit = new Button("Submit");
		submit.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					long bwInstanceOID = (Long) bwInstances.getValue();
					String goalName = (String) name.getValue();
					String goalDescription = (String) description.getValue();
					long parentGoalID = (Long) parentGoal.getValue();
					long entityOID = (Long) entityContext.getValue();
					String activeUserID = "";
					if (sucessCondition == "" || activateCondition.size() == 0) {
						getApplication().getMainWindow().showNotification("Please create at least one Activate and one Sucess Conditions.");
					} else {
						Transaction.begin();
						activeUserID = BlendedWorkflow.getInstance().getOrganizationalManager().getActiveUser().getID();
						BlendedWorkflow.getInstance().getWorkListManager().createGoal(bwInstanceOID, goalName, goalDescription, parentGoalID,  sucessCondition, activateCondition, entityOID, activeUserID); 
						Transaction.commit();
						getApplication().getMainWindow().showNotification("Goal created successfully", Notification.TYPE_TRAY_NOTIFICATION);
						getApplication().getMainWindow().removeWindow(NewGoalForm.this.getWindow());
					}
				} catch (java.lang.NullPointerException jle) {
					getApplication().getMainWindow().showNotification("Please fill all the fields");
				} 

			}
		});

		Button cancel = new Button("Cancel");
		cancel.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(NewGoalForm.this.getWindow());
			}
		});

		// Layout
		dataVL.addComponent(conditionType);
		dataVL.addComponent(relationType);
		dataVL.addComponent(addData);
		dataVL.addComponent(finishCondition);
		
		dataHL.addComponent(requiredData);
		dataHL.addComponent(dataVL);
		
		addComponent(bwInstances);
		addComponent(name);
		addComponent(description);
		addComponent(parentGoal);
		addComponent(entityContext);
		addComponent(dataHL);
		addComponent(conditionsTable);

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
		this.parentGoal.removeAllItems();
		this.entityContext.removeAllItems();
		
		Transaction.begin();
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		for (AchieveGoal goal : goalModelInstance.getAchieveGoals()) {
			this.parentGoal.addItem(goal.getOID());
			this.parentGoal.setItemCaption(goal.getOID(), goal.getName());
		}
		
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		for (Entity entity : dataModelInstance.getEntities()) {
			this.entityContext.addItem(entity.getOID());
			this.entityContext.setItemCaption(entity.getOID(), entity.getName());
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

	public String createCondition() {
		String condition = "";

		String isNot ="";
		String data ="";
		String dataType ="";
		String constrain ="";
		String relation ="";

		for (Iterator<?> i = requiredData.getItemIds().iterator(); i.hasNext();) {
			int iid = (Integer) i.next();
			Item item = requiredData.getItem(iid);

			relation = item.getItemProperty("Relation").getValue().toString();
			data = item.getItemProperty("Data").getValue().toString();
			dataType = item.getItemProperty("Type").getValue().toString();
			constrain = item.getItemProperty("Constrain").getValue().toString();
			isNot = item.getItemProperty("Not?").getValue().toString();

			// RelationType
			if (!relation.equals("")) {
				condition += " " + relation + " ";
			}

			// Condition Type
			if (constrain.equals("")) {
				condition += "exists" + dataType + "(" + data + ")";
			}
			else {
				condition += "compare" + dataType + "To("+ data + "," + constrain + ")";
			}

			// NOT?
			if (isNot.equals("true")) {
				condition += ".not()";
			}
		}
		if (condition == "") {
			condition = "true";
		}
		return condition;
	}

	// Add data item
	public void addDataToTable(String relation, String data, String type, String constrain, Boolean isNot) {
		if (!relationType.isEnabled()) {
			relationType.setEnabled(true);
			relationType.setValue("and");
		}
		int index = requiredData.getItemIds().size();
		requiredData.addItem(new Object[] {relation, data, type, constrain, isNot}, new Integer(index+1));
	}
	
	public void addDataToConditionTable(String conditionType, String condition) {
		int index = conditionsTable.getItemIds().size();
		conditionsTable.addItem(new Object[] {conditionType, condition}, new Integer(index+1));
	}

	// generate dataModelWindow
	protected void showDataModelTreeWindow(long bwInstanceOID, String relation) {
		Window dataModel = new Window("Choose a data element");
		dataModel.setContent(new DataModelTree(this, bwInstanceOID, relation));
		dataModel.center();
		dataModel.setClosable(false);
		dataModel.setResizable(false);
		getApplication().getMainWindow().addWindow(dataModel);
	}
}
