package com.lts.dao;

import org.apache.ibatis.annotations.Param;

import com.lts.entity.Logistics;

public interface ILogisticsDao {

	int addLogistics(Logistics logistics);

	Logistics findByOrderId( @Param("orderid")int orderid);

	int updateLogistics(Logistics logistics);
	

}
