app.directive('errorMessage', function($uibModal) {
	return {
		restrict : 'AE',
		replace : true,
		scope : {
			errorData : "@"
		},
		link : function(scope, elem, attrs) {
			open = function(errorData) {
				$uibModal.open({
					animation : true,
					templateUrl : '../../views/errorMessage.html',
					controller : 'ModalInstanceCtrl',
					size : 'lg',
					resolve : {
						errorMessage : function() {
							return errorData;
						}
					}
				});
			};
						
			scope.$watch('errorData', function(newValue, oldValue) {
				open(scope.errorData);
			});

		}
	};
});

app.controller('ModalInstanceCtrl', function($scope, $uibModalInstance,
		errorMessage) {
	$scope.errorMessage = errorMessage;

	$scope.ok = function() {
		$uibModalInstance.close();
	};

});
