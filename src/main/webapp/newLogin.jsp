
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>物流追踪系统——LTS</title>
	<script type="text/javascript" src="/javascripts/jquery.1.12.4.min.js"></script>
    <style>
        *all{
            margin: 0;
            padding: 0;
        }
        div.inner,table{
            position:absolute;
        }
		body{
			background-image:url(./static/images/4.jpg);
			background-size:100%;
		}
        .outer{
            height: 100%;    
        }
        .inner{
			border: 5px solid rgba(255,255,255,1);
		    border-radius: 20px;
		    width: 370px;
		    height: 340px;
		    top: 40%;
		    left: 50%;
		    transform: translate(-50%,-50%);
			box-shadow:11px 11px 11px rgba(50,50,50,1);	///边框阴影
        }
        .table{
            top: 7%;
		    left: 12%;
			//border: solid;
			width: 260px;
			height: 190px;
			border-spacing:7px;
		}
        .under{
            position: fixed; 
            left: 0px; 
            bottom: 30px; 
            width: 100%; 
            text-align:center;
            font-size: 15px;
        }
		.Time{
			top: 65%;
			left: 62%;
            position: fixed; 
			width: 400px;
			text-align:right;
		}
		.TEXT{
			height:72%;
			width: 200px;
		}
		a {text-decoration: none}	//去超链接下划线
    </style>
</head>
<body>
	<div class="outer">
		<div class="inner">
			<table class="table">
				<caption> <h1>物流追踪系统</h1> </caption>
				<tr height="30px">
					<td><big><b>账&nbsp;&nbsp;号</b></big></td>
					<td><input type="text" name="username" id="username"class="TEXT"/> </td>
				</tr>
				<tr height="30px">
					<td><big><b>密&nbsp;&nbsp;码</b></big></td>
					<td><input class="TEXT"type="password" name="password" id="password"/> </td>
				</tr>
				<tr height="30px">
					<td colspan="2" align="right">
						<input type="button" id="loginBtn" value="登录" class="buttons"/>
   					
   					<input type="reset" value="取消" class="buttons"/>
					</td>
				</tr>
				<tr height="20px"><td colspan="2" style="text-align:right;font-size:small"><a href = "">联系我们</a> | <a href = "">帮助中心</a> | <a href = "">其他</a></td></tr>
			 </table>
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
