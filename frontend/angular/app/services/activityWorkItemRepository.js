app.factory('activityWorkItemRepository', function($http) {
	return {
		getNextActivityWorkItems : function(spec,instance) {
			var url = baseUrl + "specs/" + spec + "/instances/"+ instance + "/activityworkitem/next";
			return $http.get(url);
		},
		getLogActivityWorkItems : function(spec,instance) {
			var url = baseUrl + "specs/" + spec + "/instances/"+ instance + "/activityworkitem/log";
			return $http.get(url);
		},
		executeWorkItem : function(spec, instance,
				workItemName, workItem) {
			var url = baseUrl + "specs/" + spec + "/instances/"+ instance + "/activityworkitem";
			return $http.post(url, {
				"specId" :  spec,
				"workflowInstanceName" : instance,
				"name" : workItemName,
				"definitionGroupSet" : workItem.definitionGroupSet
			});
		}
	}
});