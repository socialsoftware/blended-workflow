app.controller('ActivityGraphController', function($scope, activityRepository) {
	activityRepository.getActivityGraph($scope.spec.specId).then(
			function(response) {
				$scope.activityGraph = response.data;
			});
});
