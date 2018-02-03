app.factory('instanceRepository', function($http) {
	return {
		getWorkflowInstances : function(specId) {
			var url = baseUrl + "specs/" + specId + "/instances";
			return $http.get(url);
		},
		getWorkflowInstance : function(specId, name) {
			var url = baseUrl + "specs/" + specId + "/instances/" + name;
			return $http.get(url);
		},
		createWorkflowInstance : function(specId, name) {
			var url = baseUrl + "specs/" + specId + "/instances";
			return $http.post(url, {
				"specId" : specId,
				"name" : name
			});
		},
		deleteWorkflowInstance : function(specId, name) {
			var url = baseUrl + "specs/" + specId + "/instances/" + name;
			return $http.delete(url);
		}
	};
});