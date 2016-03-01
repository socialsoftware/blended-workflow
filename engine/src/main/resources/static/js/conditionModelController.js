app.controller('conditionModelController', function($scope, $http) {
	$http.get("specs/" + $scope.spec.specId + "/conditionmodel/entityachieveconditions").then(
			function(response) {
				$scope.entityachieveconditions = response.data;
			});

	$http.get("specs/" + $scope.spec.specId + "/conditionmodel/entitydependenceconditions").then(
			function(response) {
				$scope.entitydependenceconditions = response.data;
			});

	$http.get("specs/" + $scope.spec.specId + "/conditionmodel/attributeachieveconditions").then(
			function(response) {
				$scope.attributeachieveconditions = response.data;
			});

	$http.get("specs/" + $scope.spec.specId + "/conditionmodel/attributedependenceconditions").then(
			function(response) {
				$scope.attributedependenceconditions = response.data;
			});

	$http.get("specs/" + $scope.spec.specId + "/conditionmodel/entityinvariantconditions").then(
			function(response) {
				$scope.entityinvariantconditions = response.data;
			});

	$http.get("specs/" + $scope.spec.specId + "/conditionmodel/attributeinvariantconditions").then(
			function(response) {
				$scope.attributeinvariantconditions = response.data;
			});

});