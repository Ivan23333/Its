<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单管理页面</title>
<%@ include file="script.html"%>
<script type="text/javascript" src="static/jquery/datagrid-filter.js"></script>
<script type="text/javascript">
	$(function(){
		$("#orderList").datagrid({
			url : "findMyOrderByPage.do",
			pagination : true,
			toolbar : "#toolbar",
			fitColumns : true,
			idField : "orderid",
			rownumbers : true,
			//singleSelect:true,
			sortOrder:"asc",
			remoteSort:false,
			columns : [[
				{field:"orderid",title:"选择",checkbox:true},
				{field:"goods",title:"货物名",sortable:true,width:20},
				{field:"unit",title:"单位",sortable:true,width:15},
				{field:"price",title:"价格",sortable:true,width:20},
				{field:"num",title:"数量",sortable:true,width:15},
				{field:"address",title:"目的地",sortable:true,width:15},
				{field:"createtime",title:"创建时间",sortable:true,width:15},
				{field:"status",title:"订单状态",sortable:true,formatter:function(value,rowData,index){
					if(value == 1){
						return "待处理";
					}else if(value == 2){
						return "处理中";
					}else if(value == 3){
						return "已完成";
					}else if(value == 4){
						return "已取消";
					}else if(value == 5){
						return "已拒绝";
					}else{
						return "";
					}
				}}
			]],
			loadFilter:function(data){
				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
				return {"total":data.total,"rows":data.rows};
			}
		});
		
		$('#orderList').datagrid('enableFilter');
		$('#orderList').datagrid('enableFilter', [{
			field:'ordername',
			type:'text',
			options:{precision:1},
			op:['equal','notequal']
		},{
			field:'price',
			type:'numberbox',
			options:{precision:1},
			op:['equal','notequal','less','greater']
		},{
			field:'num',
			type:'numberbox',
			options:{precision:1},
			op:['equal','notequal','less','greater']
		},{
			field:'goods',
			type:'text',
			options:{precision:1},
			op:['equal','notequal']
		},{
			field:'createtime',
			type:'text',
			options:{precision:1},
			op:['equal','notequal','less','greater']
		},{
			field:'address',
			type:'numberbox',
			options:{precision:1},
			op:['equal','notequal']
		}
		]);
	})
	
	function add(url){
		//调用父页面的弹出页面的方法
		parent.openTopWindow({
			width:700,
			height:600,
			title:"下订单",
			url:url,
			close:function(){
				$("#orderList").datagrid("reload");
			}
		});
	}
	
	
	function cancel(url){ 
		
		var rows = $("#orderList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条订单取消！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条订单取消！");
			return;
		}else{
			var id = rows[0].orderid;
			//调用父页面的弹出页面的方法
			parent.openTopWindow({
				width:700,
				height:500,
				title:"取消订单",
				url:url+"?id="+id+"&status="+"4",
				close:function(){
					$("#orderList").datagrid("reload");
				}
			});
		}
	}
	
	function finish(url){
		var rows = $("#orderList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条订单完成！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条订单完成！");
			return;
		}else{
			var id = rows[0].orderid;
			$.get(
					url,
					{"id":id,
					 "status":"3"	
					},
					function(data){
						
						if(data.success){
							alert(data.msg);
						}
					},
					"json"
				);
		}		
	}
	function cancel(url){
		var rows = $("#orderList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条订单取消！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条订单取消！");
			return;
		}else{
			var id = rows[0].orderid;
			$.get(
					url,
					{"id":id,
					 "status":"4"	
					},
					function(data){
						
						if(data.success){
							alert(data.msg);
						}
					},
					"json"
				);
		}		
	}
	function logistics(url){
		var rows = $("#orderList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条订单查询！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条订单查询！");
			return;
		}else{
			var id = rows[0].orderid;
			parent.openTopWindow({
				width:700,
				height:500,
				title:"货物追踪",
				url:url+"?id="+id,
				isScrolling:"yes"
			});
		}		
	}
	function query(){
		var rows = $("#orderList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条订单查询！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条订单查询！");
			return;
		}else{
			var id = rows[0].orderid;
			alert("该订单ID是："+id);
		}		
	}
</script>
</head>
<body>
	<div id="toolbar">
		<a href="javascript:void(0);" onclick="return add('toAddOrder.do')" class="easyui-linkbutton" 
			data-options="iconCls:'icon-add',plain:true">下订单</a> 
		<a href="javascript:void(0);" onclick="return finish('updateOrder.do')" 
			class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">完成订单</a> 
		<a href="javascript:void(0);" onclick="return cancel('updateOrder.do');"
			id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true">取消订单</a> 
		<a href="javascript:void(0);" onclick="return logistics('logisticsMyOrder.do');"
			id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">物流追踪</a> 
		<a href="javascript:void(0);" onclick="return query();"
			id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">查询ID</a> 
		
		</div>
	<table id="orderList"></table>
</body>
</html>