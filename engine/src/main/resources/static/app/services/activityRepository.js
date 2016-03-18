app.factory('activityRepository', function($http) {
	return {
		getActivities : function(spec) {
			var url = "specs/" + spec + "/activitymodel/activities";
			return $http.get(url);
		},
		getPreConditions : function(spec, activity) {
			var url = "specs/" + spec + "/activitymodel/activities/" + activity
					+ "/pre";
			return $http.get(url);
		},
		getSeqConditions : function(spec, activity) {
			var url = "specs/" + spec + "/activitymodel/activities/" + activity
					+ "/seq";
			return $http.get(url);
		},
		getPostConditions : function(spec, activity) {
			var url = "specs/" + spec + "/activitymodel/activities/" + activity
					+ "/post";
			return $http.get(url);
		},
		getMulConditions : function(spec, activity) {
			var url = "specs/" + spec + "/activitymodel/activities/" + activity
					+ "/postmul";
			return $http.get(url);
		},
		getRuleConditions : function(spec, activity) {
			var url = "specs/" + spec + "/activitymodel/activities/" + activity
					+ "/postrule";
			return $http.get(url);
		},
		getActivityGraph : function(spec) {
			var url = "specs/" + spec + "/activitymodel/activities/graph";
			return $http.get(url);
		}
	};
});