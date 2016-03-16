var app = angular.module('specManager', [ 'ngRoute', 'ui.bootstrap' ]);
app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "views/home.html"
	}).when("/data", {
		templateUrl : "views/dataModel.html",
		controller : "dataModelController"
	}).when("/condition", {
		templateUrl : "views/conditionModel.html",
		controller : "conditionModelController"
	}).when("/activity", {
		templateUrl : "views/activityModel.html",
		controller : "activityModelController"
	}).otherwise({
		redirectTo : "/"
	});
});