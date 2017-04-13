function init(){
	clickKey();
	loginFocus();
	showForgot();
	showRegister();
	backLogin();
};

function clickKey(){
	// 键盘回车事件
	var tds = $("html");
	tds.keydown(function(event) {
		var myEvent = event || window.event; //解决不同浏览器获取事件对象的差异
		switch (myEvent.keyCode) {
		case 13://回车事件
			$("form").click();
			break;
		}
	});
};

function loginFocus(){
	var userName = $("#login_username");
	var passWord = $("#login_password");
	if (userName.val() == "") {
		userName.focus();
	} else if (passWord.val() == "") {
		passWord.focus();
	} else {
		$("#login_authCode").focus();
	}
};

function showForgot(){
	$('.forgot').on('click', function() {
		$('.box-login').removeClass("animated bounceInDown").hide();
		$('.box-forgot').addClass("animated bounceInLeft").show();
		$('#forgot_pwd').focus();
	});
};

function showRegister(){
	$('.register').on('click', function() {
		$('.box-login').removeClass("animated bounceInDown").hide();
		$('.box-register').addClass("animated bounceInLeft").show();
		$('#register_username').focus();
	});
};

function goLogin(){
	var boxToShow;
	if ($('.box-register').is(":visible")) {
		boxToShow = $('.box-register');
	} else {
		boxToShow = $('.box-forgot');
	}
	boxToShow.removeClass("animated bounceOutLeft").hide();

	$('.box-login').addClass("animated bounceInLeft").show();
	$("#login_username").focus();
}

function backLogin(){
	$('.go-back').click(function() {
		goLogin();
	});
};

var countdown=60;
function time(obj){
	if (countdown == 0) {
        obj.removeAttribute("disabled");    
        obj.value="免费获取验证码"; 
        countdown = 60; 
        return;
    } else { 
        obj.setAttribute("disabled", true); 
        obj.value="重新发送(" + countdown + ")"; 
        countdown--; 
    }
	setTimeout(function() { 
		time(obj) },1000) 
}

function rsendSms(obj){
	if($("#reg_phone").val() == "" || $("#reg_phone").val() == null){
		$("#register_Message").text("手机号不正确").show();
		return;
	}
	time(obj);
	var phone = $("#reg_phone").val();
	$.ajax({
		url: "registCode",
		type :'post',
		dataType: 'json',
		data: {"phone":phone},
		success: function(json){
			if(json.code == 300){
		        countdown = 0;
			}
			$("#register_Message").text(json.msg).show();
		}
	});
};

function fsendSms(obj){
	if($("#for_phone").val() == "" || $("#for_phone").val() == null){
		$("#forgot_Message").text("手机号不正确").show();
		return;
	}
	time(obj);
	var phone = $("#for_phone").val();
	$.ajax({
		url: "forgotCode",
		type :'post',
		dataType: 'json',
		data: {"phone":phone},
		success: function(json){
			if(json.code == 300){
		        countdown = 0;
			}
			$("#forgot_Message").text(json.msg).show();
		}
	});
}


function ajaxuser(thiz) {
    $.post('/unique', {name: $(thiz).val()}, function (data) {
        if (data.indexOf("ajaxSuccess") == -1) {
            $("#tag").text(data);
        } else {
            $("#tag").text("");
        }
    })
}