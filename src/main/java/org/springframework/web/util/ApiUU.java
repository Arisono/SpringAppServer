package org.springframework.web.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.samples.mvc.jpa.entity.DownloadRepoMessageEvent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;






import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * 测试UU互联接口 平台接口
 * 
 * @author Arison RaoMeng
 */
@SuppressWarnings("unused")
public class ApiUU {

	private static final String baseurl_normal = "http://218.18.115.198:8888/ERP/";
	private static final String baseurl_uas = "http://218.17.158.219:8090/ERP/";
	private static final String baseurl_test = "http://218.18.115.198:8887/ERP/";
	private static final String master_normal = "USOFTSYS";
	private static final String master_uas = "UAS";
	private static final String master_test = "USOFT_MALL";

	private static final String master = master_normal;// UAS//USOFTSYS//DATACENTER
	private static final String baseurl = baseurl_normal;// 正式账套
	private static String sessionId;
	private static String emcode;
	private static String cookies;

	
	
	public static void sendSign(){
		loginERP("13266699268", "111111", master); // uas系统登录13691965521	
	}

	/**
	 * 登录成功之后请求接口
	 */
	protected static void callbackResquest() {
		startTaskCard(new DateFormatUtil().getDateStr(new Date()));
	}

	// 登录ERP
	public static void loginERP(String user, String password, String master) {
		String url = baseurl + "mobile/login.action";
		RequestBody formBody = new FormBody.Builder().add("username", user)
				.add("password", password).add("master", master).build();
		Request request = new Request.Builder().url(url)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		OkhttpUtils.println(url);
		OkhttpUtils.println("user:" + user + " password:" + password
				+ " master:" + master);
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("uas登录：" + json);
				sessionId = JSON.parseObject(json).getString("sessionId");// 拿到关键参数
				emcode = JSON.parseObject(json).getString("erpaccount");// 拿到关键参数
				callbackResquest();
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}
	
	private static void startTaskCard(String date) {
		addMobileMac("addMobileMac");
	
		//注册rxbus事件
		RxjavaUtils.registerSubscription(RxBus.getInstance().toObservable()
				.filter(o -> o instanceof DownloadRepoMessageEvent)
				.map(o -> (DownloadRepoMessageEvent) o)
				.doOnNext(o -> RxjavaUtils.showMessage(o.getMessage()))
				.subscribe(new Subscriber<DownloadRepoMessageEvent>() {

					@Override
					public void onCompleted() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onError(Throwable e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onNext(DownloadRepoMessageEvent t) {
						OkhttpUtils.println(t.getMessage());
						selectCardLog(date,"selectCardLog");
						
					}
				}));
	}

	static String json;

	
	public static void addMobileMac(String method){
		//master=USOFTSYS, emcode=U0316, sessionUser=U0316, sessionId=29DB60DE6E40D859B9169FE5013A8520, macAddress=3c:b6:b7:64:a7:ea
		String url = baseurl + "mobile/addMobileMac.action";
		RequestBody formBody = new FormBody.Builder()
				.add("master", master)
				.add("emcode",emcode)		
				.add("macAddress", "3c:b6:b7:64:a7:ea")
				.add("sessionId", sessionId)
				.build();

		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();

		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				OkhttpUtils.println(OkhttpUtils.getResponseString(response),OkhttpUtils.typeMiddle,method);
				getStringCode("getStringCode");
				return;
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}
	
	 static String code = "";
	
	public static void getStringCode(String method){
		
		//master=USOFTSYS, sessionUser=U0316, sessionId=29DB60DE6E40D859B9169FE5013A8520, caller=CardLog, type=2
		String url = baseurl + "common/getCodeString.action";
		RequestBody formBody = new FormBody.Builder()
				.add("master", master)
				.add("type","2")		
				.add("caller", "CardLog")
				.add("sessionId", sessionId)
				.build();

		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();

		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
			    String result=	OkhttpUtils.getResponseString(response);
			    OkhttpUtils.println(result,OkhttpUtils.typeMiddle,method);
			
				code=JSON.parseObject(result).getString("code");
				saveCardLog("12.12", code, "saveCardLog");
				
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	
	}
	
	
	public static void saveCardLog(String dis,String code,String method){
		//master=USOFTSYS, sessionUser=U0316, sessionId=29DB60DE6E40D859B9169FE5013A8520, caller=CardLog, type=2
		String formStore=""
				+ "{\"cl_emname\":\"刘杰\","
				+ "\"cl_distance\":"+dis+","
				+ "\"cl_emcode\":\"U0316\","
				+ "\"cl_phone\":\"13266699268\","
				+ "\"cl_code\":\""+code+"\","
				+ "\"cl_location\":\"在英唐大厦附近\","
				+ "\"cl_address\":\"中国广东省深圳市南山区科技南五路5\"}";
		OkhttpUtils.println(formStore);
		String url = baseurl + "mobile/saveCardLog.action";
		RequestBody formBody = new FormBody.Builder()
				.add("master", master)	
				.add("caller", "CardLog")
				.add("formStore", formStore)
				.add("sessionId", sessionId)
				.build();

		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();

		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
			    String result=	OkhttpUtils.getResponseString(response);
			    OkhttpUtils.println(result,OkhttpUtils.typeMiddle,method);
			    RxBus.getInstance().send(new DownloadRepoMessageEvent("保存成功！"));
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
		
	}
	
	public static void selectCardLog(String date,String method){
		//{master=USOFTSYS, emcode=U0316, pageSize=100, sessionUser=U0316, 
		//condition=cl_emcode='U0316' and to_char(cl_time,'yyyy-MM-dd')='2017-03-03', 
		//sessionId=29DB60DE6E40D859B9169FE5013A8520, caller=CardLog, page=1, currentMaster=USOFTSYS}
		
		String url = baseurl + "/mobile/oa/workdata.action";
		RequestBody formBody = new FormBody.Builder()
				.add("currentMaster", master)
				.add("master", master)
				.add("emcode",emcode)	
				.add("condition", "cl_emcode='U0316' and to_char(cl_time,'yyyy-MM-dd')='"+date+"'")
				.add("caller", "CardLog")
				.add("page", "1")
				.add("sessionId", sessionId)
				.build();

		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();

		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
			    String result=	OkhttpUtils.getResponseString(response);
			    OkhttpUtils.println(result,OkhttpUtils.typeMiddle,method);
			
		
				
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
		
	}
}
