app.controller("DashboardController",
    function($location, $rootScope, Authorize){
        var self = this;
        self.submit = function(){
            Authorize.setUser(null);
            Authorize.setLoggedOutInfo(true);
            $rootScope.loggedIn = false;
            $location.path('/login');
        }
    }
);