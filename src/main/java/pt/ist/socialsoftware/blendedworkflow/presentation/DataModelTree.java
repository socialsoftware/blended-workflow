package pt.ist.socialsoftware.blendedworkflow.presentation;

import jvstm.Transaction;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.event.Action;

@SuppressWarnings("serial")
public class DataModelTree extends VerticalLayout {

	protected final Tree treetable = new Tree("Data Model");

	private NewGoalForm parentWindow;
	private String dataRelation;

	private static final Action ADD_ENTITY_ACTION = new Action("Add Entity");
	private static final Action ADD_ATTRIBUTE_ACTION = new Action("Add Attribute");
	private static final Action ADD_RELATION_ACTION = new Action("Add Relation");

	public DataModelTree(final NewGoalForm parent, final long bwInstanceOID, final String relation) {
		parentWindow = parent;
		dataRelation = relation;
		HorizontalLayout footer = new HorizontalLayout();

		// Properties
		setMargin(true);
		setWidth("400px");
		setSpacing(true);

		treetable.setWidth("100%");
		treetable.setSelectable(true);

		footer.setSpacing(true);

		treetable.addActionHandler(new Action.Handler() {
			public void handleAction(Action action, Object sender, Object target) {
				if (action == ADD_ATTRIBUTE_ACTION) {
					String entityName = treetable.getItemCaption(target).toString();
					getApplication().getMainWindow().showNotification(entityName);
					showNewAttributeWindow(bwInstanceOID, entityName);
				} else if (action == ADD_ENTITY_ACTION) {
					showNewEntityWindow(bwInstanceOID);
				} 
				else if (action == ADD_RELATION_ACTION) {
					String entityName = treetable.getItemCaption(target).toString();
					showNewRelationWindow(bwInstanceOID, entityName);
				} 
			}

			public Action[] getActions(Object target, Object sender) {
				if (treetable.areChildrenAllowed(target)) {
					return new Action[]{ADD_ENTITY_ACTION, ADD_ATTRIBUTE_ACTION, ADD_RELATION_ACTION };
				} else {
					return new Action[] {};
				}
			}
		});

		Button submitButton = new Button("Select");
		submitButton.addListener(new ClickListener() {
//			Object item = treetable.addItem(new Object[] { "New Attribute: with Parent" + entity}, null);
//			treetable.setChildrenAllowed(item, false);
//			treetable.setParent(item, target);
			@Override
			public void buttonClick(ClickEvent event) {
				if (treetable.hasChildren(treetable.getValue())) {
					String entity = treetable.getValue().toString();
					showConstrainsWindow(entity, "Entity");
				}
				else {
					String attribute = treetable.getValue().toString();
					String entity = treetable.getParent(treetable.getValue()).toString();
					String data = entity +"."+attribute;
					showConstrainsWindow(data, "Attribute");
				}
			}
		});

		Button cancelButton = new Button("Cancel");
		cancelButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(DataModelTree.this.getWindow());
			}
		});

		addComponent(treetable);
		footer.addComponent(submitButton);
		footer.addComponent(cancelButton);
		addComponent(footer);
		setComponentAlignment(footer, Alignment.BOTTOM_CENTER);

		// Populate
		getDataModel(bwInstanceOID);
	}

	public void getDataModel(long bwInstanceOID) {
		Transaction.begin();
		BWInstance bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		for (Entity entity : dataModelInstance.getEntities()) {
			String entityName = entity.getName();
			treetable.addItem(entityName);
			for (Attribute attribute : entity.getAttributes()) {
				String attributeName = attribute.getName();
				treetable.addItem(attributeName);
				treetable.setParent(attributeName,entityName);
				treetable.setChildrenAllowed(attributeName, false);
			}
			treetable.expandItemsRecursively(entityName);
		}
		Transaction.commit();
	}

	protected void showNewEntityWindow(long bwInstanceOID) {
		Window newEntity = new Window("New Entity");
		newEntity.setContent(new NewEntityForm(this, bwInstanceOID));
		newEntity.center();
		getApplication().getMainWindow().addWindow(newEntity);
	}

	protected void showNewAttributeWindow(long bwInstanceOID, String entityName) {
		Window newAttribute = new Window("New Attribute");
		newAttribute.setContent(new NewAttributeForm(this, bwInstanceOID, entityName));
		newAttribute.center();
		getApplication().getMainWindow().addWindow(newAttribute);
	}

	protected void showNewRelationWindow(long bwInstanceOID, String entityName) {
		Window newRelation= new Window("New Relation");
		newRelation.setContent(new NewRelationForm(this, bwInstanceOID, entityName));
		newRelation.center();
		getApplication().getMainWindow().addWindow(newRelation);
	}

	protected void showConstrainsWindow(String data, String dataType) {
		Window newConstrain= new Window("Data Constrain");
		newConstrain.setContent(new ConstrainsForm(this, data, dataType));
		newConstrain.center();
		getApplication().getMainWindow().addWindow(newConstrain);
	}

	public void refreshTree(long bwInstanceOID) {
		treetable.removeAllItems();
		getDataModel(bwInstanceOID);
	}

	public void finalize(String data, String dataType, Boolean isNot, String constrain) {
		this.parentWindow.addDataToTable(this.dataRelation, data, dataType, constrain, isNot);
	}

}
