
function serch() {
    $.get("/article/serch", {content: "%" + $('#serch').val() + "%", pageNumber: 0}, function (data) {
        pageNumber = 0;
        $("#content").replaceWith("<div id='content'>" + data + "</div>");
    })
}
function toDetial(id) {
    window.location.href = "/article/details?id=" + id;
}