package com.lts.entity;

public class MenuVo {

	private String id;
	private String name;
	private String url;
	private String pId;
	
	private boolean open;
	private boolean checked;
	
	
	
	public MenuVo() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "MenuVo [id=" + id + ", name=" + name + ", url=" + url + ", pId=" + pId + ", open=" + open + ", checked="
				+ checked + "]";
	}
	
	
}
