(function(){
    'use strict';
    angular
    .module('App')
    .controller('navigationController', navigationController);

    function navigationController($state, $scope, AuthService, RestServices){
        var self = this;
        if (AuthService.user){
            self.username = AuthService.user.name + ' ' + AuthService.user.surname;
        }
        RestServices.receiveMessages().then(function() {
            self.messages = RestServices.data();
        });
        self.submit = function(){
            AuthService.user = null;
            AuthService.flag = true;
            $state.go('login');
        }
    }
})();