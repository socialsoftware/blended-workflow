package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

//TODO: Create two separate Relations in DML for EntityOne and EntityTwo.
public class BWRelation extends BWRelation_Base {

    public enum Cardinality {
        ZERO_MANY, ONE_MANY, ZERO_OR_ONE, ONE
    }

    @Override
    public void setName(String name) {
        checkRelationName(name);
        super.setName(name);
    }

    @Override
    public void setRoleNameOne(String roleNameOne) {
        checkRoleNameOne(roleNameOne);
        super.setRoleNameOne(roleNameOne);
    }

    @Override
    public void setRoleNameTwo(String roleNameTwo) {
        checkRoleNameTwo(roleNameTwo);
        super.setRoleNameTwo(roleNameTwo);
    }

    public BWRelation(BWDataModel dataModel, String name, BWEntity entityOne,
            String roleNameOne, Cardinality cardinalityOne,
            boolean isOneKeyEntity, BWEntity entityTwo, String roleNameTwo,
            Cardinality cardinalityTwo, boolean isTwoKeyEntity)
                    throws BWException {
        setDataModel(dataModel);
        setName(name);
        setEntityOne(entityOne);
        setEntityTwo(entityTwo);
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
        Boolean find = getDataModel().getRelationsSet().stream().anyMatch(
                rel -> ((rel != this) && (rel.getName().equals(name))));

        if (find)
            throw new BWException(BWErrorType.INVALID_RELATION_NAME, name);
    }

    private void checkRoleNameOne(String roleNameOne) {
        if ((roleNameOne == null) || roleNameOne.equals(""))
            throw new BWException(BWErrorType.INVALID_ROLE_NAME, roleNameOne);

        checkUniqueRolename(getEntityTwo(), roleNameOne);
    }

    private void checkRoleNameTwo(String roleNameTwo) {
        if ((roleNameTwo == null) || roleNameTwo.equals(""))
            throw new BWException(BWErrorType.INVALID_ROLE_NAME, roleNameTwo);

        checkUniqueRolename(getEntityOne(), roleNameTwo);
    }

    private void checkUniqueRolename(BWEntity entity, String roleName) {
        if (entity.getRelationsSet().stream().filter(rel -> rel != this)
                .anyMatch(rel -> (rel.getRoleNameOne().equals(roleName)
                        && rel.getEntityTwo() == entity)
                        || (rel.getRoleNameTwo().equals(roleName)
                                && rel.getEntityOne() == entity)))
            throw new BWException(BWErrorType.INVALID_ROLE_NAME, roleName);
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

    public BWEntity getEntity(String element) {
        if (getRoleNameOne().equals(element))
            return getEntityOne();
        if (getRoleNameTwo().equals(element))
            return getEntityTwo();
        return null;
    }

}