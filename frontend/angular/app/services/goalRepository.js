app.factory('goalRepository', function($http) {
	return {
		getGoals : function(spec) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals";
			return $http.get(url);
		},
		getSubGoals : function(spec, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/" + goalName
					+ "/sub";
			return $http.get(url);
		},
		getActivationConditions : function(spec, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/" + goalName
					+ "/act";
			return $http.get(url);
		},
		getEntitySuccessConditions : function(spec, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/" + goalName
					+ "/sucent";
			return $http.get(url);
		},
		getAttributeSuccessConditions : function(spec, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/" + goalName
					+ "/sucatt";
			return $http.get(url);
		},
		getMulConditions : function(spec, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/" + goalName
					+ "/invent";
			return $http.get(url);
		},
		getRuleConditions : function(spec, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/" + goalName
					+ "/invatt";
			return $http.get(url);
		},
		getGoalGraph : function(spec) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/graph";
			return $http.get(url);
		},
		renameGoal : function(spec, goal, goalName) {
			var url = baseUrl + "specs/" + spec + "/goalmodel/goals/" + goal
					+ "/" + goalName;
			return $http.put(url);
		},
		mergeGoals : function(spec, goalOne, goalTwo,
				newGoalName) {
			var url = baseUrl + "specs/" + spec
					+ "/goalmodel/goals/merge;
			return $http.post(url, {
				"spec" : spec,
				"nameOne" : goalOne,
				"nameTwo" : goalTwo,
				"newName" : newGoalName
			});

		},
		splitParentGoal : function(spec, goal, sucConditions, newGoalName) {
			var url = baseUrl + "specs/" + spec
			+ "/goalmodel/goals/extractparent";
			return $http.post(url, {
				"sourceGoalName" : goal,
				"newGoalName" :  newGoalName,
				"successConditions" : sucConditions
			});
		},
		splitChildGoal : function(spec, goal, sucConditions, newGoalName) {
			var url = baseUrl + "specs/" + spec
			+ "/goalmodel/goals/extractchild";
			return $http.post(url, {
				"sourceGoalName" : goal,
				"newGoalName" :  newGoalName,
				"successConditions" : sucConditions
			});
		},
		splitSiblingGoal : function(spec, goal, sucConditions, newGoalName) {
			var url = baseUrl + "specs/" + spec
			+ "/goalmodel/goals/extractsibling";
			return $http.post(url, {
				"sourceGoalName" : goal,
				"newGoalName" :  newGoalName,
				"successConditions" : sucConditions
			});
		}
	};
})
