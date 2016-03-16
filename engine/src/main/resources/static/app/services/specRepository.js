app.factory('specRepository', function($http) {
	return {
		getSpecifications : function(spec) {
			var url = "specs";
			return $http.get(url);
		}
	};
});