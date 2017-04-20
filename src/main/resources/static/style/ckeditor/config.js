/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function (config) {
    var heig = document.body.clientHeight;
    config.toolbarGroups = [
        {name: 'document', groups: ['mode', 'document', 'doctools']},
        {name: 'clipboard', groups: ['clipboard', 'undo']},
        {name: 'styles', groups: ['styles']},
        {name: 'forms', groups: ['forms']},
        {name: 'paragraph', groups: ['list', 'indent', 'blocks', 'align', 'bidi', 'paragraph']},
        {name: 'basicstyles', groups: ['basicstyles', 'cleanup']},
        {name: 'editing', groups: ['find', 'selection', 'spellchecker', 'editing']},
        {name: 'links', groups: ['links']},
        {name: 'insert', groups: ['insert']},
        {name: 'colors', groups: ['colors']},
        {name: 'tools', groups: ['tools']},
        {name: 'others', groups: ['others']},
        {name: 'about', groups: ['about']}
    ];

    config.removeButtons = 'Source,Cut,Copy,Paste,PasteText,PasteFromWord,SelectAll,Scayt,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,CopyFormatting,RemoveFormat,Indent,Outdent,CreateDiv,Language,BidiRtl,BidiLtr,Anchor,PageBreak,Iframe,ShowBlocks,About,Undo,Redo,Subscript,Superscript,Strike,SpecialChar,Link,Unlink,Smiley,Flash,Find,Replace,Preview,Print,NewPage';


    config.language = 'zh-cn';
    config.uiColor = '#F7B42C';
    config.height = heig;
    config.toolbarCanCollapse = true;
    config.codeSnippet_theme = 'monokai_sublime';
    //工具栏默认是否展开
    config.toolbarStartupExpanded = true;
    //当用户键入TAB时，编辑器走过的空格数，(&nbsp;) 当值为0时，焦点将移出编辑框 plugins/tab/plugin.js
    config.tabSpaces = 4;
    //是否对编辑区域进行渲染 plugins/editingblock/plugin.js
    config.editingBlock = true;
    config.smiley_images = ['1.png', '2.png', '3.png', '4.png', '5.png', '6.png', '7.png', '8.png',
        '9.png', '10.png', '11.png', '12.png', '13.png', '14.png', '15.png', '16.png', '17.png', '18.png', '19.png',
        '20.png', '21.png', '22.png', '23.png', '24.png', '25.png', '26.png', '27.png', '28.png', '29.png', '30.png', '31.png'];
    config.filebrowserImageUploadUrl = "/upload";
    config.resize_enabled = false;
    config.removePlugins = 'elementspath';
};
