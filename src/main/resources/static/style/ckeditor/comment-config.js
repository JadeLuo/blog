CKEDITOR.editorConfig = function (config) {
    config.toolbarGroups = [
        {name: 'document', groups: ['mode', 'document', 'doctools']},
        {name: 'clipboard', groups: ['clipboard', 'undo']},
        {name: 'styles', groups: ['styles']},
        {name: 'forms', groups: ['forms']},
        {name: 'paragraph', groups: ['list', 'indent', 'blocks', 'align', 'bidi', 'paragraph']},
        {name: 'basicstyles', groups: ['basicstyles', 'cleanup']},
        {name: 'editing', groups: ['find', 'selection', 'spellchecker', 'editing']},
        {name: 'insert', groups: ['insert']},
        {name: 'links', groups: ['links']},
        {name: 'colors', groups: ['colors']},
        {name: 'tools', groups: ['tools']},
        {name: 'others', groups: ['others']},
        {name: 'about', groups: ['about']}
    ];

    config.removeButtons = 'Source,Save,NewPage,Preview,Templates,Cut,Copy,Paste,PasteText,PasteFromWord,SelectAll,Scayt,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,CopyFormatting,RemoveFormat,Indent,Outdent,CreateDiv,Language,BidiRtl,BidiLtr,PageBreak,Iframe,ShowBlocks,About,Print,Undo,Redo,Superscript,SpecialChar,Styles,Format,Font,FontSize,NumberedList,BulletedList,Blockquote,JustifyLeft,JustifyCenter,JustifyRight,JustifyBlock,Bold,Italic,Underline,Strike,Subscript,Find,Replace,Anchor,Flash,Table,HorizontalRule,TextColor,BGColor,Maximize';
    config.height = 100;
    config.language = 'zh-cn';
    config.smiley_images = ['1.png', '2.png', '3.png', '4.png', '5.png', '6.png', '7.png', '8.png',
        '9.png', '10.png', '11.png', '12.png', '13.png', '14.png', '15.png', '16.png', '17.png', '18.png', '19.png',
        '20.png', '21.png', '22.png', '23.png', '24.png', '25.png', '26.png', '27.png', '28.png', '29.png', '30.png', '31.png'];
    config.filebrowserImageUploadUrl = "/upload";
    config.tabSpaces = 4;
    config.uiColor = '#0bf7e5';
    config.removePlugins = 'elementspath';
    config.resize_enabled = false;
};