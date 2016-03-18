app.controller('DataModelController', function($scope,  dataRepository) {
	dataRepository.getEntities($scope.spec.specId).then(
			function(response) {
				$scope.entities = response.data;
			});

	dataRepository.getAttributes($scope.spec.specId).then(
			function(response) {
				$scope.attributes = response.data;
			});

	dataRepository.getDependencies($scope.spec.specId).then(
			function(response) {
				$scope.dependencies = response.data;
			});

	dataRepository.getRules($scope.spec.specId).then(
			function(response) {
				$scope.rules = response.data;
			});

	dataRepository.getRelations($scope.spec.specId).then(
			function(response) {
				$scope.relations = response.data;
			});

	$scope.hasDependencies = function(productExtId) {
		for (i = 0; i < $scope.dependencies.length; i++) { 
		    if ($scope.dependencies[i].productExtId == productExtId)
		    	return true;
		}
		return false;
	}

	$scope.hasRules = function(entityName) {
		for (i = 0; i < $scope.rules.length; i++) { 
		    if ($scope.rules[i].entityName == entityName)
		    	return true;
		}
		return false;
	}
});
