app.controller('specController', function($scope, specRepository) {
	specRepository.getSpecifications().then(function(response) {
		$scope.specs = response.data;
	});

	$scope.selectSpecification = function(spec) {
		$scope.spec = spec;
	};

});
