package com.lts.entity;

public class Roles {
	
	private int roleid;
	private String rolename;
	private String note;
	private int status;
	private String createtime;
	private String updatetime;
	
	public Roles() {
		super();
	}

	public Roles(int roleid, String rolename, String note, int status, String createtime, String updatetime) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
		this.note = note;
		this.status = status;
		this.createtime = createtime;
		this.updatetime = updatetime;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
		return "Roles [roleid=" + roleid + ", rolename=" + rolename + ", note=" + note + ", status=" + status
				+ ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
	
	

}
