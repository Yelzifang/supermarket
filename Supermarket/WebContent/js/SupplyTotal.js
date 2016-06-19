/**
 * 员工报表
 */

var request;
window.onload=function(){
	
	sendSupplyTotalRequest();
}

function sendSupplyTotalRequest(){
	request=CreateRequest();
	
	request.open("POST","http://localhost:8080/Supermarket/SupplyTotal",true);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.onreadystatechange=parseSupplyTotalRequest;
//	alert("测试");
	request.send("");
}

function parseSupplyTotalRequest(){
	
	if(request.status==200&&request.readyState==4){
		alert(request.responseText);
		
		var supply_json=JSON.parse(request.responseText);
		
		if(supply_json.status){
			
			var supply_msg=supply_json.message;
			
			CreateSupplyTotal(supply_msg);
		}
		else
			alert(supply_json.detail);
		
	}
}

function CreateSupplyTotal(msg){
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    
    // 指定图表的配置项和数据
    option = {
    		
    		 title: {
                 text: '供应商报表'
             },
    		
    	    series : [
    	        {
    	            name: '销售总金额',
    	            type: 'pie',
    	            radius: '55%',
    	            data:msg
    	        }
    	    ]
    	};

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    
    myChart.on('click', function (params) {

        console.log(params.name);
        sendSupplyEachRequest(params.name);
        
    });
}