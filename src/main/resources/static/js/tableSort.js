function paginator(value) {
    var scriptParams = document.getElementById('paginatorValues');
    var perPage;
    var minimal;

    if (value==1) {
        perPage = parseInt(scriptParams.getAttribute('first'));
        minimal = perPage;
    }
    if (value==2) {
        perPage = parseInt(scriptParams.getAttribute('second'));
    }
    if (value==3) {
        perPage = parseInt(scriptParams.getAttribute('third'));
    }

    var items = $(".selection");
    items.show();
    var numItems = items.length;

    items.slice(perPage).hide();

    $(".selector").pagination({
        items : numItems,
        itemsOnPage : perPage,
        cssStyle : "light-theme",
        onPageClick : function(pageNumber) {
            var showFrom = perPage * (pageNumber - 1);
            var showTo = showFrom + perPage;
            items.hide().slice(showFrom, showTo).show();
        }
    });

    if (numItems <= perPage) {
        $(".selector").hide();
    }
    else {
        $(".selector").show();
    }

    if (numItems <= minimal) {
        $(".pageCountPanel").hide();
    }

};