package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class RelationInstance extends RelationInstance_Base {

	public RelationInstance(Relation relation, EntityInstance entityInstanceOne, EntityInstance entityInstanceTwo, String id) {
		setRelationType(relation);
		setID(getRelationType().getName() + "." + id);
		addEntityInstances(entityInstanceOne);
		addEntityInstances(entityInstanceTwo);
	}
	
	public EntityInstance getEntityOne () {
		return getEntityInstances().get(0);
	}

	public EntityInstance getEntityTwo () {
		return getEntityInstances().get(1);
	}
	
	public EntityInstance getEntityInstance (Entity entity) {
		if (entity.equals(getEntityOne().getEntity())) {
			return getEntityOne();
		} else {
			return getEntityTwo();
		}
	}
}