(function() {

	const FOOD_RS_URL = BASE_URL + 'food/';

	const module = angular.module('dish', []) ;
	
	module.controller('DishController', ['$http', DishController]);

	function DishController($http) {
		const self = this;

        self.init = function() {
            
        };

        self.init();
	}
})();
