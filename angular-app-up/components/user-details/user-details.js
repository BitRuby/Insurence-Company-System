(function(){
    'use strict';
    angular
    .module('App')
    .controller('userDetailsController', userDetailsController);

    function userDetailsController(AuthService){
        var self = this;
        if (AuthService.user)
            self.fullName = AuthService.user.name;
    }
})();