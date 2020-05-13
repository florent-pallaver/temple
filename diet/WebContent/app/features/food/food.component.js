(function() {
	angular.module('food').component('food', {
		templateUrl : 'app/features/food/food.component.html',
		bindings : {
			value : '='
		}
	});
})();
