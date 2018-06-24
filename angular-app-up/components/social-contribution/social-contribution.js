(function(){
    'use strict';
    angular.module('App')
    .controller('socialContrController', socialContrController);

    function socialContrController($scope, RestServices, AuthService){
        var self = this;
        $scope.tab = 1;
        $scope.setTab = function(newTab){
            $scope.tab = newTab;
        }
        $scope.isSet = function(tabNum){
          return $scope.tab === tabNum;
        }
        RestServices.socialContribution().then(function(){
            console.log(RestServices.data());
            if (RestServices.data()){
                self.active = true;
                self.fromDate = RestServices.data().fromDate;
                self.amount = RestServices.data().amount;
                self.info = "Active (Insurence ID# "+RestServices.data().id+")";

                self.pAmount = RestServices.data().pension.amount;
                self.pFromDate = RestServices.data().pension.fromDate;
                if(RestServices.data().pension.granted)
                    self.pPaid = "Granted";
                else
                    self.pPaid = "Not granted";

                self.rAmount = RestServices.data().rent.amount;
                self.rFromDate = RestServices.data().rent.fromDate;
                if(RestServices.data().rent.paid)
                    self.rPaid = "Paid";
                else
                    self.rPaid = "Not paid";
            }
        });
        if (!AuthService.user.socialContribution.id)
            self.noInsurenceDisplay = true;

    }

})();