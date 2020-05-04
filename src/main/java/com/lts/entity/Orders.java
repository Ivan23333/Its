package com.lts.entity;

public class Orders {
	
	private int orderid;
	private String goods;
	private int price;
	private int num;
	private String unit;
	private String city;
	private String address;
	private int fromid;
	private int toid;
	private int status;
	private String createtime;
	private String updatetime;
	
	public Orders() {
		super();
	}

	public Orders(int orderid, String goods, int price, int num, String unit, String city, String address, int fromid,
			int toid, int status, String createtime, String updatetime) {
		super();
		this.orderid = orderid;
		this.goods = goods;
		this.price = price;
		this.num = num;
		this.unit = unit;
		this.city = city;
		this.address = address;
		this.fromid = fromid;
		this.toid = toid;
		this.status = status;
		this.createtime = createtime;
		this.updatetime = updatetime;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getFromid() {
		return fromid;
	}

	public void setFromid(int fromid) {
		this.fromid = fromid;
	}

	public int getToid() {
		return toid;
	}

	public void setToid(int toid) {
		this.toid = toid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "Orders [orderid=" + orderid + ", goods=" + goods + ", price=" + price + ", num=" + num + ", unit="
				+ unit + ", city=" + city + ", address=" + address + ", fromid=" + fromid + ", toid=" + toid
				+ ", status=" + status + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}

	
	
}
