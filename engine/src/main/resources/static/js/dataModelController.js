app.controller('dataModelController', function($scope, $http) {
	$http.get("specs/" + $scope.spec.specId + "/datamodel/entities").then(
			function(response) {
				$scope.entities = response.data;
			});

	$http.get("specs/" + $scope.spec.specId + "/datamodel/attributes").then(
			function(response) {
				$scope.attributes = response.data;
			});

	$http.get("specs/" + $scope.spec.specId + "/datamodel/dependencies").then(
			function(response) {
				$scope.dependencies = response.data;
			});

	$http.get("specs/" + $scope.spec.specId + "/datamodel/rules").then(
			function(response) {
				$scope.rules = response.data;
			});

	$http.get("specs/" + $scope.spec.specId + "/datamodel/relations").then(
			function(response) {
				$scope.relations = response.data;
			});
	
	$scope.hasDependencies = function(productExtId) {
		for (i = 0; i < $scope.dependencies.length; i++) { 
		    if ($scope.dependencies[i].productExtId == productExtId)
		    	return true;
		}
		return false;
	}

	$scope.hasRules = function(entityName) {
		for (i = 0; i < $scope.rules.length; i++) { 
		    if ($scope.rules[i].entityName == entityName)
		    	return true;
		}
		return false;
	}
});
