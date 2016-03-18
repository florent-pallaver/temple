/**
 * 
 */
(function() {

	var app = angular.module('geoApp', ['ngRoute', 'placesModule']) ;

	app.config([ '$routeProvider', configRoute ]) ;

//	.filter('dateDay', function() {
//		return function(date) {
//			return Date.parse(date.split('T')[0]);
//		};
//	}).filter('userFiler', function() {
//		return function(user) {
//			return Date.parse(date.split('T')[0]);
//		};
//	});

	function configRoute($routeProvider) {
		$routeProvider.when('/countries', {
			templateUrl : 'resources/geoApp/places/world.html',
			controller : 'PlacesController',
			controllerAs : 'placesCtrl',
		}).when('/countries/:country/divisions', {
			templateUrl : 'resources/geoApp/places/country.html',
			controller : 'PlacesController',
			controllerAs : 'placesCtrl',
		}).when('/countries/:country/divisions/:division/cities', {
			templateUrl : 'resources/geoApp/places/division.html',
			controller : 'PlacesController',
			controllerAs : 'placesCtrl',
		}).when('/countries/:country/divisions/:division/cities/:city', {
			templateUrl : 'resources/geoApp/places/city.html',
			controller : 'PlacesController',
			controllerAs : 'placesCtrl',
		}).otherwise({
			redirectTo : '/countries'
		});
	}

})();