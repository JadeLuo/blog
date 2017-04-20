var pageNumber = 1;

$(window).scroll(function () {
    //下面这句主要是获取网页的总高度，主要是考虑兼容性所以把Ie支持的documentElement也写了，这个方法至少支持IE8
    var htmlHeight = document.body.scrollHeight || document.documentElement.scrollHeight;
    //clientHeight是网页在浏览器中的可视高度，
    var clientHeight =  document.documentElement.clientHeight;
    //scrollTop是浏览器滚动条的top位置，
    var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
    //通过判断滚动条的top位置与可视网页之和与整个网页的高度是否相等来决定是否加载内容；
    if (htmlHeight-scrollTop - clientHeight <10 ) {
        extracted();
    }
})

function extracted() {
    var path = "/article/list";
    if ($('#serch').val() != null && $('#serch').val() != "") {
        path = "/article/serch";
    }
    $.get(path, {content: "%" + $('#serch').val() + "%", pageNumber:pageNumber,user:$("#userId").val()}, function (data) {
        if(data!=""){
            $('.blog-main-left').append(data);
            pageNumber++;
        }
    });
}

function serch() {
    $.get("/article/serch", {content: "%" + $('#serch').val() + "%", pageNumber: 0}, function (data) {
        pageNumber = 0;
        $(".blog-main-left").replaceWith("<div class='blog-main-left'>" + data + "</div>");
    })
}