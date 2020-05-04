package com.lts.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lts.entity.Functions;
import com.lts.entity.MenuVo;
import com.lts.entity.Users;
import com.lts.service.IFunctionService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class FunctionController {

	@Resource
	private IFunctionService functionservice;
	
	
	@RequestMapping("/toIndex.do")
	public String toIndex() {
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/index.do")
	public List<MenuVo> findMenu(HttpServletRequest request){
		List<MenuVo> list = null;
		//查询当前用户能访问的菜单 1.获取当前用户id，根据用户id获取该用户所属的角色
		//2.根据角色id查询该角色所具有的权限?
		
		//获取session
		HttpSession session = request.getSession();
		Object object = session.getAttribute("Users");
		if(object != null) {
			Users Users = (Users)object;
			//获取当前用户的id
			int userid = Users.getUserid();
			System.out.println(userid);
			//根据用户id查询数据库，获取菜单列表
			list = functionservice.findMenu(userid);
			System.out.println(list);
		}
		else {
			System.out.println("null");
		}
		return list;
	}
	/**
	 * 用于页面跳转
	 * @return
	 */
	@RequestMapping("/functionManager.do")
	public String toFunctionList() {
		return "listFunction";
	}
	
	/**
	 * 用于获取数据，�?�过ajax的回调函数返回的页面，返回的数据格式是：json
	 * @throws Exception 
	 */
	@RequestMapping("/findFunctionList.do")
	public void findFunctionList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//设置字符�?
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获取权限表中的所有的数据
		List<Functions> functionList = functionservice.findFunctionList();
		//		System.out.println(functionList);
		//把获取的数据封装到json对象�?
		JSONObject json = new JSONObject();
		if(functionList != null && functionList.size() > 0) {
			json.put("success",true);
			json.put("msg", "权限数据获取成功!");
			JSONArray array = JSONArray.fromObject(functionList);
			json.put("data",array);
		}else {
			json.put("success",false);
			json.put("msg", "权限数据获取失败!");
		}
		//把数据写入响应流，返回到页面
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping("/toAddFunction.do")
	public String toAddFunction(HttpServletRequest request) {
		String parentid = request.getParameter("parentid");
		request.setAttribute("parentid", parentid);
		
		return "addFunction";
	}
	
	@RequestMapping("/addFunction.do")
	public void saveFunction(Functions function,HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println(function);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//把获取到的数据提交保存到数据库
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = sdf.format(new Date());
		function.setCreatetime(createTime);
		function.setUpdatetime(createTime);
		int result = functionservice.addFunction(function);
		JSONObject json = new JSONObject();
		if(result == 1) {
			json.put("success",true);
			json.put("msg", "权限添加成功！");
		}else {
			json.put("success",false);
			json.put("msg", "权限添加失败！");
		}
		//把数据写入到响应流中，返回到页面
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping("/deleteFunction.do")
	public void deleteFunction(String ids,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		if(ids != null && !"".equals(ids)) {
			String[] id = ids.split(",");
			//把要删除的id数组传入到数据库进行批量删除
			int result = functionservice.deleteFunction(id);
			if(result >= 1) {
				json.put("success",true);
				json.put("msg","删除成功！");
			}else {
				json.put("success",false);
				json.put("msg","删除失败！");
			}
		}else {
			json.put("success",false);
			json.put("msg","获取id失败！");
		}
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping("/toUpdateFunction.do")
	public String toUpdateFunction(HttpServletRequest request) {
		String id = request.getParameter("id");
		//根据id查询数据库中原有数据的内容，然后返回到页面上
		Functions function = functionservice.findById(Integer.parseInt(id));
		request.setAttribute("function",function);
		return "updateFunction";
	}
	
	@RequestMapping("/updateFunction.do")
	public void updateFunction(Functions function,HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		//获取用户对象
		if(function != null) {
			//创建时间和更新时间全部采用当前系统时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String updateTime = sdf.format(date); //更新时间
			function.setUpdatetime(updateTime);
			
			//调用修改方法
			int result = functionservice.updateFunction(function);
			if(result == 1) {
				json.put("msg","权限修改成功");
				json.put("success",true);
			}else {
				json.put("msg","权限修改失败！");
				json.put("success",false);
			}
		}else {
			json.put("msg","获取权限信息失败！");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
}
