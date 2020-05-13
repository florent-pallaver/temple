(function() {

	const USER_RS_URL = BASE_URL + 'user/' ;
	
	const module = angular.module('user', []) ;
	
	module.factory('UserService', ['$http', UserService]);

	function UserService($http) {
		const self = this;

		var currentUser = null;
		
		var users = [];

        self.isSignedIn = function() {
            return currentUser !== null;
        }

		self.getUsers = function() {
			return users;
		}
		
		self.getUser = function() {
			return currentUser;
		}
		
		self.setUser = function(user) {
			currentUser = user;
		};
		
		self.getFoodScores = function() {
			return $http.get(USER_RS_URL + 'scores/food');
		}
		
		self.setFoodScore = function(foodId, score) {
			return $http.put(USER_RS_URL + 'scores/food/' + foodId + '/' + score, {});
		}
		
		var onError = function(response) {
			window.alert('An error occured...');
			console.log(response);
		};

		return this;
	}
})();
