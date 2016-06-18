/**
 * 
 */


 //创建登录请求
function CreateLoginRequest(){
	var req=null;
	
	if(window.XMLHttpRequest)
		req=new XMLHttpRequest();
	else
		req=new ActiveXObject("Microsoft.XMLHTTP");
	
	return req;
}
//创建登录参数
function CreateLoginParamter(){

	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	var identity=document.getElementById("identitys").value;

	if(identity=="管理员")
		identity=1;
	else
		identity=2;
	alert("?username="+username+"&password="+password+"&identity="+identity);
	
	return "username="+username+"&password="+password+"&identity="+identity;
}
//发送登录请求
function SendLoginRequest(){

	
	request=CreateLoginRequest();
	
	request.open("POST","http://localhost:8080/Supermarket/Login",true);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

	request.onreadystatechange=parseLoginRequest;
	request.send(CreateLoginParamter());


	
}
//解析登录请求
function parseLoginRequest(){

	if(request.status==200&&request.readyState==4){
		var login_msg=JSON.parse(request.responseText);
		alert(login_msg.detail);
		var identity=document.getElementById("identitys").value;
		alert(identity);
		if(identity=="员工")
			window.location.href="staff.html";
		else
			alert("管理员1");

	}

	
}
