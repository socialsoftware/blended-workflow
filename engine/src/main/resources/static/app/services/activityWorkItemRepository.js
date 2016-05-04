app.factory('activityWorkItemRepository', function($http) {
	return {
		getActivityWorkItems : function(spec,instance) {
			var url = "specs/" + spec + "/instances/"+ instance + "/activity";
			return $http.get(url);
		}
	}
});