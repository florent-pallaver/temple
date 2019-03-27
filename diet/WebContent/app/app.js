const BASE_URL = '/diet/api/' ;
const dateFormat = new Intl.DateTimeFormat('fr', {year: 'numeric', month: 'short', day: '2-digit'});
var noop = function(){};
var onError = function(response) {
	var msg = (response.data && response.data.message) || 'An error occured...';
	window.alert(msg);
	console.log(response);
};

(function() {
	'use strict';

	var app = angular.module('diet', ['ngRoute', 'diary', 'food']);

	app.filter('dayDate', function() {
		return function(dateStr) {
			var parts = dateStr.split('-');
			return dateFormat.format(new Date(parts[0], parts[1] - 1, parts[2]));
		} ;
	}) ;

	app.filter('fixed1', function() {
		return function(number) {
			return number.toFixed(1);
		} ;
	}) ;
	
	app.config(['$routeProvider', function($routeProvider) {
		
		$routeProvider
		.when('/', {
			templateUrl: 'app/features/home/home.html',
			controller: 'HomeController',
			controllerAs: 'homeCtrl'
		})
		.when('/food', {
			templateUrl: 'app/features/food/food.html',
			controller: 'FoodController',
			controllerAs: 'foodCtrl'
		})
		.when('/diary/:dayId?', {
			templateUrl: 'app/features/diary/diary.html',
			controller: 'DiaryController',
			controllerAs: 'diaryCtrl',
			reloadOnUrl: false
		})
		.otherwise({redirectTo: '/'}) ;
	
	}]) ;

	app.controller('MainController', ['$location', '$http', 'UserService', MainController]);

	function MainController($location, $http, UserService) {
		const self = this;
		
		self.growthModes = {
			'INCREASE': 'Prise de masse',
			'DECREASE': 'Sèche'
		};

		self.user = null;
		
		self.signInData = {
			name: '',
			pass: ''
		};
		
		self.init = function() {
			$http.get(BASE_URL + 'session').then(function(response) {
				self.user = response.data;
			});
		};
		
		self.signIn = function() {
			self.signInData.pass = self.signInData.name; 
			$http.post(BASE_URL + 'session', self.signInData).then(function(response) {
				self.user = response.data;
				//$location.reload();
			}, onError);
		};
		
		self.isCurrentPage = function(page) {
			return $location.path().endsWith(page); 
		};
		
		self.init();
	}
	
})();
