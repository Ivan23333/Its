<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理页面</title>
<%@ include file="script.html"%>
<script type="text/javascript">
	$(function(){
		$("#dataList").treegrid({
			url : "findFunctionList.do",
			fitColumns : true,
			idField : "functionid",
			treeField: "functionname",
			rownumbers : true,
			toolbar : "#toolbar",
			checkbox : true,
			singleSelect : false,
			cascadeCheck : false,
			columns : [[
				{field:"functionname",title:"权限名称",sortable:true,width:10},
				{field:"type",title:"权限类型",width:5,formatter:function(value,rowData,index){
					if(value == 1){
						return "菜单";
					}else if(value == 0){
						return "按钮";
					}else{
						return "其他";
					}
				}},
				{field:"functionurl",title:"权限URL",width:10},
				{field:"createtime",title:"创建时间",width:10},
				{field:"updatetime",title:"更新时间",width:10},
				{field:"status",title:"权限状态",formatter:function(value,rowData,index){
					if(value == 1){
						return "可用";
					}else if(value == 0){
						return "禁用";
					}else if(value == 2){
						return "已删除";
					}else{
						return "";
					}
				}}
			]],
			loadFilter:function(data){
				if(data && data.data){
					$.each(data.data ,function(index,obj){
						if(obj.parentid){
							data.data[index]._parentId = obj.parentid; 
						}
					});
					return {"rows":data.data};
				}
				return null;
			}
		});
	});
	//添加权限菜单
	function add(url){
		//获取父id在选中的父id下面添加子菜单
		var rows = $("#dataList").treegrid("getCheckedNodes");
		if(rows.length != 1){
			$.messager.alert("警告","请选择一个父节点！");
			return;
		}
		var parentid = rows[0].functionid; //获取父节点的id的值
		//调用父页面的弹出页面的方法
		parent.openTopWindow({
			width:700,
			height:500,
			title:"添加权限",
			url:url + "?parentid="+ parentid,
			close:function(){
				$("#dataList").treegrid("reload");
			}
		});
	}
	function del(url){
		//获取父id在选中的父id下面添加子菜单
		var rows = $("#dataList").treegrid("getCheckedNodes");
		if(rows.length <= 0){
			$.messager.alert("警告","请至少选择一个节点！");
			return;
		}
		if(confirm("确认要删除选中的记录吗？")){
			//跟节点原则上时不能删除的，删除父节点的原则是，先删除子节点再删除父节点
			var ids = new Array();
			$.each(rows,function(index,obj){
				ids.push(obj.functionid);
			});
			ids = ids.join(",");
			//删除功能
			$.get(
				url,
				{"ids":ids},
				function(data){
					alert(data.msg);
					if(data.success){
						$("#dataList").treegrid("reload");
					}
				},
				"json"
			);
		}
	}
	
	function edit(url){
		var rows = $("#dataList").treegrid("getCheckedNodes");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条记录修改！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条记录修改！");
			return;
		}else{
			var id = rows[0].functionid;
			//调用父页面的弹出页面的方法
			parent.openTopWindow({
				width:700,
				height:500,
				title:"修改权限",
				url:url+"?id="+id,
				close:function(){
					$("#dataList").treegrid("reload");
				}
			});
		}
	}
</script>
</head>
<body>
	<div id="toolbar">
		<a href="javascript:void(0);" onclick="return add('toAddFunction.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">添加</a> 
		<a href="javascript:void(0);" onclick="return del('deleteFunction.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a> 
		<a href="javascript:void(0);" onclick="return edit('toUpdateFunction.do');"
			id="editBtn" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<table id="dataList"></table>
</body>
</html>