app
		.controller(
				'GoalGraphController',
				function($rootScope, $scope, $routeParams, specRepository,
						goalRepository) {

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
							name : 'Rename Goal'
						}, {
							id : 2,
							name : 'Merge Goal'
						}, {
							id : 3,
							name : 'Split Goal'
						} ],
						selectedOperation : {
							id : '0',
							name : '---Choose Operation---'
						}
					};

					$scope.readGoals = function(specId) {
						goalRepository
								.getGoals(specId)
								.then(
										function(response) {
											$scope.goalsOne = {
												availableGoals : [ {
													name : '--- Goal ---'
												} ].concat(response.data),
												selectedGoal : {
													name : '--- Goal ---'
												}
											};
											$scope.goalsOne.selectedGoal = $scope.goalsOne.availableGoals[0];
											$scope.goalsTwo = {
												availableGoals : [ {
													name : '--- with Goal ---'
												} ].concat(response.data),
												selectedGoal : {
													name : '--- with Goal ---'
												}
											};
											$scope.goalsTwo.selectedGoal = $scope.goalsTwo.availableGoals[0];
										});
					};

					$scope.goalSucConditions = {
						availableSucConditions : [ {
							path : '--- Success Conditions ---'
						} ],
						selectedSucConditions : []
					};

					$scope.readGoalSucConditions = function(specId, goal) {
						goalRepository
								.getEntitySuccessConditions(specId, goal)
								.then(
										function(response) {
											$scope.goalSucConditions = {
												availableSucConditions : []
														.concat(response.data),
												selectedSucConditions : []
											};
											goalRepository
													.getAttributeSuccessConditions(
															specId, goal)
													.then(
															function(response) {
																$scope.goalSucConditions = {
																	availableSucConditions : $scope.goalSucConditions.availableSucConditions
																			.concat(response.data),
																};
															});
										});
					};

					$scope.readGraph = function(specId) {
						goalRepository.getGoalGraph(specId).then(
								function(response) {
									$scope.goalGraph = response.data;
								});
					};

					$scope.goalNameInput = function() {
						// rename, merge, and split operations
						return ($scope.operations.selectedOperation.id >= 1 && $scope.operations.selectedOperation.id <= 3);
					};

					$scope.goalsTwoSelect = function() {
						// merge operation
						return ($scope.operations.selectedOperation.id == 2);
					};

					$scope.goalSucConditionsSelect = function() {
						// split operation
						return ($scope.operations.selectedOperation.id == 3);
					};

					$scope.validForm = function() {
						// applies to all operations

						// for all operations it is necessary to select a
						// goal, but not the header
						if ($scope.goalsOne.selectedGoal == $scope.goalsOne.availableGoals[0])
							return false;

						// rename, merge and split operation require a name for
						// the new operation
						if (($scope.operations.selectedOperation.id >= 1 && $scope.operations.selectedOperation.id <= 3)) {
							// the new goal name should not exist
							for (i = 0; i < $scope.goalsOne.availableGoals.length; i++)
								if ($scope.goalsOne.availableGoals[i].name == $scope.newGoalName)
									return false;
						}

						// merge operation
						if ($scope.operations.selectedOperation.id == 2) {
							// has to select the second goal, but not the
							// header
							if ($scope.goalsTwo.selectedActivity == $scope.goalsTwo.availableGoals[0])
								return false;

							// the second selected activity should be different
							// from the first
							for (i = 0; i < $scope.goalsTwo.availableGoals.length; i++)
								if ($scope.goalsTwo.selectedGoal.name == $scope.goalsOne.selectedGoal.name)
									return false;
						}

						// split operation
						if ($scope.operations.selectedOperation.id == 3) {
							// at least one of success condition is selected,
							// but
							// nor all the conditions
							if ($scope.goalSucConditions.selectedSucConditions.length < 1
									|| $scope.goalSucConditions.selectedSucConditions.length == $scope.goalSucConditions.availableSucConditions.length
									|| $scope.splitType == '')
								return false;
						}

						return true;
					};

					$scope.submitForm = function() {
						// clean error message
						$scope.error = '';

						switch ($scope.operations.selectedOperation.id) {
						case 1: // rename
							goalRepository.renameGoal(specId,
									$scope.goalsOne.selectedGoal.name,
									$scope.newGoalName).then(
									function() {
										$scope.updateState();
									},
									function(error) {
										$scope.error = response.data.type + '('
												+ response.data.value + ')';
									});
							break;
						case 2: // merge
							goalRepository.mergeGoals(specId,
									$scope.goalsOne.selectedGoal.name,
									$scope.goalsTwo.selectedGoal.name,
									$scope.newGoalName).then(
									function(response) {
										$scope.updateState();
									},
									function(response) {
										$scope.error = response.data.type + '('
												+ response.data.value + ')';
									});
							break;
						case 3: // split
							if ($scope.splitType == 'child') {
								goalRepository
										.splitChildGoal(
												specId,
												$scope.goalsOne.selectedGoal.name,
												$scope.goalSucConditions.selectedSucConditions,
												$scope.newGoalName)
										.then(
												function(response) {
													$scope.updateState();
												},
												function(response) {
													$scope.error = response.data.type
															+ '('
															+ response.data.value
															+ ')';
												});
							} else if ($scope.splitType == 'sibling') {
								goalRepository
										.splitSiblingGoal(
												specId,
												$scope.goalsOne.selectedGoal.name,
												$scope.goalSucConditions.selectedSucConditions,
												$scope.newGoalName)
										.then(
												function(response) {
													$scope.updateState();
												},
												function(response) {
													$scope.error = response.data.type
															+ '('
															+ response.data.value
															+ ')';
												});
							}
							break;
						}
					};

					$scope.updateState = function() {
						$scope.readGoals(specId);
						$scope.readGraph(specId);
						$scope.operations.selectedOperation = $scope.operations.availableOperations[0];
					};
					$scope.updateState();

					$scope.updateSelects = function(specId, goal) {
						// split operation
						if ($scope.operations.selectedOperation.id == 3)
							$scope.readGoalSucConditions(specId, goal);
					};

					$scope.cleanAll = function() {
						$scope.goalsOne.selectedGoal = $scope.goalsOne.availableGoals[0];
						$scope.goalsTwo.selectedGoal = $scope.goalsTwo.availableGoals[0];
						$scope.goalSucConditions.availableSucConditions = [ {
							path : '--- Success Conditions ---'
						} ]
						$scope.splitType = '';
						$scope.newGoalName = '';
					};

				});
