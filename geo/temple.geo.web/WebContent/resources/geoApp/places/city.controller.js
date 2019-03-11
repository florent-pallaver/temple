/**
 * 
 */
(function() {

	angular.module('placesModule').controller('CityController',
			[ '$routeParams', 'PlacesService', CityController ]);

	/**
	 * @param
	 * @param {PlacesService}
	 *            placesService
	 */
	function CityController($routeParams, placesService) {
		var vm = this;

		vm.country = $routeParams.country ;
		vm.division = $routeParams.division ;
		vm.divisionId = $routeParams.divisionId ;
		vm.cityId = $routeParams.cityId ;
		vm.city = {};
		
		placesService.getCity(vm.cityId).then(function(response) {
			vm.city = response.data;
		}, serviceError) ;
	}

})();