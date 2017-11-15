//新建用户
function addUser(){
	var username = $(".username").val();
	var password = $(".password").val();
	var sex = $(".sex:checked").attr("value");
	var email = $(".email").val();
	var phoneno = $(".phoneno").val();
	if(username==""){
		alert("用户名不能为空！");
		return;
	}

	  var params ={"username":username,"email":email,"phoneno":phoneno,"sex":sex,"password":password};
		  $.ajax({
				url:"/yuantec_test/user/insert",//原来的
				type:"post",
				data:JSON.stringify(params),
				contentType:"application/json",
				dataType:"json",
				success:function(data){
					if(data=="0"){
							alert("插入失败！");
					}
					else if(data == "1"){
						  W.AA();
					}
				},error:function(){
					  W.AA();
					alert("系统繁忙!请稍后再试!!!");
				}

	           });//ajax结束
}
function closeAddPage(){
	frameElement.api.close(); 
}
