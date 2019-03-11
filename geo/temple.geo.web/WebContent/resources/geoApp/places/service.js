/**
 * 
 */
(function() {
	
	angular.module('placesModule')
		.factory('PlacesService', ['$http', function($http) {
			return new PlacesService($http) ;
		}]) ;
	
	
	/**
	 * @constructor
	 * @this {PlacesService}
	 */
	function PlacesService($http) {
		var baseUrl = 'webapi/service/' ;
		
		this.getCountries = function() {
			return $http.get(baseUrl + 'countries') ;
		} ;
		
		this.getDivisions = function(country) {
			return $http.get(baseUrl + 'divisions/' + country) ;
		} ;
		
		this.getCities = function(divisionId, page) {
			var p = page || 0 ;
			return $http.get(baseUrl + 'cities/' + divisionId + "?page="+page) ;
		} ;
		
		this.getCity = function(cityId) {
			return $http.get(baseUrl + 'city/' + cityId) ;
		}
		
		this.get = function(params) {
			var suffix ;
			if(params.country) {
				if(params.division) {
					if(params.city) {
						suffix = 'city/' + params.city ;
					} else{
						suffix = 'places/' + params.division ;
					}
				} else {
					suffix = 'divisions/' + params.country ;
				}
			} else {
				suffix = 'countries' ;
			}
			
			return $http.get(baseUrl + suffix) ;
		} ;
	}
	
})() ;