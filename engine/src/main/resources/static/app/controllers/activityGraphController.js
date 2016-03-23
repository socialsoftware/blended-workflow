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

					$scope.activityPostConditions = {
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
											$scope.activityPostConditions = {
												availablePostConditions : []
														.concat(
																response.data.defAtts)
														.concat(
																response.data.defEnts),
												selectedPostConditions : []
											};
										});
					};

					$scope.activitySeqConditions = {
						availableSeqConditions : [ {
							path : '--- Sequence Condition ---'
						} ],
						selectedSeqCondition : {
							path : '--- Sequence Condition ---'
						}
					};
					$scope.activitySeqConditions.selectedSeqCondition = $scope.activitySeqConditions.availableSeqConditions[0];

					$scope.readActivitySeqConditions = function(specId,
							activity) {
						activityRepository
								.getSeqConditions(specId, activity)
								.then(
										function(response) {
													$scope.activitySeqConditions.availableSeqConditions = [ $scope.activitySeqConditions.availableSeqConditions[0] ]
															.concat(response.data),
													$scope.activitySeqConditions.selectedSeqCondition = $scope.activitySeqConditions.availableSeqConditions[0];
										});

					};

					$scope.readGraph = function(specId) {
						activityRepository.getActivityGraph(specId).then(
								function(response) {
									$scope.activityGraph = response.data;
								});
					};

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

					$scope.sequenceConditionInput = function() {
						// add sequence condition
						return ($scope.operations.selectedOperation.id == 4);
					};

					$scope.removeSequenceConditionSelect = function() {
						// remove sequence condition
						return ($scope.operations.selectedOperation.id == 5);
					};

					$scope.validForm = function() {
						// applies to all operations

						// for all operations it is necessary to select an
						// activity, but not the header
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
							// has to select the second activity, but not the
							// header
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
							// at least one of post condition is selected, but
							// nor all the conditions
							if ($scope.activityPostConditions.selectedPostConditions.length < 1
									|| $scope.activityPostConditions.selectedPostConditions.length == $scope.activityPostConditions.availablePostConditions.length)
								return false;
						}

						// remove sequence condition operation
						if ($scope.operations.selectedOperation.id == 5) {
							// has to select a sequence condition, but not the
							// header
							if ($scope.activitySeqConditions.selectedSeqCondition == $scope.activitySeqConditions.availableSeqConditions[0])
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
										$scope.updateState();
									},
									function(error) {
										$scope.error = response.data.type + '('
												+ response.data.value + ')';
									});
							break;
						case 2: // merge
							activityRepository.mergeActivities(specId,
									$scope.activitiesOne.selectedActivity.name,
									$scope.activitiesTwo.selectedActivity.name,
									$scope.newActivityName).then(
									function(response) {
										$scope.updateState();
									},
									function(response) {
										$scope.error = response.data.type + '('
												+ response.data.value + ')';
									});
							break;
						case 3: // split
							activityRepository
									.splitActivity(
											specId,
											$scope.activitiesOne.selectedActivity.name,
											$scope.activityPostConditions.selectedPostConditions,
											$scope.newActivityName)
									.then(
											function(ressponse) {
												$scope.updateState();
											},
											function(response) {
												$scope.error = response.data.type
														+ '('
														+ response.data.value
														+ ')';
											});
							break;
						case 4: // add sequence
							activityRepository.addSequenceCondition(specId,
									$scope.activitiesOne.selectedActivity.name,
									$scope.sequenceConditionToAdd).then(
									function(ressponse) {
										$scope.updateState();
									},
									function(response) {
										$scope.error = response.data.type + '('
												+ response.data.value + ')';
									});
							break;
						case 5: // remove sequence
							activityRepository
									.remSequenceCondition(
											specId,
											$scope.activitiesOne.selectedActivity.name,
											$scope.activitySeqConditions.selectedSeqCondition.path)
									.then(
											function(ressponse) {
												$scope.updateState();
											},
											function(response) {
												$scope.error = response.data.type
														+ '('
														+ response.data.value
														+ ')';
											});
							break;
						}
					};

					$scope.updateState = function() {
						$scope.readActivities(specId);
						$scope.readGraph(specId);
						$scope.operations.selectedOperation = $scope.operations.availableOperations[0];
					};
					$scope.updateState();

					$scope.updateSelects = function(specId, activity) {
						// split operation
						if ($scope.operations.selectedOperation.id == 3)
							$scope.readActivityPostConditions(specId, activity);

						// remove sequence condition operation
						if ($scope.operations.selectedOperation.id == 5)
							$scope.readActivitySeqConditions(specId, activity);
					};

					$scope.cleanAll = function() {
						$scope.activitiesOne.selectedActivity = $scope.activitiesOne.availableActivities[0];
						$scope.activitiesTwo.selectedActivity = $scope.activitiesTwo.availableActivities[0];
						$scope.activityPostConditions.availablePostConditions = [ {
							path : '--- Post Conditions ---'
						} ]
						$scope.newActivityName = '';
						$scope.sequenceConditionToAdd = '';
						$scope.activitySeqConditions.availableSeqConditions = [ {
							path : '--- Sequence Condition ---'
						} ];
						$scope.activitySeqConditions.selectedSeqCondition = $scope.activitySeqConditions.availableSeqConditions[0];
					};

				});
