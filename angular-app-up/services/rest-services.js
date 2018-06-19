
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
        receiveMessages: function(){
            REST_SERVICE_URI = 'http://localhost:8090/receiveMessages/'+AuthService.user.id;
            return this.resolve();
        },
        uncheckMessage: function(id){
            REST_SERVICE_URI = 'http://localhost:8090/setAsReaded/'+id;
            return this.resolve();
        },
        findUser: function(keyword){
            REST_SERVICE_URI = 'http://localhost:8090/searchUsersBySurname/'+keyword;
            return this.resolve();
        },
        findUserById: function(keyword){
            REST_SERVICE_URI = 'http://localhost:8090/users/'+keyword;
            return this.resolve();
        },
        showAllUsers: function(keyword){
            REST_SERVICE_URI = 'http://localhost:8090/users';
            return this.resolve();
        },
        sendMessageToAll: function(obj){
            REST_SERVICE_URI = 'http://localhost:8090/messageToAll';
            return this.resolveWithPost(obj);
        },
        sendMessageToUser: function(obj){
            REST_SERVICE_URI = 'http://localhost:8090/messageToUser';
            return this.resolveWithPost(obj);
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
        resolveWithPost: function(params){
            var deffered = $q.defer();
            $http({
                url: REST_SERVICE_URI,
                method: "POST",
                params
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
