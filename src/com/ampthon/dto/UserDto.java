package com.ampthon.dto;

import java.io.Serializable;

import com.ampthon.pojo.TabUser;
import com.ampthon.util.Const;
/*
 * userdto类
 */
public class UserDto implements Serializable {
	
	TabUser tabUser;
	String rtnFlag=Const.RTN_DEFAULT;
	
	public TabUser getTabUser() {
		return tabUser;
	}
	public void setTabUser(TabUser tabUser) {
		this.tabUser = tabUser;
	}
	public String getRtnFlag() {
		return rtnFlag;
	}
	public void setRtnFlag(String rtnFlag) {
		this.rtnFlag = rtnFlag;
	}
}
