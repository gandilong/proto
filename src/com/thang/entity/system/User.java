package com.thang.entity.system;

import com.thang.tools.mate.entity.Id;
import com.thang.tools.mate.entity.Link;
import com.thang.tools.mate.entity.LinkType;
import com.thang.tools.mate.entity.Table;
@Table("user")
public class User {

	@Id
	private int id;
	@Link(LinkType.like)
	private String userName;
	private String loginName;
	private String loginPass;
	private String createTime;
	private int used;
	private String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPass() {
		return loginPass;
	}
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getUsed() {
		return used;
	}
	public void setUsed(int used) {
		this.used = used;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", loginName="
				+ loginName + ", loginPass=" + loginPass + ", createTime="
				+ createTime + ", used=" + used + ", email=" + email + "]";
	}
	
}
