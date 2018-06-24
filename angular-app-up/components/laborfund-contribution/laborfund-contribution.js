(function(){
    'use strict';
    angular.module('App')
    .controller('laborFundContrController', laborFundContrController);

    function laborFundContrController($scope){
        var self = this;
        $scope.tab = 1;
        $scope.setTab = function(newTab){
            $scope.tab = newTab;
        }
        $scope.isSet = function(tabNum){
          return $scope.tab === tabNum;
        }
    }

})();