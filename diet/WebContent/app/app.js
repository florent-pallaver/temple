const BASE_URL = '/diet/api/' ;
const dateFormat = new Intl.DateTimeFormat('fr', {year: 'numeric', month: 'short', day: '2-digit'});
const durationFormat =  new Intl.DateTimeFormat('fr', {hour: '2-digit', minute:'2-digit'});
var noop = function(){};
var onError = function(response) {
	var msg = (response.data && response.data.message) || 'An error occured...';
	
	document.getElementById('alert-box').classList.add('show');
	document.getElementById('alert-msg').innerText = msg;

	console.log(response);
};

(function() {
	'use strict';

	var app = angular.module('diet', ['ngRoute', 'home', 'diary', 'food']);

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
	
	app.filter('fixed0', function() {
		return function(number) {
			return number.toFixed(0);
		} ;
	}) ;
	
	app.filter('duration', function() {
		return function(durationInMinutes) {
			var date = new Date(durationInMinutes * 60 * 1000);
			var duration = date.getUTCHours() + 'h';
			var minutes = date.getUTCMinutes();
			if(minutes) {
				duration += minutes;
			}
			return duration;
		} ;
	}) ;

	app.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
		
		$routeProvider
		.when('/', {
			templateUrl: 'app/features/home/home.html',
			controller: 'HomeController',
			controllerAs: 'homeCtrl'
		})
		.when('/food', {
			redirectTo: '/food/misc'
		})
		.when('/food/:foodType', {
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

	    //initialize get if not there
	    if (!$httpProvider.defaults.headers.get) {
	        $httpProvider.defaults.headers.get = {};    
	    }    

	    //disable IE ajax request caching
	    $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
	    // extra
	    $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
	    $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
	}]) ;

	app.controller('MainController', ['$location', '$http', 'UserService', MainController]);

	function MainController($location, $http, UserService) {
		const self = this;
		
		self.growthModes = {
			'INCREASE': 'Prise de masse',
            'MAINTAIN': 'Maintenance',
			'DECREASE': 'Sèche'
		};

		self.foodTypes = {
			MISC: {full: 'autres', short: 'autres', icon: 'fa-pizza-slice'},
			DRINKS: {full: 'boissons', short: 'boissons', icon: 'fa-glass-whiskey'},
			VEGGIES: {full: 'légumes', short: 'légumes', icon: 'fa-carrot'},
			FRUITS: {full: 'fruits', short: 'fruits', icon: 'fa-apple-alt'},
			CEREALS: {full: 'céréales / féculents', short: 'céréales /féculents', icon: 'fa-seedling'},
			DAIRIES: {full: 'produits laitiers', short: 'laitages', icon: 'fa-cheese'},
			ANIMALS: {full: 'animaux', short: 'animaux', icon: 'fa-paw'}, 
			FATS: {full: 'matières grasses', short: 'graisses', icon: 'fa-bacon'},  
			SWEETS: {full: 'sucre / produits sucrées', short: 'sucre', icon: 'fa-candy-cane'}
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
			}, onError);
		};
		
		self.isCurrentPage = function(page) {
			return $location.path().endsWith(page); 
		};
		
		self.init();
	}
	
})();
