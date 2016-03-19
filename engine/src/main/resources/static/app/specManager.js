var app = angular.module('specManager', [ 'ngRoute', 'ui.bootstrap' ]);

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "views/home.html"
	}).when("/specs/:specId/datamodel", {
		templateUrl : "views/dataModel.html",
		controller : "DataModelController"
	}).when("/specs/:specId/conditionmodel", {
		templateUrl : "views/conditionModel.html",
		controller : "ConditionModelController"
	}).when("/specs/:specId/activitymodel", {
		templateUrl : "views/activityModel.html",
		controller : "ActivityModelController"
	}).when("/specs/:specId/activitygraph", {
		templateUrl : "views/activityGraph.html",
		controller : "ActivityGraphController"
	}).otherwise({
		redirectTo : "/"
	});
});
