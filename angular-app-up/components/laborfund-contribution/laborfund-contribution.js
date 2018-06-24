(function(){
    'use strict';
    angular.module('App')
    .controller('laborFundContrController', laborFundContrController);

    function laborFundContrController($scope, RestServices){
        var self = this;
        $scope.tab = 1;
        $scope.setTab = function(newTab){
            $scope.tab = newTab;
        }
        $scope.isSet = function(tabNum){
          return $scope.tab === tabNum;
        }
        RestServices.laborFund().then(function(){
            self.active = true;
                self.fromDate = RestServices.data().fromDate;
                self.amount = RestServices.data().amount;
                self.toDate = RestServices.data().fromDate;
                if (RestServices.data().insured)
                    self.info = "Active (Insurence ID# "+RestServices.data().id+")";
                else
                    self.info = "Not active";
            });
        }

})();