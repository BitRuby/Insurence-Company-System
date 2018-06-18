(function(){
    'use strict';
    angular
    .module('App')
    .controller('navigationController', navigationController);

    function navigationController($state, $scope, AuthService, RestServices){
        var self = this;
        if (AuthService.user){
            self.username = AuthService.user.name + ' ' + AuthService.user.surname;
        }
        self.submit = function(){
            AuthService.user = null;
            AuthService.flag = true;
            $state.go('login');
        }
        function updateMessageNumber(){
            RestServices.receiveMessages().then(function() {
                self.messages = RestServices.data();
                var n = 0;
                for (var i = 0; i< RestServices.data().length ; i++){
                    if (!RestServices.data()[i].readed) n++;
                }
                if (n!=0){
                    self.unread = n;
                    self.showBadge = true;
                } 
            });
        }
        updateMessageNumber();
        self.unCheckMessage = function(value){
            RestServices.uncheckMessage(value).then(function() {
               self.topicM = RestServices.data().topic;
               self.messageM = RestServices.data().message;
               updateMessageNumber();
            }); 
        }
        self.menu = function(){
            var mainmenu = document.getElementById("main-menu");

            if(mainmenu.style.display !== "block") {
                mainmenu.style.display = "block";
            }
            else {
                mainmenu.style.display = "none";
            }
        }
        self.search = function(){
            var navAll = $(".nav-all-collapse");
            var navSearch = $(".nav-search-collapse");
            var navbarHeader = $(".navbar-header");
            navAll.css("display", "none");
            navSearch.css("display", "block");
            navbarHeader.css("float", "left");
        }
        self.searchBack = function(){
            var navAll = $(".nav-all-collapse");
            var navSearch = $(".nav-search-collapse");
            var navbarHeader = $(".navbar-header");
            navAll.css("display", "block");
            navSearch.css("display", "none");
            navbarHeader.css("float", "none");
        }
    }
})();