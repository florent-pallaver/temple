(function() {

	const FOOD_RS_URL = BASE_URL + 'food/' ;
	
	const module = angular.module('food', []) ;
	
	module.controller('FoodController', ['$http', '$routeParams', FoodController]);

	function FoodController($http, $routeParams) {
		const self = this;

		self.current = $routeParams;
		
		self.countings = [
			{label: '100g', value: 'GRAM', unit: 'g'}, 
			{label: '100ml', value: 'MILLILITER', unit: 'ml'}, 
			{label: 'unité', value: 'UNIT', unit: ''}
		];
		
		self.foods = [];
		
        self.newFood = {
        	name: '', brand: '', intake: {ig: 0}, counting: 'GRAM'
        };

        self.selected = {};
        
		self.init = function() {
			$http.get(FOOD_RS_URL).then(function(response) {
				self.foods = response.data;
				self.foods.forEach(food => food.locked = true);
			}, onError);
		};

		self.create = function(food) {
			var f = food || self.newFood; 
			if(checkFood(f)) {
				$http.post(FOOD_RS_URL, f).then(function() {
					self.init();
					self.newFood = {name: '', brand: '', intake: {ig: 0}, counting: 'GRAM'}
				}, onError);
			}
		};

		function checkFood(f) {
			f.name = f.name.trim();
			if(!f.name) {
				return false;
			}
			if(!f.intake.protein && f.intake.protein !== 0) {
				return false;
			}
			if(!f.intake.carb && f.intake.carb !== 0) {
				return false;
			}
			if(!f.intake.fiber && f.intake.fiber !== 0) {
				return false;
			}
			if(!f.intake.fat && f.intake.fat !== 0) {
				return false;
			}
			if(!f.intake.ig && f.intake.ig !== 0) {
				return false;
			}
			if(!f.intake.kcal) {
				return false;
			}
			if(!f.type) {
				return false;
			}
			// TODO check fiber < carb ???
			// TODO check kcal = 9*fat + 4*(prot+carb)
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
			if(!food.locked && checkFood(food)) {
				$http.put(FOOD_RS_URL + food.id, food).then(noop, onError);
			}
		};

		self.copy = function(food) {
			var copiedFood = angular.copy(food);
			copiedFood.name = food.name.concat(' (copy)');
			self.create(copiedFood);
		};
		
		self.toggleLock = function(food) {
			food.locked = !food.locked;
		};
		
		self.init();
	}
})();
