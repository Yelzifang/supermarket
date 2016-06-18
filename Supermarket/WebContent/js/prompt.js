// JavaScript Document
function link1(){
	var post;
	post = document.getElementByID('select');
	if(post == '管理员'){
		window.location.href="admin.html";
	}
	else
	{
		window.location.href="staff.html";
	}
}
/*function sure_print(){
	alert("修改成功！");
}*/
function sure1(){
	var ensure=confirm("是否添加销售信息？");
	if(ensure==ture){
		alert("添加成功！");
	}
	else{
		alert("未进行添加！");
	}
}