<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>待收货物管理页面</title>
<%@ include file="script.html"%>
<script type="text/javascript" src="static/jquery/datagrid-filter.js"></script>
<script type="text/javascript">
	$(function(){
		$("#goodsList").datagrid({
			url : "findTransportGoodsByPage.do",
			pagination : true,
			toolbar : "#toolbar",
			fitColumns : true,
			idField : "goodsid",
			rownumbers : true,
			//singleSelect:true,
			sortOrder:"asc",
			remoteSort:false,
			columns : [[
				{field:"goodsid",title:"选择",checkbox:true},
				{field:"goodsname",title:"货物名",sortable:true,width:20},
				{field:"unit",title:"单位",sortable:true,width:15},
				{field:"price",title:"价格",sortable:true,width:20},
				{field:"num",title:"数量",sortable:true,width:15},
				{field:"origin",title:"产地",sortable:true,width:15},
				{field:"producttime",title:"生产时间",sortable:true,width:15},
				{field:"status",title:"货物状态",sortable:true,formatter:function(value,rowData,index){
					if(value == 1){
						return "在库中";
					}else if(value == 2){
						return "已发货";
					}else if(value == 3){
						return "已到库";
					}else if(value == 4){
						return "派送中";
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
		$('#goodsList').datagrid('enableFilter');
		$('#goodsList').datagrid('enableFilter', [{
			field:'goodsname',
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
			field:'origin',
			type:'text',
			options:{precision:1},
			op:['equal','notequal']
		},{
			field:'producttime',
			type:'text',
			options:{precision:1},
			op:['equal','notequal','less','greater']
		},{
			field:'status',
			type:'text',
			options:{precision:1},
			op:['equal','notequal']
		}
		]);
	})
	
	
	function del(url){
		//获取选中的记录的条数数组
		var rows = $("#goodsList").datagrid("getChecked");
		
		if(rows.length == 0){
			$.messager.alert("警告","必须选中一条要删除的记录！");
			return;
		}
		if(rows.length >= 1){
			$.messager.confirm("警告","数据删除将无法恢复，确认删除选中的信息吗?",function(b){
				if(b){
					var ids = new Array();
					$.each(rows,function(index,obj){
						ids.push(obj.goodsid);
					});
					//以逗号进行分割
					ids = ids.join(",");
					alert(ids);
					//通过ajax提交删除操作
					$.post(
						url,
						{"ids":ids},
						function(data){
							alert(data.msg);
							//删除成功之后，刷新列表页面
							if(data.success){
								$("#goodsList").datagrid("reload");
								return;
							}
						},
						"json"
					);
				}
			});
		}
	}
	
	function edit(url){ //updateUser.do
		//选中修改的记录
		var rows = $("#goodsList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条记录修改！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条记录修改！");
			return;
		}else{
			var id = rows[0].goodsid;
			//调用父页面的弹出页面的方法
			parent.openTopWindow({
				width:700,
				height:500,
				title:"修改货物",
				url:url+"?id="+id,
				close:function(){
					$("#goodsList").datagrid("reload");
				}
			});
		}
	}
	function confirm(url){
		var rows = $("#goodsList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条订单完成！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条订单完成！");
			return;
		}else{
			var id = rows[0].goodsid;
			$.get(
					url,
					{"id":id	
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
	
	function detail(url){
		//选中修改的记录
		var rows = $("#goodsList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条记录！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条记录！");
			return;
		}else{
			var id = rows[0].goodsid;
			//调用父页面的弹出页面的方法
			parent.openTopWindow({
				width:700,
				height:500,
				title:"货物详情",
				url:url+"?id="+id,
				close:function(){
					$("#goodsList").datagrid("reload");
				},
				isScrolling:"yes"
			});
		}
	}
	function query(){
		var rows = $("#goodsList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条货物查询！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条货物查询！");
			return;
		}else{
			var id = rows[0].goodsid;
			alert("该货物ID是："+id);
		}		
	}
</script>
</head>
<body>
	<div id="toolbar">
		<a href="javascript:void(0);" onclick="return del('deleteGoods.do')" 
			class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a> 
		<a href="javascript:void(0);" onclick="return edit('toUpdateGoods.do');"
			id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a> 
		<a href="javascript:void(0);" onclick="return detail('toGoodsDetail.do')"
			id="setBtn" class="easyui-linkbutton" data-options="iconCls:'icon-more',plain:true">货物详情</a>
		<a href="javascript:void(0);" onclick="return confirm('confirmGoods.do')"
			id="setBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确认收货</a>
		<a href="javascript:void(0);" onclick="return query();"
			id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">查询ID</a> 
		
	</div>
	<table id="goodsList"></table>
</body>
</html>