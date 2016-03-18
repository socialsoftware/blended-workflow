app.controller('SpecificationController', function($scope, specRepository) {
	specRepository.getSpecifications().then(function(response) {
		$scope.specs = response.data;
	});

	$scope.selectSpecification = function(spec) {
		$scope.spec = spec;
	};

});
