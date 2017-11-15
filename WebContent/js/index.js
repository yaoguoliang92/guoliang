$(function () {
    //表单验证
    $('input[name="loginbtn"]').click(function(event) {
        var $name = $('input[name="username"]');
        var $password = $('input[name="password"]');
        var $verify = $('input[name="verify"]');
        var $text = $('#text');
        var _name = $.trim($name.val());
        var _password = $.trim($password.val());
        var _verify = $.trim($verify.val());

        if('' == _name){
            $text.text('请输入账号名！');
            $name.css("border","solid 3px red");
            $name.focus();
            return;
        }else{
        	$text.text('');
        	 $name.css("border","");
        }
        if('' == _password){
            $text.text('请输入密码！');
            $password.css("border","solid 3px red");
            $password.focus();
            return;
        }else{
        	$text.text('');
        	$password.css("border","");
       }

        var username = $name.val();
        var pass = $password.val();
        
      //登录
        var params ={"username":username,"password":pass};
		  $.ajax({
				url:"/yuantec_test/user/login",//原来的
				type:"post",
				data:JSON.stringify(params),
				contentType:"application/json",
				dataType:"json",
				success:function(data){
					var jsondata = JSON.stringify(data);
				
					if(data.rtnFlag=="0"){
						  //alert(jsondata);
							alert("用户名或密码错误");
					}
					else if(data.rtnFlag == "1"){
						sessionStorage.setItem("user", jsondata);
						location.href="html/content.html";
					}
				},error:function(){
					alert("系统繁忙!请稍后再试!!!");
				}

	           });//ajax结束
    	});
});
