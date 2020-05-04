package com.lts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lts.entity.Warehouses;

public interface IWarehouseDao {
	
	public List<Warehouses> findByPage(@Param("start")int start, @Param("pageSize")int pageSize);
	
	public int countWarehouse();

	public Warehouses findByCity(@Param("city")String city);

	public Warehouses findById(@Param("deliverWarehouseid")int deliverWarehouseid);

}	
