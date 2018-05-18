(function(){
    'use strict';
    angular.module('App')
    .controller('dashboardController', dashboardController);

    function dashboardController($location, $rootScope, AuthService){
        var self = this;
        self.username = AuthService.user.name;
        /*
        var self = this;
        self.submit = function(){
            Authorize.setUser(null);
            Authorize.setLoggedOutInfo(true);
            $rootScope.loggedIn = false;
            $location.path('/login');
        }
        */
    }

})();