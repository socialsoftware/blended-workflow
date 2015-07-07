package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;

//TODO: Create two separate Relations in DML for EntityOne and EntityTwo.
public class Relation extends Relation_Base {

    public enum Cardinality {
        MANY, ZERO_OR_ONE, ONE
    }

    @Override
    public void setName(String name) {
        checkRelationName(name);
        super.setName(name);
    }

    public Relation(DataModel dataModel, String name, Entity entityOne,
            String roleNameOne, Cardinality cardinalityOne,
            boolean isOneKeyEntity, Entity entityTwo, String roleNameTwo,
            Cardinality cardinalityTwo, boolean isTwoKeyEntity)
                    throws BWException {
        setDataModel(dataModel);
        setEntityOne(entityOne);
        setEntityTwo(entityTwo);
        setName(name);
        setRoleNameOne(roleNameOne);
        setRoleNameTwo(roleNameTwo);
        setCardinalityOne(cardinalityOne);
        setCardinalityTwo(cardinalityTwo);
        setIsOneKeyEntity(isOneKeyEntity);
        setIsTwoKeyEntity(isTwoKeyEntity);
    }

    private void checkRelationName(String name) {
        if ((name == null) || name.equals(""))
            throw new BWException(BlendedWorkflowError.INVALID_RELATION_NAME,
                    name);

        checkUniqueRelationName(name);
    }

    private void checkUniqueRelationName(String name) throws BWException {
        Boolean find = getEntityOne().getRelationsSet().stream().anyMatch(
                rel -> ((rel != this) && (isInRelation(getEntityTwo(), rel))));

        if (find)
            throw new BWException(BlendedWorkflowError.INVALID_RELATION_NAME,
                    name);
    }

    private boolean isInRelation(Entity entityTwo, Relation relation) {
        return relation.getEntityOne().equals(entityTwo)
                || relation.getEntityTwo().equals(entityTwo);
    }

    public void cloneRelation(DataModelInstance dataModelInstance,
            Entity entityOne, Entity entityTwo) throws BWException {
        new Relation(dataModelInstance, getName(), entityOne, getRoleNameOne(),
                getCardinalityOne(), getIsOneKeyEntity(), entityTwo,
                getRoleNameTwo(), getCardinalityTwo(), getIsTwoKeyEntity());
    }

    public Entity getEntity(Entity entity) {
        if (entity.equals(getEntityOne())) {
            return getEntityOne();
        } else {
            return getEntityTwo();
        }
    }

    public Set<Entity> getEntitiesSet() {
        Set<Entity> entities = new HashSet<Entity>();
        entities.add(this.getEntityOne());
        entities.add(this.getEntityTwo());
        return entities;
    }

    public void delete() {
        setDataModel(null);
        setEntityOne(null);
        setEntityTwo(null);

        deleteDomainObject();
    }

}