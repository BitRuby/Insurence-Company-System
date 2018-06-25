(function(){
    'use strict';
    angular.module('App')
    .controller('adminApplicationsController', adminApplicationsController);

    function adminApplicationsController(AuthService, RestServices){
        var self = this;
        RestServices.getAllApplications().then(function(){
            self.applicants = RestServices.data();
        });
        self.accept = function(id){
            RestServices.confirmApplication(id).then(function(){
                self.confirmAccepted = true;
            });
        }
        self.details = function(id){

        }
    }

})();