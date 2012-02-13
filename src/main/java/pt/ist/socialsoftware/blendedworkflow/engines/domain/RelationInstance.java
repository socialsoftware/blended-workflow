package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class RelationInstance extends RelationInstance_Base {

	public RelationInstance(Relation relation, EntityInstance entityInstanceOne, EntityInstance entityInstanceTwo, String id) {
		setRelationType(relation);
		setId(getRelationType().getName() + "." + id);
		addEntityInstances(entityInstanceOne);
		addEntityInstances(entityInstanceTwo);
	}

}