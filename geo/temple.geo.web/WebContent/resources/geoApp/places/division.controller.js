/**
 * 
 */
(function() {

	angular.module('placesModule').controller('DivisionController',
			[ '$location', '$routeParams', 'PlacesService', DivisionController ]);

	/**
	 * @param
	 * @param {PlacesService}
	 *            placesService
	 */
	function DivisionController($location, $routeParams, placesService) {
		var vm = this;
		
		vm.page = 1 ;
		vm.path = $location.path() ;
		vm.country = $routeParams.country ;
		vm.division = $routeParams.division ;
		vm.divisionId = $routeParams.divisionId ;
		vm.cities = [];
		
		this.link = function(city) {
			return '#' + vm.path + '/' + city.name + '/' + city.id ;
		} ;
		
		this.newPage = function() {
			placesService.getCities(vm.divisionId, vm.page).then(function(response) {
				vm.cities = response.data;
			}, serviceError) ;
		}
		
		this.newPage() ;
	}

})();