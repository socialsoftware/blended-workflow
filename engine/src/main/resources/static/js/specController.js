app.controller('specController', function($scope, $http) {
	$scope.model = "";

	$http.get("specs").then(function(response) {
		$scope.specs = response.data;
	});

	$scope.dataModel = function() {
		if ($scope.spec) {
			$scope.model = "dataModel";
		} else {
			$scope.model = "";
		}
	}

	$scope.conditionModel = function() {
		if ($scope.spec) {
			$scope.model = "conditionModel";
		} else {
			$scope.model = "";
		}
	}

	$scope.goalModel = function() {
		if ($scope.spec) {
			$scope.model = "goalModel";
		} else {
			$scope.model = "";
		}
	}

	$scope.activityModel = function() {
		if ($scope.spec) {
			$scope.model = "activityModel";
		} else {
			$scope.model = "";
		}
	}

});