package com.model;

import java.util.Map;

public class MobileNumberMess {
	String errNum;
	String retMsg;
	Map<String,String> retData;
	public String getErrNum() {
		return errNum;
	}
	public void setErrNum(String errNum) {
		this.errNum = errNum;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public Map<String, String> getRetData() {
		return retData;
	}
	public void setRetData(Map<String, String> retData) {
		this.retData = retData;
	}
	
}
