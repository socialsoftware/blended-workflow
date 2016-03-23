app.factory('goalRepository', function($http) {
	return {
		getGoals : function(spec) {
			var url = "specs/" + spec + "/goalmodel/goals";
			return $http.get(url);
		},
		getSubGoals : function(spec, activityName) {
			var url = "specs/" + spec + "/goalmodel/goals/" + activityName
					+ "/sub";
			return $http.get(url);
		},
		getActivationConditions : function(spec, activityName) {
			var url = "specs/" + spec + "/goalmodel/goals/" + activityName
					+ "/act";
			return $http.get(url);
		},
		getEntitySuccessConditions : function(spec, activityName) {
			var url = "specs/" + spec + "/goalmodel/goals/" + activityName
					+ "/sucent";
			return $http.get(url);
		},
		getAttributeSuccessConditions : function(spec, activityName) {
			var url = "specs/" + spec + "/goalmodel/goals/" + activityName
					+ "/sucatt";
			return $http.get(url);
		},
		getMulConditions : function(spec, activityName) {
			var url = "specs/" + spec + "/goalmodel/goals/" + activityName
					+ "/invent";
			return $http.get(url);
		},
		getRuleConditions : function(spec, activityName) {
			var url = "specs/" + spec + "/goalmodel/goals/" + activityName
					+ "/invatt";
			return $http.get(url);
		}
	};
})
