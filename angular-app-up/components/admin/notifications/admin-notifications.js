(function(){
    'use strict';
    angular.module('App')
    .controller('adminNotificationsController', adminNotificationsController);

    function adminNotificationsController(RestServices){
        var self = this;
        var id;
        RestServices.showAllUsers().then(function(){
            self.userList = RestServices.data();
        });
        self.userVal = function(){
            if(self.userInput.length>1){
                RestServices.findUser(self.userInput).then(function(){
                    self.userList = RestServices.data();
                });
            }else{
                RestServices.showAllUsers().then(function(){
                    self.userList = RestServices.data();
                });
            }
        }
        self.setUser = function(id){
            RestServices.findUserById(id).then(function(){
                console.log(RestServices.data());
                self.name = RestServices.data().name + " " + RestServices.data().surname;
                self.username = RestServices.data().username;
                id = RestServices.data().id;
            });
        }
    }


})();