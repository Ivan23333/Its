package com.lts.entity;

public class Detail {
	
	private int goodsid;
	private int ownerid;
	private int warehouseid;
	private String intime;
	private String outtime;
	private int nownerid;
	
	public Detail() {
		super();
	}

	public Detail(int goodsid, int ownerid, int warehouseid, String intime, String outtime, int nownerid) {
		super();
		this.goodsid = goodsid;
		this.ownerid = ownerid;
		this.warehouseid = warehouseid;
		this.intime = intime;
		this.outtime = outtime;
		this.nownerid = nownerid;
	}

	public int getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public int getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}

	public int getWarehouseid() {
		return warehouseid;
	}

	public void setWarehouseid(int warehouseid) {
		this.warehouseid = warehouseid;
	}

	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public String getOuttime() {
		return outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	public int getNownerid() {
		return nownerid;
	}

	public void setNownerid(int nownerid) {
		this.nownerid = nownerid;
	}

	@Override
	public String toString() {
		return "Detail [goodsid=" + goodsid + ", ownerid=" + ownerid + ", warehouseid=" + warehouseid + ", intime="
				+ intime + ", outtime=" + outtime + ", nownerid=" + nownerid + "]";
	}

	

}
