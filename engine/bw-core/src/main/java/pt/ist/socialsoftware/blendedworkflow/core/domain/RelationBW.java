package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.RelationDto;

//TODO: Create two separate Relations in DML for EntityOne and EntityTwo.
public class RelationBW extends RelationBW_Base {

	@Override
	public void setName(String name) {
		checkRelationName(name);
		super.setName(name);
	}

	@Override
	public void setRolenameOne(String roleNameOne) {
		checkRolenameOne(roleNameOne);
		super.setRolenameOne(roleNameOne);
	}

	@Override
	public void setRolenameTwo(String roleNameTwo) {
		checkRolenameTwo(roleNameTwo);
		super.setRolenameTwo(roleNameTwo);
	}

	public RelationBW(DataModel dataModel, String name, Entity entityOne, String rolenameOne, String cardinalityOne,
			Entity entityTwo, String rolenameTwo, String cardinalityTwo) throws BWException {
		this(dataModel, name, entityOne, rolenameOne, cardinalityOne, false, entityTwo, rolenameTwo, cardinalityTwo,
				false);
	}

	public RelationBW(DataModel dataModel, String name, Entity entityOne, String rolenameOne, String cardinalityOne,
			boolean isOneKeyEntity, Entity entityTwo, String rolenameTwo, String cardinalityTwo, boolean isTwoKeyEntity)
			throws BWException {
		setDataModel(dataModel);
		setName(name);
		setEntityOne(entityOne);
		setEntityTwo(entityTwo);
		setRolenameOne(rolenameOne);
		setRolenameTwo(rolenameTwo);
		setCardinalityOne(Cardinality.parseCardinality(cardinalityOne));
		setCardinalityTwo(Cardinality.parseCardinality(cardinalityTwo));
		setIsOneKeyEntity(isOneKeyEntity);
		setIsTwoKeyEntity(isTwoKeyEntity);
	}

	private void checkRelationName(String name) {
		if (name == null || name.equals("")) {
			throw new BWException(BWErrorType.INVALID_RELATION_NAME, name);
		}

		checkUniqueRelationName(name);
	}

	private void checkUniqueRelationName(String name) throws BWException {
		Boolean find = getDataModel().getRelationBWSet().stream()
				.anyMatch(rel -> (rel != this && rel.getName().equals(name)));

		if (find) {
			throw new BWException(BWErrorType.INVALID_RELATION_NAME, name);
		}
	}

	private void checkRolenameOne(String rolenameOne) {
		if (rolenameOne == null || rolenameOne.equals("")) {
			throw new BWException(BWErrorType.INVALID_ROLE_NAME, rolenameOne);
		}

		getEntityTwo().checkUniqueElementName(rolenameOne);
	}

	private void checkRolenameTwo(String rolenameTwo) {
		if (rolenameTwo == null || rolenameTwo.equals("")) {
			throw new BWException(BWErrorType.INVALID_ROLE_NAME, rolenameTwo);
		}

		getEntityOne().checkUniqueElementName(rolenameTwo);
	}

	public Set<Entity> getEntitySet() {
		Set<Entity> entities = new HashSet<>();
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
		if (getRolenameOne().equals(rolename)) {
			return getEntityOne();
		}
		if (getRolenameTwo().equals(rolename)) {
			return getEntityTwo();
		}
		return null;
	}

	public Entity getSourceOfRolename(String rolename) {
		if (getRolenameOne().equals(rolename)) {
			return getEntityTwo();
		}
		if (getRolenameTwo().equals(rolename)) {
			return getEntityOne();
		}

		throw new BWException(BWErrorType.INVALID_ROLE_NAME, rolename);
	}

	public Cardinality getCardinalityByRolename(String rolename) {
		if (getRolenameOne().equals(rolename)) {
			return getCardinalityOne();
		}
		if (getRolenameTwo().equals(rolename)) {
			return getCardinalityTwo();
		}

		throw new BWException(BWErrorType.INVALID_ROLE_NAME, rolename);
	}

	public RelationDto getDTO() {
		RelationDto relDTO = new RelationDto();
		relDTO.setSpecId(getDataModel().getSpecification().getSpecId());
		relDTO.setExtId(getExternalId());
		relDTO.setName(getName());
		relDTO.setEntOneExtId(getEntityOne().getExternalId());
		relDTO.setEntOneName(getEntityOne().getName());
		relDTO.setRolenameOne(getRolenameOne());
		relDTO.setCardinalityOne(getCardinalityOne().getExp());
		relDTO.setPathOne(getPath(getEntityOne()));
		relDTO.setEntTwoExtId(getEntityTwo().getExternalId());
		relDTO.setEntTwoName(getEntityTwo().getName());
		relDTO.setRolenameTwo(getRolenameTwo());
		relDTO.setCardinalityTwo(getCardinalityTwo().getExp());
		relDTO.setPathTwo(getPath(getEntityTwo()));

		return relDTO;
	}

	public String getPath(Entity e) {
		if (e == getEntityOne()) {
			return getEntityTwo().getName() + "." + getRolenameOne();
		}

		if (e == getEntityTwo()) {
			return getEntityOne().getName() + "." + getRolenameTwo();
		}

		return null;
	}

}