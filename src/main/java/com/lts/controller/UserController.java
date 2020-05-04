package com.lts.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lts.entity.Roles;
import com.lts.entity.Users;
import com.lts.service.IUserService;

@Controller
public class UserController {

	@Resource
	private IUserService userservice;
	
	@RequestMapping("/findAll.do")
	public String findAll(Model model) {
		List<Users> userList = userservice.findAll();
		model.addAttribute("userList", userList);
		return "userList";
	}
	
	@RequestMapping("/totest.do")
	public String totest() {

		return "test";
	}
	@ResponseBody
	@RequestMapping("/login.do")
	public Map<String,Object> login(String username,String password,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("username:" + username + ", password:" + password);
		Users Users = userservice.login(username,password);
		if(Users != null) {
			HttpSession session = request.getSession();
			session.setAttribute("Users",Users);
			map.put("flag",true);
			map.put("msg", "登陆成功！");
		}else {
			map.put("flag",false);
			map.put("msg","登陆失败！");
		}
		return map;
	}
	
	@RequestMapping("/userManager.do")
	public String userManager() {
		return "listUser";
	}
	
	@RequestMapping("/findByPage.do")
	public void findByPage(String page,String rows,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");

		if(page == null || "".equals(page)) {
			page = "1";
		}		
		
		if(rows == null || "".equals(rows)) {
			rows = "10";
		}
		int pageSize = Integer.parseInt(rows);
		int start = (Integer.parseInt(page) - 1) * pageSize;
		
		List<Users> list = userservice.findByPage(start,pageSize);
		
		int total = userservice.countUser();
		
	
		JSONObject json = new JSONObject();
		json.put("total",total);
		JSONArray array = JSONArray.fromObject(list);
		json.put("rows", array);
		

		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
	}
	@RequestMapping("/toAddUser.do")
	public String addUser() {
		return "addUser";
	}
	
	@RequestMapping("/addUser.do")
	public void addUser(Users Users,HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println(Users);
		JSONObject json = new JSONObject();
		if(Users != null) {
			//创建时间和更新时间全部采用当前系统时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime = sdf.format(date); //创建时间
			String updateTime = sdf.format(date); //更新时间
			Users.setCreatetime(createTime);
			Users.setUpdatetime(updateTime);
			
			//把信息保存到数据库
			int result = userservice.addUser(Users);
			
			if(result == 1) {
				json.put("msg","用户添加成功");
				json.put("success",true);
			}else {
				json.put("msg","用户添加失败");
				json.put("success",false);
			}
		}else {
			json.put("msg","用户添加失败");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping("/delUser.do")
	public void deleteUser(String ids,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		if(ids != null && !"".equals(ids)) {
			int reulst = 0;
			String[] id = ids.split(",");
			for(String userId : id) {
				reulst += userservice.deleteUser(Integer.parseInt(userId)); 
			}
			if(reulst >= 1) {
				json.put("msg","用户刪除成功");
				json.put("success",true);
			}else {
				json.put("msg","用户刪除失败");
				json.put("success",false);
			}
		}else {
			json.put("msg","用户刪除失败");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
	
	@RequestMapping("/toUpdateUser.do")
	public String toUpdateUser(HttpServletRequest request) {
		//获取参数 id
		String id = request.getParameter("id");
		//根据id查询数据库中原有数据的内容，然后返回到页面上
		Users users = userservice.findById(Integer.parseInt(id));
		request.setAttribute("user",users);
		return "updateUser";
	}
	@RequestMapping("/updateUser.do")
	public void updateUser(Users users,HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		//获取用户对象
		if(users != null) {
			//创建时间和更新时间全部采用当前系统时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String updateTime = sdf.format(date); //更新时间
			users.setUpdatetime(updateTime);
			
			//调用修改方法
			int result = userservice.updateUser(users);
			if(result == 1) {
				json.put("msg","用户修改成功");
				json.put("success",true);
			}else {
				json.put("msg","用户修改失败！");
				json.put("success",false);
			}
		}else {
			json.put("msg","获取用户信息失败！");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping("/toAssignRole.do")
	public String toAssignRole(String id,HttpServletRequest request) {
		if(id != null && !"".equals(id)) {
			request.setAttribute("userid",id);
		}
		return "assignRole";
	}
	
	@RequestMapping("/findRoleByUserId.do")
	public void findFuncByRoleId(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		System.out.println("userid:"+userid);
		List<Roles> roleList = userservice.findRoleByUserId(Integer.parseInt(userid));
		JSONObject json = new JSONObject();
		JSONArray array = JSONArray.fromObject(roleList);
		json.put("data", array);
		json.put("success",true);
		System.out.println(roleList);
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	
	@RequestMapping("/AssignRole.do")
	public void AssignRole(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		String ids = request.getParameter("ids");

		userservice.deleteByUserId(Integer.parseInt(userid));
		int result = 0;

		String[] strs = ids.split(",");
		for(String roleid : strs) {
			result = userservice.saveUserRole(Integer.parseInt(userid),Integer.parseInt(roleid));
			result ++;
		}
		JSONObject json = new JSONObject();
		if(result >= 1) {
			json.put("msg", "角色分配成功！");
			json.put("success", true);
		}else {
			json.put("msg", "角色分配失败！");
			json.put("success", false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping("/deliverUserFind")
	public void deliverUserFind(String id,HttpServletResponse response,HttpServletRequest request) throws IOException {
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		if(id != null && !"".equals(id)) {
			Users users = userservice.findById(Integer.parseInt(id));
			int result=0;
			if(users!=null) {
				result=1;
			}
			request.setAttribute("user",users);
			System.out.println(users);
			if(result >= 1) {
				json.put("msg","用户获取成功");
				json.put("success",true);
				json.put("user",users);
			}else {
				json.put("msg","用户获取失败");
				json.put("success",false);
			}
		}else {
			json.put("msg","用户获取失败");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
}