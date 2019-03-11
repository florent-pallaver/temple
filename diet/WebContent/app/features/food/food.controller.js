(function() {

	const FOOD_RS_URL = BASE_URL + 'food/' ;
	
	const module = angular.module('food', []) ;
	
	module.controller('FoodController', ['$http', FoodController]);

	function FoodController($http) {
		const self = this;

		self.foods = [];
		
        self.newFood = {
        	name: '', brand: ''
        };

        self.selected = {};
        
		self.init = function() {
			$http.get(FOOD_RS_URL).then(function(response) {
				self.foods = response.data;
			}, onError);
		};

		self.create = function(food) {
			var f = food || self.newFood; 
			if(checkFood(f)) {
				$http.post(FOOD_RS_URL, f).then(self.init, onError);
			}
		};

		function checkFood(f) {
			f.name = f.name.trim();
			if(!f.name) {
				return false;
			}
			if(!f.protein && f.protein !== 0) {
				return false;
			}
			if(!f.carb && f.carb !== 0) {
				return false;
			}
			if(!f.fat && f.fat !== 0) {
				return false;
			}
			if(!f.ig && f.ig !== 0) {
				return false;
			}
			if(!f.kcal) {
				return false;
			}
			f.brand = f.brand.trim(); 
			return true;
		};
		
		self.toggleSelect = function(food) {
			food.selected = !food.selected;
		};
		
		self.delete = function() {
			var ids = self.foods
				.filter(food => food.selected)
				.map(food => food.id);
			if(ids.length && window.confirm('Es-tu sûr de vouloir faire ça?')) {
				$http['delete'](FOOD_RS_URL, {params: {id: ids}}).then(self.init, onError);
			}
		};

		self.update = function(food) {
			if(checkFood(food)) {
				$http.put(FOOD_RS_URL + food.id, food).then(noop, onError);
			}
		};

		self.copy = function(food) {
			var copiedFood = angular.copy(food);
			copiedFood.name = food.name.concat(' (copy)');
			self.create(copiedFood);
		};
		
		var noop = function(){};
		
		var onError = function(response) {
			window.alert('An error occured...');
			console.log(response);
		};

		self.init();
	}
})();
