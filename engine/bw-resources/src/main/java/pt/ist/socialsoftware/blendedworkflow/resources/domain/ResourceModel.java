package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.apache.ojb.broker.util.logging.Logger;
import org.apache.ojb.broker.util.logging.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.MergeResourcesPolicy;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResourceModel extends ResourceModel_Base {
	private static Logger logger = LoggerFactory.getLogger(ResourceModel.class);

	public ResourceModel() {
		super();
	}

    public void clean() {
		getEntityIsPersonSet().stream().forEach(e -> removeEntityIsPerson(e));
		getRalExpressionSet().stream().forEach(RALExpression::delete);
		getPersonSet().stream().forEach(Person::delete);
		getPositionSet().stream().forEach(Position::delete);
		getCapabilitySet().stream().forEach(Capability::delete);
		getRoleSet().stream().forEach(Role::delete);
		getUnitSet().stream().forEach(Unit::delete);
		getSpec().getBlendedWorkflow().getUsersSet().stream().forEach(User::delete);
    }

	public void delete() {
		clean();
		setSpec(null);
		deleteDomainObject();
	}

	public Entity addEntityIsPerson(String entityName) {
		Entity entity = getSpec().getDataModel().getEntity(entityName)
				.orElseThrow(() -> new RMException(RMErrorType.INVALID_ENTITY_NAME, "Entity " + entityName + " does not exist."));

		addEntityIsPerson(entity);
		return entity;
	}

	public Capability addCapability(String name, String description) {
		Capability capability = new Capability(this, name, description);

		return capability;
	}

	public Role addRole(String name, String description) {
		Role role = new Role(this,name,description);

		return role;
	}

	public Unit addUnit(String name, String description) {
		Unit unit = new Unit(this,name,description);

		return unit;
	}

	public Position addPosition(String name, String description, String unitName) {
		Unit unit = getUnit(unitName);
		return new Position(this, name, description, unit);
	}

	public Position initPosition(String name, List<String> roleNames,
								 List<String> delegatesNames, String reportsNames) throws RMException {
		// Fetch position
		Position position = getPosition(name);

		// Fetch attributes from names
		List<Role> roles = getRolesFromStringList(roleNames);
		List<Position> delegates = getPositionsFromStringList(delegatesNames);
		Position reports = null;
		if (reportsNames != null) {
			reports = getPosition(reportsNames);
		}

		// Update position
		roles.stream().forEach(r -> position.addRole(r));
		delegates.stream().forEach(p -> position.addCanDelegateWorkTo(p));
		position.setReportsTo(reports);


		return position;
	}

	public Person addPerson(String name, String description, List<String> positionsNames, List<String> capabilitiesNames) {
		List<Position> positions = getPositionsFromStringList(positionsNames);
		List<Capability> capabilities = getCapabilitiesFromStringList(capabilitiesNames);

		return new Person(this, name, description, positions, capabilities);
	}

	public Unit getUnit(String unitName) throws RMException {
		Optional<Unit> unit = getUnitSet().stream().filter(u -> u.getName().equals(unitName))
				.findFirst();

		if (!unit.isPresent()) {
			throw new RMException(RMErrorType.INVALID_UNIT_NAME, "Unit with name '" + unitName + "' does not exist");
		}

		return unit.get();
	}

	public Person getPerson(String personName) throws RMException {
		Optional<Person> person = getPersonSet().stream().filter(p -> p.getName().equals(personName))
				.findFirst();

		if (!person.isPresent()) {
			throw new RMException(RMErrorType.INVALID_PERSON_NAME, "Person with name '" + personName + "' does not exist");
		}

		return person.get();
	}

	public Position getPosition(String positionName) throws RMException {
		Optional<Position> position = getPositionSet().stream().filter(p -> p.getName().equals(positionName))
				.findFirst();

		if (!position.isPresent()) {
			throw new RMException(RMErrorType.INVALID_POSITION_NAME, "Position with name '" + positionName + "' does not exist");
		}

		return position.get();
	}

	private List<Position> getPositionsFromStringList(List<String> delegatesNames) throws RMException {
		return delegatesNames.stream().map(d -> getPosition(d)).collect(Collectors.toList());
	}

	public Capability getCapability(String capabilityName) throws RMException {
		Optional<Capability> capability = getCapabilitySet().stream().filter(c -> c.getName().equals(capabilityName))
				.findFirst();

		if (!capability.isPresent()) {
			throw new RMException(RMErrorType.INVALID_CAPABILITY_NAME, "Capability with name '" + capabilityName + "' does not exist");
		}

		return capability.get();
	}

	private List<Capability> getCapabilitiesFromStringList(List<String> capabilitiesNames) throws RMException {
		return capabilitiesNames.stream().map(c -> getCapability(c)).collect(Collectors.toList());
	}

	public Role getRole(String roleName) {
		Optional<Role> role = getRoleSet().stream().filter(r -> r.getName().equals(roleName))
				.findFirst();

		if (!role.isPresent()) {
			throw new RMException(RMErrorType.INVALID_ROLE_NAME, "Role with name '" + roleName + "' does not exist");
		}

		return role.get();
	}

	private List<Role> getRolesFromStringList(List<String> roleNames) throws RMException {
		return roleNames.stream().map(d -> getRole(d)).collect(Collectors.toList());
	}

    public boolean checkEntityIsPerson(String path) {
		Product entity = getSpec().getDataModel().getTargetOfPath(path);
		return (entity instanceof Entity) && getEntityIsPersonSet().stream()
				.anyMatch(e -> e.getName().equals(entity.getName()));
    }

	public boolean checkEntityIsPerson(Product product) {
		return (product instanceof Entity) && getEntityIsPersonSet().stream()
				.anyMatch(e -> e.getName().equals(product.getName()));
	}

	public Activity mergeActivities(RALExpression responsibleExpr1, RALExpression responsibleExpr2,
									RALExpression informsExpr1, RALExpression informsExpr2,
									Activity activityMerged, MergeResourcesPolicy mode) {
		mergeActivitiesResponsibleForExpr(responsibleExpr1, responsibleExpr2, activityMerged, mode);

		mergeActivitiesInformsExpr(informsExpr1, informsExpr2, activityMerged, mode);

		return activityMerged;
	}

	private RALExpression getMergedExpr(MergeResourcesPolicy mode, RALExpression expressionA1, RALExpression expressionA2) {
		RALExpression expressionMerged;
		switch (mode) {
			case RESTRICTED:
				expressionMerged = new RALExprAnd(this, expressionA1, expressionA2);
				break;
			case RELAXED:
				expressionMerged = new RALExprOr(this, expressionA1, expressionA2);
				break;
			default:
				throw new RMException(RMErrorType.INVALID_MERGE_TYPE);
		}

		return expressionMerged;
	}

	private void mergeActivitiesResponsibleForExpr(RALExpression expressionA1, RALExpression expressionA2, Activity activityMerged, MergeResourcesPolicy mode) {
		if (expressionA1 == null) {
			activityMerged.setResponsibleFor(expressionA2);
			return;
		}
		if (expressionA2 == null) {
			activityMerged.setResponsibleFor(expressionA1);
			return;
		}

		expressionA1.isMergable(expressionA2);

		RALExpression expressionMerged = getMergedExpr(mode, expressionA1, expressionA2);

		activityMerged.setResponsibleFor(expressionMerged);
	}

	private void mergeActivitiesInformsExpr(RALExpression expressionA1, RALExpression expressionA2, Activity activityMerged, MergeResourcesPolicy mode) {
		if (expressionA1 == null) {
			activityMerged.setInforms(expressionA2);
			return;
		}
		if (expressionA2 == null) {
			activityMerged.setInforms(expressionA1);
			return;
		}

		expressionA1.isMergable(expressionA2);

		RALExpression expressionMerged = getMergedExpr(mode, expressionA1, expressionA2);

		activityMerged.setInforms(expressionMerged);
	}

	public void cleanActivity(Activity activityOne) {
		activityOne.setResponsibleFor(null);
		activityOne.setInforms(null);
	}
}
