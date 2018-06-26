(function(){
    'use strict';
    angular.module('App')
    .controller('adminNotificationsController', adminNotificationsController);
    
    function adminNotificationsController(RestServices){
        var self = this;
        var id;
        self.loading = true;
        RestServices.showAllUsers().then(function(){
            self.userList = RestServices.data();
            self.loading = false;
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
                self.name = RestServices.data().name + " " + RestServices.data().surname;
                self.username = RestServices.data().username;
                this.id = RestServices.data().id;
            });
        }
        self.submitAll = function(){
            var params = {
                topic: self.subjectAllInput,
                message: self.messageAllInput
            }
            RestServices.sendMessageToAll(params).then(function(){
            });
        }
        self.submitUser = function(){
            var params = {
                id: this.id,
                topic: self.subjectUserInput,
                message: self.messageUserInput
            }
            RestServices.sendMessageToUser(params).then(function(){
            });
        }
    }


})();