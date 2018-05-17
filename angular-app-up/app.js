'use strict'; //Use Strict mode ES5
angular
.module('App', ['ui.router'])
.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider){

        var loginState = {
            name: 'login',
            url: '/login',
            templateUrl: 'components/login/login.tpl.html',
            controller: 'loginController as log'
        }
        var dashboardState = {
            name: 'dashboard',
            url: '/dashboard',
            templateUrl: 'components/dashboard/dashboard.tpl.html',
            controller: 'dashboardController as dash'
        } 
        var defaultState = {
            name: 'default',
            url: '/',
            redirectTo: 'login'
        }
        $urlRouterProvider.otherwise('/');

        $stateProvider.state(loginState);
        $stateProvider.state(dashboardState);
        $stateProvider.state(defaultState);
    }
])
.run(
    function(AuthService, $rootScope, $state){
        $rootScope.$on('$stateChangeStart', function(event, toState){
            if (!AuthService.user) {
                if (toState.name != 'login'){
                    event.preventDefault();
                    $state.go('login');
                }
            }
            else {
                if (toState.data && toState.data.role) {
                    var hasAccess = false;
                    for (var i = 0; i < AuthService.user.roles.length; i++) {
                        var role = AuthService.user.roles[i];
                        if (toState.data.role == role) {
                            hasAccess = true;
                            break;
                        }
                    }
                    if (!hasAccess) {
                        event.preventDefault();
                        $state.go('default');
                    }
                }
            }
        });
    }
);
        /*

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
        */


