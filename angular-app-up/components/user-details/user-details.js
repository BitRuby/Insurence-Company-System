(function(){
    'use strict';
    angular
    .module('App')
    .controller('userDetailsController', userDetailsController);

    function userDetailsController(AuthService, RestServices, $scope){
        var self = this;
        $scope.tab = 1;
        $scope.setTab = function(newTab){
            $scope.tab = newTab;
        }
        $scope.isSet = function(tabNum){
          return $scope.tab === tabNum;
        }
        if (AuthService.user){
            self.firstName = AuthService.user.name;
            self.secondName = AuthService.user.surname;
            self.dateOfBirth = AuthService.user.dateOfBirth;
            self.street = AuthService.user.address.street;
            self.postcode = AuthService.user.address.postcode;
            self.city = AuthService.user.address.city;
            self.country = AuthService.user.address.country;
            self.building = AuthService.user.address.building;
            self.roles = AuthService.user.roles;
        }
        self.healthContributionView = true;
        self.healthContributionFromDate = RestServices.healthContribution();
        if (RestServices.healthContribution.isInsured){
            self.healthContributionView = true;
            self.healthContributionFromDate = RestServices.healthContribution.fromDate;
            self.healthContributionToDate = RestServices.healthContribution.toDate;
        }
    }
})();