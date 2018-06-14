
angular
.module("App")
.factory("RestServices", RestServices);

function RestServices(AuthService, $http){
    return{
        healthContribution: function(){
            var REST_SERVICE_URI = 'http://localhost:8090/getHealthContribution/'+AuthService.user.id;
            $http({
                url: REST_SERVICE_URI,
                method: "GET",
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).then(function (response) {
                if (response.data) {
                    return response.data;
                } else {
                    return null;
                }
            }, function (errResponse) {
                console.log(errResponse);
                //$state.go('404');
            });
            //return null;
        }
    }
}