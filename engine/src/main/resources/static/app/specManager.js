var app = angular.module('specManager', [ 'ngRoute', 'ui.bootstrap' ]);

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "views/home.html"
	}).when("/data", {
		templateUrl : "views/dataModel.html",
		controller : "DataModelController"
	}).when("/condition", {
		templateUrl : "views/conditionModel.html",
		controller : "ConditionModelController"
	}).when("/activitymodel", {
		templateUrl : "views/activityModel.html",
		controller : "ActivityModelController"
	}).when("/activitygraph", {
		templateUrl : "views/activityGraph.html",
		controller : "ActivityGraphController"
	}).otherwise({
		redirectTo : "/"
	});
});
