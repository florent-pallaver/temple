/**
 * 
 */
(function() {

	angular.module('placesModule').controller('CountryController',
			[ '$location', '$routeParams', 'PlacesService', CountryController ]);

	/**
	 * @param 
	 * @param 
	 * @param {PlacesService}
	 *            placesService
	 */
	function CountryController($location, $routeParams, placesService) {
		var vm = this;

		vm.path = $location.path() ;
		vm.country = $routeParams.country ;
		vm.divisions = [];

		placesService.getDivisions(vm.country).then(function(response) {
			vm.divisions = response.data;
		}, serviceError) ;
		
		this.link = function(div) {
			return '#' + vm.path + '/' + div.name + '/' + div.id ;
		} ;
	}

})();