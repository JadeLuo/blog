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
    alert(con)
    var thing = {cotent: con, articleId: $('#articleid').val()};
    $.post('/comment/add',
        {comment: $.toJSON(thing)}
        , function (data) {
            CKEDITOR.instances.comment.setData("");
            layer.msg(data)
            $('.blog-comment').load("/comment/list", {id: $('#articleid').val()});
        })
}