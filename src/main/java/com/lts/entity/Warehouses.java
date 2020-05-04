package com.lts.entity;

public class Warehouses {
	
	private int warehouseid;
	private String city;
	private String warehousename;
	private String address;
	private int ownerid;
	private int capacity;
	private int load;
	private String createtime;
	private String updatetime;
	private String lng;
	private String lat;
	
	public Warehouses() {
		super();
	}

	public Warehouses(int warehouseid, String city, String warehousename, String address, int ownerid, int capacity,
			int load, String createtime, String updatetime, String lng, String lat) {
		super();
		this.warehouseid = warehouseid;
		this.city = city;
		this.warehousename = warehousename;
		this.address = address;
		this.ownerid = ownerid;
		this.capacity = capacity;
		this.load = load;
		this.createtime = createtime;
		this.updatetime = updatetime;
		this.lng = lng;
		this.lat = lat;
	}

	public int getWarehouseid() {
		return warehouseid;
	}

	public void setWarehouseid(int warehouseid) {
		this.warehouseid = warehouseid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getLoad() {
		return load;
	}

	public void setLoad(int load) {
		this.load = load;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "Warehouses [warehouseid=" + warehouseid + ", city=" + city + ", warehousename=" + warehousename
				+ ", address=" + address + ", ownerid=" + ownerid + ", capacity=" + capacity + ", load=" + load
				+ ", createtime=" + createtime + ", updatetime=" + updatetime + ", lng=" + lng + ", lat=" + lat + "]";
	}
	
	
}
