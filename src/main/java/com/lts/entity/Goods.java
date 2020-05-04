package com.lts.entity;

public class Goods {
	
	private int goodsid;
	private String RFID;
	private String goodsname;
	private String unit;
	private int price;
	private int num;
	private String origin;
	private String producttime;
	private String updatetime;
	private int status;
	
	
	
	public Goods() {
		super();
	}



	public Goods(int goodsid, String rFID, String goodsname, String unit, int price, int num, String origin,
			String producttime, String updatetime, int status) {
		super();
		this.goodsid = goodsid;
		RFID = rFID;
		this.goodsname = goodsname;
		this.unit = unit;
		this.price = price;
		this.num = num;
		this.origin = origin;
		this.producttime = producttime;
		this.updatetime = updatetime;
		this.status = status;
	}



	public int getGoodsid() {
		return goodsid;
	}



	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}



	public String getRFID() {
		return RFID;
	}



	public void setRFID(String rFID) {
		RFID = rFID;
	}



	public String getGoodsname() {
		return goodsname;
	}



	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}



	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
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



	public String getOrigin() {
		return origin;
	}



	public void setOrigin(String origin) {
		this.origin = origin;
	}



	public String getProducttime() {
		return producttime;
	}



	public void setProducttime(String producttime) {
		this.producttime = producttime;
	}



	public String getUpdatetime() {
		return updatetime;
	}



	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "Goods [goodsid=" + goodsid + ", RFID=" + RFID + ", goodsname=" + goodsname + ", unit=" + unit
				+ ", price=" + price + ", num=" + num + ", origin=" + origin + ", producttime=" + producttime
				+ ", updatetime=" + updatetime + ", status=" + status + "]";
	}
	
	

}
