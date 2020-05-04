package com.lts.service.impl;

import java.util.List;

import com.lts.dao.IOrderDao;
import com.lts.entity.Orders;
import com.lts.service.IOrderService;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {
	
	@Resource
	private IOrderDao orderDao;

	@Override
	public List<Orders> findPendingOrderByPage(int start, int pageSize,int toid) {
		return orderDao.findPendingOrderByPage(start,pageSize,toid);
	}

	@Override
	public int countPendingOrder(int toid) {
		return orderDao.countPendingOrder(toid);
	}
	
	@Override
	public List<Orders> findMyOrderByPage(int start, int pageSize,int fromid) {
		return orderDao.findMyOrderByPage(start,pageSize,fromid);
	}

	@Override
	public int countMyOrder(int fromid) {
		return orderDao.countMyOrder(fromid);
	}
	
	@Override
	public int addOrder(Orders order) {
		return orderDao.addOrder(order);
		
	}
	
	@Override
	public Orders findById(int orderid) {
		return orderDao.findById(orderid);
	}

	@Override
	public int updateOrder(Orders order) {
		return orderDao.updateOrder(order);
	}


}
