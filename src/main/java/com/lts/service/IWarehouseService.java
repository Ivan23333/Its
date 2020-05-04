package com.lts.service;

import java.util.List;

import com.lts.entity.Warehouses;;


public interface IWarehouseService {
	
	public List<Warehouses> findByPage(int start, int pageSize);
	
	public int countWarehouse();

	public Warehouses findByCity(String city);

	public Warehouses findById(int deliverWarehouseid);

}
