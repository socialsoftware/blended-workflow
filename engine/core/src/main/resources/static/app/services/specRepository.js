app.factory('specRepository', function($http) {
	return {
		getSpecifications : function() {
			var url = "specs";
			return $http.get(url);
		},
		getSpecification : function(specId) {
			var url = "specs/" + specId;
			return $http.get(url);
		}
	};
});