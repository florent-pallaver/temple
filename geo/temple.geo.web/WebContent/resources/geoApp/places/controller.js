/**
 * 
 */
(function() {

	angular.module('placesModule').controller('PlacesController',
			[ '$routeParams', 'PlacesService', placesController ]);

	function error(response) {
		alert(response.statusText);
	}

	/**
	 * @param {PlacesService}
	 *            placesService
	 */
	function placesController($routeParams, placesService) {
		var vm = this;

		vm.all = [];

		vm.country = $routeParams.country ;
		vm.division = $routeParams.division ;
		vm.city = $routeParams.city ;
		
		placesService.get($routeParams).then(function(response) {
			vm.all = response.data;
		}, error)
	}

})();