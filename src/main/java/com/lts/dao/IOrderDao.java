package com.lts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lts.entity.Orders;

public interface IOrderDao {
	
	public List<Orders> findPendingOrderByPage(@Param("start")int start, @Param("pageSize")int pageSize,@Param("toid")int toid);
	
	public int countPendingOrder(@Param("toid")int toid);

	public List<Orders> findMyOrderByPage(@Param("start")int start, @Param("pageSize")int pageSize,@Param("fromid")int fromid);
	
	public int countMyOrder(@Param("fromid")int fromid);
	
	public int addOrder(Orders order);
	
	public Orders findById(@Param("orderid")int orderid);

	public int updateOrder(Orders order);

}	
