function sortCheck() {
    var sortBy = $('#sortTypeChoose').val();
    if(sortBy=="value") {
        $("#showIfSortByValues").css("visibility", "visible");
    }
}