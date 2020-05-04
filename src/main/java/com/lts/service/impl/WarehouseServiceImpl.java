package com.lts.service.impl;

import java.util.List;

import com.lts.dao.IWarehouseDao;
import com.lts.entity.Warehouses;
import com.lts.service.IWarehouseService;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("warehouseService")
public class WarehouseServiceImpl implements IWarehouseService {
	
	@Resource
	private IWarehouseDao warehouseDao;

	@Override
	public List<Warehouses> findByPage(int start, int pageSize) {
		return warehouseDao.findByPage(start,pageSize);
	}

	@Override
	public int countWarehouse() {
		return warehouseDao.countWarehouse();
	}

	@Override
	public Warehouses findByCity(String city) {
		return warehouseDao.findByCity(city);
	}

	@Override
	public Warehouses findById(int deliverWarehouseid) {
		return warehouseDao.findById(deliverWarehouseid);
	}

}
