package com.lts.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.StandardToStringStyle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lts.entity.Goods;
import com.lts.entity.Logistics;
import com.lts.entity.Users;
import com.lts.entity.Warehouses;
import com.lts.entity.Detail;
import com.lts.service.IGoodService;
import com.lts.service.ILogisticsService;
import com.lts.service.IWarehouseService;

@Controller
public class Goodcontroller {
	
	@Resource
	private IGoodService goodservice;
	
	@Resource
	private ILogisticsService LogisticsService;
	
	@Resource
	private IWarehouseService warehouseService;
	
	@RequestMapping("/myGoodsManager.do")
	public String tomyGoodsList() {
		return "listMyGoods";
	}
	
	
	
	@RequestMapping("/findMyGoodsByPage.do")
	public void findMyGoodsByPage(String page,String rows,HttpServletResponse response,HttpServletRequest request) throws IOException {
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
		
		List<Goods> list = goodservice.findMyGoodsByPage(start,pageSize,user.getUserid());
		
		
		int total = goodservice.countMyGoods(user.getUserid());
		
	
		JSONObject json = new JSONObject();
		json.put("total",total);
		JSONArray array = JSONArray.fromObject(list);
		json.put("rows", array);
		

		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
	}	
	
	@RequestMapping("/transportGoodsManager.do")
	public String totransportGoodsList() {
		return "listTransportGoods";
	}
	
	
	
	@RequestMapping("/findTransportGoodsByPage.do")
	public void findTransportGoodsByPage(String page,String rows,HttpServletResponse response,HttpServletRequest request) throws IOException {
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
		
		List<Goods> list = goodservice.findTransportGoodsByPage(start,pageSize,user.getUserid());
		
		
		int total = goodservice.countTransportGoods(user.getUserid());
		
	
		JSONObject json = new JSONObject();
		json.put("total",total);
		JSONArray array = JSONArray.fromObject(list);
		json.put("rows", array);
		

		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
	}	
	
	@RequestMapping("/deleteGoods.do")
	public void delete(String ids,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		if(ids != null && !"".equals(ids)) {
			int reulst = 0;
			String[] id = ids.split(",");
			for(String goodsid : id) {
				reulst += goodservice.deleteGoods(Integer.parseInt(goodsid)); 
			}
			if(reulst >= 1) {
				json.put("msg","货物刪除成功");
				json.put("success",true);
			}else {
				json.put("msg","货物刪除失败");
				json.put("success",false);
			}
		}else {
			json.put("msg","货物刪除失败");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
	
	@RequestMapping("/toUpdateGoods.do")
	public String toUpdateGoods(HttpServletRequest request) {
		String id = request.getParameter("id");
		Goods goods = goodservice.findById(Integer.parseInt(id));
		request.setAttribute("goods",goods);
		System.out.println(goods);
		return "updateGoods";
	}
	
	@RequestMapping("/updateGoods.do")
	public void updateFunction(Goods goods,HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		//获取用户对象
		if(goods != null) {
			//创建时间和更新时间全部采用当前系统时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String updateTime = sdf.format(date); //更新时间
			goods.setUpdatetime(updateTime);
			
			//调用修改方法
			int result = goodservice.updateGoods(goods);
			if(result == 1) {
				json.put("msg","货物修改成功");
				json.put("success",true);
			}else {
				json.put("msg","货物修改失败！");
				json.put("success",false);
			}
		}else {
			json.put("msg","获取货物信息失败！");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	
	@RequestMapping("/toGoodsDetail.do")
	public String toGoodsDetail(HttpServletRequest request) {
		String id = request.getParameter("id");
		Goods goods = goodservice.findById(Integer.parseInt(id));
		Detail detail = goodservice.findDetail(Integer.parseInt(id));
		request.setAttribute("goods",goods);
		request.setAttribute("detail", detail);
		return "detailOfGoods";
	}
	

	@RequestMapping("/goodsDeliver.do")
	public String toDeliverGoods() {
		return "deliverGoods";
	}
	@RequestMapping("/deliverGoodsFind")
	public void deliverGoodsFind(String id,HttpServletResponse response,HttpServletRequest request) throws IOException {
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		if(id != null && !"".equals(id)) {
			Goods goods = goodservice.findById(Integer.parseInt(id));
			int result=0;
			if(goods!=null) {
				result=1;
			}
			if(result >= 1) {
				json.put("msg","货物信息获取成功");
				json.put("success",true);
				json.put("goods",goods);
			}else {
				json.put("msg","货物信息获取失败");
				json.put("success",false);
			}
		}else {
			json.put("msg","货物信息获取失败");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
	@RequestMapping("/deliverGoods")
	public void deliverGoods(String userid,String goodsid,String num, String deliverNum, String orderid,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		if(goodsid != null && !"".equals(goodsid)) {
			//更新旧货物状态
			Goods goods = goodservice.findById(Integer.parseInt(goodsid));
			Detail detail = goodservice.findDetail(Integer.parseInt(goodsid));
			goods.setNum(Integer.parseInt(num)-Integer.parseInt(deliverNum));
			int result = goodservice.updateGoods(goods);
			int deliverWarehouseid = detail.getWarehouseid();
			
			//添加新货物
			goods.setNum(Integer.parseInt(deliverNum));
			goods.setStatus(2);
			result+=goodservice.addGoods(goods);
			int newGoodsid = goods.getGoodsid();
			detail.setGoodsid(newGoodsid);
			detail.setWarehouseid(0);
			detail.setNownerid(Integer.parseInt(userid));
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String updateTime = sdf.format(date); 
			detail.setOuttime(updateTime);
			result+=goodservice.addDetail(detail);
			
			//完善物流链
			Warehouses warehouse = warehouseService.findById(deliverWarehouseid);
			Logistics logistics = LogisticsService.findByOrderId(Integer.parseInt(orderid));
			logistics.setGoodsid(goods.getGoodsid()); 
			logistics.setWarehouse_deliver_lng(warehouse.getLng());
			logistics.setWarehouse_deliver_lat(warehouse.getLat());
			result+=LogisticsService.updateLogistics(logistics);

			

			if(result == 4) {
				json.put("msg","发货成功");
				json.put("success",true);
			}else {
				json.put("msg","发货失败");
				json.put("success",false);
			}
		}else {
			json.put("msg","发货失败");
			json.put("success",false);
		}
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
	
	@RequestMapping("/confirmGoods.do")
	public void confirmGoods(String id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		JSONObject json = new JSONObject();
		Goods goods = goodservice.findById(Integer.parseInt(id));
		Detail detail = goodservice.findDetail(Integer.parseInt(id));
		
		
		
		
		if(goods != null) {
			//创建时间和更新时间全部采用当前系统时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String updateTime = sdf.format(date); //更新时间
			goods.setUpdatetime(updateTime);
			detail.setIntime(updateTime);
			goods.setStatus(5);
			detail.setOwnerid(detail.getNownerid());
			detail.setNownerid(0);
			
			//调用修改方法
			int result = goodservice.updateGoods(goods);
			result+=goodservice.updateDetail(detail);
			if(result == 2) {
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
	
	


}
