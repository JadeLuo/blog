
layui.define(['laypage', 'layer', 'form', 'pagesize'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form(),
        laypage = layui.laypage;
    var laypageId = 'pageNav';

    //ҳ���ݳ�ʼ��
    //currentIndex����ǰҲ�±�
    //pageSize��ҳ������ÿҳ��ʾ��������
    function initilData(currentIndex, pageSize) {
        var index = layer.load(1);

        //ģ�����ݼ���
        setTimeout(function () {
            layer.close(index);
            //������ҳ����һ���ɺ�̨���أ�
            pages = Math.ceil(data.length / pageSize);
            //ģ�����ݷ�ҳ��ʵ���ϻ�ȡ�������Ѿ�������ҳ��
            var skip = pageSize * (currentIndex - 1);
            var take = skip + Number(pageSize);
            form.render('checkbox');  //������ȾCheckBox���༭����ӵ�ʱ��
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
                //���ﲻ�ܴ���ǰҳ����Ϊ�ı�ҳ�����󣬵�ǰҳ�ܿ���û������
                initilData(1, newPageSize);
            });
        }, 500);
    }

    //�����ö�CheckBox
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
            layer.msg('����ʧ�ܣ�����ԭ��״̬');
            form.render();  //������Ⱦ
        }, 300);
    });

    //�����Ƽ�CheckBox
    form.on('checkbox(recommend)', function (data) {
        var index = layer.load(1);
        setTimeout(function () {
            layer.close(index);
            layer.msg('�����ɹ�');
        }, 300);
    });
    //�������
    $('#addArticle').click(function () {
        var index = layer.load(1);
        setTimeout(function () {
            layer.close(index);
            layer.msg('����Ӵ���');
        }, 500);
    });

    //����ӿڣ���Ҫ������������һ��ɾ��һ���༭
    var datalist = {
        deleteData: function (id) {
            layer.confirm('ȷ��ɾ����', {
                btn: ['ȷ��', 'ȡ��'] //��ť
            }, function () {
                layer.msg('ɾ��IdΪ��' + id + '��������');
            }, function () {

            });
        },
        editData: function (id) {
            layer.msg('�༭IdΪ��' + id + '��������');
        }
    };
    exports('datalist', datalist);
});