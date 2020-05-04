package com.lts.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lts.entity.Functions;
import com.lts.entity.Roles;
import com.lts.service.IRoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class RoleController {
	@Resource
	private IRoleService roleService;

	@RequestMapping("/roleManager.do")
	public String toRoleList() {
		return "listRole";
	}

	@RequestMapping("/findRoleByPage.do")
	public void findByRolePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 分页的初始化参数
		String page = request.getParameter("page");
		String size = request.getParameter("rows");
		if(page == null || "".equals(page)) {
			page = "1";
		}
		if(size == null || "".equals(size)) {
			size = "10";
		}
		//计算当前页的开始位置
		int pageSize = Integer.parseInt(size);
		int start = (Integer.parseInt(page) - 1 ) * pageSize;
		
		JSONObject json = new JSONObject();
		//分页查询数据库
		List<Roles> list = roleService.findByRolePage(start,pageSize);
		if(list !=null && list.size() > 0) {
			int total = roleService.totalRoles();
			JSONArray array = JSONArray.fromObject(list);
			json.put("success", true);
			json.put("msg", "数据查询成功！");
			json.put("total",total);
			json.put("rows",array);
		}else {
			json.put("success", false);
			json.put("msg", "数据查询失败！");
		}
		//把json数据写入到输出流
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	@RequestMapping("/findRoleList.do")
	public void findFunctionList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获取权限表中的所有的数据
		List<Roles> roleList = roleService.findRoleList();
		//把获取的数据封装到json对象中
		JSONObject json = new JSONObject();
		if(roleList != null && roleList.size() > 0) {
			json.put("success",true);
			json.put("msg", "角色数据获取成功！");
			JSONArray array = JSONArray.fromObject(roleList);
			json.put("data",array);
		}else {
			json.put("success",false);
			json.put("msg", "角色数据获取失败！");
		}
		//把数据写入响应流，返回到页面
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}

	@RequestMapping("/toAddRole.do")
	public String toAddRole() {
		return "addRole";
	}
	@RequestMapping("/addRole.do")
	public void addRole(Roles role,HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println(role);
		JSONObject json = new JSONObject();
		if(role!= null) {
			//创建时间和更新时间全部采用当前系统时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime = sdf.format(date); //创建时间
			String updateTime = sdf.format(date); //更新时间
			role.setCreatetime(createTime);
			role.setUpdatetime(updateTime);
			
			//把信息保存到数据库
			int result = roleService.addRole(role);
			
			if(result == 1) {
				json.put("msg","角色添加成功");
				json.put("success",true);
			}else {
				json.put("msg","角色添加失败");
				json.put("success",false);
			}
		}else {
			json.put("msg","角色添加失败");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	@RequestMapping("/deleteRole.do")
	public void delete(String ids,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		if(ids != null && !"".equals(ids)) {
			int reulst = 0;
			String[] id = ids.split(",");
			for(String roleid : id) {
				reulst += roleService.deleteRole(Integer.parseInt(roleid)); 
			}
			if(reulst >= 1) {
				json.put("msg","角色刪除成功");
				json.put("success",true);
			}else {
				json.put("msg","角色刪除失败");
				json.put("success",false);
			}
		}else {
			json.put("msg","角色刪除失败");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
	
	@RequestMapping("/toUpdateRole.do")
	public String toUpdateRole(HttpServletRequest request) {
		String id = request.getParameter("id");
		Roles role = roleService.findById(Integer.parseInt(id));
		request.setAttribute("role",role);
		System.out.println(role);
		return "updateRole";
	}
	
	@RequestMapping("/updateRole.do")
	public void updateFunction(Roles role,HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		//获取用户对象
		if(role != null) {
			//创建时间和更新时间全部采用当前系统时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String updateTime = sdf.format(date); //更新时间
			role.setUpdatetime(updateTime);
			
			//调用修改方法
			int result = roleService.updateRole(role);
			if(result == 1) {
				json.put("msg","角色修改成功");
				json.put("success",true);
			}else {
				json.put("msg","角色修改失败！");
				json.put("success",false);
			}
		}else {
			json.put("msg","获取角色信息失败！");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping("/toAssignFunction.do")
	public String toAssignFunction(String id,HttpServletRequest request) {
		if(id != null && !"".equals(id)) {
			request.setAttribute("roleid",id);
		}
		return "assignFunction";
	}

	@RequestMapping("/findFunctionByRoleId.do")
	public void findFuncByRoleId(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String roleid = request.getParameter("id");
		//根据角色id获取该角色所有的权限
		List<Functions> list = roleService.findFuncByRoleId(Integer.parseInt(roleid));
		JSONObject json = new JSONObject();
		JSONArray array = JSONArray.fromObject(list);
		json.put("data", array);
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping("/saveRoleFunction.do")
	public void saveRoleFunction(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//获取参数
		String roleid = request.getParameter("roleid");
		String ids = request.getParameter("ids");
		//根据roleId删除角色权限表中该角色原有的所有数据
		roleService.deleteByRoleId(Integer.parseInt(roleid));
		int result = 0;
		//把新选择的所有的ids中的权限id保存到角色权限表中
		String[] strs = ids.split(",");
		for(String functionid : strs) {
			result = roleService.saveRoleFunction(Integer.parseInt(roleid),Integer.parseInt(functionid));
			result ++;
		}
		JSONObject json = new JSONObject();
		if(result >= 1) {
			json.put("msg", "授权成功！");
			json.put("success", true);
		}else {
			json.put("msg", "授权失败！");
			json.put("success", false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
}
