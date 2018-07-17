app.factory('goalRepository', function($http) {
	return {
		getGoals : function(spec) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals";
			return $http.get(url);
		},
		getProductGoals : function(spec) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/product";
			return $http.get(url);
		},
		getAssociationGoals : function(spec) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/association";
			return $http.get(url);
		},
		getActivationConditions : function(spec, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/"
					+ goalName + "/act";
			return $http.get(url);
		},
		getEntitySuccessConditions : function(spec, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/"
					+ goalName + "/sucent";
			return $http.get(url);
		},
		getAttributeSuccessConditions : function(spec, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/"
					+ goalName + "/sucatt";
			return $http.get(url);
		},
		getMulConditions : function(spec, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/"
					+ goalName + "/invent";
			return $http.get(url);
		},
		getRelations : function(spec, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/"
					+ goalName + "/relations";
			return $http.get(url);
		},
		getRuleConditions : function(spec, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/"
					+ goalName + "/invatt";
			return $http.get(url);
		},
		getGoalGraph : function(spec) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/graph";
			return $http.get(url);
		},
		renameGoal : function(spec, goal, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/" + goal
					+ "/rename/" + goalName;
			return $http.post(url);
		},
		mergeGoals : function(spec, goalOne, goalTwo, newGoalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/merge";
			return $http.post(url, {
				"specId" : spec,
				"nameOne" : goalOne,
				"nameTwo" : goalTwo,
				"newName" : newGoalName
			});

		},
		splitProductGoal : function(spec, goal, sucConditions, newGoalName) {
			var url = baseUrl + "specs/" + spec
					+ "/goalmodel/goals/extract";
			return $http.post(url, {
				"sourceGoalName" : goal,
				"newGoalName" : newGoalName,
				"successConditions" : sucConditions
			});
		},
		splitAssociationGoal : function(spec, goal, relations, newGoalName) {
			var url = baseUrl + "specs/" + spec
					+ "/goalmodel/goals/extract";
			return $http.post(url, {
				"sourceGoalName" : goal,
				"newGoalName" : newGoalName,
				"relations" : relations
			});
		}
	};
})
