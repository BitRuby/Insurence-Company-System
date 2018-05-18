(function(){
    'use strict';
    angular.module('App')
    .controller('dashboardController', dashboardController);

    function dashboardController($location, $rootScope){
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