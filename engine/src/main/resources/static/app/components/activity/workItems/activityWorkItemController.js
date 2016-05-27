app.controller('ActivityWorkItemController', function($rootScope, $scope,
		$routeParams, specRepository, instanceRepository,
		activityWorkItemRepository) {
	specId = $routeParams.specId;
	name = $routeParams.instance;

	specRepository.getSpecification(specId).then(function(response) {
		$rootScope.spec = response.data;
	});

	instanceRepository.getWorkflowInstance(specId, name).then(
			function(response) {
				$rootScope.instance = response.data;
			});

	$scope.getWorkItems = function(specId, name) {
		activityWorkItemRepository.getActivityWorkItems(specId, name).then(
				function(response) {
					$scope.workItems = response.data;
				});
	};
	
	$scope.getWorkItems(specId, name);

	$scope.submitActivityWorkItem = function(activityName, workItem) {
		$scope.error = '';
		activityWorkItemRepository.executeWorkItem(specId, name, activityName,
				workItem).then(
				function(response) {
					$scope.getWorkItems(specId, name);
				},
				function(response) {
					$scope.error = response.data.type + '('
							+ response.data.value + ')';
				});
	}

});
