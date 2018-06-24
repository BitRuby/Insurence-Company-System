(function(){
    'use strict';
    angular.module('App')
    .controller('companyController', companyController);

    function companyController($scope, AuthService, RestServices){
        var self = this;
        $scope.tab = 1;
        $scope.setTab = function(newTab){
            $scope.tab = newTab;
        }
        $scope.isSet = function(tabNum){
          return $scope.tab === tabNum;
        }
        if (AuthService.user.ownCompany){
            self.companyTabs = true;
            self.companyName = AuthService.user.ownCompany.companyName;
            self.companyAddressCity = AuthService.user.ownCompany.address.city;
            self.companyAddressBuilding =  AuthService.user.ownCompany.address.building;
            self.companyAddressStreet =  AuthService.user.ownCompany.address.street;
            self.companyAddressPost = AuthService.user.ownCompany.address.postcode;
            self.companyAddressCountry = AuthService.user.ownCompany.address.country;
            self.companyTID = AuthService.user.ownCompany.nip;
            self.companyBRN = AuthService.user.ownCompany.regon;
            self.companyID = AuthService.user.ownCompany.companyID;
        }
        else{
            self.noCompanyInfo = true;
        }
        RestServices.getAllCompanies().then(function(){
            for (var i=0; i<RestServices.data().length; i++){
                if (RestServices.data()[i].companyID == AuthService.user.ownCompany.companyID)
                    self.employees = RestServices.data()[i].employees; 
            }
        });
    }

})();