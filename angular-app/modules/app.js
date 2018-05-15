var app = angular.module('App', ['ngRoute', 'angular-jwt']);


app.controller("DefaultController",
    function($scope, jwtHelper){
         
        $scope.token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3NhbXBsZXMuYXV0aDAuY29tLyIsInN1YiI6ImZhY2Vib29rfDEwMTU0Mjg3MDI3NTEwMzAyIiwiYXVkIjoiQlVJSlNXOXg2MHNJSEJ3OEtkOUVtQ2JqOGVESUZ4REMiLCJleHAiOjE0MTIyMzQ3MzAsInJvbGVzIjpbInJlYWRlciIsIndyaXRlciJdLCJpYXQiOjE0MTIxOTg3MzB9.egsc0YfweH_O9cpOApAkYbAw58buECpjDG77hfDUS_0"
        
        // Try another token by getting them from http://jwt.io
        
        $scope.$watch('token', function(token) {
            if (!token) return;
            
            $scope.decodedToken = jwtHelper.isTokenExpired(token);
        });
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