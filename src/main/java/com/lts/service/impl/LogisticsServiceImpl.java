package com.lts.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lts.dao.ILogisticsDao;
import com.lts.entity.Logistics;
import com.lts.service.ILogisticsService;

@Service("logisticsService")
public class LogisticsServiceImpl implements ILogisticsService {
	
	@Resource
	private ILogisticsDao logisticsDao;

	@Override
	public int addLogistics(Logistics logistics) {

		return logisticsDao.addLogistics(logistics);
	}

	@Override
	public Logistics findByOrderId(int orderid) {
		return logisticsDao.findByOrderId(orderid);
	}

	@Override
	public int updateLogistics(Logistics logistics) {
		return logisticsDao.updateLogistics(logistics);
	}
	

}
