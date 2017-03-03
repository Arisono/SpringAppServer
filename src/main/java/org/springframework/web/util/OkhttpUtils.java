package org.springframework.web.util;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSON;


import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * @author Arison
 * okhttp工具�?
 */
@SuppressWarnings("unused")
public class OkhttpUtils {
	
	private static boolean debug = true;// 是否日志打印
	
	public static OkHttpClient client = new OkHttpClient.Builder()
	.connectTimeout(10, TimeUnit.SECONDS)
	.readTimeout(10, TimeUnit.SECONDS)
	//信任�?有证�?
	.sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())
	.hostnameVerifier(new TrustAllHostnameVerifier())
	.build();
	
	
	/**
	 * 打印日志
	 * @param msg
	 */
	public static void println(String msg,int type) {
		if (debug) {
			System.out.println(getLineInfo(type) + msg);
		}
	}
	
	/**
	 * 打印日志
	 * @param msg
	 */
	public static void println(String msg) {
		if (debug) {
			System.out.println(getLineInfo(typeSimple) + msg);
		}
	}
	
	/**
	 * 打印日志
	 * @param msg
	 */
	public static void println(String msg,int type,String method) {
		if (debug) {
			System.out.println(getLineInfo(type,method) + msg);
		}
	}
	
	/**
	 * 打印日志
	 * @param msg
	 */
	public static void println(String msg,String method) {
		if (debug) {
			System.out.println(getLineInfo(typeSimple,method) + msg);
		}
	}

	public static final int typeAll=0;
	public static final int typeSimple=1;
	public static final int typeClass=2;
	public static final int typeMiddle=3;
	/**
	 * 获取代码当前行数
	 * 
	 * @return
	 */
	public static String getLineInfo(int dispalyName,String method) {
		Throwable root= new Throwable();
		StackTraceElement ste = root.getStackTrace()[2];
//        for(int i=0;i<root.getStackTrace().length;i++){
//        	 method=root.getStackTrace()[i].getMethodName();
//        	System.out.println(method);
//        }
		switch (dispalyName) {
		case typeAll:
			return "�?"+ste.getFileName()+"�?" + ":【第" + ste.getLineNumber() + "行�??:�?"+method+"�?";
		case typeSimple:
			return "【第" + ste.getLineNumber() + "行�??:";
		case typeClass:
			return ste.getFileName() + ":【第" + ste.getLineNumber() + "行�??:";
		case typeMiddle:
			return  "【第" + ste.getLineNumber() + "行�??:"+ "�?"+method+"�?";
		default:
			break;
		}
		return null;
	}

	/**
	 * 获取代码当前行数
	 * 
	 * @return
	 */
	public static String getLineInfo(int dispalyName) {
		Throwable root= new Throwable();
		StackTraceElement ste = root.getStackTrace()[2];
		switch (dispalyName) {
		case typeAll:
			return "�?"+ste.getFileName()+"�?" + ":【第" + ste.getLineNumber() + "行�??";
		case typeSimple:
			return "【第" + ste.getLineNumber() + "行�??:";
		case typeClass:
			return ste.getFileName() + ":【第" + ste.getLineNumber() + "行�??:";
		case typeMiddle:
			return  "【第" + ste.getLineNumber() + "行�??:";
		default:
			break;
		}
		return null;
	}
	/**
	 * 统一的网络失败回调方�?
	 * 
	 * @param e
	 */
	public static void onFailurePrintln(IOException e) {
		println("onFailure:" + ExceptionUtils.printExceptionStack(e));
		if (e instanceof ConnectException) {
			println("服务器拒绝访问！");
		} else if (e instanceof SocketTimeoutException) {
			println("超时响应�?");
		}
	}

	/**
	 * @param response
	 * @throws IOException
	 */
	public static String getResponseString(Response response)
			throws IOException {
		if (response.isSuccessful()) {
			String json = response.body().string();
			return json;
		} else {
			return "code:" + response.code() + "  message:"
					+ response.message();
		}
	}
	
	
	//http://blog.csdn.net/u013686019/article/details/52856389  信任�?有证�?
	private static class TrustAllCerts implements X509TrustManager {  
	    @Override  
	    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}  
	  
	    @Override  
	    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}  
	  
	    @Override  
	    public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}

		
	}  
	
	private static class TrustAllHostnameVerifier implements HostnameVerifier {  
	    @Override  
	    public boolean verify(String hostname, SSLSession session) {  
	        return true;  
	    }  
	}  
	
	private static SSLSocketFactory createSSLSocketFactory() {  
	    SSLSocketFactory ssfFactory = null;  
	  
	    try {  
	        SSLContext sc = SSLContext.getInstance("TLS");  
	        sc.init(null,  new TrustManager[] { new TrustAllCerts() }, new SecureRandom());  
	              
	        ssfFactory = sc.getSocketFactory();  
	    } catch (Exception e) {  
	    }  
	  
	    return ssfFactory;  
	}  

}
