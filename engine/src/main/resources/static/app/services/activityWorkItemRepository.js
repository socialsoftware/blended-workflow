app.factory('activityWorkItemRepository', function($http) {
	return {
		getNextActivityWorkItems : function(spec,instance) {
			var url = "specs/" + spec + "/instances/"+ instance + "/activityworkitem/next";
			return $http.get(url);
		},
		getLogActivityWorkItems : function(spec,instance) {
			var url = "specs/" + spec + "/instances/"+ instance + "/activityworkitem/log";
			return $http.get(url);
		},
		executeWorkItem : function(spec, instance,
				activityName, workItem) {
			var url = "specs/" + spec + "/instances/"+ instance + "/activityworkitem";
			return $http.post(url, {
				"specId" :  spec,
				"workflowInstanceName" : instance,
				"activityName" : activityName,
				"definitionGroupSet" : workItem.definitionGroupSet
			});
		}
	}
});