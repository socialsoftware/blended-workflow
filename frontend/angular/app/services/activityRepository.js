app.factory('activityRepository', function($http) {
	return {
		getActivities : function(spec) {
			var url = baseUrl + "specs/" + spec + "/activitymodel/activities";
			return $http.get(url);
		},
		getPreConditions : function(spec, activity) {
			var url = baseUrl + "specs/" + spec + "/activitymodel/activities/"
					+ activity + "/pre";
			return $http.get(url);
		},
		getSeqConditions : function(spec, activity) {
			var url = baseUrl + "specs/" + spec + "/activitymodel/activities/"
					+ activity + "/seq";
			return $http.get(url);
		},
		getPostConditions : function(spec, activity) {
			var url = baseUrl + "specs/" + spec + "/activitymodel/activities/"
					+ activity + "/post";
			return $http.get(url);
		},
		getMulConditions : function(spec, activity) {
			var url = baseUrl + "specs/" + spec + "/activitymodel/activities/"
					+ activity + "/postmul";
			return $http.get(url);
		},
		getRuleConditions : function(spec, activity) {
			var url = baseUrl + "specs/" + spec + "/activitymodel/activities/"
					+ activity + "/postrule";
			return $http.get(url);
		},
		getActivityGraph : function(spec) {
			var url = baseUrl + "specs/" + spec
					+ "/activitymodel/activities/graph";
			return $http.get(url);
		},
		renameActivity : function(spec, activity, activityName) {
			var url = baseUrl + "specs/" + spec + "/activitymodel/activities/"
					+ activity + "/" + activityName;
			return $http.put(url);
		},
		mergeActivities : function(spec, activityOne, activityTwo,
				newActivityName) {
			var url = baseUrl + "specs/" + spec
					+ "/activitymodel/activities/merge";

			return $http.post(url, {
				"spec" : spec,
				"nameOne" : activityOne,
				"nameTwo" : activityTwo,
				"newName" : newActivityName
			});
		},
		splitActivity : function(spec, activity, postConditions,
				newActivityName) {
			var url = baseUrl + "specs/" + spec
					+ "/activitymodel/activities/extract";
			return $http.post(url, {
				"sourceActivityName" : activity,
				"newActivityName" : newActivityName,
				"successConditions" : postConditions
			});
		},
		addSequenceCondition : function(spec, activity, path) {
			var url = baseUrl + "specs/" + spec + "/activitymodel/activities/"
					+ activity + "/seq/add";
			return $http.post(url, path);
		},
		remSequenceCondition : function(spec, activity, path) {
			var url = baseUrl + "specs/" + spec + "/activitymodel/activities/"
					+ activity + "/seq/rem";
			return $http.post(url, path);
		},
		getSeqConditions : function(spec, activity) {
			var url = baseUrl + "specs/" + spec + "/activitymodel/activities/"
					+ activity + "/seq";
			return $http.get(url);
		}
	};
});