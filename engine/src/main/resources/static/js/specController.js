app.controller('specController', function($scope, $http) {
	$scope.model = "";

	$http.get("specs").then(function(response) {
		$scope.specs = response.data;
	});

	$scope.setModel = function(modelType) {
		if ($scope.spec) {
			$scope.model = modelType;
		} else {
			$scope.model = "";
		}
	};
	
	$scope.selectSpecification = function(spec) {
		$scope.spec = spec;
		$scope.model = "";
	};

});