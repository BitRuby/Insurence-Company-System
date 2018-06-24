(function(){
    'use strict';
    angular.module('App')
    .controller('healthContrController', healthContrController);

    function healthContrController(RestServices, $scope){
        var self = this;
        $scope.tab = 1;
        $scope.setTab = function(newTab){
            $scope.tab = newTab;
        }
        $scope.isSet = function(tabNum){
          return $scope.tab === tabNum;
        }
        RestServices.healthContribution().then(function(){
            console.log(RestServices.data());
            if (RestServices.data()){
                self.active = true;
                if (RestServices.data().insured){
                    self.info = "Insured (Insurence ID# "+RestServices.data().id+")";
                }
                else {
                    self.info = "Not insured";
                }
                self.date = RestServices.data().fromDate;
                self.amount  = RestServices.data().amount;         
        }
        });
    }

})();