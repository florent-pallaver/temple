(function() {

	const module = angular.module('home', ['user']) ;
	
	module.controller('HomeController', ['UserService', HomeController]);

	function HomeController(UserService) {
		const self = this;
	}
})();
