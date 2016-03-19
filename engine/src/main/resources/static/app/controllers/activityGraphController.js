app
.controller(
		'ActivityGraphController',
		function($rootScope, $scope, $routeParams,
				specRepository, activityRepository) {
			
			var specId = $routeParams.specId;
			
			specRepository.getSpecification(specId).then(
					function(response) {
						$rootScope.spec = response.data;
					});

			$scope.operations = {
					availableOperations : [ {
						id : 0,
						name : '---Choose Operation---'
					}, {
						id : 1,
						name : 'Rename Activity'
					}, {
						id : 2,
						name : 'Merge Activities'
					}, {
						id : 3,
						name : 'Split Activity'
					}, {
						id : 4,
						name : 'Add Sequence'
					}, {
						id : 5,
						name : 'Remove Sequence'
					} ],
					selectedOperation : {
						id : '0',
						name : '---Choose Operation---'
					}
			};

			$scope.readActivities = function(specId) {
				activityRepository
				.getActivities(specId)
				.then(
						function(response) {
							$scope.activities = {
									availableActivities : [ {
										name : '---Choose Activity---'
									} ].concat(response.data),
									selectedActivity : {
										name : '---Choose Activity---'
									}
							};
							$scope.activities.selectedActivity = $scope.activities.availableActivities[0];
						});
			};
			$scope.readActivities(specId);

			$scope.readGraph = function(specId) {
				activityRepository.getActivityGraph(specId).then(
						function(response) {
							$scope.activityGraph = response.data;
						});
			};

			$scope.readGraph(specId);

			$scope.activityNameInput = function() {
				return ($scope.operations.selectedOperation.id >= 1 && $scope.operations.selectedOperation.id <= 3);

			};

			$scope.validForm = function() {
				if ($scope.activities.selectedActivity == $scope.activities.availableActivities[0])
					return false;

				for (i = 0; i < $scope.activities.availableActivities.length; i++)
					if ($scope.activities.availableActivities[i].name == $scope.newActivityName)
						return false;

				return true;
			};

			$scope.submitForm = function() {
				if ($scope.operations.selectedOperation.id == 1)
					activityRepository
					.renameActivity(
							specId,
							$scope.activities.selectedActivity.name,
							$scope.newActivityName)
							.success(
									function() {
										$scope.status = 'Updated Activity! Refreshing.';
										$scope.readActivities(specId);
										$scope.readGraph(specId);
									})
									.error(
											function(error) {
												$scope.status = 'Unable to rename activity: '
													+ error.message;
											});
			};

		});
