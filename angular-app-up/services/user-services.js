
angular.module('App')
//.factory("Authorize", Authorize)
.service("AuthService", AuthService);

function AuthService(){
    return {
        user: null,
        flag: false,
        isAdmin: function(element) {
            return element == "ADMIN";
        }
    }
}