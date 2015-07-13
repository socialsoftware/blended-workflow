package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

//TODO: Create two separate Relations in DML for EntityOne and EntityTwo.
public class BWRelation extends BWRelation_Base {

    public enum Cardinality {
        MANY, ZERO_OR_ONE, ONE
    }

    @Override
    public void setName(String name) {
        checkRelationName(name);
        super.setName(name);
    }

    public BWRelation(BWDataModel dataModel, String name, BWEntity entityOne,
            String roleNameOne, Cardinality cardinalityOne,
            boolean isOneKeyEntity, BWEntity entityTwo, String roleNameTwo,
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
            throw new BWException(BWErrorType.INVALID_RELATION_NAME, name);

        checkUniqueRelationName(name);
    }

    private void checkUniqueRelationName(String name) throws BWException {
        Boolean find = getEntityOne().getRelationsSet().stream().anyMatch(
                rel -> ((rel != this) && (isInRelation(getEntityTwo(), rel))));

        if (find)
            throw new BWException(BWErrorType.INVALID_RELATION_NAME, name);
    }

    private boolean isInRelation(BWEntity entityTwo, BWRelation relation) {
        return relation.getEntityOne().equals(entityTwo)
                || relation.getEntityTwo().equals(entityTwo);
    }

    public void cloneRelation(DataModelInstance dataModelInstance,
            BWEntity entityOne, BWEntity entityTwo) throws BWException {
        new BWRelation(dataModelInstance, getName(), entityOne,
                getRoleNameOne(), getCardinalityOne(), getIsOneKeyEntity(),
                entityTwo, getRoleNameTwo(), getCardinalityTwo(),
                getIsTwoKeyEntity());
    }

    public BWEntity getEntity(BWEntity entity) {
        if (entity.equals(getEntityOne())) {
            return getEntityOne();
        } else {
            return getEntityTwo();
        }
    }

    public Set<BWEntity> getEntitiesSet() {
        Set<BWEntity> entities = new HashSet<BWEntity>();
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