
angular.module('App')
//.factory("Authorize", Authorize)
.service("AuthService", AuthService);

function AuthService(){
    return {
        user: null,
        flag: false
    }
}
/*
function Authorize(){
    var user;
    var id;
    var cond = false;

    var authorize = {
        setUser: setUser,
        currentUser: currentUser,
        setLoggedOutInfo: setLoggedOutInfo,
        getLoggedOutInfo: getLoggedOutInfo
    }
    return authorize;

    function setUser(id){
        this.id = id;
    }
    function currentUser(){
        return this.id;
    }
    function setLoggedOutInfo(cond){
        this.cond = cond;
    }
    function getLoggedOutInfo(){
        return this.cond;
    }
}
*/
(function(){
    'use strict';
    angular
    .module('App')
    .controller('loginController', loginController);
    function loginController($http, $q, $state, $scope, $rootScope, AuthService){
        var self = this;
        self.submit = function(){
            var params = {
                username: self.username,
                password: self.password
            }
            var REST_SERVICE_URI = 'http://localhost:8090/authenticate';
            //var deferredObject = $q.defer();
            $http({
                url: REST_SERVICE_URI,
                method: "POST",
                params
            }).then(function (response) {
                //deferredObject.resolve(response);
                self.password = null;
                if (response.data.token) {
                    self.message = '';
                    $http.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.token;
                    AuthService.user = response.data.user;
                    $rootScope.$broadcast('LoginSuccessful');
                    $state.go('dashboard');
                } else {
                    self.message = 'Authetication Failed. Invalid credentials provided';
                }
            }, function (errResponse) {
                //defferedObject.reject(errResponse);
                self.message = 'Authetication Failed. Invalid credentials provided';
            });
            //return deferredObject.promise;
        }
        if (AuthService.flag==true)
            self.showTouchID = true;
        self.enter = function(){
            if (event.which == 13){
                self.submit();
            }
        }
    }
    
    


/*
    angular.module('App')
    .controller('temp', temp)
    .controller('loginController', loginController);
    function loginController($http, $location, $rootScope, Authorize, $scope, $q){
        var self = this;        
        self.submit = function(){
            $http.get('http://127.0.0.1:5500/angular-app/json/users.json')
            .then(function(response){
                var records = response.data.records;
                for (var i=0; i < records.length; i++){
                    if ( records[i].username == self.username &&
                        records[i].password == self.password ){
                            Authorize.setUser(records[i].id);
                            $rootScope.loggedIn = true;
                            $location.path('/dashboard');
                        }
                }
                if ( Authorize.currentUser() == null ){
                    if (!self.password != "" || !self.username != "") { self.message = "Fields can't be empty!";}
                    else{ self.message = "Invalid credentials provided"; }
                }
            });
        }
        self.enter = function(){
            if (event.which == 13){
                self.submit();
            }
        }
        if (Authorize.getLoggedOutInfo())
        {
            self.showTouchID=true;
        }
    }
    function temp($http, $q){
        var user = {username: "John", password: "Doe"};
        var REST_SERVICE_URI = "";
        var deferredObject = $q.defer();
        $http.get(REST_SERVICE_URI, user).then(
            function(response){
                deferredObject.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while send user');
                defferedObject.reject(errResponse);
            }
        );
        return deferredObject.promise;
    }
*/
})();
