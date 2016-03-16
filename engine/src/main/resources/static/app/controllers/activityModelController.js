app.controller('activityModelController', function($scope, activityRepository) {
	activityRepository.getActivities($scope.spec.specId).then(
			function(response) {
				$scope.activities = response.data;
			});

	$scope.preconditions = new Map();
	$scope.preConditions = function(activity) {
		activityRepository.getPreConditions($scope.spec.specId, activity).then(
				function(response) {
					$scope.preconditions.set(activity, response.data);
				});
	}

	$scope.seqconditions = new Map();
	$scope.seqConditions = function(activity) {
		activityRepository.getSeqConditions($scope.spec.specId, activity)
				.then(function(response) {
					$scope.seqconditions.set(activity, response.data);
				});
	}

	$scope.postconditions = new Map();
	$scope.postConditions = function(activity) {
		activityRepository.getPostConditions($scope.spec.specId, activity)
				.then(function(response) {
					$scope.postconditions.set(activity, response.data);
				});
	}

	$scope.mulconditions = new Map();
	$scope.mulConditions = function(activity) {
		activityRepository.getMulConditions($scope.spec.specId, activity)
				.then(function(response) {
					$scope.mulconditions.set(activity, response.data);
				});
	}

	$scope.ruleconditions = new Map();
	$scope.ruleConditions = function(activity) {
		activityRepository.getRuleConditions($scope.spec.specId, activity)
				.then(function(response) {
					$scope.ruleconditions.set(activity, response.data);
				});
	}

});
