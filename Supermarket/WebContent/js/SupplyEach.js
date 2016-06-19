/**
 * 供应商销售柱状图
 */

function sendSupplyEachRequest(name){
	request=CreateRequest();
	
	request.open("POST","http://localhost:8080/Supermarket/SupplyEach",true);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.onreadystatechange=parseSupplyEachRequest;
//	alert("测试");
	request.send("name="+name);
}

function parseSupplyEachRequest(){
	
	if(request.status==200&&request.readyState==4){
		alert(request.responseText);
		
		var supply_json=JSON.parse(request.responseText);
		
		if(supply_json.status){
			
			var supply_msg=supply_json.message;
			
			CreateSupplyEach(supply_msg);
		}
		else
			alert(supply_json.detail);
	}
}

function CreateSupplyEach(supply_msg){
	
	var name_array=supply_msg.name;
	var value_array=supply_msg.value;
	
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    
    var option = {
            title: {
                text: '供应商-商品报表'
            },
            tooltip: {},
            legend: {
            	 orient: 'vertical',
                 left: 'center',
                data:['销量']
            },
            xAxis: {
            	
                data: name_array
                
            },
            yAxis: {
            	name:'销售金额/千元',
            },
            series: [{
            	
                type: 'bar',
                data: value_array
            }]
        };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
	
}