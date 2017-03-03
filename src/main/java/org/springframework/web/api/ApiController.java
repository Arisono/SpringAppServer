
package org.springframework.web.api;


import java.security.PublicKey;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.DateFormatUtil;
import org.springframework.web.util.RSAUtils;

import com.alibaba.fastjson.JSON;


/** 接口处理
 * @author Arison
 *
 */
@Controller(value="ApiController")
public class ApiController {
	
	
	/**Get请求
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/sign" ,method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String sign(String param1,String param2) {
		System.out.println(param1+""+param2);
		return param1+""+param2;
	}
	
	/**Get请求
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/get1" ,method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String get1(String param1,String param2) {
		System.out.println(param1+""+param2);
		return param1+""+param2;
	}
	
	/**Get请求
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/get" ,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String get(HttpServletRequest request) {
		return JSON.toJSONString((request.getParameterMap()));
	}
	
   
	/**进入api测试界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/index")
	public String index(HttpServletRequest request) {
		return "me/index";
	}
	
	/**接收请求头
	 * @return
	 */
	@RequestMapping(value = "/api/getHeaders")
	private @ResponseBody LinkedHashMap<String, Object>  receiveHeaders(
			HttpServletRequest request,
			@CookieValue(value = "JSESSIONID", required = false)
			String sessionId,
			@RequestBody String postBody) {
		LinkedHashMap<String, Object> result=new LinkedHashMap<String, Object>();
		Map<String, Object> header=new HashMap<String, Object>();
		Map<String, Object> params=new HashMap<String, Object>();
		result.put("postBody", postBody);
//		result.put("postBody1", postBody1);
//		result.put("postBody2", postBody2);
		@SuppressWarnings("rawtypes")
		Enumeration paramNames  =request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String key = (String) paramNames.nextElement();
			Object value =  request.getParameter(key);
			params.put(key, value);
		}
		result.put("params", params);
		@SuppressWarnings("rawtypes")
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			header.put(key, value);
		}
		result.put("headers", header);
		result.put("JSESSIONID", sessionId);
		System.out.println(result.toString());
		String public_key=request.getParameter("publicKey");
		String miwen=request.getParameter("miwen");
		
		String token=RSAUtils.RSADecode(Base64.decodeBase64(public_key), Base64.decodeBase64(miwen));
	    System.out.println("token:"+token);
		result.put("token", token);
		return result;
	}

	/**接口参数测试
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/api/paramBody",produces = "application/json; charset=utf-8")
	@ResponseBody
	private  String testParamBody(HttpServletRequest request){
		System.out.println(request.getParameter("data"));
		System.out.println(request.getParameter("type"));
		return "{'msg1':'访问成功！'}"+"data:"+request.getParameter("data")+" type:"+request.getParameter("type");
	}
	
	
	/**接口参数测试
	 * @return
	 */
	@RequestMapping(value = "/api/paramBody1",produces = "application/json; charset=utf-8")
	@ResponseBody
	private  String testParamBody(String data,String type){
		System.out.println(data);
		System.out.println(type);
		return "{'msg':'访问成功！'}"+data;
	}
	
	/**接口参数测试
	 * @return
	 */
	@RequestMapping(value = "/api/paramBody2",produces = "application/json; charset=utf-8")
	@ResponseBody
	private  String testParamBody(@RequestBody String data,@RequestBody String data1,String data2){
		System.out.println(data);
		System.out.println(data1);
		System.out.println(data2);
		return "{'msg':'访问成功！'}"+"data="+data+"data1="+data1+"data2="+data2;
	}
	
	/**接口参数测试
	 * @return
	 */
	@RequestMapping(value = "/api/paramBody3",produces = "application/json; charset=utf-8")
	@ResponseBody
	private  String testParamBody(@RequestBody String data){
		System.out.println(data);
		return "{'msg':'访问成功！'}"+"data="+data;
	}
//	异步任务
/*	@RequestMapping(value="/asynctask", method = RequestMethod.GET)
	public DeferredResult<ModelAndView> asyncTask(){
	    DeferredResult<ModelAndView> deferredResult = new DeferredResult<ModelAndView>();
	    System.out.println("/asynctask 调用！thread id is : " + Thread.currentThread().getId());
	    
	    ModelAndView mav = new ModelAndView("remotecalltask");
        mav.addObject("result", "result");
	    deferredResult.setResult(mav);
	}*/
	
	@RequestMapping("/testCall")
	@ResponseBody
	public WebAsyncTask<String> testCall() {
		System.out.println("testCall 开始执行...."+DateFormatUtil.getDateTime());
		System.out.println("testCallWait() 当前线程："+Thread.currentThread().getName());
		Callable<String> callable = new Callable<String>() {
	        @Override
	        public String call() throws Exception {
	             Thread.sleep(2000);
	             System.out.println("testCallWait() 异步回调() 当前线程："+Thread.currentThread().getName());
	             return "task/task";
	        }
	    };
	    System.out.println("testCall 非阻塞...."+DateFormatUtil.getDateTime());
	    return new WebAsyncTask<String>(2000, callable);

	}
	
	
	@RequestMapping("/testCallWait")
	@ResponseBody
	public String testCallWait() {
		  System.out.println("testCallWait() 开始执行...."+DateFormatUtil.getDateTime());
		  System.out.println("testCallWait() 当前线程："+Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		  System.out.println("testCallWait() 阻塞...."+DateFormatUtil.getDateTime());
        return "task/task";
	}
	
//	@ExceptionHandler
//	@ResponseBody
//	public String handleException(IllegalStateException ex) {
//		return "Handled exception: " + JSON.toJSONString(ex);
//	}
}
