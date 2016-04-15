package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class RelationInstance extends RelationInstance_Base {

	@Override
	public void setEntityInstanceOne(EntityInstance entityInstance) {
		checkConsistency(entityInstance, getEntityInstanceTwo(), getRelationType());
		super.setEntityInstanceOne(entityInstance);
	}

	@Override
	public void setEntityInstanceTwo(EntityInstance entityInstance) {
		checkConsistency(getEntityInstanceOne(), entityInstance, getRelationType());
		super.setEntityInstanceTwo(entityInstance);
	}

	@Override
	public void setRelationType(RelationBW relation) {
		checkConsistency(getEntityInstanceOne(), getEntityInstanceTwo(), relation);
		super.setRelationType(relation);
	}

	public RelationInstance(EntityInstance entityInstanceOne, EntityInstance entityInstanceTwo, RelationBW relation) {
		setEntityInstanceOne(entityInstanceOne);
		setEntityInstanceTwo(entityInstanceTwo);
		setRelationType(relation);
	}

	private void checkConsistency(EntityInstance entityInstanceOne, EntityInstance entityInstanceTwo,
			RelationBW relation) {
		if (entityInstanceOne != null && entityInstanceTwo != null && relation != null) {
			if (entityInstanceOne.getWorkflowInstance() != entityInstanceTwo.getWorkflowInstance()) {
				throw new BWException(BWErrorType.RELATIONINSTANCE_CONSISTENCY,
						entityInstanceOne.getEntity().getName() + ":" + entityInstanceTwo.getEntity().getName());
			}

			if (entityInstanceOne.getEntity() != relation.getEntityOne()) {
				throw new BWException(BWErrorType.RELATIONINSTANCE_CONSISTENCY,
						entityInstanceOne.getEntity().getName() + ":" + relation.getEntityOne().getName());
			}

			if (entityInstanceTwo.getEntity() != relation.getEntityTwo()) {
				throw new BWException(BWErrorType.RELATIONINSTANCE_CONSISTENCY,
						entityInstanceTwo.getEntity().getName() + ":" + relation.getEntityTwo().getName());
			}
		}

	}

	public void delete() {
		setEntityInstanceOne(null);
		setEntityInstanceTwo(null);
	}

}
