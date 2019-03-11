/**
 * 
 */
function serviceError(response) {
	alert(response.statusText);
}

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
		$routeProvider.when('/', {
			templateUrl : 'resources/geoApp/places/world.html',
			controller : 'WorldController',
			controllerAs : 'ctrl',
		}).when('/:country', {
			templateUrl : 'resources/geoApp/places/country.html',
			controller : 'CountryController',
			controllerAs : 'ctrl',
		}).when('/:country/:division/:divisionId', {
			templateUrl : 'resources/geoApp/places/division.html',
			controller : 'DivisionController',
			controllerAs : 'ctrl',
		}).when('/:country/:division/:divisionId/:city/:cityId', {
			templateUrl : 'resources/geoApp/places/city.html',
			controller : 'CityController',
			controllerAs : 'ctrl',
		}).otherwise({
			redirectTo : '/'
		});
	}

})();