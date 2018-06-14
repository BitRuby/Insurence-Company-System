
angular
.module("App")
.factory("RestServices", RestServices);

function RestServices(AuthService, $http, $q){
    var data = {};
    return{
        healthContribution: function(){
            var deffered = $q.defer();
            var REST_SERVICE_URI = 'http://localhost:8090/getHealthContribution/'+AuthService.user.id;
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