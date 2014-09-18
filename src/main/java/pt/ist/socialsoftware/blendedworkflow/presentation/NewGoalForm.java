package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.util.ArrayList;
import java.util.Iterator;

import jvstm.Transaction;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;

import com.vaadin.data.Item;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class NewGoalForm extends VerticalLayout {

	private final TextField name = new TextField("Goal Name");
	private final TextField description = new TextField("Description");
	private final NativeSelect conditionType = new NativeSelect(
			"Condition Type");
	private final NativeSelect relationType = new NativeSelect("Joiner Type");
	private final NativeSelect entityContext = new NativeSelect(
			"Entity Context");
	private final Table requiredData = new Table("Create Condition:");
	private final Table conditionsTable = new Table("Conditions Created");

	private String sucessCondition = "";
	private final ArrayList<String> activateCondition = new ArrayList<String>();

	private final long bwInstanceOID;

	public NewGoalForm(final long bwInstance, final long parentGoalOID) {

		HorizontalLayout dataHL = new HorizontalLayout();
		VerticalLayout dataVL = new VerticalLayout();
		HorizontalLayout submitPanel = new HorizontalLayout();

		HorizontalLayout contextHL = new HorizontalLayout();

		bwInstanceOID = bwInstance;

		// Properties
		setMargin(true);
		setSpacing(false);
		setHeight("500px");
		setWidth("570px");

		contextHL.setSpacing(true);

		dataHL.setSpacing(true);
		submitPanel.setSpacing(true);

		conditionType.setNullSelectionAllowed(false);
		conditionType.setEnabled(true);

		conditionType.addItem("Activate");
		conditionType.addItem("Success");
		conditionType.setEnabled(true);

		relationType.setNullSelectionAllowed(true);
		relationType.setImmediate(true);
		relationType.addItem("and");
		relationType.addItem("or");
		relationType.setEnabled(false);

		requiredData.setWidth("400px");
		requiredData.setHeight("130px");
		requiredData.addContainerProperty("Joiner", String.class, null);
		requiredData.addContainerProperty("Condition", String.class, null);

		conditionsTable.setWidth("400px");
		conditionsTable.setHeight("130px");
		conditionsTable.addContainerProperty("Type", String.class, null);
		conditionsTable.addContainerProperty("Condition", String.class, null);

		Button addData = new Button("Add Data");
		addData.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Long entityContextOID = (Long) entityContext.getValue();
				if (entityContext == null) {
					getApplication().getMainWindow().showNotification(
							"Please select a Context!");
				} else {
					String relation = (String) relationType.getValue();

					if (relation == null) {
						relation = "";
					}
					showDataModelTreeWindow(bwInstanceOID, entityContextOID,
							relation);
				}
			}
		});

		Button finishCondition = new Button("Finish Condition");
		finishCondition.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				String type = (String) conditionType.getValue();
				if (type == null) {
					getApplication().getMainWindow().showNotification(
							"Please select a condition type!");
				} else {

					if (type.equals("Success")) {
						sucessCondition = createCondition();
						addDataToConditionTable("Sucess", sucessCondition);
					} else {
						String newActivateCondition = createCondition();
						activateCondition.add(createCondition());
						addDataToConditionTable("Activate",
								newActivateCondition);
					}
					requiredData.removeAllItems();
				}
			}
		});

		Button submit = new Button("Submit");
		submit.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					String goalName = (String) name.getValue();
					String goalDescription = (String) description.getValue();
					long entityOID = (Long) entityContext.getValue();
					String activeUserID = "";
					if (sucessCondition.equals("")
							|| activateCondition.size() == 0) {
						getApplication()
								.getMainWindow()
								.showNotification(
										"Please create at least one Activate and one Sucess Conditions.");
					} else {
						Transaction.begin();
						activeUserID = BlendedWorkflow.getInstance()
								.getOrganizationalManager().getActiveUser()
								.getID();
						BlendedWorkflow
								.getInstance()
								.getWorkListManager()
								.createGoal(bwInstanceOID, goalName,
										goalDescription, parentGoalOID,
										sucessCondition, activateCondition,
										entityOID, activeUserID);
						Transaction.commit();
						getApplication().getMainWindow().removeWindow(
								NewGoalForm.this.getWindow());
					}
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
						NewGoalForm.this.getWindow());
			}
		});

		Button addContext = new Button("...");
		addContext.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				showAllDataModelTreeWindow(bwInstanceOID);
			}
		});
		addContext.setHeight("30px");
		addContext.setWidth("40px");

		// Layout
		dataVL.addComponent(conditionType);
		dataVL.addComponent(relationType);
		dataVL.addComponent(addData);
		dataVL.addComponent(finishCondition);

		dataHL.addComponent(requiredData);
		dataHL.addComponent(dataVL);

		contextHL.addComponent(entityContext);
		contextHL.addComponent(addContext);
		contextHL.setComponentAlignment(addContext, Alignment.BOTTOM_CENTER);

		addComponent(name);
		addComponent(description);

		addComponent(contextHL);
		addComponent(dataHL);
		addComponent(conditionsTable);

		submitPanel.addComponent(submit);
		submitPanel.addComponent(cancel);
		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.BOTTOM_CENTER);

		// Populate
		getGoals();
	}

	public void getGoals() {
		BWInstance bwInstance = FenixFramework.getDomainObject(bwInstanceOID);
		Transaction.begin();

		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		for (Entity entity : dataModelInstance.getEntities()) {
			this.entityContext.addItem(entity.getOID());
			this.entityContext
					.setItemCaption(entity.getOID(), entity.getName());
		}
		Transaction.commit();
	}

	public String createCondition() {
		String condition = "";
		String relation = "";
		String subCondition = "";

		for (Iterator<?> i = requiredData.getItemIds().iterator(); i.hasNext();) {
			int iid = (Integer) i.next();
			Item item = requiredData.getItem(iid);

			relation = item.getItemProperty("Joiner").getValue().toString();
			subCondition = item.getItemProperty("Condition").getValue()
					.toString();

			// RelationType
			if (!relation.equals("")) {
				condition += " " + relation + " ";
			}

			condition += subCondition;
		}

		if (condition.equals("")) {
			condition = "true";
		}
		return condition;
	}

	// Add data item
	public void addDataToTable(String conditionJoiner, String condition) {
		if (!relationType.isEnabled()) {
			relationType.setEnabled(true);
			relationType.setValue("and");
		}
		int index = requiredData.getItemIds().size();
		requiredData.addItem(new Object[] { conditionJoiner, condition },
				new Integer(index + 1));
	}

	public void addDataToConditionTable(String conditionType, String condition) {
		int index = conditionsTable.getItemIds().size();
		conditionsTable.addItem(new Object[] { conditionType, condition },
				new Integer(index + 1));
	}

	// generate dataModelWindow
	protected void showDataModelTreeWindow(long bwInstanceOID, long contextOID,
			String relation) {
		Window dataModel = new Window("Choose a data element");
		dataModel.setContent(new DataModelTree(this, bwInstanceOID, contextOID,
				relation));
		dataModel.center();
		dataModel.setClosable(false);
		dataModel.setResizable(false);
		getApplication().getMainWindow().addWindow(dataModel);
	}

	// generate alldataModelWindow
	protected void showAllDataModelTreeWindow(long bwInstanceOID) {
		Window dataModel = new Window("Choose a data element");
		dataModel.setContent(new AllDataModelTree(this, bwInstanceOID));
		dataModel.center();
		dataModel.setClosable(false);
		dataModel.setResizable(false);
		getApplication().getMainWindow().addWindow(dataModel);
	}

	public void setContext(long OID) {
		entityContext.select(OID);
	}
}
