package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.domain.Relation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;

public class Entity extends Entity_Base {

    @Override
    public void setName(String name) {
        checkEntityName(name);
        super.setName(name);
    }

    public Entity(DataModel dataModel, String name) throws BWException {
        setDataModel(dataModel);
        setName(name);
        setEntityInstanceCounter(0);
    }

    private void checkEntityName(String name) {
        if ((name == null) || (name.equals("")))
            throw new BWException(BlendedWorkflowError.INVALID_ENTITY_NAME,
                    name);

        checkUniqueEntityName(name);
    }

    private void checkUniqueEntityName(String name) throws BWException {
        boolean exists = getDataModel().getEntitiesSet().stream().anyMatch(
                ent -> (ent.getName() != null) && (ent.getName().equals(name)));
        if (exists)
            throw new BWException(BlendedWorkflowError.INVALID_ENTITY_NAME,
                    name);
    }

    public Attribute createAttribute(String name, AttributeType type) {
        return new Attribute(this.getDataModel(), name, this, type, false,
                false);
    }

    public Relation createRelation(String roleNameOne,
            Cardinality cardinalityOne, Entity entityTwo, String roleNameTwo,
            Cardinality cardinalityTwo) {
        String name = getName() + "." + roleNameOne + "-" + roleNameTwo + "."
                + entityTwo.getName();
        return new Relation(getDataModel(), name, this, roleNameOne,
                cardinalityOne, false, entityTwo, roleNameTwo, cardinalityTwo,
                false);
    }

    public void cloneEntity(DataModelInstance dataModelInstance)
            throws BWException {
        Entity newEntity = new Entity(dataModelInstance, getName());
        for (Attribute attribute : getAttributesSet()) {
            attribute.cloneAttribute(dataModelInstance, newEntity);
        }

        for (EntityInstance ei : getEntityInstancesSet()) {
            ei.cloneEntityInstance(dataModelInstance, newEntity);
        }
    }

    /**
     * Create and assign EntityInstances and AttributesInstances to Workitems
     */
    public void assignAttributeInstances(GoalWorkItem goalWorkItem,
            Attribute attribute, ConditionType conditionType) {
        EntityInstance entityInstanceContext = goalWorkItem
                .getEntityInstanceContext();
        Entity entityContext = entityInstanceContext.getEntity();

        if (this.equals(entityContext)) {
            entityInstanceContext.assignAttributeInstances(goalWorkItem,
                    attribute, conditionType);
        } else {
            for (RelationInstance relationInstance : entityInstanceContext
                    .getEntityInstanceOneRelationInstancesSet()) {
                Entity relationEntityContext = relationInstance
                        .getEntityInstanceTwo().getEntity();
                EntityInstance relationEntityInstanceContext = relationInstance
                        .getEntityInstanceTwo();
                if (relationEntityContext.equals(this)) {
                    relationEntityInstanceContext.assignAttributeInstances(
                            goalWorkItem, attribute, conditionType);
                }
            }

            for (RelationInstance relationInstance : entityInstanceContext
                    .getEntityInstanceTwoRelationInstancesSet()) {
                Entity relationEntityContext = relationInstance
                        .getEntityInstanceOne().getEntity();
                EntityInstance relationEntityInstanceContext = relationInstance
                        .getEntityInstanceOne();
                if (relationEntityContext.equals(this)) {
                    relationEntityInstanceContext.assignAttributeInstances(
                            goalWorkItem, attribute, conditionType);
                } else {
                    for (RelationInstance r2 : relationEntityInstanceContext
                            .getEntityInstanceTwoRelationInstancesSet()) {
                        if (r2.getEntityInstanceOne().getEntity()
                                .equals(this)) {
                            r2.getEntityInstanceOne().assignAttributeInstances(
                                    goalWorkItem, attribute, conditionType);
                        }
                    }
                }
            }
        }
    }

    public void assignAttributeInstances(TaskWorkItem taskWorkItem,
            Attribute attribute, ConditionType conditionType) {
        DataModelInstance dataModelInstance = taskWorkItem.getBwInstance()
                .getDataModelInstance();
        if (getEntityInstancesSet().isEmpty()) {
            // EntityInstance entityInstance = new
            // EntityInstance(dataModelInstance, this); //OLD
            EntityInstance entityInstance = new EntityInstance(this);
            entityInstance.assignAttributeInstances(taskWorkItem, attribute,
                    conditionType);

            // FIXME: Bad Hack!
            if (!taskWorkItem.getTask().getName().equals("Booking")) {
                createRelationInstances(dataModelInstance, entityInstance);
            }
        } else {
            for (EntityInstance entityInstance : getEntityInstancesSet()) { // FIXME:
                                                                            // only
                                                                            // 1
                                                                            // entityInstance
                entityInstance.assignAttributeInstances(taskWorkItem, attribute,
                        conditionType);
            }
        }
    }

    public void assignAllAttributeInstances(GoalWorkItem goalWorkItem,
            Entity entity, ConditionType conditionType) {
        EntityInstance entityInstanceContext = goalWorkItem
                .getEntityInstanceContext();
        Entity entityContext = entityInstanceContext.getEntity();

        if (this.equals(entityContext)) {
            for (Attribute attribute : entityContext.getAttributesSet()) {
                if (attribute.getIsKeyAttribute()) {
                    entityInstanceContext.assignAttributeInstances(goalWorkItem,
                            attribute, conditionType);
                }
            }
        } else {
            for (RelationInstance relationInstance : entityInstanceContext
                    .getEntityInstanceOneRelationInstancesSet()) {
                Entity relationEntityContext = relationInstance
                        .getEntityInstanceTwo().getEntity();
                EntityInstance relationEntityInstanceContext = relationInstance
                        .getEntityInstanceTwo();
                if (relationEntityContext.equals(this)) {
                    for (Attribute attribute : relationEntityContext
                            .getAttributesSet()) {
                        if (attribute.getIsKeyAttribute()) {
                            relationEntityInstanceContext
                                    .assignAttributeInstances(goalWorkItem,
                                            attribute, conditionType);
                        }
                    }
                }
            }

            for (RelationInstance relationInstance : entityInstanceContext
                    .getEntityInstanceTwoRelationInstancesSet()) {
                Entity relationEntityContext = relationInstance
                        .getEntityInstanceOne().getEntity();
                EntityInstance relationEntityInstanceContext = relationInstance
                        .getEntityInstanceOne();
                if (relationEntityContext.equals(this)) {
                    for (Attribute attribute : relationEntityContext
                            .getAttributesSet()) {
                        if (attribute.getIsKeyAttribute()) {
                            relationEntityInstanceContext
                                    .assignAttributeInstances(goalWorkItem,
                                            attribute, conditionType);
                        }
                    }
                }
            }
        }
    }

    public void assignAllAttributeInstances(TaskWorkItem taskWorkItem,
            Entity entity, ConditionType conditionType) {
        DataModelInstance dataModelInstance = taskWorkItem.getBwInstance()
                .getDataModelInstance();

        if (getEntityInstancesSet().isEmpty()) {
            // EntityInstance entityInstance = new
            // EntityInstance(dataModelInstance, this);
            EntityInstance entityInstance = new EntityInstance(this);
            for (Attribute attribute : getAttributesSet()) {
                if (attribute.getIsKeyAttribute())
                    entityInstance.assignAttributeInstances(taskWorkItem,
                            attribute, conditionType);
            }

            // FIXME: Bad Hack!
            if (!taskWorkItem.getTask().getName().equals("Booking")) {
                createRelationInstances(dataModelInstance, entityInstance);
            }
        } else {
            for (EntityInstance entityInstance : getEntityInstancesSet()) { // FIXME:
                                                                            // Only
                                                                            // 1
                                                                            // entityInstance
                for (Attribute attribute : getAttributesSet()) {
                    if (attribute.getIsKeyAttribute())
                        entityInstance.assignAttributeInstances(taskWorkItem,
                                attribute, conditionType);
                }
            }
        }
    }

    private void createRelationInstances(DataModelInstance dataModelInstance,
            EntityInstance entityInstance) {
        Entity relationEntityTwo = null;
        EntityInstance relationEntityInstanceTwo = null;
        // Relation Type Exists?
        if (this.getRelationsCount() > 0) {
            for (Relation relation : this.getRelationsSet()) {
                // Get the other relation entity
                for (Entity entity : relation.getEntitiesSet()) {
                    if (!this.getName().equals(entity.getName())) {
                        relationEntityTwo = entity; // entity2
                    }
                }
                // Relations instances already exists?
                if (relation.getRelationInstancesSet().isEmpty()) {
                    if (relationEntityTwo.getEntityInstancesSet().isEmpty()) {
                        // relationEntityInstanceTwo = new
                        // EntityInstance(dataModelInstance, relationEntityTwo);
                        relationEntityInstanceTwo = new EntityInstance(
                                relationEntityTwo);
                    } else {
                        for (EntityInstance entityInstance1 : relationEntityTwo
                                .getEntityInstancesSet()) { // FIXME: only 1
                                                            // entityInstance
                            relationEntityInstanceTwo = entityInstance1;
                        }
                    }
                    // Create Relation Instance and re-call the method for the 2
                    // entity
                    EntityInstance relationInstanceOne;
                    EntityInstance relationInstanceTwo;

                    if (entityInstance.getEntity()
                            .equals(relation.getEntityOne())) {
                        relationInstanceOne = entityInstance;
                        relationInstanceTwo = relationEntityInstanceTwo;
                    } else {
                        relationInstanceOne = relationEntityInstanceTwo;
                        relationInstanceTwo = entityInstance;
                    }
                    new RelationInstance(relation, relationInstanceOne,
                            relationInstanceTwo,
                            relationInstanceOne.getNewRelationInstanceID());
                    // if (entityInstance.getEntity().getName().equals("?")) {
                    // relationEntityTwo.createRelationInstances(dataModelInstance,
                    // relationEntityInstanceTwo);
                    // }
                }
            }
        }
    }

    public int getNewEntityInstanceId() {
        setEntityInstanceCounter(getEntityInstanceCounter() + 1);
        return getEntityInstanceCounter();
    }

    public Attribute getAttribute(String name) {
        return getAttributesSet().stream()
                .filter(att -> att.getName().equals(name)).findFirst()
                .orElse(null);
    }

    public EntityInstance getEntityInstance(String ID) {
        for (EntityInstance entityInstance : getEntityInstancesSet()) {
            if (entityInstance.getID().equals(ID)) {
                return entityInstance;
            }
        }
        return null;
    }

    public Set<Relation> getRelationsSet() {
        Set<Relation> relations = this.getRelationsOneSet();
        relations.addAll(getRelationsTwoSet());
        return relations;
    }

    public int getRelationsCount() {
        return getRelationsOneSet().size() + getRelationsTwoSet().size();
    }

    public void delete() {
        setDataModel(null);
        getAttributesSet().stream().forEach(att -> att.delete());
        getRelationsOneSet().stream().forEach(rel -> rel.delete());
        getRelationsTwoSet().stream().forEach(rel -> rel.delete());

        deleteDomainObject();
    }

}