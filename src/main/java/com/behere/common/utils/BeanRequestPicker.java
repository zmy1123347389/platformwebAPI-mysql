package com.behere.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.behere.common.convert.BeanConvent;
import com.behere.common.convert.BeanJsonConvent;
import com.behere.common.convert.BeanMapConvent;



public class BeanRequestPicker<T> {
	@SuppressWarnings("unused")
	private final Class<T> type;
	private HttpServletRequest request;
	private BeanConvent convent = new BeanConvent();
	private BeanMapConvent mapConvent = new BeanMapConvent();
	private Map<String, String> map = new HashMap<String, String>();
	private BeanJsonConvent jsonConvent = new BeanJsonConvent();
	public BeanRequestPicker(Class<T> type,HttpServletRequest request){
		this.type=type;
		this.request=request;
	}

	public BeanRequestPicker(Class<T> type,Map<String, String> map ){
		this.type=type;
		this.map=map;
	}
	
	@SuppressWarnings("hiding")
	public <T> T handle(Class<T> type){		
		return convent.toBean(request, type);
	}
	@SuppressWarnings("hiding")
	public <T> T handleMap(Class<T> type){
		return mapConvent.toBean(map, type);
	}
}
