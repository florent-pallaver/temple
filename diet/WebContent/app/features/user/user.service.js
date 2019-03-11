(function() {

	const USER_RS_URL = BASE_URL + 'user/' ;
	
	const module = angular.module('user', []) ;
	
	module.factory('UserService', ['$http', UserService]);

	function UserService($http) {
		const self = this;

		self.users = [];

		self.init = function() {
			self.users = [{id: 1, name: 'flominou'}];
//			$http.get(USER_RS_URL).then(function(response) {
//				self.users = response.data;
//			}, onError);
		};
		
		var onError = function(response) {
			window.alert('An error occured...');
			console.log(response);
		};

		self.init();
		
		return this;
	}
})();
