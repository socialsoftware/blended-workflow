app.controller('SpecificationController', function($scope, specRepository, instanceRepository) {
	$scope.newInstanceName = '';
	$scope.specError = '';
	
	specRepository.getSpecifications().then(function(response) {
		$scope.specs = response.data;
	});

	$scope.selectSpecification = function(spec) {
		$scope.spec = spec;
		instanceRepository.getWorkflowInstances(spec.specId).then(function(response) {
			$scope.instances = response.data;
		})
	};

	$scope.selectInstance = function(instance) {
		$scope.instance = instance
	};
	
	$scope.submitInstanceForm = function(specId, newInstanceName) {
		$scope.specError = '';
		instanceRepository.createWorkflowInstance(specId,
				newInstanceName).then(
				function(response) {
					$scope.updateState();
				},
				function(response) {
					$scope.specError = response.data.type + '('
							+ response.data.value + ')';
				});
	};
	
	$scope.deleteInstance = function(specId, name) {
		$scope.specError = '';
		instanceRepository.deleteWorkflowInstance(specId,
				name).then(
				function(response) {
					$scope.updateState();
				},
				function(response) {
					$scope.specError = response.data.type + '('
							+ response.data.value + ')';
				});
	};
	
	$scope.updateState = function() {
		$scope.newInstanceName = '';
		instanceRepository.getWorkflowInstances($scope.spec.specId).then(function(response) {
			$scope.instances = response.data;
		});
	};
	
	$scope.cleanInstanceAll = function() {
		$scope.specError = '';
		$scope.newInstanceName = '';
		$scope.instance = '';
	}
	
	$scope.cleanAll = function() {
		$scope.spec = '';
		$scope.cleanInstanceAll();
	}
	
});
