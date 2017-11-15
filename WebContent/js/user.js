/*删除用户*/
function deleteAjax(params){
	$.ajax({
		url:"/yuantec_test/user/delete",
		type:"post",
		data:JSON.stringify(params),
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			if(data=="0"){
					alert("删除失败");
			}
			else if(data == "1"){
				history.go(0);
				
			}
		},error:function(){
			alert("系统繁忙!请稍后再试!!!");
		}

       });//ajax结束
}
function deleteUser(){
	var userid = $(this).parent().parent().attr("value");
    var params ={"userid":userid};
    layer.confirm('确定要删除吗？', {
      shadeClose :true,
  	  btn: ['确定','取消'] //按钮
  	}, function(){
  		deleteAjax(params);
  	}, function(){
  		layer.closeAll();
  	});
}
/*新增弹出框*/
function addPage(){
	$.dialog({
		width:'400px',
		height:'250px',
		content: 'url:addPage.html',
		max: false,
	    min: false

	});
}
/*编辑弹出框*/
function editPage(){
	var userid = $(this).parent().parent().attr("value");
	var username = $(this).parent().parent().find("td.username").html();
	var sex = $(this).parent().parent().find("td.sex").html();
	
	
	var email = $(this).parent().parent().find("td.email").html();
	var phoneno = $(this).parent().parent().find("td.phoneno").html();
	
	//var editData = {"userid":userid,"username":username,"sex":sex,"email":email,"phoneno":phoneno};
	//editData = editData.toString();
	sessionStorage.setItem("userid_edit",userid);
	sessionStorage.setItem("username_edit",username);
	sessionStorage.setItem("sex_edit",sex);
	sessionStorage.setItem("email_edit",email);
	sessionStorage.setItem("phoneno_edit",phoneno);
	
	$.dialog({
		width:'400px',
		height:'250px',
		content: 'url:editPage.html',
		max: false,
	    min: false
	});
}

/*查询所有用户信息*/
function getAllUser(){
	  var params ={};
	  $.ajax({
			url:"/yuantec_test/user/allUser",//原来的
		type:"post",
		data:JSON.stringify(params),
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			if(data.rtnFlag=="0"){
				layer.msg("系统繁忙!请稍后再试!!!");
			}
			else{
				var  str ="";
				for(var i = 0;i<data.length; i++){
					 str += '<tr class="id" value="'+data[i].userid+'">';
				     str += '<td class="text-center username">'+data[i].username+'</td>';
				     str += '<td class="text-center sex">'+data[i].sex+'</td>';
				     str += '<td class="text-center email">'+data[i].email+'</td>';
				     str += '<td class="text-center phoneno">'+data[i].phoneno+'</td>';
				     str += '<td class="text-center">';
				     str += 	'<input type="button" value="编辑" class="btn btn-info btn_deit">';
				     str += 	'<input type="button" value="删除" class="btn btn-danger btn_delete">';
				     str += '</td>';
				     str += '</tr>';
				}
				$("tbody").html(str);
			}
		},error:function(){
			layer.msg("系统繁忙!请稍后再试!!!");
		}

     });//ajax结束
 }

/*刷新*/
function AA()
{
	history.go(0) ;
};

	
