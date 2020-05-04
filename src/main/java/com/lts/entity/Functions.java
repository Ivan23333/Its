package com.lts.entity;

public class Functions {
	
	private Integer functionid;
	private String functionname;
	private String functionurl;
	private String functioncode;
	private Integer parentid;
	private Integer type;
	private Integer status;
	private Integer sortnum;
	private String createtime;
	private String updatetime;
	
	public Functions() {
		super();
	}

	public Functions(Integer functionid, String functionname, String functionurl, String functioncode, Integer parentid,
			Integer type, Integer status, Integer sortnum, String createtime, String updatetime) {
		super();
		this.functionid = functionid;
		this.functionname = functionname;
		this.functionurl = functionurl;
		this.functioncode = functioncode;
		this.parentid = parentid;
		this.type = type;
		this.status = status;
		this.sortnum = sortnum;
		this.createtime = createtime;
		this.updatetime = updatetime;
	}

	public Integer getFunctionid() {
		return functionid;
	}

	public void setFunctionid(Integer functionid) {
		this.functionid = functionid;
	}

	public String getFunctionname() {
		return functionname;
	}

	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}

	public String getFunctionurl() {
		return functionurl;
	}

	public void setFunctionurl(String functionurl) {
		this.functionurl = functionurl;
	}

	public String getFunctioncode() {
		return functioncode;
	}

	public void setFunctioncode(String functioncode) {
		this.functioncode = functioncode;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSortnum() {
		return sortnum;
	}

	public void setSortnum(Integer sortnum) {
		this.sortnum = sortnum;
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
		return "Functions [functionid=" + functionid + ", functionname=" + functionname + ", functionurl=" + functionurl
				+ ", functioncode=" + functioncode + ", parentid=" + parentid + ", type=" + type + ", status=" + status
				+ ", sortnum=" + sortnum + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}

}
