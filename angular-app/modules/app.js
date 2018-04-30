var app = angular.module('App', ['ngRoute']);
app.controller("DefaultController",
    function(){
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

app.controller("DashboardController",
    function($location, $rootScope){
        var self = this;
        self.submit = function(){
            $rootScope.loggedIn = false;
            $location.path('/login');
        }
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