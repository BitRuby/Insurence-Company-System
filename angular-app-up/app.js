'use strict'; //Use Strict mode ES5
angular
.module('App', ['ui.router'])
.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider){

        var loginState = {
            name: 'login',
            url: '/login',
            views: {
                login: {
                    templateUrl: 'components/login/login.tpl.html',
                    controller: 'loginController as log'
                }
            }
        }
        var dashboardState = {
            name: 'dashboard',
            url: '/dashboard',
            views: {
                nav: {
                    templateUrl: 'components/navigation/navigation.tpl.html',
                    controller: 'navigationController as nav'
                },
                menu: {
                    templateUrl: 'components/menu/menu.tpl.html',
                    controller: 'menuController as menu'
                },
                content: {
                    templateUrl: 'components/dashboard/dashboard.tpl.html',
                    controller: 'dashboardController as dash'
                }
            },
        } 
        var userDetailsState = {
            name: 'userDetails',
            url: '/userDetails',
            views: {
                nav: {
                    templateUrl: 'components/navigation/navigation.tpl.html',
                    controller: 'navigationController as nav'
                },
                menu: {
                    templateUrl: 'components/menu/menu.tpl.html',
                    controller: 'menuController as menu'
                },
                content: {
                    templateUrl: 'components/user-details/user-details.tpl.html',
                    controller: 'userDetailsController as user'
                }
            },
        } 
        var defaultState = {
            name: 'default',
            url: '/',
            redirectTo: 'login'
        }
        $urlRouterProvider.otherwise('/');

        $stateProvider.state(loginState);
        $stateProvider.state(dashboardState);
        $stateProvider.state(userDetailsState);
        $stateProvider.state(defaultState);
    }
])
.run(
    function($transitions, $state) {
        $transitions.onSuccess({}, function($transitions){
            var toState = $transitions.$to();
            var auth = $transitions.injector().get('AuthService');
            if (!auth.user) {
                if (toState.name != 'login'){
                    $state.go('login');
                }
            }
            else{
                if (toState.data && toState.data.role) {
                    var hasAccess = false;
                    for (var i = 0; i < auth.user.roles.length; i++) {
                        var role = auth.user.roles[i];
                        if (toState.data.role == role) {
                            hasAccess = true;
                            break;
                        }
                    }
                    if (!hasAccess) {
                        $state.go('login');
                    }
                }
                if (toState.name == 'login'){
                    $state.go('dashboard');
                }
            }
          });
    }
); 
    /*function(AuthService, $rootScope, $state){
        $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams, options){  
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
    }*/

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


