var app = angular.module('specManager', [ 'ngRoute', 'ngAnimate', 'ui.bootstrap']);

var baseUrl = 'http://localhost:8081/';

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "views/home.html"
	}).when("/specs/:specId/datamodel", {
		templateUrl : "views/dataModel.html",
		controller : "DataModelController"
	}).when("/specs/:specId/conditionmodel", {
		templateUrl : "views/conditionModel.html",
		controller : "ConditionModelController"
	}).when("/specs/:specId/goalmodel", {
		templateUrl : "views/goalModel.html",
		controller : "GoalModelController"
	}).when("/specs/:specId/goalgraph", {
		templateUrl : "app/shared/goal/goalGraph.html",
		controller : "GoalGraphController"
	}).when("/specs/:specId/activitymodel", {
		templateUrl : "views/activityModel.html",
		controller : "ActivityModelController"
	}).when("/specs/:specId/activitygraph", {
		templateUrl : "app/shared/activity/activityGraph.html",
		controller : "ActivityGraphController"
	}).when("/specs/:specId/instancesmanager", {
		templateUrl : "views/instancesManager.html"
	}).when("/specs/:specId/instancesmanager/:instance/activity", {
		templateUrl : "app/components/activity/workItems/activityWorkItem.html",
		controller : "ActivityWorkItemController"
	}).when("/specs/:specId/instancesmanager/:instance/goal", {
		templateUrl : "app/components/goal/workItems/goalWorkItem.html",
		controller : "GoalWorkItemController"
	}).otherwise({
		redirectTo : "/"
	});
});
