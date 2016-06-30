app.controller('GoalWorkItemController', function($rootScope, $scope,
		$routeParams, specRepository, instanceRepository,
		goalWorkItemRepository) {
	specId = $routeParams.specId;
	name = $routeParams.instance;

	specRepository.getSpecification(specId).then(function(response) {
		$rootScope.spec = response.data;
	});

	instanceRepository.getWorkflowInstance(specId, name).then(
			function(response) {
				$rootScope.instance = response.data;
			});

	$scope.getNextWorkItems = function(specId, name) {
		goalWorkItemRepository.getNextGoalWorkItems(specId, name).then(
				function(response) {
					$scope.nextWorkItems = response.data;
				});
	};
	
	$scope.getNextWorkItems(specId, name);

	$scope.submitGoalWorkItem = function(goalName, workItem) {
		$scope.error = '';
		goalWorkItemRepository.executeWorkItem(specId, name, goalName,
				workItem).then(
				function(response) {
					$scope.getNextWorkItems(specId, name);
				},
				function(response) {
					$scope.error = response.data.type + '('
							+ response.data.value + ')';
				});
	}

});
