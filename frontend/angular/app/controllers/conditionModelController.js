app.controller('ConditionModelController', function($rootScope, $scope, $routeParams,
		specRepository, conditionRepository) {
	var specId = $routeParams.specId;
	
	specRepository.getSpecification(specId).then(
			function(response) {
				$rootScope.spec = response.data;
			});

	conditionRepository.getEntityAchieveConditions(specId).then(
			function(response) {
				$scope.entityachieveconditions = response.data;
			});

	conditionRepository.getEntityDependenceConditions(specId).then(
			function(response) {
				$scope.entitydependenceconditions = response.data;
			});

	conditionRepository.getAttributeAchieveConditions(specId).then(
			function(response) {
				$scope.attributeachieveconditions = response.data;
			});

	conditionRepository.getAttributeDependenceConditions(specId).then(
			function(response) {
				$scope.attributedependenceconditions = response.data;
			});

	conditionRepository.getEntityInvariantConditions(specId).then(
			function(response) {
				$scope.entityinvariantconditions = response.data;
			});

	conditionRepository.getAttributeInvariantConditions(specId).then(
			function(response) {
				$scope.attributeinvariantconditions = response.data;
			});
});

