app.factory('goalWorkItemRepository', function($http) {
	return {
		getNextGoalWorkItems : function(spec,instance) {
			var url = "specs/" + spec + "/instances/"+ instance + "/goalworkitem/next";
			return $http.get(url);
		}
	}
});