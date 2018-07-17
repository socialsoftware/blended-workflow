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

					$scope.types = {
							availableTypes : [ {
								id : 0,
								name : '--- Type ---'
							}, {
								id : 1,
								name : 'Product'
							}, {
								id : 2,
								name : 'Association'
							} ],
							selectedType : {
								id : '0',
								name : '---Choose Type---'
							}
						};
					
					$scope.getGoalsFromRepository = function(specId) {
						if ($scope.types.selectedType.id == 0) {
							return goalRepository.getGoals(specId);
						} else if ($scope.types.selectedType.id == 1) {
							return goalRepository.getProductGoals(specId);
						} else {
							return goalRepository.getAssociationGoals(specId);
						}
					};

					$scope.readGoals = function(specId) {
						$scope.getGoalsFromRepository(specId)
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

					$scope.goalRelations = {
							availableRelations : [ {
								name : '--- Relations ---'
							} ],
							selectedRelations : []
						};

					$scope.readGoalRelations = function(specId, goal) {
							goalRepository
									.getRelations(specId, goal)
									.then(
											function(response) {
												$scope.goalRelations = {
													availableRelations : []
															.concat(response.data),
													selectedRelations : []
												};
											});

					};

					$scope.readGraph = function(specId) {
						goalRepository.getGoalGraph(specId).then(
								function(response) {
									$scope.goalGraph = response.data;
								});
					};

					$scope.goalNameInput = function() {
						// if split of a goal with empty success conditions
						if ($scope.operations.selectedOperation.id == 3
								&& $scope.goalSucConditions.availableSucConditions.length == 0)
							return false;

						// rename, merge, and split operations
						return ($scope.operations.selectedOperation.id >= 1 && $scope.operations.selectedOperation.id <= 3);
					};

					$scope.goalRenameOperation = function() {
						// merge operation
						return ($scope.operations.selectedOperation.id == 1);
					};

					$scope.goalMergeOperation = function() {
						// merge operation
						return ($scope.operations.selectedOperation.id == 2);
					};

					$scope.goalSplitOperation = function() {
						// split operation
						return ($scope.operations.selectedOperation.id == 3);
					};

					$scope.goalTypeSelected = function() {
						return ($scope.types.selectedType.id != 0);
					};

					$scope.productGoals = function() {
						return ($scope.types.selectedType.id == 1);
					};

					$scope.associationGoals = function() {
						return ($scope.types.selectedType.id == 2);
					};

					$scope.goalSucConditionsSelect = function() {
						return ($scope.goalSplitOperation() && $scope.productGoals() && $scope.goalSucConditions.availableSucConditions.length > 0);
					};

					$scope.goalRelationsSelect = function() {
						return ($scope.goalSplitOperation() && $scope.associationGoals() && $scope.goalRelations.availableRelations.length > 0);
					};

					$scope.validForm = function() {
						// applies to all operations

						// for all operations it is necessary to select a
						// goal, but not the header
						if ($scope.operations.selectedOperation.id != 0 && $scope.goalsOne.selectedGoal == $scope.goalsOne.availableGoals[0])
							return false;

						// if it is not a merge the new goal should have a non existing name
						if (($scope.operations.selectedOperation.id == 1 && $scope.operations.selectedOperation.id == 3)) {
							// the new goal name should not exist
							for (i = 0; i < $scope.goalsOne.availableGoals.length; i++)
								if ($scope.goalsOne.availableGoals[i].name == $scope.newGoalName)
									return false;
						}

						// merge operation
						if ($scope.operations.selectedOperation.id == 2) {
							// the type of goal is defined
							if (!$scope.goalTypeSelected())
								return false;
							
							// have to select the second goal, but not the
							// header
							if ($scope.goalsTwo.selectedActivity == $scope.goalsTwo.availableGoals[0])
								return false;

							// the second selected goal should be different
							// from the first
							for (i = 0; i < $scope.goalsTwo.availableGoals.length; i++)
								if ($scope.goalsTwo.selectedGoal.name == $scope.goalsOne.selectedGoal.name)
									return false;

							// the new goal name should not exist or reuse one
							// of the selected
							for (i = 0; i < $scope.goalsOne.availableGoals.length; i++)
								if ($scope.goalsOne.availableGoals[i].name == $scope.newGoalName
										&& $scope.newGoalName != $scope.goalsOne.selectedGoal.name
										&& $scope.newGoalName != $scope.goalsTwo.selectedGoal.name)
									return false;
						}

						// split operation
						if ($scope.operations.selectedOperation.id == 3) {
							// the type of goal is defined
							if (!$scope.goalTypeSelected())
								return false;
							
							// the product goal has empty success conditions or non selected conditions or are all selected
							if ($scope.productGoals && !$scope.goalSucConditions 
									&& $scope.goalSucConditions.selectedSucConditions.length < 1 
									&& $scope.goalSucConditions.selectedSucConditions.length == $scope.goalSucConditions.availableSucConditions.length)
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
									function(response) {
										$scope.updateState();
									},
									function(response) {
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
							if ($scope.productGoals()) {
								goalRepository
										.splitProductGoal(
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
							} else if ($scope.associationGoals()) {
								goalRepository
										.splitAssociationGoal(
												specId,
												$scope.goalsOne.selectedGoal.name,
												$scope.goalRelations.selectedRelations,
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
							};
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
						// split operation and product goals
						if ($scope.operations.selectedOperation.id == 3 && $scope.productGoals())
							$scope.readGoalSucConditions(specId, goal);
						
						// split operation and association goals
						if ($scope.operations.selectedOperation.id == 3 && $scope.associationGoals())
							$scope.readGoalRelations(specId, goal);
					};

					$scope.cleanGoals = function() {
						$scope.goalsOne.selectedGoal = $scope.goalsOne.availableGoals[0];
						$scope.goalsTwo.selectedGoal = $scope.goalsTwo.availableGoals[0];
						$scope.goalSucConditions.availableSucConditions = [ {
							path : '--- Success Conditions ---'
						} ]
						$scope.goalRelations.availableRelations = [ {
							path : '--- Relations ---'
						} ]
						$scope.newGoalName = '';
						$scope.readGoals(specId);
					};

					$scope.cleanAll = function() {
						$scope.types.selectedType = $scope.types.availableTypes[0];
						$scope.cleanGoals();
					};

				});
