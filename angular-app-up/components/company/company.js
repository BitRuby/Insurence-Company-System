(function(){
    'use strict';
    angular.module('App')
    .controller('companyController', companyController);

    function companyController($scope, AuthService){
        $scope.tab = 1;
        $scope.setTab = function(newTab){
            $scope.tab = newTab;
        }
        $scope.isSet = function(tabNum){
          return $scope.tab === tabNum;
        }
        console.log(AuthService.user);
    }

})();