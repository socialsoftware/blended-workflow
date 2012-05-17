package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class RelationInstance extends RelationInstance_Base {

	public RelationInstance(Relation relation, EntityInstance entityInstanceOne, EntityInstance entityInstanceTwo, String id) {
		setRelationType(relation);
		setID(getRelationType().getName() + "." + id);
//		addEntityInstances(entityInstanceTwo); //FIXME
//		addEntityInstances(entityInstanceOne);
		
		setEntityInstanceOne(entityInstanceOne);
		setEntityInstanceTwo(entityInstanceTwo);
	}
	
//	public EntityInstance getEntityOne () {
//		return getEntityInstances().get(0);//FIXME
//	}

//	public EntityInstance getEntityTwo () {
//		return getEntityInstances().get(1); //FIXME
//	}
	
	public EntityInstance getEntityInstance (Entity entity) {
		if (entity.equals(getEntityInstanceOne().getEntity())) {
			return getEntityInstanceOne();
		} else {
			return getEntityInstanceTwo();
		}
	}
}