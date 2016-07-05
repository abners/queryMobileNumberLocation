package com.factory;

import com.service.QueryMobileNumberLocationService;

public interface Factory {
	public QueryMobileNumberLocationService getInstance(Class<? extends QueryMobileNumberLocationService> c);
}
