(function(){
    'use strict';
    angular.module('App')
    .controller('adminApplicationsController', adminApplicationsController);

    function adminApplicationsController(AuthService, RestServices){
        var self = this;
        RestServices.getAllApplications().then(function(){
            console.log(RestServices.data());
            self.applicants = RestServices.data();
        });
    }

})();