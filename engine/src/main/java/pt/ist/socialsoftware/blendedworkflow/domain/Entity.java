package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Product.ProductType;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityDTO;

public class Entity extends Entity_Base {
    private static Logger log = LoggerFactory.getLogger(Entity.class);

    @Override
    public void setName(String name) {
        checkEntityName(name);
        super.setName(name);
    }

    public Entity(DataModel dataModel, String name, boolean exists) {
        setDataModel(dataModel);
        setName(name);
        setExists(exists);
        setEntityInstanceCounter(0);
    }

    private void checkEntityName(String name) {
        if ((name == null) || (name.equals("")))
            throw new BWException(BWErrorType.INVALID_ENTITY_NAME, name);

        checkUniqueEntityName(name);
    }

    private void checkUniqueEntityName(String name) throws BWException {
        boolean exists = getDataModel().getEntitiesSet().stream().anyMatch(
                ent -> (ent.getName() != null) && (ent.getName().equals(name)));
        if (exists)
            throw new BWException(BWErrorType.INVALID_ENTITY_NAME, name);
    }

    @Override
    public ProductType getProductType() {
        return ProductType.ENTITY;
    }

    public Attribute createAttribute(AttributeGroup attGroup, String name,
            AttributeType type, boolean isMandatory) {
        return new Attribute(getDataModel(), this, attGroup, name, type,
                isMandatory, false, false);
    }

    public AttributeGroup createAttributeGroup(String name,
            boolean isMandatory) {
        return new AttributeGroup(getDataModel(), this, name, isMandatory);
    }

    public RelationBW createRelation(String name, String roleNameOne,
            Cardinality cardinalityOne, Entity entityTwo, String roleNameTwo,
            Cardinality cardinalityTwo) {
        return new RelationBW(getDataModel(), name, this, roleNameOne,
                cardinalityOne, false, entityTwo, roleNameTwo, cardinalityTwo,
                false);
    }

    public void cloneEntity(DataModelInstance dataModelInstance)
            throws BWException {
        Entity newEntity = new Entity(dataModelInstance, getName(), false);
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
            for (RelationBW relation : this.getRelationsSet()) {
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

    public Optional<Attribute> getAttribute(String name) {
        return getAttributesSet().stream()
                .filter(att -> att.getName().equals(name)).findFirst();
    }

    public EntityInstance getEntityInstance(String ID) {
        for (EntityInstance entityInstance : getEntityInstancesSet()) {
            if (entityInstance.getID().equals(ID)) {
                return entityInstance;
            }
        }
        return null;
    }

    public Set<RelationBW> getRelationsSet() {
        Set<RelationBW> relations = new HashSet<RelationBW>(
                this.getRelationsOneSet());
        relations.addAll(getRelationsTwoSet());
        return relations;
    }

    public int getRelationsCount() {
        return getRelationsOneSet().size() + getRelationsTwoSet().size();
    }

    public Optional<AttributeGroup> getAttributeGroup(String name) {
        return getAttributeGroupSet().stream()
                .filter(attGroup -> attGroup.getName().equals(name))
                .findFirst();
    }

    @Override
    public void delete() {
        setDataModel(null);
        getAttributeGroupSet().stream().forEach(attGroup -> attGroup.delete());
        getAttributesSet().stream().forEach(att -> att.delete());
        getRelationsOneSet().stream().forEach(rel -> rel.delete());
        getRelationsTwoSet().stream().forEach(rel -> rel.delete());
        if (getDefEntityCondition() != null)
            getDefEntityCondition().delete();

        super.delete();
    }

    @Override
    public Entity getEntity() {
        return this;
    }

    @Override
    public Product getNext(List<String> pathLeft, String path) {
        log.debug("getNext {}:{}", path, pathLeft);

        if (pathLeft.isEmpty())
            return this;

        String element = pathLeft.get(0);
        Optional<AttributeGroup> oBwAttGroup = getAttributeGroup(element);
        if (oBwAttGroup.isPresent()) {
            pathLeft.remove(0);
            return oBwAttGroup.get().getNext(pathLeft, path);
        }

        Optional<Attribute> oBwAtt = getAttribute(element);
        if (oBwAtt.isPresent()) {
            pathLeft.remove(0);
            return oBwAtt.get().getNext(pathLeft, path);
        }

        Optional<RelationBW> oBwRel = getRelationsSet().stream()
                .filter(rel -> (rel.getRoleNameOne().equals(element)
                        && rel.getEntityTwo() == this)
                        || (rel.getRoleNameTwo().equals(element)
                                && rel.getEntityOne() == this))
                .findFirst();
        if (oBwRel.isPresent()) {
            pathLeft.remove(0);
            return oBwRel.get().getEntitybyRolename(element).getNext(pathLeft,
                    path);
        }

        throw new BWException(BWErrorType.INVALID_PATH, path + ":" + pathLeft);
    }

    @Override
    public EntityDTO getDTO() {
        EntityDTO entityDTO = new EntityDTO();
        entityDTO.setSpecId(getDataModel().getSpecification().getSpecId());
        entityDTO.setExtId(getExternalId());
        entityDTO.setProductType(getProductType().name());
        entityDTO.setName(getName());
        entityDTO.setExists(getExists());

        return entityDTO;
    }

    public Set<MulCondition> getMultConditions() {
        return Stream
                .concat(getRelationsOneSet().stream(),
                        getRelationsTwoSet().stream())
                .map((r) -> r.getMulCondition(this))
                .collect(Collectors.toSet());
    }

    @Override
    public Condition getDefCondition() {
        return DefEntityCondition.getDefEntity(this);
    }

}