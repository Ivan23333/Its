<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>货物更新页面</title>
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
					"updateGoods.do",
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
					<td class="text-title">货物名称：</td>
					<td class="text-content">
						<input type="hidden" name="goodsid" value="${goods.goodsid}"> 
						<input type="text" name="goodsname" value="${goods.goodsname}" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[2,20]'">
					</td>
				</tr>
				<tr>
					<td class="text-title">RFID：</td>
					<td class="text-content">
						<input type="text" name="RFID" value="${goods.RFID}"
							class="easyui-textbox theme-textbox-radius" >
					</td>
				</tr>
				<tr>
					<td class="text-title">单位：</td>
					<td class="text-content">
						<input type="text" name="unit" value="${goods.unit}"
							class="easyui-textbox theme-textbox-radius" >
					</td>
				</tr>
				<tr>
					<td class="text-title">价格：</td>
					<td class="text-content">
						<input type="text" name="price" value="${goods.price}"
							class="easyui-textbox theme-textbox-radius" >
					</td>
				</tr>
				<tr>
					<td class="text-title">数量：</td>
					<td class="text-content">
						<input type="text" name="num" value="${goods.num}"
							class="easyui-textbox theme-textbox-radius" >
					</td>
				</tr>
				<tr>
					<td class="text-title">产地：</td>
					<td class="text-content">
						<input type="text" name="origin" value="${goods.origin}"
							class="easyui-textbox theme-textbox-radius" >
					</td>
				</tr>
				<tr>
					<td class="text-title">生产时间：</td>
					<td class="text-content">
						<input type="text" name="producttime" value="${goods.producttime}"
							class="easyui-textbox theme-textbox-radius" >
					</td>
				</tr>
				<tr>
					<td class="text-title">位置：</td>
					<td class="text-content">
						<input type="text" name="status" value="${goods.status}"
							class="easyui-textbox theme-textbox-radius" >
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