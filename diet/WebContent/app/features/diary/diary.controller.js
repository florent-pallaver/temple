(function() {

	const FOOD_RS_URL = BASE_URL + 'food/';

	const module = angular.module('diary', [ 'user', 'meal' ]);

	module.controller('DiaryController', [ '$http', '$routeParams', 'UserService', DiaryController ]);

	function DiaryController($http, $routeParams, UserService) {
		const self = this;

		const baseUrl = BASE_URL + 'diary';

		self.days = [];

		self.foods = {};
		self.foodList = [];
		
		self.newDay = new Date();

		self.day = null;

		self.init = function() {
			$http.get(baseUrl).then(function(response) {
				self.days = response.data;
				if (self.days && self.days.length) {
					// TODO checker si c'est today
					self.setDay(self.days[0]);
				} else {
					self.addDay();
				}
			}, onError);
		};

		self.initFood = function() {
			$http.get(FOOD_RS_URL).then(function(response) {
				self.foods = {};
				self.foodList = response.data;
				self.foodList.forEach(food => self.foods[food.id] = food);
			}, onError);
		}

		self.addDay = function() {
			var data = {
				day : self.newDay.getDate(),
				month : self.newDay.getMonth() + 1,
				year : self.newDay.getFullYear()
			};
			$http.post(baseUrl, data).then(self.init, onError);
		}

		self.setDay = function(day) {
			self.day = day;
			refreshMeals();
			computeTargetIntake();
		};

		var refreshMeals = function() {
			self.day.meals = [];
			self.day.intake = new Intake();
			
			$http.get(baseUrl + '/' + self.day.id + '/meals').then(function(response) {
				self.day.meals = response.data;
				self.day.meals.forEach(initMeal);
				self.day.meals.forEach(meal => self.day.intake.add(meal.intake));
			}, onError);
		};
		
		var initMeal = function(meal) {
			meal.items = {};
			meal.content.forEach(entry => meal.items[entry.key] = entry.value);
			refreshIntake(meal);
		};

		var refreshIntake = function(meal) {
			meal.intake = new Intake() ;
			Object.keys(meal.items).forEach(key => meal.intake.add(self.foods[key].intake, meal.items[key]));
		};
		
		function Intake() {
			var self = this;
			
			self.kcal = 0;
			self.fat = 0;
			self.carb = 0;
			self.protein = 0;

			self.add = function(intake, quantity) {
				var factor = quantity ? quantity / 100 : 1;
				self.kcal += intake.kcal * factor;
				self.fat += intake.fat * factor;
				self.carb += intake.carb * factor;
				self.protein += intake.protein * factor;
			};
			
			self.isNotEmpty = function() {
				return self.fat || self.carb || self.protein;
			};
			
			self.diff = function(intake) {
				var d = new Intake();
				d.add(self);
				d.add(intake, -100);
				return d;
			};
		}
		
		var computeTargetIntake = function() {
			var d = self.day;
			d.targetIntake = new Intake();
			var t = d.targetIntake;
			if(d.metabolicRate && d.metabolicFactor && d.weight) {
				if(d.growthMode === 'INCREASE') {
					var xtraKcal = 400; // entre 300 et 500
					var fatFactor = 1.25; // entre 1 et 1.5
					
					t.kcal = xtraKcal + (d.metabolicRate * d.metabolicFactor / 100);
					t.fat = d.weight * fatFactor;
					t.protein = d.weight * 2.5;
					t.carb = (t.kcal - (9 * t.fat)) / 4 - t.protein;
				}
				// TODO add mode decrease 
			}
		};
		
		self.setDayData = function() {
			var data = {
				dayDate: self.day.dayDate, 
				growthMode: self.day.growthMode, 
				weight: self.day.weight,
				metabolicRate: self.day.metabolicRate,
				metabolicFactor: self.day.metabolicFactor
			};
			computeTargetIntake();
			$http.put(baseUrl + '/' + self.day.id, data).then(noop, onError);			
		};
		
		self.toggleMealEditing = function(meal) {
			meal.editing = !meal.editing;
			if(!meal.editing) {
				self.foodList.forEach(food => !meal.items[food.id] && (delete meal.items[food.id]));
				$http.put(baseUrl + '/' + self.day.id + '/meals/' + meal.time, meal.items).then(function() {
					refreshIntake(meal);
				}, onError);
			} else {
				self.foodList.forEach(food => !meal.items[food.id] && (meal.items[food.id] = ''));
			}
		};
		
		self.initFood();
		self.init();
	}
})();
