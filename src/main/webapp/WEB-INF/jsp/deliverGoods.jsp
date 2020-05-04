<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发货页面</title>
<%@ include file="script.html"%>
<!-- <script type="text/javascript" src="static/jquery/jquery.min.js"></script> -->
<script type="text/javascript">
function deliverUserFind(url){
	//获取父id在选中的父id下面添加子菜单
	var result = $("#form").form("validate");
	if(!result){
		$.messager.alert("警告","请正确填写ID！");
		return;
	}
	
	var id = $('input[name="userid"]').val();
		$.get(
			url,
			{"id":id},
			function(data){
				
				if(data.success){
					alert(data.msg);
	 			$("#form").form("load",{
	 				username:data.user.username,
	 				address:data.user.address,
	 				phone:data.user.phone,
	 				email:data.user.email
	 				
	 			}); 
				}
			},
			"json"
		);
	
}
function deliverGoodsFind(url){
	//获取父id在选中的父id下面添加子菜单
	var result = $("#form").form("validate");
	if(!result){
		$.messager.alert("警告","请正确填写ID！");
		return;
	}
	
	var id = $('input[name="goodsid"]').val();
		$.get(
			url,
			{"id":id},
			function(data){
				
				if(data.success){
					alert(data.msg);
	 			$("#form").form("load",{
	 				goodsname:data.goods.goodsname,
	 				RFID:data.goods.RFID,
	 				unit:data.goods.unit,
	 				price:data.goods.price,
	 				num:data.goods.num,
	 				origin:data.goods.origin,
	 				producttime:data.goods.producttime
	 				
	 			}); 
				}
			},
			"json"
		);
	
}
function deliverGoods(url){
	var result = $("#form").form("validate");
	if(!result){
		$.messager.alert("警告","请正确填写信息！");
		return;
	}
	var userid = $('input[name="userid"]').val();
	var goodsid = $('input[name="goodsid"]').val();
	var orderid = $('input[name="orderid"]').val();
	var num = $('input[name="num"]').val();
	var deliverNum = $('input[name="deliverNum"]').val();
	if(parseInt(deliverNum) > parseInt(num)){
		$.messager.alert("警告","货物储量不足！");
		return;
	}
		$.get(
			url,
			{	"userid":userid,
				"goodsid":goodsid,
			 	"num":num,
			 	"deliverNum":deliverNum,
			 	"orderid":orderid
			 },
			function(data){
				
				if(data.success){
					alert(data.msg);
	 			$("#form").form("clear"); 
				}
			},
			"json"
		);
	
}

</script>
</head>
<body>
	<section class="info-section">
	<p>${user.username}</p>
		<form id="form" method="post">
			<table>
				<tr>
					<td class="text-title">用户ID：</td>
					<td class="text-content">
						<input type="text" name="userid" value="${user.userid}" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[1,20]'">
					</td>
					<td class="text-title">输入ID后点击：</td>
					<td colspan="2" style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0);" id="selectBtn" class="easyui-linkbutton button-primary" onclick="return deliverUserFind('deliverUserFind.do');">获取用户信息</a> 
					</td>
				</tr>
				<tr>
					<td class="text-title">用户名称：</td>
					<td class="text-content">
						<input type="text" name="username" value="${user.username}" 
							class="easyui-textbox theme-textbox-radius" >
					</td>
				
					<td class="text-title">地址：</td>
					<td class="text-content">
						<input type="text" name="address" value="${user.address}" 
							class="easyui-textbox theme-textbox-radius" >
					</td>
				</tr>

				<tr>
					<td class="text-title">联系电话：</td>
					<td class="text-content">
						<input type="text" name="phone" value="${user.phone}"
							class="easyui-textbox theme-textbox-radius" >
					</td>
				
					<td class="text-title">电子邮箱：</td>
					<td class="text-content">
						<input type="text" name="email" value="${user.email}"
							class="easyui-textbox theme-textbox-radius",data-options="validType:'email'"></td>
				</tr>

				<tr>
					<td class="text-title">货物ID：</td>
					<td class="text-content">
						<input type="text" name="goodsid" value="${goods.goodsid}"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[1,20]'">
					</td>
					<td class="text-title">输入ID后点击：</td>
					<td colspan="2" style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0);" id="selectBtn" class="easyui-linkbutton button-primary" onclick="return deliverGoodsFind('deliverGoodsFind.do');">获取货物信息</a> 
					</td>
				</tr>
				
				
				<tr>
					<td class="text-title">货物名称：</td>
					<td class="text-content"> 
						<input type="text" name="goodsname" value="${goods.goodsname}" 
							class="easyui-textbox theme-textbox-radius" >
					</td>
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

					<td class="text-title">发货数量：</td>
					<td class="text-content">
						<input type="text" name="deliverNum" 
							class="easyui-textbox theme-textbox-radius" >
					</td>
				</tr>
				<tr>
					<td class="text-title">目标订单ID：</td>
					<td class="text-content">
						<input type="text" name="orderid" 
							class="easyui-textbox theme-textbox-radius" >
					</td>
					<td class="text-title">点击发货：</td>
					<td colspan="2" style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0);" id="selectBtn" class="easyui-linkbutton button-primary" onclick="return deliverGoods('deliverGoods.do');">点击发货</a> 
					</td>
				</tr>
				</table>
		</form>
	</section>
</body>
</html>