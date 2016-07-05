package com.service;

import java.util.HashMap;
import java.util.Map;

import com.factory.Factory;

public class QueryMobileNumberLocationFactory implements Factory{
	private static Map<Class<? extends QueryMobileNumberLocationService>,QueryMobileNumberLocationService> mobileNumberLocationService
			= new HashMap<Class<? extends QueryMobileNumberLocationService>, QueryMobileNumberLocationService>();
	@Override
	public QueryMobileNumberLocationService getInstance(
			Class<? extends QueryMobileNumberLocationService> c) {
		QueryMobileNumberLocationService queryMobileNumberLocationService = null;
		synchronized (mobileNumberLocationService) {
			if(mobileNumberLocationService.get(c)==null){
				try {
					queryMobileNumberLocationService = c.newInstance();
					mobileNumberLocationService.put(c, queryMobileNumberLocationService);
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				queryMobileNumberLocationService = mobileNumberLocationService.get(c);
			}
		}
		return queryMobileNumberLocationService;
	}
	
}
