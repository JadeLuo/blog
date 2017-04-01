function addSubmit(formid) {
    var option = {
        success: function (data) {
            layer.msg(data);
        }
    }
    $(formid).ajaxSubmit(option)
}


function removeobj(thiz) {
    $.post($(thiz).attr("abbr") + '/delete', {id: $(thiz).attr("value")},
        function (data) {
            if (data.indexOf("ajaxSuccess") != -1) {
                layer.msg("操作成功");
                $(thiz).parent().parent().remove();
            } else {
                layer.msg("操作失败");
            }
        }
    );
}
function updateObj(thiz) {
    //Ajax获取
    $.get($(thiz).attr("abbr") + '/add', {id: $(thiz).attr("value")}, function (data) {
        layer.open({
            type: 1,
            area: ['500px', '300px'],
            btn: ['修改', '取消'], //按钮
            btn1: function (index) {
                $("#add").submit();
                layer.close(index);
            },
            btn2: function () {
            },
            content: data
        });
    });
}