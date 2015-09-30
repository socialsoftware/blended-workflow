package pt.ist.socialsoftware.blendedworkflow.presentation;

import com.vaadin.event.Action;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;

@SuppressWarnings("serial")
public class AllDataModelTree extends VerticalLayout {

    protected final Tree treetable = new Tree("Data Model");
    private NewGoalForm parentWindow;

    private static final Action ADD_ENTITY_ACTION = new Action("Add Entity");
    private static final Action ADD_ATTRIBUTE_ACTION = new Action(
            "Add Attribute");
    private static final Action ADD_RELATION_ACTION = new Action(
            "Add Relation");

    public AllDataModelTree(final NewGoalForm parent,
            final String bwInstanceOID) {
        parentWindow = parent;
        HorizontalLayout footer = new HorizontalLayout();

        // Properties
        setMargin(true);
        setWidth("400px");
        setSpacing(true);

        treetable.setWidth("100%");
        treetable.setSelectable(true);

        footer.setSpacing(true);

        treetable.addActionHandler(new Action.Handler() {
            @Override
            public void handleAction(Action action, Object sender,
                    Object target) {
                if (action == ADD_ATTRIBUTE_ACTION) {
                    String entityName = treetable.getItemCaption(target)
                            .toString();
                    showNewAttributeWindow(bwInstanceOID, entityName);
                } else if (action == ADD_ENTITY_ACTION) {
                    showNewEntityWindow(bwInstanceOID);
                } else if (action == ADD_RELATION_ACTION) {
                    String entityName = treetable.getItemCaption(target)
                            .toString();
                    showNewRelationWindow(bwInstanceOID, entityName);
                }
            }

            @Override
            public Action[] getActions(Object target, Object sender) {
                if (treetable.areChildrenAllowed(target)) {
                    return new Action[] { ADD_ENTITY_ACTION,
                            ADD_ATTRIBUTE_ACTION, ADD_RELATION_ACTION };
                } else {
                    return new Action[] {};
                }
            }
        });

        Button submitButton = new Button("Select");
        submitButton.addListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                upadateEntities();
                parent.setContext((String) treetable.getValue());
                getApplication().getMainWindow()
                        .removeWindow(AllDataModelTree.this.getWindow());
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.addListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                upadateEntities();
                getApplication().getMainWindow()
                        .removeWindow(AllDataModelTree.this.getWindow());
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

    @Atomic(mode = TxMode.WRITE)
    public void getDataModel(String bwInstanceOID) {
        BWInstance bwInstance = FenixFramework.getDomainObject(bwInstanceOID);
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
        for (Entity entity : dataModelInstance.getEntitySet()) {
            String entityName = entity.getName();
            String entityOID = entity.getExternalId();
            treetable.addItem(entityOID);
            treetable.setItemCaption(entityOID, entityName);
            for (AttributeBasic attribute : entity.getAttributeBasicSet()) {
                String attributeName = attribute.getName();
                String attributeOID = attribute.getExternalId();
                treetable.addItem(attributeOID);
                treetable.setParent(attributeOID, entityOID);
                treetable.setItemCaption(attributeOID, attributeName);
                treetable.setChildrenAllowed(attributeOID, false);
            }
        }
    }

    protected void showNewEntityWindow(String bwInstanceOID) {
        Window newEntity = new Window("New Entity");
        newEntity.setContent(new NewEntityForm(this, bwInstanceOID));
        newEntity.center();
        getApplication().getMainWindow().addWindow(newEntity);
    }

    protected void showNewAttributeWindow(String bwInstanceOID,
            String entityName) {
        Window newAttribute = new Window("New Attribute");
        newAttribute.setContent(
                new NewAttributeForm(this, bwInstanceOID, entityName));
        newAttribute.center();
        getApplication().getMainWindow().addWindow(newAttribute);
    }

    protected void showNewRelationWindow(String bwInstanceOID,
            String entityName) {
        Window newRelation = new Window("New Relation");
        newRelation.setContent(
                new NewRelationForm(this, bwInstanceOID, entityName));
        newRelation.center();
        getApplication().getMainWindow().addWindow(newRelation);
    }

    public void refreshTree(String bwInstanceOID) {
        treetable.removeAllItems();
        getDataModel(bwInstanceOID);
    }

    public void upadateEntities() {
        this.parentWindow.getGoals();
    }

}
