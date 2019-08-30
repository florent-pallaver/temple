(function() {

	const FOOD_RS_URL = BASE_URL + 'food/' ;
	
	const module = angular.module('food', []) ;
	
	module.factory('FoodService', ['$http', FoodService]);

	function FoodService($http) {
		const self = this;

		self.countings = [
			{label: '100g', value: 'GRAM', unit: 'g'}, 
			{label: '100ml', value: 'MILLILITER', unit: 'ml'}, 
			{label: 'unité', value: 'UNIT', unit: ''}
		];
		
		self.foods = [];
		
		self.refresh = function() {
			return $http.get(FOOD_RS_URL).then(function(response) {
				self.foods = response.data;
			}, onError);
		};

		self.refresh();
	}
})();
