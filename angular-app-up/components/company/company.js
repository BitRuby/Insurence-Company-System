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
            console.log(AuthService.user);
            self.companyTabs = true;
            self.companyName = AuthService.user.currentCompany.companyName;
            self.companyAddressCity = AuthService.user.currentCompany.address.city;
            self.companyAddressBuilding =  AuthService.user.currentCompany.address.building;
            self.companyAddressStreet =  AuthService.user.currentCompany.address.street;
            self.companyAddressPost = AuthService.user.currentCompany.address.postcode;
            self.companyAddressCountry = AuthService.user.currentCompany.address.country;
            self.companyTID = AuthService.user.currentCompany.nip;
            self.companyBRN = AuthService.user.currentCompany.regon;
            self.companyID = AuthService.user.currentCompany.companyID;
        }
        else{
            self.noCompanyInfo = true;
        }
        RestServices.getAllCompanies().then(function(){
            console.log(RestServices.data());
        
        });
    }

})();