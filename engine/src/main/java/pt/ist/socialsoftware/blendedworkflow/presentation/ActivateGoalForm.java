package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;

@SuppressWarnings("serial")
public class ActivateGoalForm extends VerticalLayout {

    // Variables
    private final String _bwInstanceOID;
    private final String _goalOID;
    private final HashMap<String, String> _entities = new HashMap<String, String>();
    private static Logger log = LoggerFactory.getLogger("????????????");

    // Interface
    private static final String NEW_DATA = "";
    private static final String NEW_DATA_CAPTION = "New Data...";

    private final NativeSelect entityInstanceContext = new NativeSelect(
            "Select the Goal Context:");
    private final CheckBox conditions = new CheckBox("Disable Conditions?");
    private final VerticalLayout keyRelationsVL = new VerticalLayout();
    private final VerticalLayout subGoalContextVL = new VerticalLayout();
    private final HorizontalLayout submitPanel = new HorizontalLayout();

    public ActivateGoalForm(String bwInstanceOID, String goalOID) {
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
            @Atomic(mode = TxMode.WRITE)
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
            @Atomic(mode = TxMode.WRITE)
            @Override
            public void buttonClick(ClickEvent event) {
                Boolean activate = true;

                // Get Goal Context
                String entityInstanceOID = (String) entityInstanceContext
                        .getValue();
                if (entityInstanceOID == "") {
                    Goal goal = FenixFramework.getDomainObject(_goalOID);
                    _entities.put(goal.getEntityContext().getExternalId(),
                            null);
                } else {
                    EntityInstance entityInstance = FenixFramework
                            .getDomainObject(entityInstanceOID);
                    String entityOID = entityInstance.getEntity()
                            .getExternalId();
                    _entities.put(entityOID, entityInstanceOID);
                }

                // Get Keys
                for (int i = 0; i < keyRelationsVL.getComponentCount(); i++) {
                    NativeSelect selec = (NativeSelect) keyRelationsVL
                            .getComponent(i);
                    if (selec.getValue() == null) {
                        activate = false;
                    }
                    String keyEntityInstanceOID = (String) selec.getValue();

                    EntityInstance keyEntityInstance = FenixFramework
                            .getDomainObject(keyEntityInstanceOID);
                    String keyEntityOID = keyEntityInstance.getEntity()
                            .getExternalId();
                    _entities.put(keyEntityOID, keyEntityInstanceOID);
                }

                // Get SubGoals Context
                for (int i = 0; i < subGoalContextVL.getComponentCount(); i++) {
                    NativeSelect selec = (NativeSelect) subGoalContextVL
                            .getComponent(i);
                    String subEntityInstanceOID = (String) selec.getValue();

                    BWInstance bwInstance = FenixFramework
                            .getDomainObject(_bwInstanceOID);
                    DataModelInstance dataModelInstance = bwInstance
                            .getDataModelInstance();
                    log.info("|" + selec.getCaption() + "|");
                    Entity subGoalEntity = dataModelInstance
                            .getEntity(selec.getCaption()).get();
                    if (subEntityInstanceOID == "") {
                        _entities.put(subGoalEntity.getExternalId(), null);
                    } else {
                        EntityInstance subEntityInstance = FenixFramework
                                .getDomainObject(subEntityInstanceOID);
                        String subEntityOID = subEntityInstance.getEntity()
                                .getExternalId();
                        _entities.put(subEntityOID, subEntityInstanceOID);
                    }

                }

                for (Map.Entry<String, String> entry : _entities.entrySet()) {
                    log.debug(
                            "E:" + entry.getKey() + " EI:" + entry.getValue());
                }

                // Show Disable Form depending on its checkbox value
                if (activate) {
                    if (conditions.getValue().equals(true)) {
                        showDisableConditionsWindow(_bwInstanceOID, _goalOID,
                                _entities);
                    } else {
                        BlendedWorkflow.getInstance().getWorkListManager()
                                .createGoalInstance(_bwInstanceOID, _goalOID,
                                        null, null, _entities);
                    }
                    getApplication().getMainWindow()
                            .removeWindow(ActivateGoalForm.this.getWindow());
                } else {
                    getApplication().getMainWindow().showNotification(
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
                getApplication().getMainWindow()
                        .removeWindow(ActivateGoalForm.this.getWindow());
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
    @Atomic(mode = TxMode.WRITE)
    private void getEntityInstances() {

        BWInstance bwInstance = FenixFramework.getDomainObject(_bwInstanceOID);
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
        Goal goal = FenixFramework.getDomainObject(_goalOID);
        Entity goalContext = goal.getEntityContext();

        for (Entity entity : dataModelInstance.getEntitiesSet()) {
            if (entity.equals(goalContext)) {
                for (EntityInstance entityInstance : entity
                        .getEntityInstancesSet()) {
                    this.entityInstanceContext
                            .addItem(entityInstance.getExternalId());
                    this.entityInstanceContext.setItemCaption(
                            entityInstance.getExternalId(),
                            entityInstance.getID());
                }
            }
        }

    }

    private void getKeyEntities() {

        Goal goal = FenixFramework.getDomainObject(_goalOID);
        Entity goalContext = goal.getEntityContext();
        for (RelationBW relation : goalContext.getRelationsSet()) {
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

    }

    private void getSubGoalsEntities() {
        Goal goal = FenixFramework.getDomainObject(_goalOID);
        for (Entity entity : goal.getSubGoalsContext()) {
            addNativeSelect(subGoalContextVL, entity);
            _entities.put(entity.getExternalId(), null);
        }

        if (subGoalContextVL.getComponentCount() > 0) {
            setHeight("250px");
        }
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

        for (EntityInstance entityInstance : entity.getEntityInstancesSet()) {
            ns.addItem(entityInstance.getExternalId());
            ns.setItemCaption(entityInstance.getExternalId(),
                    entityInstance.getID());
        }
    }

    protected void showDisableConditionsWindow(String bwInstanceOID,
            String goalOID, HashMap<String, String> entitiesOID) {
        Window dataModel = new Window("Disable Conditions Form");
        dataModel.setContent(new ManageAchieveGoalsConditionsForm(this,
                bwInstanceOID, goalOID, entitiesOID));
        dataModel.center();
        dataModel.setClosable(false);
        dataModel.setResizable(false);
        getApplication().getMainWindow().addWindow(dataModel);
    }

}
