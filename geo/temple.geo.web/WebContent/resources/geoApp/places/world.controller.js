/**
 * 
 */
(function() {

	angular.module('placesModule').controller('WorldController',
			[ 'PlacesService', WorldController ]);

	/**
	 * @param {PlacesService}
	 *            placesService
	 */
	function WorldController(placesService) {
		var vm = this;

		vm.countries = [];

		placesService.getCountries().then(function(response) {
			vm.countries = response.data;
		}, serviceError) ;
	}

})();