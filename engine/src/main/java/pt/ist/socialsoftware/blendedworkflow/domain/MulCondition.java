package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class MulCondition extends MulCondition_Base {

	public static MulCondition getMulCondition(RelationBW relation, String rolename) {
		for (MulCondition mulCondition : relation.getMulConditionSet()) {
			if (mulCondition.getRolename().equals(rolename))
				return mulCondition;
		}

		if (relation.getMulConditionSet().size() >= 2)
			throw new BWException(BWErrorType.INVALID_ROLE_NAME, rolename);

		if (relation.getRoleNameOne().equals(rolename))
			return new MulCondition(relation, 1);

		if (relation.getRoleNameTwo().equals(rolename))
			return new MulCondition(relation, 2);

		assert (false);
		return null;
	}

	public static void createMUlConditions(RelationBW relation) {
		getMulCondition(relation, relation.getRoleNameOne());
		getMulCondition(relation, relation.getRoleNameTwo());
	}

	private MulCondition(RelationBW relation, int side) {
		relation.getDataModel().getSpecification().getConditionModel().addEntityInvariantCondition(this);
		setRelationBW(relation);
		setSide(side);
	}

	public Entity getEntity() {
		if (getSide() == 1)
			return getRelationBW().getEntityTwo();
		else
			return getRelationBW().getEntityOne();
	}

	public String getRolename() {
		if (getSide() == 1)
			return getRelationBW().getRoleNameOne();
		else
			return getRelationBW().getRoleNameTwo();
	}

	public Cardinality getCardinality() {
		if (getSide() == 1)
			return getRelationBW().getCardinalityOne();
		else
			return getRelationBW().getCardinalityTwo();
	}

	public String getExpression() {
		return "MUL(" + getEntity().getName() + "." + getRolename() + "," + getCardinality().name() + ")";
	}

	@Override
	public void delete() {
		setConditionModel(null);
		setRelationBW(null);
		setInvariantConditionGoal(null);
		setTaskWithMultiplicity(null);

		deleteDomainObject();
	}

	@Override
	public String getSubPath() {
		return "MUL(" + getRelationBW().getSourceOfRolename(getRolename()).getName() + "."
				+ getRelationBW().getCardinalityByRolename(getRolename()).name() + ")";
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignAttributeInstances(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub

	}

	@Override
	void assignAttributeInstances(TaskWorkItem taskWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Entity> getEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<AttributeBasic> getAttributeBasicSet() {
		return new HashSet<AttributeBasic>();
	}

	@Override
	public Set<String> getPathSet() {
		return new HashSet<String>();

	}

	@Override
	public HashMap<AttributeBasic, String> getcompareConditionValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrUndefinedCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrSkippedCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrTrueCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrFalseCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existExistEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existCompareAttributeToValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existTrue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripleStateBool evaluate(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance, GoalWorkItem goalWorkItem,
			ConditionType conditionType) {
		// TODO Auto-generated method stub
		return null;
	}

	public MulConditionDTO getDTO() {
		MulConditionDTO mulConditionDTO = new MulConditionDTO();
		mulConditionDTO.setSpecId(getConditionModel().getSpecification().getExternalId());
		mulConditionDTO.setRolePath(getEntity().getName() + "." + getRolename());
		mulConditionDTO.setCardinality(getCardinality().toString());

		return mulConditionDTO;
	}

	@Override
	public ExpressionDTO getDTO(String specId) {
		// TODO Auto-generated method stub
		return null;
	}

}
