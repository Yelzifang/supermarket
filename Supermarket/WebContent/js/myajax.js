/**
 * 创建请求(ajax)
 */
function CreateRequest(){
	var req=null;
	
	
	if(window.XMLHttpRequest)
		req=new XMLHttpRequest();
	else
		req=new ActiveXObject("Microsoft.XMLHTTP");
	
	return req;
}
