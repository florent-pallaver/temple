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
		var baseUrl = 'api/service/' ;
		
		this.get = function(params) {
			var suffix ;
			if(params.country) {
				if(params.division) {
					suffix = 'cities/' + params.division ;
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