package com.lts.service;

import com.lts.entity.Logistics;

public interface ILogisticsService {

	int addLogistics(Logistics logistics);

	Logistics findByOrderId(int orderid);

	int updateLogistics(Logistics logistics);
	

}
