function WorkItemController() {
	var ctrl = this;

	ctrl.entityCounter = 0;

	ctrl.nextEntityCounter = function () {
		ctrl.entityCounter = ctrl.entityCounter - 1;
	}

	ctrl.initializeDefinitionGroupInstance = function () {
		for (i in ctrl.workItem.definitionGroupSet) {
			let definitionGroup = ctrl.workItem.definitionGroupSet[i];
			if (definitionGroup.definitionGroupInstanceSet.length == 0 &&
				definitionGroup.innerRelationSet.length == 0) {
				ctrl.createDefinitionGroupInstance(definitionGroup);
			}

			for (j in definitionGroup.innerRelationSet) {
				let difference = definitionGroup.innerRelationSet[j].mulCondition.sourceMin - definitionGroup.definitionGroupInstanceSet.length;
				for (k = 0; k < difference; k++) {
					ctrl.createDefinitionGroupInstance(definitionGroup);
				}
			}
		}
	}

	ctrl.canCreateDefinitionGroupInstance = function (definitionGroup) {
		if (definitionGroup.definitionGroupInstanceSet.length == 0) {
			return true;
		}

		if (definitionGroup.innerRelationSet.length != 0) {
			for (i in definitionGroup.innerRelationSet) {
				if (definitionGroup.innerRelationSet[i].mulCondition.sourceMax > (definitionGroup.definitionGroupInstanceSet.length)) {
					return true;
				}
			}
		}

		return false;
	}

	ctrl.createDefinitionGroupInstance = function createDefinitionGroupInstance(
		definitionGroup) {
		definitionGroupInstance = {
			entityInstanceContextSet: [],
			productInstanceSet: [],
			innerRelationInstanceSet: []

		};
		if (definitionGroup.defEnt != null) {
			ctrl.nextEntityCounter();
			productInstance = {
				product: {
					productType: 'ENTITY'
				},
				path: definitionGroup.defEnt.path,
				externalId: ctrl.entityCounter,
				id: ctrl.entityCounter,
				value: ""
			};
			definitionGroupInstance.productInstanceSet.push(productInstance);
			ctrl.addToInnerRelations(productInstance);
		}
		for (i in definitionGroup.defAtts) {
			ctrl.nextEntityCounter();
			definitionGroupInstance.productInstanceSet.push({
				product: {
					productType: 'ATTRIBUTE'
				},
				path: definitionGroup.defAtts[i].path,
				externalId: ctrl.entityCounter,
				id: ctrl.entityCounter,
				value: ""
			})
		}
		for (i in definitionGroup.innerRelationSet) {
			definitionGroupInstance.innerRelationInstanceSet
				.push({
					mulConditionDTO: definitionGroup.innerRelationSet[i].mulCondition,
					entityInstanceSet: []
				})
		}
		// ctrl.workItem.definitionGroup.definitionGroupInstanceSet.innerRelationInstanceSet
		// .forEach(function(val) {
		// alert(val.mulConditionDTO.rolePath);
		// })

		definitionGroup.definitionGroupInstanceSet
			.push(definitionGroupInstance);
	};

	ctrl.deleteDefinitionGroupInstance = function (definitionGroup, externalId) {
		ctrl.deleteFromInnerRelations(externalId);
		for (i in definitionGroup.definitionGroupInstanceSet) {
			if (definitionGroup.definitionGroupInstanceSet[i].productInstanceSet[0].externalId === externalId) {
				definitionGroup.definitionGroupInstanceSet.splice(i, 1);
				return;
			}
		}
	}

	ctrl.getInnerRelationEntityInstances = function (definitionGroup,
		externalId) {
		for (i in definitionGroup.innerRelationSet) {
			if (definitionGroup.innerRelationSet[i].mulCondition.externalId == externalId) {
				return definitionGroup.innerRelationSet[i].entityInstanceSet;
			}
		}
	}

	ctrl.addToInnerRelations = function (entityInstance) {
		for (j in ctrl.workItem.definitionGroupSet) {
			for (i in ctrl.workItem.definitionGroupSet[j].innerRelationSet) {
				if (ctrl.workItem.definitionGroupSet[j].innerRelationSet[i].targetEntity.name === entityInstance.path) {
					ctrl.workItem.definitionGroupSet[j].innerRelationSet[i].entityInstanceSet
						.push(entityInstance);
				}
			}
		}
	}

	ctrl.deleteFromInnerRelations = function (externalId) {
		for (j in ctrl.workItem.definitionGroupSet) {
			for (i in ctrl.workItem.definitionGroupSet[j].innerRelationSet) {
				for (k in ctrl.workItem.definitionGroupSet[j].innerRelationSet[i].entityInstanceSet) {
					if (ctrl.workItem.definitionGroupSet[j].innerRelationSet[i].entityInstanceSet[k].externalId == externalId) {
						ctrl.workItem.definitionGroupSet[j].innerRelationSet[i].entityInstanceSet
							.splice(k, 1);
					}
				}
			}
		}
	}

	ctrl.submitWorkItem = function () {
		ctrl.onUpdate({
			'activityName': ctrl.name,
			'workItem': ctrl.workItem
		});
	}

}

app.component('workItem', {
	templateUrl: 'app/shared/workItem/workItem.html',
	controller: WorkItemController,
	bindings: {
		name: '<',
		workItem: '<',
		onUpdate: '&'
	}
});