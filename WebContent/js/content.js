$(function(){
	//获取用户
	function getUser(){
		var isHaveUser = sessionStorage.getItem("user");
		var userJson = JSON.parse(isHaveUser);
		return userJson;
	}
	
	//登陆检测
	function check(){
		var isHaveUser = sessionStorage.getItem("user");
		if(!isHaveUser){
			window.location.href = "../index.html";
		}
	}
	//退出登录
	$("#link_2").click(function(){
		layer.confirm('确定要退出吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				sessionStorage.removeItem("user");
				history.go(0);
			}, function(){
				layer.closeAll();
			});
	});
	
	//显示用户名
	function showLoginNm(){
		var userName = getUser().tabUser.username;
		if(userName){
			$("#link_0 a").html("您好，"+userName+"");
		}
	}
	check();
	showLoginNm();
	
});