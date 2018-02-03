app.controller('GoalWorkItemController', function($rootScope, $scope,
		$routeParams, specRepository, instanceRepository,
		goalWorkItemRepository) {
	specId = $routeParams.specId;
	instance = $routeParams.instance;

	specRepository.getSpecification(specId).then(function(response) {
		$rootScope.spec = response.data;
	});

	instanceRepository.getWorkflowInstance(specId, instance).then(
			function(response) {
				$rootScope.instance = response.data;
			});

	$scope.getNextWorkItems = function(specId, instance) {
		goalWorkItemRepository.getNextGoalWorkItems(specId, instance).then(
				function(response) {
					$scope.nextWorkItems = response.data;
				});
	};

	$scope.getNextWorkItems(specId, instance);

	$scope.getLogWorkItems = function(specId, instance) {
		goalWorkItemRepository.getLogGoalWorkItems(specId, instance).then(
				function(response) {
					$scope.logWorkItems = response.data;
				});
	};

	$scope.getLogWorkItems(specId, instance);

	$scope.submitGoalWorkItem = function(workItemName, workItem) {
		$scope.error = '';
		goalWorkItemRepository
				.executeWorkItem(specId, instance, workItemName, workItem).then(
						function(response) {
							$scope.getNextWorkItems(specId, instance);
							$scope.getLogWorkItems(specId, instance);
						},
						function(response) {
							$scope.error = response.data.type + '('
									+ response.data.value + ')';
						});
	}

});
