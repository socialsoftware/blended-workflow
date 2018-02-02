app.factory('dataRepository', function($http) {
	return {
		getEntities : function(spec) {
			var url = "specs/" + spec + "/datamodel/entities";
			return $http.get(url);
		},
		getAttributes : function(spec) {
			var url = "specs/" + spec + "/datamodel/attributes";
			return $http.get(url);
		},
		getDependencies : function(spec) {
			var url = "specs/" + spec + "/datamodel/dependencies";
			return $http.get(url);
		},
		getRules : function(spec) {
			var url = "specs/" + spec + "/datamodel/rules";
			return $http.get(url);
		},
		getRelations : function(spec) {
			var url = "specs/" + spec + "/datamodel/relations";
			return $http.get(url);
		}
	};
});