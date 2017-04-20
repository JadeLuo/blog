$(function () {
    CKEDITOR.replace('comment', {
        customConfig: '/style/ckeditor/comment-config.js'
    })
})
function commentArticle() {
    var con = CKEDITOR.instances.comment.getData();
    if (con == null || con == "") {
        layer.msg("不能为空")
        return;
    }
    var thing = {cotent: con, articleId: $('#articleid').val()};
    $.post('/comment/add',
        {comment: $.toJSON(thing)}
        , function (data) {
            CKEDITOR.instances.comment.setData("");
            layer.msg(data)
            $('.blog-comment').load("/comment/list", {id: $('#articleid').val()});
        })
}
function removeart(thiz) {
    layer.confirm('你确定要删除？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.post($(thiz).attr("abbr") + '/delete', {id: $(thiz).attr("value")},
            function (data) {
                layer.msg(data, {time: 1000}, function () {
                    window.location.href = "/article/list?user=" + $("#userId").val();
                });
            })
    }, function(){
    });
}