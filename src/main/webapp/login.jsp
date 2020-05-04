<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="static/css/login.css">
  </head>
  
  <body>
   	<div class="container">
   	<div class="center">
		<h1>物流追踪系统</h1>
		</div>
   	
   		<div id="formDiv">
   		
   			<form id="loginForm" action="#" method="post">
   				<p>
   					<label class="label" for="username">用户名：</label>
   					<input id="username" type="text" name="username" placeholder="请输入用户名" >
   					<p id="usernameInfo"></p>
   				</p>
   				<p>
   					<label class="label" for="password">密码：</label>
   					<input id="password" type="password" name="password" placeholder="请输入密码" >
   					<p id="passwordInfo"></p>
   				</p>
   				<p style="padding-left:0px;">
   					<input type="button" id="loginBtn" value="登录" class="buttons"/>
   					
   					<input type="reset" value="取消" class="buttons"/>
   				</p>
   			</form>
   		</div>
   	</div>
   	<script type="text/javascript" src="static/js/jquery-1.12.4.min.js"></script>
   	<script type="text/javascript">
   	$(function(){
   	 
   	 //为表单添加提交监听提交事件的事件处理函数
   	 $("#loginBtn").click(function() {
   	 	//提交到服务器
   	 	var username = $("#username").val();
   	 	var password = $("#password").val();
   	 	if(username == null || username == ""){
   	 		$("#usernameInfo").text("用户名不能为空！");
   	 		return;
   	 	}
   	 	if(password == null || password == ""){
	 		$("#passwordInfo").text("密码不能为空！");
	 		return;
	 	}
   	 	//校验通过之后，进行ajax提交
   	 	$.post(
   	 		"login.do",
   	 		{"username":username,"password":password},
   	 		function(data){
   	 			//用于判断是否成功登陆
   	 			if(data.flag == true){
   	 				alert(data.msg);
   	 				document.location.href="toIndex.do";
   	 			}else{
   	 				alert(data.msg);
   	 				return;
   	 			}
   	 		},
   	 		"json"
   	 	);
   	 	
   	 })
   	 
   	})
   	</script>
  </body>
</html>