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

import com.lts.entity.Detail;
import com.lts.entity.Goods;
import com.lts.entity.Logistics;
import com.lts.entity.Orders;
import com.lts.entity.Users;
import com.lts.entity.Warehouses;
import com.lts.service.IGoodService;
import com.lts.service.ILogisticsService;
import com.lts.service.IOrderService;
import com.lts.service.IWarehouseService;

@Controller
public class Ordercontroller {
	
	@Resource
	private IOrderService orderservice;
	
	@Resource 
	private IGoodService goodsservice;
	
	@Resource
	private ILogisticsService logisticsservice;
	
	@Resource
	private IWarehouseService warehouseservice;
	
	@RequestMapping("/pendingOrderManager.do")
	public String toPendingOrderList() {
		return "listPendingOrder";
	}
	
	
	@RequestMapping("/findPendingOrderByPage.do")
	public void findPendingOrderByPage(String page,String rows,HttpServletResponse response,HttpServletRequest request) throws IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Users user = new Users();
		user = (Users)session.getAttribute("Users");

		if(page == null || "".equals(page)) {
			page = "1";
		}		
		
		if(rows == null || "".equals(rows)) {
			rows = "10";
		}
		int pageSize = Integer.parseInt(rows);
		int start = (Integer.parseInt(page) - 1) * pageSize;
		
		List<Orders> list = orderservice.findPendingOrderByPage(start,pageSize,user.getUserid());
		
		
		int total = orderservice.countPendingOrder(user.getUserid());
		
	
		JSONObject json = new JSONObject();
		json.put("total",total);
		JSONArray array = JSONArray.fromObject(list);
		json.put("rows", array);
		

		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
	}
	
	@RequestMapping("/myOrderManager.do")
	public String toMyOrderList() {
		return "listMyOrder";
	}
	
	
	@RequestMapping("/findMyOrderByPage.do")
	public void findMyOrderByPage(String page,String rows,HttpServletResponse response,HttpServletRequest request) throws IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Users user = new Users();
		user = (Users)session.getAttribute("Users");

		if(page == null || "".equals(page)) {
			page = "1";
		}		
		
		if(rows == null || "".equals(rows)) {
			rows = "10";
		}
		int pageSize = Integer.parseInt(rows);
		int start = (Integer.parseInt(page) - 1) * pageSize;
		
		List<Orders> list = orderservice.findMyOrderByPage(start,pageSize,user.getUserid());
		
		
		int total = orderservice.countMyOrder(user.getUserid());
		
	
		JSONObject json = new JSONObject();
		json.put("total",total);
		JSONArray array = JSONArray.fromObject(list);
		json.put("rows", array);
		

		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
	}
	
	@RequestMapping("/toAddOrder.do")
	public String addOrder() {
		return "addOrder";
	}
	
	@RequestMapping("/addOrder.do")
	public void addOrder(Orders order,HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		if(order != null) {
			//创建时间和更新时间全部采用当前系统时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime = sdf.format(date); //创建时间
			String updateTime = sdf.format(date); //更新时间
			order.setCreatetime(createTime);
			order.setUpdatetime(updateTime);
			
			System.out.println(order);
			
			//把信息保存到数据库
			int result = orderservice.addOrder(order);
			
			if(result == 1) {
				json.put("msg","订单添加成功");
				json.put("success",true);
			}else {
				json.put("msg","订单添加失败");
				json.put("success",false);
			}
		}else {
			json.put("msg","订单添加失败");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping("/updateOrder.do")
	public void updateOrder(String id,String status,HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		JSONObject json = new JSONObject();
		Orders order = orderservice.findById(Integer.parseInt(id));
		
		if(order != null) {
			//创建时间和更新时间全部采用当前系统时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String updateTime = sdf.format(date); //更新时间
			order.setUpdatetime(updateTime);
			order.setStatus(Integer.parseInt(status));
			int result = orderservice.updateOrder(order);
			//创建物流链
			if(Integer.parseInt(status)==2) {
				Warehouses warehouse = new Warehouses();
				Logistics logistics = new Logistics();
				warehouse = warehouseservice.findByCity(order.getCity());
				logistics.setOrderid(order.getOrderid());
				logistics.setStatus(1);
				logistics.setWarehouse_receiver_lng(warehouse.getLng());
				logistics.setWarehouse_receiver_lat(warehouse.getLat());
				logistics.setDetail_address(order.getAddress());
				result += logisticsservice.addLogistics(logistics);
				if(result == 2) {
					json.put("msg","操作成功！");
					json.put("success",true);
				}else {
					json.put("msg","操作失败！");
					json.put("success",false);
				}
				
				PrintWriter out = response.getWriter();
				out.print(json.toString());
				out.flush();
				out.close();
				return;
			}
			//调用修改方法
			
			if(result == 1) {
				json.put("msg","操作成功！");
				json.put("success",true);
			}else {
				json.put("msg","操作失败！");
				json.put("success",false);
			}
		}else {
			json.put("msg","操作失败！");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping("/logisticsMyOrder.do")
	public String logisticsMyOrder(HttpServletRequest request) {
		String id = request.getParameter("id");
		Logistics logistics = logisticsservice.findByOrderId(Integer.parseInt(id));
		Goods goods = goodsservice.findById(logistics.getGoodsid());
		Orders order = orderservice.findById(logistics.getOrderid());
		request.setAttribute("order", order);
		request.setAttribute("logistics", logistics);
		request.setAttribute("goods", goods);
		return "Logistics";
	}


}
