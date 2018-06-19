(function(){
    'use strict';
    angular.module('App')
    .controller('adminNotificationsController', adminNotificationsController);

    function adminNotificationsController(RestServices){
        var self = this;
        self.userVal = function(){
            if(self.userInput.length>1){
                RestServices.findUser(self.userInput).then(function(){
                    console.log(RestServices.data());
                    self.userList = RestServices.data();
                });
            }
        }
    }


})();