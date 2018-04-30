var app = angular.module('App', ['ngRoute']);

app.controller("DefaultController",
    function($http){
        var self = this;
        $http.get('http://127.0.0.1:5500/angular-app/json/users.json')
        .then(function(response){
            self.logins = response.data.records;
        });
    }
);


app.controller("LoginController",
    function($http, $location, $rootScope){
        var self = this;        
        self.submit = function(){
            $http.get('http://127.0.0.1:5500/angular-app/json/users.json')
            .then(function(response){
                var records = response.data.records;
                for (var i=0; i < records.length; i++){
                    if ( records[i].username == self.username &&
                        records[i].password == self.password ){
                            $rootScope.loggedIn = true;
                            $location.path('/dashboard');
                        }
                }
            });
        }
    }
);

app.config(['$routeProvider', 
    function($routeProvider){
        $routeProvider
        .when('/login', {
            templateUrl: 'modules/login.html'
        })
        .when('/dashboard', {
            resolve: {
                "check": function($location, $rootScope){
                    if(!$rootScope.loggedIn){
                        $location.path('/login');
                    }
                }
            },
            templateUrl: 'modules/dashboard.html'
        })
        .otherwise({
            redirectTo: '/'
        });
    }
]);