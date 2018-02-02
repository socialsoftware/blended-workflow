app.controller('DataModelController', function($rootScope, $scope, $routeParams,
		specRepository, dataRepository) {
	var specId = $routeParams.specId;
	
	specRepository.getSpecification(specId).then(
			function(response) {
				$rootScope.spec = response.data;
			});

	dataRepository.getEntities(specId).then(function(response) {
		$scope.entities = response.data;
	});

	dataRepository.getAttributes(specId).then(function(response) {
		$scope.attributes = response.data;
	});

	dataRepository.getDependencies(specId).then(function(response) {
		$scope.dependencies = response.data;
	});

	dataRepository.getRules(specId).then(function(response) {
		$scope.rules = response.data;
	});

	dataRepository.getRelations(specId).then(function(response) {
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
