package com.behere.common.utils;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.behere.common.exception.AppException;
import com.behere.common.exception.ParameterException;

public class Function {


	/**
	 * 反射获取对象
	 * 
	 * @param clazz
	 *            class
	 * @param className
	 *            Class名称
	 * @param parameterTypes
	 *            构造函数参数类型
	 * @param initargs
	 *            构造函数参数
	 * @return
	 * @throws AppException
	 */
	public static <T> T createObject(Class<T> clazz, String className,
			Class<?>[] parameterTypes, Object[] initargs) throws AppException {
		try {
			Class<?> instanceClass = Class.forName(className);
			if (parameterTypes != null && initargs != null) {
				if (parameterTypes.length == initargs.length) {
					Constructor<?> constructor = instanceClass
							.getConstructor(parameterTypes);
					return clazz.cast(constructor.newInstance(initargs));
				} else {
					throw new AppException(
							"Argument arrays lengths are not match");
				}
			} else if (parameterTypes == null && initargs == null) {
				return clazz.cast(instanceClass.newInstance());
			} else {
				throw new AppException(
						"Argument arrays must be both null or both not null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	/**
	 * 解析参数
	 * 
	 * @param parameters
	 *            imei=863291001183571&netEnv=wifi&areaCode=100023&telNum=
	 *            13804456375&operator=chinamobile&sw=180&sh=360
	 * @return
	 */
	public static Map<String, String> parseParameter(String parameters)
			throws ParameterException {
		if (StringUtils.isEmpty(parameters)) {
			throw new ParameterException("传入参数不合法!");
		}
		Map<String, String> paramsMap = new HashMap<String, String>();
		String paramsStr = new String(parameters);
		String[] params = paramsStr.split("&");
		for (int i = 0; i < params.length; i++) {
			String param = params[i];
			String[] _param = param.split("=");
			if (_param != null && _param.length == 2) {
				paramsMap.put(_param[0], _param[1]);
			}
		}
		return paramsMap;
	}
	
	public static PageData parseParameterPageData(String parameters)
			throws ParameterException {
		if (StringUtils.isEmpty(parameters)) {
			throw new ParameterException("传入参数不合法!");
		}
		PageData pageData = new PageData();
		String paramsStr = new String(parameters);
		String[] params = paramsStr.split("&");
		for (int i = 0; i < params.length; i++) {
			String param = params[i];
			String[] _param = param.split("=");
			if (_param != null && _param.length == 2) {
				pageData.put(_param[0], _param[1]);
			}
		}
		return pageData;
	}
}