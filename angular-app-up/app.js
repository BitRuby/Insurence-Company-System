'use strict'; //Use Strict mode ES5
angular
.module('App', ['ui.router', 'hl.css.ui.router'])
.config(['$stateProvider', '$urlRouterProvider', '$qProvider',
    function($stateProvider, $urlRouterProvider, $qProvider){
        $qProvider.errorOnUnhandledRejections(false);
        var loginState = {
            name: 'login',
            url: '/login',
            views: {
                login: {
                    templateUrl: 'components/login/login.tpl.html',
                    controller: 'loginController as log'
                }
            },
            data: {
                css: {
                    login: 'data/style/login-inputs.css'
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
        var adminState = {
            name: 'admin',
            url: '/admin',
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
                    templateUrl: 'components/admin/admin.tpl.html',
                    controller: 'adminController as adm'
                }
            },
            data: {
                role: 'ADMIN'
            }
        }
        var adminNotState = {
            name: 'adminNotifications',
            url: '/admin/notifications',
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
                    templateUrl: 'components/admin/notifications/admin-notifications.tpl.html',
                    controller: 'adminNotificationsController as admn'
                }
            },
            data: {
                role: 'ADMIN'
            }
        } 
        var adminAppState = {
            name: 'adminApplications',
            url: '/admin/applications',
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
                    templateUrl: 'components/admin/applications/admin-applications.tpl.html',
                    controller: 'adminApplicationsController as admn'
                }
            },
            data: {
                role: 'ADMIN'
            }
        }
        var healthContrState = {
            name: 'healthContr',
            url: '/healthContribution',
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
                    templateUrl: 'components/health-contribution/health-contribution.tpl.html',
                    controller: 'healthContrController as hea'
                }
            }
        } 
        var laborFundContrState = {
            name: 'laborFundContr',
            url: '/laborFundContribution',
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
                    templateUrl: 'components/laborfund-contribution/laborfund-contribution.tpl.html',
                    controller: 'laborFundContrController as lab'
                }
            }
        }
        var socialContrState = {
            name: 'socialContr',
            url: '/socialContribution',
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
                    templateUrl: 'components/social-contribution/social-contribution.tpl.html',
                    controller: 'socialContrController as soc'
                }
            }
        }
        var companyState = {
            name: 'company',
            url: '/company',
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
                    templateUrl: 'components/company/company.tpl.html',
                    controller: 'companyController as cmp'
                }
            }
        }
        var applicationsState = {
            name: 'applications',
            url: '/applications',
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
                    templateUrl: 'components/applications/applications.tpl.html',
                    controller: 'applicationsController as app'
                }
            }
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
        $stateProvider.state(adminState);
        $stateProvider.state(adminNotState);
        $stateProvider.state(adminAppState);
        $stateProvider.state(healthContrState);
        $stateProvider.state(laborFundContrState);
        $stateProvider.state(socialContrState);
        $stateProvider.state(companyState);
        $stateProvider.state(applicationsState);
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
                    var hasAdmin = false;
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


