package com.anyu.tiangou.oauth.tuils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;
/**
 * 发送短信工具类
 * @author Administrator
 *
 */
@Component
public class SmsSampleUtil {
	
	/**
	 * 链接拼接
	 * @param username  用户名
	 * @param password  密码
	 * @param phone  手机号
	 * @param content  内容
	 * @return
	 */
	
	public static String httpArgs(String username,String password,String phone,String content) {
		StringBuffer httpArg = new StringBuffer();
		httpArg.append("u=").append(username).append("&");
		httpArg.append("p=").append(md5(password)).append("&");
		httpArg.append("m=").append(phone).append("&");
		httpArg.append("c=").append(encodeUrlString(content, "UTF-8"));
		return httpArg.toString();
		
	}
	/**
	 * 发送信息
	 * @param httpUrl  链接
	 * @param httpArg  拼接的数据
	 * @return
	 */
	public static String request(String httpUrl, String httpArg) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;
		System.out.println(httpUrl);
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = reader.readLine();
			if (strRead != null) {
				sbf.append(strRead);
				while ((strRead = reader.readLine()) != null) {
					sbf.append("\n");
					sbf.append(strRead);
				}
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 平台密码的加密，（不加密使用不了）
	 * @param plainText
	 * @return
	 */
	public static String md5(String plainText) {
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	/**
	 * 发送内容，采用UTF-8 URL ENCODE
	 * 对内容进行加密
	 * @param str
	 * @param charset
	 * @return
	 */
	public static String encodeUrlString(String str, String charset) {
		String strret = null;
		if (str == null)
			return str;
		try {
			strret = java.net.URLEncoder.encode(str, charset);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return strret;
	}

}
