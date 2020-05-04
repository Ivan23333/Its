package com.lts.entity;

public class Users {
	
	
	private int userid;
	private String username;
	private String password;
	private String address;
	private String phone;
	private String email;
	private String note;
	private int status;
	private String createtime;
	private String updatetime;
	
	
	
	public Users() {
		super();
	}
	public Users(int userid, String username, String password, String address, String phone, String email, String note,
			int status, String createtime, String updatetime) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.note = note;
		this.status = status;
		this.createtime = createtime;
		this.updatetime = updatetime;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		return "Users [userid=" + userid + ", username=" + username + ", password=" + password + ", address=" + address
				+ ", phone=" + phone + ", email=" + email + ", note=" + note + ", status=" + status + ", createtime="
				+ createtime + ", updatetime=" + updatetime + "]";
	}
	

}
