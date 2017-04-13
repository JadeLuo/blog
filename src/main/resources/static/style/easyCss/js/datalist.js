
layui.define(['laypage', 'layer', 'form', 'pagesize'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        laypage = layui.laypage;
    var laypageId = 'pageNav';

    initilData();
    //页数据初始化
    //currentIndex：当前也下标
    //pageSize：页容量（每页显示的条数）
    function initilData() {
        var index = layer.load(1);
            layer.close(index);
            var pages = $("#pageTotale").val()
            var groups = $("#pageSize").val()
            laypage({
                cont: laypageId,
                pages: pages,
                groups: groups,
                skip: true,
                curr: $("#pageNumber").val(),
                jump: function(obj,first){//点击页码出发的事件
                    if(first!=true){//是否首次进入页面
                        var currentPage = obj.curr;//获取点击的页码
                        window.location.href =$("#listUrl").val()+"?pageNumber="+(currentPage-1);
                    }
                }
            });
    }
    exports('datalist');
});