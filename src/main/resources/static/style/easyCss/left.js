function setSrc(s) {
    $("#mainIframe").attr("src", $(s).attr("href"));
}
function showleft() {
    $("#leftNav").toggleClass("visible-xs-* visible-sm-* hidden-xs hidden-sm");
}