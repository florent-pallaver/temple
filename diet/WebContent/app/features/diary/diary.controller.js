(function() {

	const FOOD_RS_URL = BASE_URL + 'food/';

	const module = angular.module('diary', [ 'user', 'meal' ]);

	module.controller('DiaryController', [ '$http', '$routeParams', 'UserService', DiaryController ]);

	function DiaryController($http, $routeParams, UserService) {
		const self = this;

		const baseUrl = BASE_URL + 'diary';
		
		self.units = {
			'GRAM': 'g', 
			'MILLILITER': 'ml', 
			'UNIT': ''
		};
		
		self.resultClasses = {
			'FAILURE': 'bg-danger',
			'CHEAT_DAY': 'bg-warning',
			'PASSABLE': 'bg-info',
			'SUCCESS': 'bg-success'
		};
		
		self.days = [];
		self.dates = [];
		
		self.foods = {};
		self.foodList = [];
		
		self.foodFilters = {
				'BREAKFAST': {},
				'MORNING_SNACK': {},
				'LUNCH': {},
				'AFTERNOON_SNACK': {},
				'DINNER': {},
				'EVENING_SNACK': {},
				'WORK_OUT': {}
		};
		
		self.newDay = new Date();
		
		self.day = null;

		self.init = function() {
			$http.get(baseUrl).then(function(response) {
				self.dates = [];
				self.days = response.data;
				if (self.days && self.days.length) {

					self.dates = self.days.map(day => day.dayDate);
					
					// TODO checker si c'est today
					self.day = self.days[0];
					self.setViewingDay();
				} else {
					self.addDay();
				}
			}, onError);
		};

		self.previousDay = function() {
			changeDay(1);
		};
		
		self.nextDay = function() {
			changeDay(-1);
		};
		
		function changeDay(i) {
			var newDayIndex = (self.dates.length + self.dates.indexOf(self.day.dayDate) + i) % self.dates.length;
			self.day = self.days[newDayIndex];
			self.setViewingDay();
		}
		
		self.initFood = function() {
			return $http.get(FOOD_RS_URL).then(function(response) {
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

		self.setViewingDay = function() {
			self.day.intake = new Intake();
			self.day.targetIntake = new Intake();
			self.day.intakeDiff = new Intake();
			refreshMeals();
		};

		var refreshMeals = function() {
			self.day.meals = [];
			$http.get(baseUrl + '/' + self.day.id + '/meals').then(function(response) {
				self.day.meals = response.data;
				self.day.meals.forEach(initMeal);
				self.day.meals.forEach(meal => self.day.intake.add(meal.intake));
				computeTargetIntake();
			}, onError);
		};
		
		var initMeal = function(meal) {
			meal.items = {};
			meal.itemIntakes = {};
			meal.editing = false;
			meal.showContent = false;
			meal.content.forEach(entry => {
				meal.items[entry.key] = entry.value;
				meal.itemIntakes[entry.key] = new Intake(self.foods[entry.key], entry.value);
			});
		};

		function Intake(food, quantity) {
			var self = this;
			
			self.add = function(intake, factor) {
				var factor = factor || 1;
				self.fat += intake.fat * factor;
				self.carb += intake.carb * factor;
				self.fiber += intake.fiber * factor;
				self.protein += intake.protein * factor;
				self.theoricKcal = self.fat * 9 + 4 * (self.carb + self.protein);
				self.kcal = self.theoricKcal;
			};
			
			self.reset = function() {
				self.fat = 0;
				self.carb = 0;
				self.fiber = 0;
				self.protein = 0;
				self.kcal = 0;
				self.theoricKcal = 0;
			};
			
			self.reset();
			
			if(food && quantity) {
				self.add(food.intake, food.counting === 'UNIT' ? quantity : (quantity / 100));
			}
		}
		
		var computeTargetIntake = function() {
			var d = self.day;
			var t = d.targetIntake;
			if(d.metabolicRate && d.activityFactor && d.fatFactor && d.proteinFactor && d.weight) {
				var kcalDelta = 0;
				switch(d.growthMode) {
				case 'INCREASE':
					kcalDelta = +300; // entre 200 et 300 ?
					break;
				case 'DECREASE':
					kcalDelta = -300;
					break;
				}
				t.kcal = kcalDelta + (d.metabolicRate * d.activityFactor / 100);
				t.fat = d.weight * d.fatFactor / 100;
				t.protein = d.weight * d.proteinFactor / 100;
				t.carb = (t.kcal - (9 * t.fat)) / 4 - t.protein;
				t.fiber = 35;
			}
			d.intakeDiff.reset();
			d.intakeDiff.add(d.intake, 1);
			d.intakeDiff.add(t, -1);
		};
		
		self.setDayData = function() {
			var data = {
				dayDate: self.day.dayDate, 
				weight: self.day.weight,
				metabolicRate: self.day.metabolicRate,
				activityFactor: self.day.activityFactor,
				growthMode: self.day.growthMode, 
				fatFactor: self.day.fatFactor,
				proteinFactor: self.day.proteinFactor,
				sleepDuration: self.day.sleepDuration,
				fasting: self.day.fasting,
				result: self.day.result,
				comments: self.day.comments || '',
			};
			$http.put(baseUrl + '/' + self.day.id, data).then(computeTargetIntake, onError);			
		};
		
		self.toggleMealEditing = function(meal) {
			meal.editing = !meal.editing;
			if(!meal.editing) {
				self.foodList.forEach(food => !meal.items[food.id] && (delete meal.items[food.id]));
				$http.put(baseUrl + '/' + self.day.id + '/meals/' + meal.time, meal.items).then(function(response) {
					self.day.intake.add(meal.intake, -1);
					meal.intake = response.data.intake;
					meal.content = response.data.content;
					initMeal(meal);
					self.day.intake.add(meal.intake, +1);
					computeTargetIntake();
					meal.showContent = true;
				}, onError);
			} else {
				self.foodList.forEach(food => !meal.items[food.id] && (meal.items[food.id] = ''));
			}
		};
		
		self.foodComparator = function(foodId1, foodId2) {
			
		};
		
		self.initFood()
			.then(self.init);
	}
})();
