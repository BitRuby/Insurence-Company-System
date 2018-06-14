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
        RestServices.healthContribution().then(function() {
            self.healthContributionView = RestServices.data().insured;
            self.healthContributionFromDate = RestServices.data().fromDate;
            self.healthContributionToDate = RestServices.data().toDate;
        });
        RestServices.laborFund().then(function() {
            self.laborFundView = RestServices.data().active;
            self.laborFundFromDate = RestServices.data().fromDate;
            self.laborFundToDate = RestServices.data().toDate;
        });
        RestServices.socialContribution().then(function() {
            console.log(RestServices.data())
            self.socialContributionPensionFromDate = RestServices.data().pension.fromDate;
            self.socialContributionRentFromDate = RestServices.data().rent.fromDate;
        });
    }
})();