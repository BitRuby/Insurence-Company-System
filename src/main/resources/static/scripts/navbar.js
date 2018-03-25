$( document ).ready(
    function() {

    var searchicon = $("#search-icon");
    var backicon = $("#back-icon");
    var navAll = $(".nav-all-collapse");
    var navSearch = $(".nav-search-collapse");

    searchicon.click(
        function(){
            navAll.css("display", "none");
            navSearch.css("display", "block");
        }
    );
    backicon.click(
        function(){
            navAll.css("display", "block");
            navSearch.css("display", "none");
        }
    );

    }
);