<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户更新页面</title>
<%@ include file="script.html"%>
<!-- <script type="text/javascript" src="static/jquery/jquery.min.js"></script> -->
<script type="text/javascript">
	$(function(){
		 $("#saveBtn").click(function(){
			//校验输入的信息是否合法
			var result = $("#form").form("validate");
			//通过ajax进行数据的提交
			if(result){
				alert("修改");
				$.post(
					"updateUser.do",
					$("#form").serialize(),
					function(data){
						alert(data.msg);
						if(data.success){
							//关掉当前弹出窗口，刷新父页面
							parent.closeTopWindow();
						}
						return;
					},
					"json"
				);
			}
		}); 
		
		
	})

</script>
</head>
<body>
	<section class="info-section">
		<form id="form" method="post">
			<table>
				<tr>
					<td class="text-title">用户名称：</td>
					<td class="text-content">
						<input type="hidden" name="userid" value="${user.userid}"> 
						<input type="text" name="username" value="${user.username}" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[2,20]'">
					</td>
				</tr>

				<tr>
					<td class="text-title">用户密码：</td>
					<td class="text-content">
						<input type="password" name="password" value="${user.password}"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[5,20]'">
					</td>
				</tr>
				<tr>
					<td class="text-title">地址：</td>
					<td class="text-content">
						<input type="text" name="address" value="${user.address}" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>

				<tr>
					<td class="text-title">联系电话：</td>
					<td class="text-content">
						<input type="text" name="phone" value="${user.phone}"
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td class="text-title">电子邮箱：</td>
					<td class="text-content">
						<input type="text" name="email" value="${user.email}"
							class="easyui-textbox theme-textbox-radius",validType:'email'"></td>
				</tr>
				<tr>
					<td class="text-title">用户状态：</td>
					<td class="text-content">
						<select name="status" class="easyui-combobox theme-textbox-radius">
							<option value="1" ${user.status == 1 ? "selected":""}>正常</option>
							<option value="0" ${user.status == 0 ? "selected":""}>禁用</option>
							<option value="2" ${user.status == 2 ? "selected":""}>已删除</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="text-title">备注：</td>
					<td class="text-content">
						<textarea class="theme-textbox-radius" rows="5"  cols="20" name="note">${user.note}</textarea></td>
				</tr>
				<tr>
					<td class="text-title">创建时间：</td>
					<td class="text-content">
						<input type="text" name="createTime" value="${user.createtime}"
							class="easyui-textbox theme-textbox-radius" data-options="required:true"></td>
				</tr>
				
			
				<tr>
					<td colspan="2" style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0);" id="saveBtn" class="easyui-linkbutton button-primary">保存</a> 
						<a href="javascript:void(0);" id="resetBtn" class="easyui-linkbutton button-danger">重置</a>
					</td>
				</tr>
				</table>
		</form>
	</section>
</body>
</html>