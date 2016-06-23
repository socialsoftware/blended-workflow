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

	$scope.getNextWorkItems = function(specId, name) {
		activityWorkItemRepository.getNextActivityWorkItems(specId, name).then(
				function(response) {
					$scope.nextWorkItems = response.data;
				});
	};
	
	$scope.getNextWorkItems(specId, name);

	$scope.getLogWorkItems = function(specId, name) {
		activityWorkItemRepository.getLogActivityWorkItems(specId, name).then(
				function(response) {
					$scope.logWorkItems = response.data;
				});
	};
	
	$scope.getLogWorkItems(specId, name);

	$scope.submitActivityWorkItem = function(activityName, workItem) {
		$scope.error = '';
		activityWorkItemRepository.executeWorkItem(specId, name, activityName,
				workItem).then(
				function(response) {
					$scope.getNextWorkItems(specId, name);
					$scope.getLogWorkItems(specId, name);
				},
				function(response) {
					$scope.error = response.data.type + '('
							+ response.data.value + ')';
				});
	}

});
