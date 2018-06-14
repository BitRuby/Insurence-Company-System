
angular
.module("App")
.factory("RestServices", RestServices);

function RestServices(AuthService, $http, $q){
    var data = {};
    var REST_SERVICE_URI = '';
    return{
        healthContribution: function(){
            REST_SERVICE_URI = 'http://localhost:8090/getHealthContribution/'+AuthService.user.id;
            return this.resolve();
        }, 
        laborFund: function(){
            REST_SERVICE_URI = 'http://localhost:8090/getLaborFundContribution/'+AuthService.user.id;
            return this.resolve();
        },
        socialContribution: function(){
            REST_SERVICE_URI = 'http://localhost:8090/getSocialContribution/'+AuthService.user.id;
            return this.resolve();
        },
        resolve: function(){
            var deffered = $q.defer();
            $http({
                url: REST_SERVICE_URI,
                method: "GET",
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).then(function (response) {
                deffered.resolve(response);  
                if (response.data){
                    data = response.data;
                }
            }, function (errResponse) {
                deffered.reject(errResponse);
            });
            return deffered.promise;
        },
        data: function(){
            return data;
        }
    }
}