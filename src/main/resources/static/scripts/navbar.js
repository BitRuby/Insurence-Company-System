$( document ).ready(
    function() {

    var searchicon = $("#search-icon");
    var menuicon = $("#menu-icon");
    var mainmenu = $('#main-menu');
    var backicon = $("#back-icon");
    var navAll = $(".nav-all-collapse");
    var navSearch = $(".nav-search-collapse");

    var navbarHeader = $(".navbar-header");

    searchicon.click(
        function(){
            navAll.css("display", "none");
            navSearch.css("display", "block");
            navbarHeader.css("float", "left");
        }
    );
    backicon.click(
        function(){
            navAll.css("display", "block");
            navSearch.css("display", "none");
            navbarHeader.css("float", "none");
        }
    );
        menuicon.click(
            function(){
                if(!mainmenu.is(':visible')) {
                    mainmenu.css("display", "block");
                }
                else {
                    mainmenu.css("display", "none");
                }
            }
        );
    }
);