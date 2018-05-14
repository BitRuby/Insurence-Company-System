var app = angular.module('App', ['ngRoute']);


app.controller("DefaultController",
    function(){
    }
);

app.config(['$routeProvider', 
    function($routeProvider){
        $routeProvider
        .when('/login', {
            resolve: {
                "check": function($location, $rootScope){
                    if($rootScope.loggedIn){
                        $location.path('/dashboard');
                    }
                }
            },
            templateUrl: 'modules/login/login.html'
        })
        .when('/dashboard', {
            resolve: {
                "check": function($location, $rootScope){
                    if(!$rootScope.loggedIn){
                        $location.path('/login');
                    }
                }
            },
            templateUrl: 'modules/dashboard/dashboard.html'
        })
        .when('/', {
            templateUrl: 'modules/main.html'
        })
        .otherwise({
            redirectTo: '/'
        });
    }
]);