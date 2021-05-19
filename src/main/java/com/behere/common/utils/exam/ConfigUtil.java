package com.behere.common.utils.exam;

import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

	/**
	 * 通过静态代码块读取配置文件,静态代码块只执行一次(单例)
	 */
	private static Properties properties = new Properties();

	private ConfigUtil() {

	}

	// 通过类装载器装载进来
	static {
		try {
			// 从类路径下读取属性文件
			properties.load(ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 函数功能说明 ：读取配置项 修改者名字 ： 修改日期 ： 修改内容 ：
	 *
	 * @参数： @return void @throws
	 */
	public static String readConfig(String key) {
		return (String) properties.get(key);
	}

	public static String getProperty(String string, String string2) {
		return properties.getProperty(string2, string2);
	}

	public static int getIntProperty(String string, int i) {
		try {
			return Integer.valueOf(properties.getProperty(string));
		} catch (Exception e) {
			return i;
		}
	}
	public static final String sftpUsername = (String) properties.get("sftp_username");
	public static final String sftpPassword = (String) properties.get("sftp_password");
	public static final String sftpPrivateKey = (String) properties.get("sftp_privateKey");
	public static final String sftpHost = (String) properties.get("sftp_host");
	public static final String sftpPort = (String) properties.get("sftp_port");
	public static final String sftpImage = (String) properties.get("sftp_image");
	public static final String sftpVideo = (String) properties.get("sftp_video");
	public static final String sftpVideoImg = (String) properties.get("sftp_video_img");
	public static final String sftpSummernoteFile = (String) properties.get("sftp_summernote_file");
	public static final String sftpOutputImg = (String) properties.get("sftp_outputimg");
	public static final String sftpOutputVideo = (String) properties.get("sftp_outputvideo");
	public static final String sftpOutputVideoImg = (String) properties.get("sftp_outputvideoimg");
	public static final String sftpOutputSummernoteFile = (String) properties.get("sftp_output_summernote_file");
	public static final String sftpOutputPubUrl = (String) properties.get("sftp_output_pub_url");
	public static final String sftPubUrl = (String) properties.get("sftp_pub_url");
}
