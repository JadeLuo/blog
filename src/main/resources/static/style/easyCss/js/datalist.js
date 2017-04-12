
layui.define(['laypage', 'layer', 'form', 'pagesize'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form(),
        laypage = layui.laypage;
    var laypageId = 'pageNav';

    //页数据初始化
    //currentIndex：当前也下标
    //pageSize：页容量（每页显示的条数）
    function initilData(currentIndex, pageSize) {
        var index = layer.load(1);

        //模拟数据加载
        setTimeout(function () {
            layer.close(index);
            //计算总页数（一般由后台返回）
            pages = Math.ceil(data.length / pageSize);
            //模拟数据分页（实际上获取的数据已经经过分页）
            var skip = pageSize * (currentIndex - 1);
            var take = skip + Number(pageSize);
            form.render('checkbox');  //重新渲染CheckBox，编辑和添加的时候
            laypage({
                cont: laypageId,
                pages: pages,
                groups: 8,
                skip: true,
                curr: currentIndex,
                jump: function (obj, first) {
                    var currentIndex = obj.curr;
                    if (!first) {
                        initilData(currentIndex, pageSize);
                    }
                }
            });
            layui.pagesize(laypageId, pageSize).callback(function (newPageSize) {
                //这里不能传当前页，因为改变页容量后，当前页很可能没有数据
                initilData(1, newPageSize);
            });
        }, 500);
    }

    //监听置顶CheckBox
    form.on('checkbox(top)', function (data) {
        var index = layer.load(1);
        setTimeout(function () {
            layer.close(index);
            if (data.elem.checked) {
                data.elem.checked = false;
            }
            else {
                data.elem.checked = true;
            }
            layer.msg('操作失败，返回原来状态');
            form.render();  //重新渲染
        }, 300);
    });

    //监听推荐CheckBox
    form.on('checkbox(recommend)', function (data) {
        var index = layer.load(1);
        setTimeout(function () {
            layer.close(index);
            layer.msg('操作成功');
        }, 300);
    });
    //添加数据
    $('#addArticle').click(function () {
        var index = layer.load(1);
        setTimeout(function () {
            layer.close(index);
            layer.msg('打开添加窗口');
        }, 500);
    });

    //输出接口，主要是两个函数，一个删除一个编辑
    var datalist = {
        deleteData: function (id) {
            layer.confirm('确定删除？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                layer.msg('删除Id为【' + id + '】的数据');
            }, function () {

            });
        },
        editData: function (id) {
            layer.msg('编辑Id为【' + id + '】的数据');
        }
    };
    exports('datalist', datalist);
});