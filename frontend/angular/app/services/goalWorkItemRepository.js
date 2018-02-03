app.factory('goalWorkItemRepository', function($http) {
	return {
		getNextGoalWorkItems : function(spec,instance) {
			var url = baseUrl + "specs/" + spec + "/instances/"+ instance + "/goalworkitem/next";
			return $http.get(url);
		},
		getLogGoalWorkItems : function(spec,instance) {
			var url = baseUrl + "specs/" + spec + "/instances/"+ instance + "/goalworkitem/log";
			return $http.get(url);
		},
		executeWorkItem : function(spec, instance,
				workItemName, workItem) {
			var url = baseUrl + "specs/" + spec + "/instances/"+ instance + "/goalworkitem";
			return $http.post(url, {
				"specId" :  spec,
				"workflowInstanceName" : instance,
				"name" : workItemName,
				"definitionGroupSet" : workItem.definitionGroupSet
			});
		}
	}
});