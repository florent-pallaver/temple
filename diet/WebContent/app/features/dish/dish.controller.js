(function() {

	const DISH_RS_URL = BASE_URL + 'dish/';
	const PREPARED_FOOD_RS_URL = DISH_RS_URL + 'preparedFood/';

	const module = angular.module('dish', []) ;
	
	module.controller('DishController', ['$http', DishController]);

	function DishController($http) {
		const self = this;

		self.newDish = {};
		
		self.foodFilter = {};
		self.selectedFood = null;
		self.newPreparedFood = {};
		
		self.dishes = [];
		
		self.foods = [];
		self.preparedFoods = [];
		
        self.init = function() {
        	loadFood()
        		.then(loadPreparedFood);
        };

        var loadFood = function() {
        	return $http.get(BASE_URL + 'food/').then(function(response) {
        		self.foods = response.data;
        	});
        };
        
        var loadPreparedFood = function() {
        	return $http.get(PREPARED_FOOD_RS_URL).then(function(response) {
        		self.preparedFoods = response.data;
        	});
        };
        
        self.selectFood = function(food) {
        	if(self.selectedFood) {
        		self.selectedFood.selected = false;
        	}
        	self.selectedFood = food;
        	self.selectedFood.selected = true;
        };
        
        self.addPreparedFood = function() {
        	$http.post(PREPARED_FOOD_RS_URL + self.selectedFood.id, self.newPreparedFood)
        		.then(loadPreparedFood);
        };
        
        self.init();
	}
})();
