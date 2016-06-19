<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>nidaye</h2><br/>
<form action="Login" method="POST">
账号：<input type="text" name="username"><br/>
密码：<input type="password" name="password"><br/>
员工<input type="radio" name="identity" value="1">&nbsp;
管理员<input type="radio" name="identity" value="2">&nbsp;
<input type="submit" value="点击"><br/>
</form>
<a href="SearchSale">员工销售查询</a><br/>
<a href="InsertSale">员工新增销售</a><br/>
<a href="SearchCom">商品查询</a><br/><br/>
<form action="PswAlter" method="POST">
账号：<input type="text" name="account"><br/>
密码：<input type="password" name="password"><br/>
<input type="submit" value="点击"><br/><br/>
<a href="StaffAlter">员工个人信息修改</a><br/>
<a href="Staffself">员工个人信息展示</a><br/>
<a href="StaffShow">员工信息查询</a><br/>
<a href="StaffDelete">员工信息删除</a><br/>
<a href="StaffInsert">员工信息插入</a><br/>
<a href="InsertCom">商品信息插入</a><br/>
<a href="AlterCom">商品信息修改</a><br/>
<a href="SearchCom">商品信息查询</a><br/>
<a href="ShowCom">商品信息展示</a><br/>
<a href="DeleteCom">商品信息展示</a><br/>
<a href="AlterCom">商品信息修改</a><br/>
<a href="ShowSort">类别查询</a><br/>
<a href="AlterSort">类别修改</a><br/>
<a href="DeleteSort">类别删除</a><br/>
<a href="InsertSort">类别插入</a><br/>
<a href="InsertStock">进货</a><br/>
<a href="ShowStock">查询进货信息</a><br/>
<a href="InsertRestock">退货</a><br/>
<a href="ShowRestock">查询退货信息</a><br/>
</body>
</html>