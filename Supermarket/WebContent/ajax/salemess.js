/**
 * 
 */
/*function createparameter(){
	alert("saleno"+$("#saleno").val());
	alert("salecomno:"+$("#salecomno").val());
	alert("salesortno:"+$("#salesortno").val());
	alert("id_state:"+$("input:radio[name='id_state']:checked").val());
	alert("id_saledate:"+$("input:radio[name='id_saledate']:checked").val());
	alert("saleall:"+$("#saleall").prop("checked"));
}*/



alert("salemess");
$(function(){
	$("#record").bind("click",function(){
		alert("insertstork");
		$.ajax({
			url: "../InsertSale",
			type: "post",
			data:{
				comno:$("#comno").val(),
				saleamount:$("#saleamount").val()
			},
			dataType:"json",
			success: function (data) {
				alert("请求成功！");
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
	
	
	$("#saleseek").bind("click",function(){
		alert("searchstork");
		$.ajax({
			url: "../SearchSale",
			type: "post",
			data:{
				saleall:$("#saleall").prop("checked"),
				saleno:$("#saleno").val(),
				salecomno:$("#salecomno").val(),
				//salesortno:$("#salesortno").val(),
				id_state:$("input:radio[name='id_state']:checked").val(),
				id_saledate:$("input:radio[name='id_saledate']:checked").val(),
				saleamount1:$("#saleamount1").val(),
				saleamount2:$("#saleamount2").val()
			},
			dataType:"json",
			success: function (data) {
				alert("请求成功！");
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
