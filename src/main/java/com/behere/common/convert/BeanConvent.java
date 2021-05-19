/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.behere.common.convert;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * @author fengjun
 * request to bean
 */
public class BeanConvent  {

	private static final Map<Class<?>, Object> primitiveDefaults = new HashMap<Class<?>, Object>();

	static {
		primitiveDefaults.put(Integer.TYPE, Integer.valueOf(0));
		primitiveDefaults.put(Short.TYPE, Short.valueOf((short) 0));
		primitiveDefaults.put(Byte.TYPE, Byte.valueOf((byte) 0));
		primitiveDefaults.put(Float.TYPE, Float.valueOf(0f));
		primitiveDefaults.put(Double.TYPE, Double.valueOf(0d));
		primitiveDefaults.put(Long.TYPE, Long.valueOf(0L));
		primitiveDefaults.put(Boolean.TYPE, Boolean.FALSE);
		primitiveDefaults.put(Character.TYPE, Character.valueOf((char) 0));
	}

	public <T> T toBean(HttpServletRequest request, Class<T> type) {
		PropertyDescriptor[] props = this.propertyDescriptors(type);
		return createBean(request, type, props);
	}
	
	public <T> T toBean(HttpServletRequest request, Class<T> type,Map<String, String> paraMap) {
		PropertyDescriptor[] props = this.propertyDescriptors(type);
		return createBean(request, type, props);
	}

	private PropertyDescriptor[] propertyDescriptors(Class<?> c) {
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(c);
		} catch (IntrospectionException e) {
		}
		return beanInfo.getPropertyDescriptors();
	}
	
	@SuppressWarnings("unused")
	private <T> T createBean(HttpServletRequest request, Class<T> type,PropertyDescriptor[] props,Map<String, String> paraMap) {
		Map<String, String> map = getMap(request,paraMap);
		T bean = this.newInstance(type);
		for (int i = 0; i < props.length; i++) {
			PropertyDescriptor prop = props[i];
			
			if (map.containsKey(prop.getName())) {
				Class<?> propType = prop.getPropertyType();
				Object obj = map.get(prop.getName());
				Object value = this.processColumn(dealNull(map.get(prop.getName())), propType);
				if (propType != null && value == null && propType.isPrimitive()) {
					value = primitiveDefaults.get(propType);
				}
				this.callSetter(bean, prop, value);
			}
		}
		return bean;
	}

	@SuppressWarnings("unused")
	private <T> T createBean(HttpServletRequest request, Class<T> type,PropertyDescriptor[] props) {
		Map<String, String> map = getMap(request,null);
		T bean = this.newInstance(type);
		for (int i = 0; i < props.length; i++) {
			PropertyDescriptor prop = props[i];
			if (map.containsKey(prop.getName())) {
				Class<?> propType = prop.getPropertyType();
				Object obj = map.get(prop.getName());
				Object value = this.processColumn(dealNull(map.get(prop.getName())), propType);
				if (propType != null && value == null && propType.isPrimitive()) {
					value = primitiveDefaults.get(propType);
				}
				this.callSetter(bean, prop, value);
			}
		}
		return bean;
	}
	
	
	private Map<String, String> getMap(HttpServletRequest request,Map<String, String> paraMap){
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> en = request.getParameterNames();
		
		while(en.hasMoreElements()){
			String name=en.nextElement();
			String value=request.getParameter(name);
			String alis = (null!=paraMap && paraMap.get(name)!=null)?paraMap.get(name):name;
			map.put(alis, value);
		}
		return map;
	}
	
	protected <T> T newInstance(Class<T> c) {
		try {
			return c.newInstance();
		} catch (Exception e) {
			return null;
		}
	}
	public  String dealNull(Object obj) {
		String str = "";
		if(obj!=null){
			if(obj instanceof String){
				str = (String)obj;
			}else{
				str = obj.toString();
			}
		}
		return str;
	}

	protected Object processColumn(String value, Class<?> propType) {
		if (propType.equals(String.class)) {
			return value.toString();
			
		} else if (propType.equals(Integer.TYPE)|| propType.equals(Integer.class)) {
			return parseInteger(value,0);
			//return Integer.valueOf(object.toString());
		} else if (propType.equals(Boolean.TYPE)|| propType.equals(Boolean.class)) {
			return Boolean.valueOf(value);
		} else if (propType.equals(Long.TYPE) || propType.equals(Long.class)) {
			return   parseLong(value, 0l);//Long.valueOf(value);
		} else if (propType.equals(Double.TYPE)|| propType.equals(Double.class)) {
			return parseDouble(value, 0d);//Double.valueOf(value);
		} else if (propType.equals(Float.TYPE) || propType.equals(Float.class)) {
			return Float.valueOf(value);
		} else if (propType.equals(Short.TYPE) || propType.equals(Short.class)) {
			return Short.valueOf(value);
		} else if (propType.equals(Byte.TYPE) || propType.equals(Byte.class)) {
			return Byte.valueOf(value);
		} else if (propType.equals(Timestamp.class)) {
			return "";
		} else {
			return value;
		}

	}
	private Double parseDouble(Object o,Double def){
    	if(null!=o){
    		try{
    			return Double.parseDouble(o.toString());
    		}catch(Exception e){
    			return def;
    		}
    	}
    	return def;
    }
	private Long parseLong(Object o,Long def){
    	if(null!=o){
    		try{
    			return Long.parseLong(o.toString());
    		}catch(Exception e){
    			return def;
    		}
    	}
    	return def;
    }
    private Integer parseInteger(Object o,Integer def){
    	if(null!=o){
    		try{
    			return Integer.valueOf(o.toString());
    		}catch(Exception e){
    			return def;
    		}
    	}
    	return def;
    }
	private void callSetter(Object target, PropertyDescriptor prop, Object value) {
		Method setter = prop.getWriteMethod();
		if (setter == null) {
			return;
		}
		Class<?>[] params = setter.getParameterTypes();
		try {
			// convert types for some popular ones
			if (value != null) {
				if (value instanceof java.util.Date) {
					if (params[0].getName().equals("java.sql.Date")) {
						value = new java.sql.Date(((java.util.Date) value)
								.getTime());
					} else if (params[0].getName().equals("java.sql.Time")) {
						value = new java.sql.Time(((java.util.Date) value)
								.getTime());
					} else if (params[0].getName().equals("java.sql.Timestamp")) {
						value = new java.sql.Timestamp(((java.util.Date) value)
								.getTime());
					}
				}
			}

			// Don't call setter if the value object isn't the right type
			if (this.isCompatibleType(value, params[0])) {
				setter.invoke(target, new Object[] { value });
			} else {
				
			}

		} catch (IllegalArgumentException e) {
			
		} catch (IllegalAccessException e) {
			

		} catch (InvocationTargetException e) {
		
		}
	}

	private boolean isCompatibleType(Object value, Class<?> type) {
		// Do object check first, then primitives
		if (value == null || type.isInstance(value)) {
			return true;

		} else if (type.equals(Integer.TYPE) && Integer.class.isInstance(value)) {
			return true;

		} else if (type.equals(Long.TYPE) && Long.class.isInstance(value)) {
			return true;

		} else if (type.equals(Double.TYPE) && Double.class.isInstance(value)) {
			return true;

		} else if (type.equals(Float.TYPE) && Float.class.isInstance(value)) {
			return true;

		} else if (type.equals(Short.TYPE) && Short.class.isInstance(value)) {
			return true;

		} else if (type.equals(Byte.TYPE) && Byte.class.isInstance(value)) {
			return true;

		} else if (type.equals(Character.TYPE)
				&& Character.class.isInstance(value)) {
			return true;

		} else if (type.equals(Boolean.TYPE) && Boolean.class.isInstance(value)) {
			return true;

		}
		return false;
	}
}