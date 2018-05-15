'use strict'; //Use Strict mode ES5
angular
.module('App', ['ngRoute', 'angular-jwt'])
.config(['$routeProvider', 
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
            templateUrl: 'components/login/login.tpl.html',
            controller: 'loginController as log'
        })
        .when('/dashboard', {
            resolve: {
                "check": function($location, $rootScope){
                    if(!$rootScope.loggedIn){
                        $location.path('/login');
                    }
                }
            },
            templateUrl: 'components/dashboard/dashboard.tpl.html',
            controller: 'dashboardController as dash'
        })
        .when('/', {
            redirectTo: '/login'
        })
        .otherwise({
            redirectTo: '/'
        });
    }
]);

