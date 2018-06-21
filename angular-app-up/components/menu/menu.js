(function(){
    'use strict';
    angular.module('App')
    .controller('menuController', menuController);

    function menuController(AuthService){
        var self = this;
        if (AuthService.user.ownCompany){
            this.company = true;
        }
    }

})();