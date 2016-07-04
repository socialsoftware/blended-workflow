package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class RelationInstance extends RelationInstance_Base {

	@Override
	public void setEntityInstanceOne(EntityInstance entityInstance) {
		super.setEntityInstanceOne(entityInstance);
		checkConsistency(entityInstance, getEntityInstanceTwo(), getRelationType());
	}

	@Override
	public void setEntityInstanceTwo(EntityInstance entityInstance) {
		super.setEntityInstanceTwo(entityInstance);
		checkConsistency(getEntityInstanceOne(), entityInstance, getRelationType());
	}

	@Override
	public void setRelationType(RelationBW relation) {
		super.setRelationType(relation);
		checkConsistency(getEntityInstanceOne(), getEntityInstanceTwo(), relation);
	}

	public RelationInstance(EntityInstance entityInstanceOne, String rolenameOne, EntityInstance entityInstanceTwo,
			String rolenameTwo, RelationBW relation) {
		if (rolenameOne.equals(relation.getRolenameOne()) && entityInstanceOne.getEntity() == relation.getEntityOne()) {
			setEntityInstanceOne(entityInstanceOne);
		} else if (rolenameOne.equals(relation.getRolenameTwo())
				&& entityInstanceOne.getEntity() == relation.getEntityTwo()) {
			setEntityInstanceTwo(entityInstanceOne);

		} else {
			throw new BWException(BWErrorType.RELATIONINSTANCE_CONSISTENCY,
					"Rolename and type do not match for rolename " + rolenameOne + " of entity type "
							+ entityInstanceOne.getEntity().getName());
		}

		if (rolenameTwo.equals(relation.getRolenameOne()) && entityInstanceTwo.getEntity() == relation.getEntityOne()) {
			setEntityInstanceOne(entityInstanceTwo);
		} else if (rolenameTwo.equals(relation.getRolenameTwo())
				&& entityInstanceTwo.getEntity() == relation.getEntityTwo()) {
			setEntityInstanceTwo(entityInstanceTwo);

		} else {
			throw new BWException(BWErrorType.RELATIONINSTANCE_CONSISTENCY,
					"Rolename and type do not match for rolename " + rolenameTwo + " of entity type "
							+ entityInstanceTwo.getEntity().getName());
		}

		setRelationType(relation);
	}

	private void checkConsistency(EntityInstance entityInstanceOne, EntityInstance entityInstanceTwo,
			RelationBW relation) {
		if (entityInstanceOne != null && entityInstanceTwo != null && relation != null) {
			if (entityInstanceOne.getWorkflowInstance() != entityInstanceTwo.getWorkflowInstance()) {
				throw new BWException(BWErrorType.RELATIONINSTANCE_CONSISTENCY, "Different workflow instances "
						+ entityInstanceOne.getEntity().getName() + " - " + entityInstanceTwo.getEntity().getName());
			}

			if (entityInstanceOne.getEntity() != relation.getEntityOne()) {
				throw new BWException(BWErrorType.RELATIONINSTANCE_CONSISTENCY, "Entity One type does not match "
						+ entityInstanceOne.getEntity().getName() + " - " + relation.getEntityOne().getName());
			}

			if (entityInstanceTwo.getEntity() != relation.getEntityTwo()) {
				throw new BWException(BWErrorType.RELATIONINSTANCE_CONSISTENCY, "Entity Two type does not match "
						+ entityInstanceTwo.getEntity().getName() + " - " + relation.getEntityTwo().getName());
			}

			checkMaxCardinality(entityInstanceOne, entityInstanceTwo, relation);
		}

	}

	private void checkMaxCardinality(EntityInstance entityInstanceOne, EntityInstance entityInstanceTwo,
			RelationBW relation) {
		if (entityInstanceOne.getEntityInstancesByRolename(relation.getRolenameTwo()).size() > relation
				.getCardinalityTwo().getMaxValue()) {
			throw new BWException(BWErrorType.RELATIONINSTANCE_CONSISTENCY,
					"Number of instances "
							+ entityInstanceOne.getEntityInstancesByRolename(relation.getRolenameTwo()).size() + " > "
							+ relation.getCardinalityTwo().getMaxValue());
		}

		if (entityInstanceTwo.getEntityInstancesByRolename(relation.getRolenameOne()).size() > relation
				.getCardinalityOne().getMaxValue()) {
			throw new BWException(BWErrorType.RELATIONINSTANCE_CONSISTENCY,
					"Number of instances "
							+ entityInstanceTwo.getEntityInstancesByRolename(relation.getRolenameOne()).size() + " > "
							+ relation.getCardinalityOne().getMaxValue());
		}
	}

	public void delete() {
		setRelationType(null);
		setEntityInstanceOne(null);
		setEntityInstanceTwo(null);
	}

	public boolean hasRolename(String rolename) {
		return (rolename.equals(getRelationType().getRolenameOne())
				|| rolename.equals(getRelationType().getRolenameTwo()));
	}

	public EntityInstance getEntityInstanceByRolename(EntityInstance entityInstance, String rolename) {
		if (rolename.equals(getRelationType().getRolenameOne()) && entityInstance == getEntityInstanceTwo()) {
			return getEntityInstanceOne();
		} else if (rolename.equals(getRelationType().getRolenameTwo()) && entityInstance == getEntityInstanceOne()) {
			return getEntityInstanceTwo();
		} else {
			return null;
		}
	}

}
