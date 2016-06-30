package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class MulCondition extends MulCondition_Base {

	// returns the mul condition that has that role in the path expression, e.
	// g. given role name a returns for MUL(B.a,n)
	public static MulCondition getMulCondition(RelationBW relation, String rolename) {
		for (MulCondition mulCondition : relation.getMulConditionSet()) {
			if (mulCondition.getRolename().equals(rolename))
				return mulCondition;
		}

		if (relation.getMulConditionSet().size() >= 2)
			throw new BWException(BWErrorType.INVALID_ROLE_NAME, rolename);

		if (relation.getRoleNameOne().equals(rolename))
			return new MulCondition(relation, 2);

		if (relation.getRoleNameTwo().equals(rolename))
			return new MulCondition(relation, 1);

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

	public Entity getSourceEntity() {
		if (getSide() == 1)
			return getRelationBW().getEntityOne();
		else
			return getRelationBW().getEntityTwo();
	}

	public Entity getTargetEntity() {
		if (getSide() == 1)
			return getRelationBW().getEntityTwo();
		else
			return getRelationBW().getEntityOne();
	}

	public String getRolename() {
		if (getSide() == 1)
			return getRelationBW().getRoleNameTwo();
		else
			return getRelationBW().getRoleNameOne();
	}

	public Cardinality getCardinality() {
		if (getSide() == 1)
			return getRelationBW().getCardinalityTwo();
		else
			return getRelationBW().getCardinalityOne();
	}

	public MulCondition getSymmetricMulCondition() {
		String rolename;

		if (getSide() == 1) {
			rolename = getRelationBW().getRoleNameOne();
		} else {
			rolename = getRelationBW().getRoleNameTwo();
		}

		return MulCondition.getMulCondition(getRelationBW(), rolename);
	}

	public String getExpression() {
		return "MUL(" + getSourceEntity().getName() + "." + getRolename() + "," + getCardinality().getExp() + ")";
	}

	@Override
	public void delete() {
		setConditionModel(null);
		setRelationBW(null);
		setInvariantConditionGoal(null);
		setActivityWithMultiplicity(null);

		deleteDomainObject();
	}

	@Override
	public String getSubPath() {
		return "MUL(" + getSourceEntity().getName() + "." + getRolename() + "," + getCardinality().getExp() + ")";
	}

	@Override
	Condition cloneCondition(OldGoalModelInstance goalModelInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Condition cloneCondition(OldTaskModelInstance taskModelInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignAttributeInstances(OldGoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub

	}

	@Override
	void assignAttributeInstances(OldTaskWorkItem taskWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Entity> getEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		return new HashSet<Attribute>();
	}

	@Override
	public Set<Path> getPathSet() {
		return new HashSet<Path>();

	}

	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
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
	public TripleStateBool evaluate(OldGoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripleStateBool evaluateWithWorkItem(OldGoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripleStateBool evaluateWithDataModel(OldEntityInstance entityInstance, OldGoalWorkItem goalWorkItem,
			ConditionType conditionType) {
		// TODO Auto-generated method stub
		return null;
	}

	public MulConditionDTO getDTO() {
		MulConditionDTO mulConditionDTO = new MulConditionDTO();
		mulConditionDTO.setExternalId(getExternalId());
		mulConditionDTO.setRolePath(getSourceEntity().getName() + "." + getRolename());
		mulConditionDTO.setCardinality(getCardinality().getExp());
		mulConditionDTO.setMin(getCardinality().getMinValue());
		mulConditionDTO.setMax(getCardinality().getMaxValue());

		return mulConditionDTO;
	}

	@Override
	public ExpressionDTO getExpressionDTO(String specId) {
		// TODO Auto-generated method stub
		return null;
	}

}
