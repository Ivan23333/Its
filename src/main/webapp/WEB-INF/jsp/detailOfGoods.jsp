<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>货物详细信息页面</title>
<%@ include file="script.html"%>
</head>
<body>
	<section class="info-section">
		<form id="form" >
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
					<td class="text-title">状态：</td>
					<td class="text-content">
						<input type="text" name="status" value="${goods.status}"
							class="easyui-textbox theme-textbox-radius" >
					</td>
				</tr>
				<tr>
					<td class="text-title">所在仓库ID：</td>
					<td class="text-content">
						<input type="text" name="warehouseid" value="${detail.warehouseid}"
							class="easyui-textbox theme-textbox-radius" >
					</td>
				</tr>
				<tr>
					<td class="text-title">入库时间：</td>
					<td class="text-content">
						<input type="text" name="intime" value="${detail.intime}"
							class="easyui-textbox theme-textbox-radius" >
					</td>
				</tr>
				<tr>
					<td class="text-title">出库时间：</td>
					<td class="text-content">
						<input type="text" name="outtime" value="${detail.outtime}"
							class="easyui-textbox theme-textbox-radius" >
					</td>
				</tr>
				
				</table>
		</form>
	</section>
</body>
</html>