package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RelationDTO;

//TODO: Create two separate Relations in DML for EntityOne and EntityTwo.
public class RelationBW extends RelationBW_Base {

	private final static String ZERO_MANY = "\\*";
	private final static String ONE_MANY = "1..\\*";
	final static String CARDINALITY = "(" + Cardinality.ONE.toString() + "|" + Cardinality.ZERO_OR_ONE.toString() + "|"
			+ ZERO_MANY + "|" + ONE_MANY + ")";

	public enum Cardinality {
		ZERO_MANY("*"), ONE_MANY("1..*"), ZERO_OR_ONE("0..1"), ONE("1");

		private String exp;

		private Cardinality(String exp) {
			this.exp = exp;
		}

		@Override
		public String toString() {
			return this.exp;
		}

		public static Cardinality parseCardinality(String cardinality) {
			if (!Pattern.matches(CARDINALITY, cardinality))
				throw new BWException(BWErrorType.INVALID_CARDINALITY, cardinality);

			Cardinality res = null;

			if (cardinality.equals(Cardinality.ONE.toString()))
				return Cardinality.ONE;

			if (cardinality.equals(Cardinality.ZERO_OR_ONE.toString()))
				return Cardinality.ZERO_OR_ONE;

			if (cardinality.equals(Cardinality.ZERO_MANY.toString()))
				return Cardinality.ZERO_MANY;

			if (cardinality.equals(Cardinality.ONE_MANY.toString()))
				return Cardinality.ONE_MANY;

			assert (false);
			return res;
		}

	}

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

	public RelationBW(DataModel dataModel, String name, Entity entityOne, String roleNameOne,
			Cardinality cardinalityOne, boolean isOneKeyEntity, Entity entityTwo, String roleNameTwo,
			Cardinality cardinalityTwo, boolean isTwoKeyEntity) throws BWException {
		setDataModel(dataModel);
		setName(name);
		setEntityOne(entityOne);
		setEntityTwo(entityTwo);
		setRoleNameOne(roleNameOne);
		setRoleNameTwo(roleNameTwo);
		setCardinalityOne(cardinalityOne);
		setCardinalityTwo(cardinalityTwo);
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

	public void cloneRelation(DataModelInstance dataModelInstance, Entity entityOne, Entity entityTwo)
			throws BWException {
		new RelationBW(dataModelInstance, getName(), entityOne, getRoleNameOne(), getCardinalityOne(),
				getIsOneKeyEntity(), entityTwo, getRoleNameTwo(), getCardinalityTwo(), getIsTwoKeyEntity());
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
		relDTO.setCardinalityOne(getCardinalityOne().toString());
		relDTO.setEntTwoExtId(getEntityTwo().getExternalId());
		relDTO.setEntTwoName(getEntityTwo().getName());
		relDTO.setRolenameTwo(getRoleNameTwo());
		relDTO.setCardinalityTwo(getCardinalityTwo().toString());

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