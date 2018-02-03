app.factory('specRepository', function($http) {
	return {
		getSpecifications : function() {
			var url = baseUrl + "specs";
			return $http.get(url);
		},
		getSpecification : function(specId) {
			var url = baseUrl + "specs/" + specId;
			return $http.get(url);
		}
	};
});