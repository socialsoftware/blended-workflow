package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RelationDTO;

//TODO: Create two separate Relations in DML for EntityOne and EntityTwo.
public class RelationBW extends RelationBW_Base {

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

	public RelationBW(DataModel dataModel, String name, Entity entityOne, String roleNameOne, String cardinalityOne,
			boolean isOneKeyEntity, Entity entityTwo, String roleNameTwo, String cardinalityTwo, boolean isTwoKeyEntity)
			throws BWException {
		setDataModel(dataModel);
		setName(name);
		setEntityOne(entityOne);
		setEntityTwo(entityTwo);
		setRoleNameOne(roleNameOne);
		setRoleNameTwo(roleNameTwo);
		setCardinalityOne(Cardinality.parseCardinality(cardinalityOne));
		setCardinalityTwo(Cardinality.parseCardinality(cardinalityTwo));
		setIsOneKeyEntity(isOneKeyEntity);
		setIsTwoKeyEntity(isTwoKeyEntity);
	}

	private void checkRelationName(String name) {
		if ((name == null) || name.equals(""))
			throw new BWException(BWErrorType.INVALID_RELATION_NAME, name);

		checkUniqueRelationName(name);
	}

	private void checkUniqueRelationName(String name) throws BWException {
		Boolean find = getDataModel().getRelationBWSet().stream()
				.anyMatch(rel -> ((rel != this) && (rel.getName().equals(name))));

		if (find)
			throw new BWException(BWErrorType.INVALID_RELATION_NAME, name);
	}

	private void checkRoleNameOne(String roleNameOne) {
		if ((roleNameOne == null) || roleNameOne.equals(""))
			throw new BWException(BWErrorType.INVALID_ROLE_NAME, roleNameOne);

		getEntityTwo().checkUniqueElementName(roleNameOne);
	}

	private void checkRoleNameTwo(String roleNameTwo) {
		if ((roleNameTwo == null) || roleNameTwo.equals(""))
			throw new BWException(BWErrorType.INVALID_ROLE_NAME, roleNameTwo);

		getEntityOne().checkUniqueElementName(roleNameTwo);
	}

	public void cloneRelation(OldDataModelInstance dataModelInstance, Entity entityOne, Entity entityTwo)
			throws BWException {
		new RelationBW(dataModelInstance, getName(), entityOne, getRoleNameOne(), getCardinalityOne().getExp(),
				getIsOneKeyEntity(), entityTwo, getRoleNameTwo(), getCardinalityTwo().getExp(), getIsTwoKeyEntity());
	}

	public Set<Entity> getEntitySet() {
		Set<Entity> entities = new HashSet<Entity>();
		entities.add(this.getEntityOne());
		entities.add(this.getEntityTwo());
		return entities;
	}

	public void delete() {
		setDataModel(null);
		setEntityOne(null);
		setEntityTwo(null);

		getMulConditionSet().stream().forEach(mul -> mul.delete());

		getCardinalityOne().delete();
		getCardinalityTwo().delete();

		getRelationInstanceSet().stream().forEach(ri -> ri.delete());

		deleteDomainObject();
	}

	public Entity getEntitybyRolename(String rolename) {
		if (getRoleNameOne().equals(rolename))
			return getEntityOne();
		if (getRoleNameTwo().equals(rolename))
			return getEntityTwo();
		return null;
	}

	public Entity getSourceOfRolename(String rolename) {
		if (getRoleNameOne().equals(rolename))
			return getEntityTwo();
		if (getRoleNameTwo().equals(rolename))
			return getEntityOne();

		throw new BWException(BWErrorType.INVALID_ROLE_NAME, rolename);
	}

	public Cardinality getCardinalityByRolename(String rolename) {
		if (getRoleNameOne().equals(rolename))
			return getCardinalityOne();
		if (getRoleNameTwo().equals(rolename))
			return getCardinalityTwo();

		throw new BWException(BWErrorType.INVALID_ROLE_NAME, rolename);
	}

	public RelationDTO getDTO() {
		RelationDTO relDTO = new RelationDTO();
		relDTO.setSpecId(getDataModel().getSpecification().getSpecId());
		relDTO.setExtId(getExternalId());
		relDTO.setName(getName());
		relDTO.setEntOneExtId(getEntityOne().getExternalId());
		relDTO.setEntOneName(getEntityOne().getName());
		relDTO.setRolenameOne(getRoleNameOne());
		relDTO.setCardinalityOne(getCardinalityOne().getExp());
		relDTO.setEntTwoExtId(getEntityTwo().getExternalId());
		relDTO.setEntTwoName(getEntityTwo().getName());
		relDTO.setRolenameTwo(getRoleNameTwo());
		relDTO.setCardinalityTwo(getCardinalityTwo().getExp());

		return relDTO;
	}

	public String getPath(Entity e) {
		if (e == getEntityOne())
			return getEntityTwo().getName() + "." + getRoleNameOne();

		if (e == getEntityTwo())
			return getEntityOne().getName() + "." + getRoleNameTwo();

		return null;
	}

}