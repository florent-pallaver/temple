(function() {

	const module = angular.module('home', ['user']) ;
	
	module.controller('HomeController', ['UserService', HomeController]);

	function HomeController(UserService) {
		const self = this;

        self.init = function() {
            
        };

        self.init();
	}
})();
