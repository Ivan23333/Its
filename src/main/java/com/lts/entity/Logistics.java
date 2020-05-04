package com.lts.entity;

public class Logistics {
	
	private int logisticsid;
	private int orderid;
	private String warehouse_receiver_lng;
	private String warehouse_receiver_lat;
	private String detail_address;
	private int status;
	private int goodsid;
	private String warehouse_deliver_lng;
	private String warehouse_deliver_lat;

	public Logistics() {
		super();
	}

	public Logistics(int logisticsid, int orderid, String warehouse_receiver_lng, String warehouse_receiver_lat,
			String detail_address, int status, int goodsid, String warehouse_deliver_lng,
			String warehouse_deliver_lat) {
		super();
		this.logisticsid = logisticsid;
		this.orderid = orderid;
		this.warehouse_receiver_lng = warehouse_receiver_lng;
		this.warehouse_receiver_lat = warehouse_receiver_lat;
		this.detail_address = detail_address;
		this.status = status;
		this.goodsid = goodsid;
		this.warehouse_deliver_lng = warehouse_deliver_lng;
		this.warehouse_deliver_lat = warehouse_deliver_lat;
	}

	public int getLogisticsid() {
		return logisticsid;
	}

	public void setLogisticsid(int logisticsid) {
		this.logisticsid = logisticsid;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getWarehouse_receiver_lng() {
		return warehouse_receiver_lng;
	}

	public void setWarehouse_receiver_lng(String warehouse_receiver_lng) {
		this.warehouse_receiver_lng = warehouse_receiver_lng;
	}

	public String getWarehouse_receiver_lat() {
		return warehouse_receiver_lat;
	}

	public void setWarehouse_receiver_lat(String warehouse_receiver_lat) {
		this.warehouse_receiver_lat = warehouse_receiver_lat;
	}

	public String getDetail_address() {
		return detail_address;
	}

	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public String getWarehouse_deliver_lng() {
		return warehouse_deliver_lng;
	}

	public void setWarehouse_deliver_lng(String warehouse_deliver_lng) {
		this.warehouse_deliver_lng = warehouse_deliver_lng;
	}

	public String getWarehouse_deliver_lat() {
		return warehouse_deliver_lat;
	}

	public void setWarehouse_deliver_lat(String warehouse_deliver_lat) {
		this.warehouse_deliver_lat = warehouse_deliver_lat;
	}

	@Override
	public String toString() {
		return "Logistics [logisticsid=" + logisticsid + ", orderid=" + orderid + ", warehouse_receiver_lng="
				+ warehouse_receiver_lng + ", warehouse_receiver_lat=" + warehouse_receiver_lat + ", detail_address="
				+ detail_address + ", status=" + status + ", goodsid=" + goodsid + ", warehouse_deliver_lng="
				+ warehouse_deliver_lng + ", warehouse_deliver_lat=" + warehouse_deliver_lat + "]";
	}

	

	
}
