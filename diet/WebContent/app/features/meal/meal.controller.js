(function() {

	const MEAL_RS_URL = BASE_URL + 'meal/' ;
	
	const module = angular.module('meal', ['user']) ;
	
	module.controller('MealController', ['$http', 'UserService', MealController]);

	function MealController($http, UserService) {
		const self = this;

		var intake = {
				
		};
		
		self.meals = [
			{
				day: '2019-03-01',
				count: 2,
				targetIntake: intake,
				meals: [
					{
						id: 1,
						mealDate: new Date(),
						intake: intake
					}
				]
			},
			
			
			{id: 1, eatDate: new Date()}
		];
		
        self.newMeal = {
        	eatDate: new Date(),
        	eaterId: null,
        	content: {}
        };

		self.init = function() {
//			$http.get(MEAL_RS_URL).then(function(response) {
//				self.meals = response.data;
//			}, onError);
		};

		var noop = function(){};
		
		var onError = function(response) {
			window.alert('An error occured...');
			console.log(response);
		};

		self.init();
	}
})();
