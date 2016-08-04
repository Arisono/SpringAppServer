
package org.springframework.api;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/** 接口处理
 * @author Arison
 *
 */
@Controller(value="ApiController")
public class ApiController {
   
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
			String sessionId,@RequestBody String postBody) {
		LinkedHashMap<String, Object> result=new LinkedHashMap<String, Object>();
		Map<String, Object> header=new HashMap<String, Object>();
		Map<String, Object> params=new HashMap<String, Object>();
		result.put("postBody", postBody);
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
		return result;
	}
	
}
