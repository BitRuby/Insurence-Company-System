(function(){
    'use strict';
    angular
    .module('App')
    .controller('navigationController', navigationController);

    function navigationController($state, $scope, AuthService){
        var self = this;
        if (AuthService.user)
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