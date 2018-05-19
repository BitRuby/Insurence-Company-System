$( document ).ready(
    function() {

    function choosePic() {
            var myPix = new Array("icover.jpg", "icover2.jpg", "icover3.jpg");
            var randomNum = Math.floor((Math.random() * myPix.length));
            document.getElementById("bg").style.backgroundImage = "url('data/images/"+myPix[randomNum]+"')";
    }
    choosePic();
    var searchicon = $("#search-icon");
    var menuicon = $("#menu-icon");
    var mainmenu = $('#main-menu');
    var backicon = $("#back-icon");
    var navAll = $(".nav-all-collapse");
    var navSearch = $(".nav-search-collapse");

    var navbarHeader = $(".navbar-header");
    $( window ).resize(
        function() {
            if( $(window).width() >= 480 ){
                mainmenu.css("display", "block");
            }
            if( $(window).width() < 480 ){
                mainmenu.css("display", "none");
            }
        }
    );
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