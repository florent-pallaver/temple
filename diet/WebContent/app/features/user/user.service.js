(function() {

	const USER_RS_URL = BASE_URL + 'user/' ;
	
	const module = angular.module('user', []) ;
	
	module.factory('UserService', ['$http', UserService]);

	function UserService($http) {
		const self = this;

		var currentUser = null;
		
		var users = [];

		self.getUsers = function() {
			return users;
		}
		
		self.getUser = function() {
			return currentUser;
		}
		
		self.setUser = function(user) {
			currentUser = user;
		};
		
		var onError = function(response) {
			window.alert('An error occured...');
			console.log(response);
		};

		return this;
	}
})();
