package com.lts.service;

import java.util.List;

import com.lts.entity.Orders;

public interface IOrderService {
	
	public List<Orders> findPendingOrderByPage(int start, int pageSize,int toid);
	
	public int countPendingOrder(int toid);
	
	public List<Orders> findMyOrderByPage(int start, int pageSize,int fromid);
	
	public int countMyOrder(int fromid);
	
	public int addOrder(Orders order);
	
	public Orders findById(int orderid);
	
	public int updateOrder(Orders order);

}
