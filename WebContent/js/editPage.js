function setEditPage(){
	var userid_edit = sessionStorage.getItem("userid_edit");
	var username_edit = sessionStorage.getItem("username_edit");
	var sex_edit = sessionStorage.getItem("sex_edit");
	var email_edit = sessionStorage.getItem("email_edit");
	var phoneno_edit = sessionStorage.getItem("phoneno_edit");
//	var jsonEditData = JSON.parse(editData);
	$(".username").attr("userid",userid_edit);
	$(".username").val(username_edit);
	$(".email").val(email_edit);
	$(".phoneno").val(phoneno_edit);
	$(".sex[value='"+sex_edit+"']").attr('checked','true');
}

function editUser(){
	var userid = $(".username").attr("userid");
	var username = $(".username").val();
	var sex = $(".sex:checked").attr("value");
	var email = $(".email").val();
	var phoneno = $(".phoneno").val();
	if(username==""){
		alert("用户名不能为空！");
		return;
	}
	  //编辑用户
	  var params ={"userid":userid,"username":username,"email":email,"phoneno":phoneno,"sex":sex};
		  $.ajax({
				url:"/yuantec_test/user/update",
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
					alert("系统繁忙!请稍后再试!!!");
				}

	           });//ajax结束
	//				
}
function closeAddPage(){
	frameElement.api.close(); 
}
