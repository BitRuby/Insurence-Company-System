(function(){
    'use strict';
    angular.module('App')
    .controller('adminNotificationsController', adminNotificationsController);

    function adminNotificationsController(RestServices){
        var self = this;
        RestServices.showAllUsers().then(function(){
            self.userList = RestServices.data();
        });
        self.userVal = function(){
            if(self.userInput.length>1){
                RestServices.findUser(self.userInput).then(function(){
                    console.log(RestServices.data());
                    self.userList = RestServices.data();
                });
            }else{
                RestServices.showAllUsers().then(function(){
                    self.userList = RestServices.data();
                });
            }
        }
    }


})();