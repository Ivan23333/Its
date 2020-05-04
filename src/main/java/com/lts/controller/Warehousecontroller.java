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

import com.lts.entity.Warehouses;
import com.lts.service.IWarehouseService;

@Controller
public class Warehousecontroller {
	
	@Resource
	private IWarehouseService warehouseservice;
	
	@RequestMapping("/warehouseManager.do")
	public String toWarehouseList() {
		return "listWarehouse";
	}
	
	@RequestMapping("/findWarehouseByPage.do")
	public void findWarehouseByPage(String page,String rows,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		if(page == null || "".equals(page)) {
			page = "1";
		}		
		
		if(rows == null || "".equals(rows)) {
			rows = "10";
		}
		int pageSize = Integer.parseInt(rows);
		int start = (Integer.parseInt(page) - 1) * pageSize;
		
		List<Warehouses> list = warehouseservice.findByPage(start, pageSize);
		
		int total = warehouseservice.countWarehouse();
		
		JSONObject json = new JSONObject();
		json.put("total",total);
		JSONArray array = JSONArray.fromObject(list);
		json.put("rows", array);
		

		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
	}

}
