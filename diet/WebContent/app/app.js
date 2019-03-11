const BASE_URL = '/diet/api/' ;

(function() {
	'use strict';

	var app = angular.module('diet', ['ngRoute', 'food', 'meal']);

//	app.filter('amount', function() {
//		return function(amount) {
//			return (amount / 100) + ' €';
//		} ;
//	}) ;
	
	app.config(['$routeProvider', function($routeProvider) {
		
		$routeProvider
		.when('/food', {
			templateUrl: 'app/features/food/food.html',
			controller: 'FoodController',
			controllerAs: 'foodCtrl'
		})
		.when('/meal', {
			templateUrl: 'app/features/meal/meal.html',
			controller: 'MealController',
			controllerAs: 'mealCtrl'
		})
		.otherwise({redirectTo: '/'}) ;
		
//		 $sceDelegateProvider.resourceUrlWhitelist([
//			   // Allow same origin resource loads.
//			   'self',
//			   // Allow loading from our assets domain.  Notice the difference between * and **.
//			   'http://localhost:9090/**']);
		
	}]) ;
	
})();
