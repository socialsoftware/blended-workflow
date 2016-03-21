app
		.controller(
				'ActivityGraphController',
				function($rootScope, $scope, $routeParams, specRepository,
						activityRepository) {

					var specId = $routeParams.specId;

					specRepository.getSpecification(specId).then(
							function(response) {
								$rootScope.spec = response.data;
							});

					$scope.operations = {
						availableOperations : [ {
							id : 0,
							name : '--- Operation ---'
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
											$scope.activitiesOne = {
												availableActivities : [ {
													name : '--- Activity ---'
												} ].concat(response.data),
												selectedActivity : {
													name : '--- Activity ---'
												}
											};
											$scope.activitiesOne.selectedActivity = $scope.activitiesOne.availableActivities[0];
											$scope.activitiesTwo = {
												availableActivities : [ {
													name : '--- with Activity ---'
												} ].concat(response.data),
												selectedActivity : {
													name : '--- with Activity ---'
												}
											};
											$scope.activitiesTwo.selectedActivity = $scope.activitiesTwo.availableActivities[0];
										});
					};
					$scope.readActivities(specId);

					$scope.activityPosConditions = {
						availablePostConditions : [ {
							path : '--- Post Conditions ---'
						} ],
						selectedPostConditions : []
					};

					$scope.readActivityPostConditions = function(specId,
							activity) {
						activityRepository
								.getPostConditions(specId, activity)
								.then(
										function(response) {
											$scope.activityPosConditions = {
												availablePostConditions : []
														.concat(
																response.data.defAtts)
														.concat(
																response.data.defEnts),
												selectedPostConditions : []
											};
										});
					};

					$scope.readGraph = function(specId) {
						activityRepository.getActivityGraph(specId).then(
								function(response) {
									$scope.activityGraph = response.data;
								});
					};

					$scope.readGraph(specId);

					$scope.activityNameInput = function() {
						// rename, merge, and split operations
						return ($scope.operations.selectedOperation.id >= 1 && $scope.operations.selectedOperation.id <= 3);

					};

					$scope.activitiesTwoSelect = function() {
						// merge operation
						return ($scope.operations.selectedOperation.id == 2);

					};

					$scope.activitiesPostConditionsSelect = function() {
						// split operation
						return ($scope.operations.selectedOperation.id == 3);

					};

					$scope.validForm = function() {
						// applies to all operations

						// has to select an activity
						if ($scope.activitiesOne.selectedActivity == $scope.activitiesOne.availableActivities[0])
							return false;

						// rename, merge and split operation require a name for
						// the new operation
						if (($scope.operations.selectedOperation.id >= 1 && $scope.operations.selectedOperation.id <= 3)) {
							// the new activity name should not exist
							for (i = 0; i < $scope.activitiesOne.availableActivities.length; i++)
								if ($scope.activitiesOne.availableActivities[i].name == $scope.newActivityName)
									return false;
						}

						// merge operation
						if ($scope.operations.selectedOperation.id == 2) {
							// has to select the second activity
							if ($scope.activitiesTwo.selectedActivity == $scope.activitiesTwo.availableActivities[0])
								return false;

							// the second selected activity should be different
							// from the first
							for (i = 0; i < $scope.activitiesTwo.availableActivities.length; i++)
								if ($scope.activitiesTwo.selectedActivity.name == $scope.activitiesOne.selectedActivity.name)
									return false;
						}

						// split operation
						if ($scope.operations.selectedOperation.id == 3) {
							if ($scope.activityPosConditions.selectedPostConditions.length < 1
									|| $scope.activityPosConditions.selectedPostConditions.length == $scope.activityPosConditions.availablePostConditions.length)
								return false;
						}

						return true;
					};

					$scope.submitForm = function() {
						switch ($scope.operations.selectedOperation.id) {
						case 1: // rename
							activityRepository.renameActivity(specId,
									$scope.activitiesOne.selectedActivity.name,
									$scope.newActivityName).then(
									function() {
										$scope.readActivities(specId);
										$scope.readGraph(specId);
									},
									function(error) {
										alert(response.data.type + '('
												+ response.data.value + ')');
									});
							break;
						case 2: // merge
							activityRepository.mergeActivities(specId,
									$scope.activitiesOne.selectedActivity.name,
									$scope.activitiesTwo.selectedActivity.name,
									$scope.newActivityName).then(
									function(response) {
										$scope.readActivities(specId);
										$scope.readGraph(specId);
									},
									function(response) {
										alert(response.data.type + '('
												+ response.data.value + ')');
									});
							break;
						case 3: // split
							activityRepository.splitActivity(specId,
									$scope.activitiesOne.selectedActivity.name,
									$scope.activityPosConditions.selectedPostConditions,
									$scope.newActivityName).then(
											function(ressponse) {
												$scope.readActivities(specId);
												$scope.readGraph(specId);
											},
											function(response) {
												alert(response.data.type + '('
														+ response.data.value + ')');												
											});
							break;
						case 4: // add sequence
							break;
						case 5: // remove sequence
							break;
						}
					};

				});
