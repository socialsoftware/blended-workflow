package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Optional;

import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class BWDataModel extends BWDataModel_Base {

    public enum DataState {
        DEFINED, UNDEFINED, SKIPPED
    }

    /**
     * Clone the DataModel.
     */
    public DataModelInstance cloneDataModel() throws BWException {
        DataModelInstance newDataModelInstance = new DataModelInstance();
        BWEntity relationEntityOne;
        BWEntity relationEntityTwo;

        for (BWEntity entity : getEntitiesSet()) {
            entity.cloneEntity(newDataModelInstance);
        }

        // Get relation -> Get new Entities -> Clone with new Entities
        for (BWRelation relation : getRelationsSet()) {
            relationEntityOne = newDataModelInstance
                    .getEntity(relation.getEntityOne().getName()).get();
            relationEntityTwo = newDataModelInstance
                    .getEntity(relation.getEntityTwo().getName()).get();
            relation.cloneRelation(newDataModelInstance, relationEntityOne,
                    relationEntityTwo);
        }
        return newDataModelInstance;
    }

    public Optional<BWEntity> getEntity(String name) {
        return getEntitiesSet().stream()
                .filter(ent -> ent.getName().equals(name)).findFirst();
    }

    public BWRelation getRelation(String name) {
        for (BWRelation relation : getRelationsSet()) {
            if (relation.getName().equals(name)) {
                return relation;
            }
        }
        return null;
    }

    public void delete() {
        setSpecification(null);
        getEntitiesSet().stream().forEach(ent -> ent.delete());
        getRuleSet().stream().forEach(rule -> rule.delete());

        deleteDomainObject();
    }

    public BWEntity createEntity(String entityName, Boolean exists) {
        return new BWEntity(this, entityName, exists);
    }

    public BWRule createRule(Condition condition) {
        return new BWRule(this, condition);
    }

}