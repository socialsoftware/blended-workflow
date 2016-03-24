app.controller('GoalGraphController', function($rootScope, $scope,
		$routeParams, specRepository, goalRepository) {

	var specId = $routeParams.specId;

	specRepository.getSpecification(specId).then(function(response) {
		$rootScope.spec = response.data;
	});

	$scope.readGraph = function(specId) {
		goalRepository.getGoalGraph(specId).then(function(response) {
			$scope.goalGraph = response.data;
		});
	};


	$scope.updateState = function() {
		//$scope.readActivities(specId);
		$scope.readGraph(specId);
		//$scope.operations.selectedOperation = $scope.operations.availableOperations[0];
	};
	$scope.updateState();

});
