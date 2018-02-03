app.controller('ActivityWorkItemController', function($rootScope, $scope,
		$routeParams, specRepository, instanceRepository,
		activityWorkItemRepository) {
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
		activityWorkItemRepository.getNextActivityWorkItems(specId, instance).then(
				function(response) {
					$scope.nextWorkItems = response.data;
				});
	};

	$scope.getNextWorkItems(specId, instance);

	$scope.getLogWorkItems = function(specId, instance) {
		activityWorkItemRepository.getLogActivityWorkItems(specId, instance).then(
				function(response) {
					$scope.logWorkItems = response.data;
				});
	};

	$scope.getLogWorkItems(specId, instance);

	$scope.submitActivityWorkItem = function(workItemName, workItem) {
		$scope.error = '';
		activityWorkItemRepository.executeWorkItem(specId, instance, workItemName,
				workItem).then(
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
