package com.service;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONObject;
import com.model.MobileNumberMess;
import com.util.HttpClientUtil;

public class QueryMobileNumberLocationServiceImpl implements QueryMobileNumberLocationService{

	@Override
	public String findMobileProvince(String mobileNumber) {
		String result = null;
		try {
			result = HttpClientUtil.httpGet(mobileNumber);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result!=null){
			MobileNumberMess jsonObject = JSONObject.parseObject(result, MobileNumberMess.class);
			return jsonObject.getRetData().get("province");
		}
		return null;
	}
}
