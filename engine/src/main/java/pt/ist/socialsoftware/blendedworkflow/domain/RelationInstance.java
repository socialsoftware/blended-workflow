package pt.ist.socialsoftware.blendedworkflow.domain;

public class RelationInstance extends RelationInstance_Base {

    public RelationInstance(RelationBW relation, EntityInstance entityInstanceOne,
            EntityInstance entityInstanceTwo, String id) {
        setRelationType(relation);
        setID(getRelationType().getName() + "." + id);
        setEntityInstanceOne(entityInstanceOne);
        setEntityInstanceTwo(entityInstanceTwo);
    }

    public EntityInstance getEntityInstance(Entity entity) {
        if (entity.equals(getEntityInstanceOne().getEntity())) {
            return getEntityInstanceOne();
        } else {
            return getEntityInstanceTwo();
        }
    }

}