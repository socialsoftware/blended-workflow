app.factory('goalRepository', function($http) {
	return {
		getGoals : function(spec) {
			var url = "specs/" + spec + "/goalmodel/goals";
			return $http.get(url);
		},
		getSubGoals : function(spec, goalName) {
			var url = "specs/" + spec + "/goalmodel/goals/" + goalName
					+ "/sub";
			return $http.get(url);
		},
		getActivationConditions : function(spec, goalName) {
			var url = "specs/" + spec + "/goalmodel/goals/" + goalName
					+ "/act";
			return $http.get(url);
		},
		getEntitySuccessConditions : function(spec, goalName) {
			var url = "specs/" + spec + "/goalmodel/goals/" + goalName
					+ "/sucent";
			return $http.get(url);
		},
		getAttributeSuccessConditions : function(spec, goalName) {
			var url = "specs/" + spec + "/goalmodel/goals/" + goalName
					+ "/sucatt";
			return $http.get(url);
		},
		getMulConditions : function(spec, goalName) {
			var url = "specs/" + spec + "/goalmodel/goals/" + goalName
					+ "/invent";
			return $http.get(url);
		},
		getRuleConditions : function(spec, goalName) {
			var url = "specs/" + spec + "/goalmodel/goals/" + goalName
					+ "/invatt";
			return $http.get(url);
		},
		getGoalGraph : function(spec) {
			var url = "specs/" + spec + "/goalmodel/goals/graph";
			return $http.get(url);
		},
		renameGoal : function(spec, goal, goalName) {
			var url = "specs/" + spec + "/goalmodel/goals/" + goal
					+ "/" + goalName;
			return $http.put(url);
		},
//		mergeActivities : function(spec, activityOne, activityTwo,
//				newActivityName) {
//			var url = "specs/" + spec
//					+ "/activitymodel/activities/merge?activityNameOne="
//					+ activityOne + "&activityNameTwo=" + activityTwo
//					+ "&newActivityName=" + newActivityName;
//			return $http.post(url);
//		},
//		splitActivity : function(spec, activity, postConditions, newActivityName) {
//			var url = "specs/" + spec
//			+ "/activitymodel/activities/extract";
//			return $http.post(url, {
//				"sourceActivityName" : activity,
//				"newActivityName" :  newActivityName,
//				"successConditions" : postConditions
//			});
//		}
	};
})
