<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仓库管理页面</title>
<%@ include file="script.html"%>
<script type="text/javascript" src="static/jquery/datagrid-filter.js"></script>
<script type="text/javascript">
	$(function(){
		$("#warehouseList").datagrid({
			url : "findWarehouseByPage.do",
			pagination : true,
			toolbar : "#toolbar",
			fitColumns : true,
			idField : "warehouseid",
			rownumbers : true,
			//singleSelect:true,
			sortOrder:"asc",
			remoteSort:false,
			columns : [[
				{field:"warehouseid",title:"选择",checkbox:true},
				{field:"warehousename",title:"仓库名",sortable:true,sortable:true,width:20},
				{field:"address",title:"地址",sortable:true,width:15},
				{field:"capacity",title:"容量",sortable:true,width:20},
				{field:"load",title:"载荷",sortable:true,width:15},
				{field:"ownerid",title:"仓库所有人ID",sortable:true,width:15},
				{field:"updatetime",title:"更新时间",sortable:true,width:15}
			]],
			loadFilter:function(data){
				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
				return {"total":data.total,"rows":data.rows};
			}
		});
		
		$('#warehouseList').datagrid('enableFilter');
		$('#warehouseList').datagrid('enableFilter', [{
			field:'warehousename',
			type:'text',
			options:{precision:1},
			op:['equal','notequal']
		},{
			field:'capacity',
			type:'numberbox',
			options:{precision:1},
			op:['equal','notequal','less','greater']
		},{
			field:'load',
			type:'numberbox',
			options:{precision:1},
			op:['equal','notequal','less','greater']
		},{
			field:'createtime',
			type:'text',
			options:{precision:1},
			op:['equal','notequal','less','greater']
		},{
			field:'ownerid',
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
			height:500,
			title:"添加用户",
			url:url,
			close:function(){
				$("#userList").datagrid("reload");
			}
		});
	}
	
	function del(url){
		//获取选中的记录的条数数组
		var rows = $("#userList").datagrid("getChecked");
		
		if(rows.length == 0){
			$.messager.alert("警告","必须选中一条要删除的记录！");
			return;
		}
		if(rows.length >= 1){
			$.messager.confirm("警告","数据删除将无法恢复，确认删除选中的信息吗?",function(b){
				if(b){
					var ids = new Array();
					$.each(rows,function(index,obj){
						ids.push(obj.userid);
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
								$("#userList").datagrid("reload");
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
		var rows = $("#userList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条记录修改！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条记录修改！");
			return;
		}else{
			var id = rows[0].userid;
			//调用父页面的弹出页面的方法
			parent.openTopWindow({
				width:700,
				height:500,
				title:"修改用户",
				url:url+"?id="+id,
				close:function(){
					$("#userList").datagrid("reload");
				}
			});
		}
	}
	
	function assign(url){
		//选中修改的记录
		var rows = $("#userList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条记录！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条记录！");
			return;
		}else{
			var id = rows[0].userid;
			//调用父页面的弹出页面的方法
			parent.openTopWindow({
				width:700,
				height:500,
				title:"分配角色",
				url:url+"?id="+id,
				close:function(){
					$("#userList").datagrid("reload");
				},
				isScrolling:"yes"
			});
		}
	}
</script>
</head>
<body>
	<div id="toolbar">
		<a href="javascript:void(0);" onclick="return add('toAddUser.do')" class="easyui-linkbutton" 
			data-options="iconCls:'icon-add',plain:true">添加</a> 
		<a href="javascript:void(0);" onclick="return del('delUser.do')" 
			class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a> 
		<a href="javascript:void(0);" onclick="return edit('toUpdateUser.do');"
			id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a> 
		<a href="javascript:void(0);" onclick="return assign('toAssignRole.do')"
			id="setBtn" class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true">分配角色</a>
	</div>
	<table id="warehouseList"></table>
</body>
</html>