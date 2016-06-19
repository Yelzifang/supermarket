/**
 * 
 */

$(function(){
	alert("staffself");
	$.ajax({
        url: "../Staffself",
        type: "POST",
        dataType:"json",
        success: function (data) {
        	if(data.status){
        		var msg = data.message;
        		$("#stano").html(msg.stano);
        		alert(msg.stano);
        		$("#account").html(msg.account);
        		$("#staname").html(msg.staname);
        		alert(msg.staname);
        		if(msg.sex==1){
        			$("#sex").html("男");
        		}else{
        			$("#sex").html("女");
        		}  
        		$("#tele").html(msg.tele);
        		$("#birthday").html(msg.birthday);
        	}else{
        		alert(data.detail);
        	}
        },
        error:function(jqXHR){
        		alert("发生错误："+jqXHR.status);
        }
	});
	
}); 
$(function(){
	$("#alterpwd").bind("click",function(){
		alert("pswalter");
		$.ajax({
			url: "../PswAlter",
			type: "POST",
			data:{
				oldpwd:$("#oldpassword").val(),
				newpwd:$("#newpassword").val()
			},
			dataType:"json",
			success: function (data) {
				if(data.status){
					alert(data.detail);
				}else{
					alert(data.detail);
				}
			},
			error:function(jqXHR){
				alert("发生错误："+jqXHR.status);
			}
		});
	});
});
