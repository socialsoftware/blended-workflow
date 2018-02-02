app.factory('conditionRepository', function($http) {
	return {
		getEntityAchieveConditions : function(spec) {
			var url = "specs/" + spec + "/conditionmodel/entityachieveconditions";
			return $http.get(url);
		},
		getEntityDependenceConditions : function(spec) {
			var url = "specs/" + spec + "/conditionmodel/entitydependenceconditions";
			return $http.get(url);
		},
		getAttributeAchieveConditions : function(spec) {
			var url = "specs/" + spec + "/conditionmodel/attributeachieveconditions";
			return $http.get(url);
		},
		getAttributeDependenceConditions : function(spec) {
			var url = "specs/" + spec + "/conditionmodel/attributedependenceconditions";
			return $http.get(url);
		},
		getEntityInvariantConditions : function(spec) {
			var url = "specs/" + spec + "/conditionmodel/entityinvariantconditions";
			return $http.get(url);
		},
		getAttributeInvariantConditions : function(spec) {
			var url = "specs/" + spec + "/conditionmodel/attributeinvariantconditions";
			return $http.get(url);
		}
	};
});
