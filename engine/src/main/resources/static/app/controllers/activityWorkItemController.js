app.controller('ActivityWorkItemController', function($rootScope, $scope, $routeParams, specRepository, activityWorkItemRepository) {
	specId = $routeParams.specId;
	instance = $routeParams.instance;
		
	activityWorkItemRepository.getActivityWorkItems(specId, instance).then(
		function(response) {
			$scope.workItems = response.data;
		});
			
});