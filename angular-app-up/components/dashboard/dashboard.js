(function(){
    'use strict';
    angular.module('App')
    .controller('dashboardController', dashboardController);

    function dashboardController($state, $scope, AuthService){
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
        self.submit = function(){
            AuthService.user = null;
            AuthService.flag = true;
            $state.go('login');
        }
    }

})();