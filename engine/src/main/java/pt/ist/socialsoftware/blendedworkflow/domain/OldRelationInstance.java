package pt.ist.socialsoftware.blendedworkflow.domain;

@Deprecated
public class OldRelationInstance extends OldRelationInstance_Base {

	public OldRelationInstance(RelationBW relation, OldEntityInstance entityInstanceOne,
			OldEntityInstance entityInstanceTwo, String id) {
		setRelationType(relation);
		setID(getRelationType().getName() + "." + id);
		setEntityInstanceOne(entityInstanceOne);
		setEntityInstanceTwo(entityInstanceTwo);
	}

	public OldEntityInstance getEntityInstance(Entity entity) {
		if (entity.equals(getEntityInstanceOne().getEntity())) {
			return getEntityInstanceOne();
		} else {
			return getEntityInstanceTwo();
		}
	}

}