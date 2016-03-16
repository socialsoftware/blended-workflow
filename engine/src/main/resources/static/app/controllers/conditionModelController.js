app.controller('conditionModelController', function($scope, conditionRepository) {
	conditionRepository.getEntityAchieveConditions($scope.spec.specId).then(
			function(response) {
				$scope.entityachieveconditions = response.data;
			});

	conditionRepository.getEntityDependenceConditions($scope.spec.specId).then(
			function(response) {
				$scope.entitydependenceconditions = response.data;
			});

	conditionRepository.getAttributeAchieveConditions($scope.spec.specId).then(
			function(response) {
				$scope.attributeachieveconditions = response.data;
			});

	conditionRepository.getAttributeDependenceConditions($scope.spec.specId).then(
			function(response) {
				$scope.attributedependenceconditions = response.data;
			});

	conditionRepository.getEntityInvariantConditions($scope.spec.specId).then(
			function(response) {
				$scope.entityinvariantconditions = response.data;
			});

	conditionRepository.getAttributeInvariantConditions($scope.spec.specId).then(
			function(response) {
				$scope.attributeinvariantconditions = response.data;
			});
});

