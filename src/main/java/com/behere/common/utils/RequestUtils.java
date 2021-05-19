package com.behere.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.behere.common.exception.ParameterException;
import com.behere.video.domain.User;

public class RequestUtils {
	private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);
	
	
	public static void loggerParams(HttpServletRequest request){
		StringBuffer sbuf = new StringBuffer();
        Enumeration<String> en = request.getParameterNames();
		boolean b=true;
		while(en.hasMoreElements()){
			String t = b?"?":"&";
			String name=en.nextElement();
			String value=request.getParameter(name);
			sbuf.append(t+name+"="+value);
			if(b) {
				b=false;
			}
		}
		logger.info(sbuf.toString());
	}
	
	public static Map<String, String> parseParameterMap(String m) throws UnsupportedEncodingException {
		Map<String, String> parameterMap = null;
		if (!StringUtils.isEmpty(m)) {
			String str = Des3.decode(m);
			logger.debug(str);
			try {
				parameterMap = Function.parseParameter(str);
			} catch (ParameterException e) {
				e.printStackTrace();
			}
		}
		return parameterMap;
	}
	
	public static PageData parseParameterPageData(String m) throws UnsupportedEncodingException {
		PageData pageData = new PageData();
		if (!StringUtils.isEmpty(m)) {
			String str = Des3.decode(m);
			logger.debug(str);
			try {
				pageData = Function.parseParameterPageData(str);
			} catch (ParameterException e) {
				e.printStackTrace();
			}
		}
		return pageData;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T extends Param> T  parseParameter(String m,Class<T> clazz) throws UnsupportedEncodingException {
		T param = null;
		Map<String, String> parameterMap = parseParameterPageData(m);
		if (parameterMap != null) {
			BeanRequestPicker<T> brp = new BeanRequestPicker<T>(clazz, parameterMap);
			param = brp.handleMap(clazz);
		}
		return param;
	}
	
	public static String getQueryString(HttpServletRequest request,boolean encode){
		StringBuffer sbuf = new StringBuffer();
        Enumeration<String> en = request.getParameterNames();
		boolean b=true;
		while(en.hasMoreElements()){
			String t = b?"?":"&";
			String name=en.nextElement();
			String value=request.getParameter(name);
			try {
				sbuf.append(t+name+"="+(encode?URLEncoder.encode(value,"utf-8"):value));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(b) {
				b = false;
			}
		}
		return sbuf.toString();
		
	}
	
	public static String getQueryString(HttpServletRequest request){
		return getQueryString(request, false);
		
	}
	
	public static String requestStr(HttpServletRequest request){
		StringBuffer sbuf = new StringBuffer();
		Enumeration<String> en = request.getParameterNames();
		boolean b=true;
		while(en.hasMoreElements()){
			String t = b?"":"&";
			String name=en.nextElement();
			String value=request.getParameter(name);
			if(!"url".equals(name)){
				sbuf.append(t+name+"="+value);
				if(b) {
					b=false;
				}
			}
			
		}
		return sbuf.toString();
	}
	
	public static void main(String[] args) {
		User user = new User();
		String m = Des3.encode("username=fengjun&password=123456");
		String m1 = Des3.encode("mobile=15103567828&password=123456");
		String m2 = Des3.encode("password=123456&mobile=13521306775");
		String m3 = Des3.encode("mobile=13521306775");
		String m4 = Des3.encode("mobile=13521306775&verificaCode=9527&password=123456");
		System.out.println(m);
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);
		System.out.println(m4);
		try {
			user = parseParameter(m, User.class);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(user.getUsername());
	}
}