(function(){
    'use strict';
    angular.module('App')
    .controller('socialContrController', socialContrController);

    function socialContrController($scope, RestServices){
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
            }
        });
    }

})();