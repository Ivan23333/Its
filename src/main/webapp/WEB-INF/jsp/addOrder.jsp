<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>下订单页面</title>
<%@ include file="script.html"%>
<script type="text/javascript" src="//api.map.baidu.com/api?v=2.0&ak=m0mhkoxTVrCGRBNARuoN46q8jrXkE87b"></script>
<script type="text/javascript">

	$(function(){
		$("#saveBtn").click(function(){
			//校验输入的信息是否合法
			var result = $("#form").form("validate");
			//通过ajax进行数据的提交
			
				$.post(
					"addOrder.do",
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
		});
	})

</script>
</head>
<body>
	<section class="info-section">
		<form id="form" method="post">
			<table>
				<tr>
					<td class="text-title">货物名称：</td>
					<td class="text-content">
						<input type="text" name="goods" value="" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[1,20]'">
					</td>
				</tr>
				<tr>
					<td class="text-title">价格：</td>
					<td class="text-content">
						<input type="text" name="price" value="" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[1,20]'">
					</td>
				</tr>
				<tr>
					<td class="text-title">数量：</td>
					<td class="text-content">
						<input type="text" name="num" value="" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				
				<tr>
					<td class="text-title">单位：</td>
					<td class="text-content">
						<input type="text" name="unit" value="" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td class="text-title">所在地市：</td>
					<td class="text-content">
						<input type="text" name="city" value="" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td class="text-title">详细地址：</td>
					<td class="text-content">
						<input type="text" name="address" value="" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>

				<tr>
					<td class="text-title">订货人ID：</td>
					<td class="text-content">
						<input type="text" name="fromid" value=""
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td class="text-title">受理人ID：</td>
					<td class="text-content">
						<input type="text" name="toid" value=""
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>

				<tr>
					<td class="text-title">用户状态：</td>
					<td class="text-content">
						<select name="status" class="easyui-combobox theme-textbox-radius">
							<option value="1">待处理</option>
							<option value="2">处理中</option>
							<option value="3">已完成</option>
							<option value="4">已取消</option>
							<option value="5">已拒绝</option>
						</select>
					</td>
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