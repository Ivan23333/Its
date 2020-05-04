<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理页面</title>
<%@ include file="script.html"%>
<script type="text/javascript" src="static/jquery/datagrid-filter.js"></script>
<script type="text/javascript">
	$(function(){
		$("#roleList").datagrid({
			url : "findRoleByPage.do",
			pagination : true,
			toolbar : "#toolbar",
			fitColumns : true,
			idField : "roleid",
			rownumbers : true,
			//singleSelect:true,
			sortOrder:"asc",
			remoteSort:false,
			columns : [[
				{field:"roleid",title:"选择",checkbox:true},
				{field:"rolename",title:"角色名",sortable:true,width:20},
				{field:"note",title:"备注信息",sortable:true,width:15},
				{field:"createtime",title:"创建时间",sortable:true,width:15},
				{field:"updatetime",title:"修改时间",sortable:true,width:15},
				{field:"status",title:"角色状态",sortable:true,formatter:function(value,rowData,index){
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
				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
				return {"total":data.total,"rows":data.rows};
			}
		});
		
		$('#roleList').datagrid('enableFilter');
		$('#roleList').datagrid('enableFilter', [{
			field:'rolename',
			type:'text',
			options:{precision:1},
			op:['equal','notequal']
		},{
			field:'createtime',
			type:'text',
			options:{precision:1},
			op:['equal','notequal','less','greater']
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
				$("#roleList").datagrid("reload");
			}
		});
	}
	
	function del(url){
		//获取选中的记录的条数数组
		var rows = $("#roleList").datagrid("getChecked");
		
		if(rows.length == 0){
			$.messager.alert("警告","必须选中一条要删除的记录！");
			return;
		}
		if(rows.length >= 1){
			$.messager.confirm("警告","数据删除将无法恢复，确认删除选中的信息吗?",function(b){
				if(b){
					var ids = new Array();
					$.each(rows,function(index,obj){
						ids.push(obj.roleid);
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
								$("#roleList").datagrid("reload");
								return;
							}
						},
						"json"
					);
				}
			});
		}
	}
	
	function toAssignFunction(url){
		var rows = $("#roleList").datagrid("getChecked");
		if(rows.length != 1){
			$.messager.alert("警告","必须选中一个角色！");
			return;
		}else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条记录！");
			return;
		}else{
			var roleid = rows[0].roleid;
			//调用父页面的弹出页面的方法
			parent.openTopWindow({
				width:700,
				height:500,
				title:"分配角色",
				url:url+"?id="+roleid,
				close:function(){
					$("#roleList").datagrid("reload");
				},
				isScrolling:"yes"
			});	
		}	
	}
	
	function edit(url){
		var rows = $("#roleList").datagrid("getChecked");
		if(rows.length == 0){
			$.messager.alert("警告","必须选中一条要修改的记录！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条记录修改！");
			return;
		}else{
			var id = rows[0].roleid;
			parent.openTopWindow({
				width:700,
				height:500,
				title:"修改权限",
				url:url+"?id="+id,
				close:function(){
					$("#roleList").datagrid("reload");
				}
			});
		}
	}
</script>
</head>
<body>
	<div id="toolbar">
		<a href="javascript:void(0);" onclick="return add('toAddRole.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">添加</a> 
		<a href="javascript:void(0);" onclick="return del('deleteRole.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a> 
		<a href="javascript:void(0);" onclick="return edit('toUpdateRole.do');"
			id="editBtn" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a> 
		<a href="javascript:void(0);" onclick="return toAssignFunction('toAssignFunction.do')"
			id="setBtn" class="easyui-linkbutton"
			data-options="iconCls:'icon-lock',plain:true">授权</a>
	</div>
	<table id="roleList"></table>
</body>
</html>