package org.springframework.samples.mvc.simple;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.samples.mvc.jpa.entity.News;
import org.springframework.samples.mvc.jpa.entity.User;
import org.springframework.samples.mvc.jpa.service.NewsService;
import org.springframework.samples.mvc.jpa.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DateFormatUtil;
import org.springframework.util.HttpRequestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Arison
 *
 */
@Controller("SimpleController")
public class SimpleController {
	@Resource
	private UserService userService;

	@Resource
	private NewsService newsService;

	/**
	 * 获取客户的请求头信息 ip地址,请求头信息,参数,缓存等等
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/client/info")
	public @ResponseBody Map<String, Object> getClientBaseInfo(
			HttpServletRequest request,
			HttpServletResponse response,
			@CookieValue(value = "JSESSIONID", required = false) String sessionId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> resultBody = new HashMap<String, Object>();
		String ipAddress = getIPAddress(request);// 获取客户端ip地址
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			result.put(key, value);
		}
		request.getHeader("cache-control");

		boolean isMobile = HttpRequestUtils.isMobileDevice(request);
		resultBody.put("isMoblie", isMobile);
		resultBody.put("headers", result);
		resultBody.put("JSESSIONID", sessionId);
		resultBody.put("ip", ipAddress);
		HttpSession httpSession = request.getSession();
		httpSession.setMaxInactiveInterval(1800);
		resultBody.put("sessionId", httpSession.getAttribute("sessionId"));
		if (httpSession != null
				&& !StringUtils.isEmpty(httpSession.getAttribute("sessionId"))) {
			if (httpSession.getAttribute("sessionId").equals(sessionId)) {
				// 登陆保持状态
				resultBody.put("loginState", "登陆状态！在线！");

			} else {
				// 登录断开状态
				resultBody.put("loginState", "会话断开！");
			//	httpSession.setAttribute("sessionId", httpSession.getId());
			}
		} else {
			// 登录断开状态
			resultBody.put("loginState", "会话断开！");
			//httpSession.setAttribute("sessionId", httpSession.getId());
		}

		resultBody.put("MaxInactiveInterval",
				httpSession.getMaxInactiveInterval());
		resultBody
				.put("LastAccessedTime", DateFormatUtil
						.getFormatDate(httpSession.getLastAccessedTime()));
		resultBody.put("CreationTime",
				DateFormatUtil.getFormatDate(httpSession.getCreationTime()));
		resultBody.put("isNew", httpSession.isNew());
		resultBody.put("HttpSessionId", httpSession.getId());

		return resultBody;
	}

	
	/**模拟客户端登陆，把当前会话ID写入浏览器request cookie
	 * @param request
	 * @param sessionId
	 * @return
	 */
	@RequestMapping(value = "/client/login")
	public String  loginSession(
			HttpServletRequest request,
			@CookieValue(value = "JSESSIONID", required = false) String sessionId) {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("sessionId", httpSession.getId());
		result.put("loginState", "登陆成功！");
		result.put("sessionId", httpSession.getId());
		return "me/index";
	}
	
	
	@RequestMapping(value = "/client/logout")
	public String logoutSession(
			HttpServletRequest request,
			@CookieValue(value = "JSESSIONID", required = false) String sessionId) {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		result.put("loginState", "注销登陆成功！");
		return "me/index";
	}
	

	/**
	 * 删除cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 */
	@RequestMapping("/delCookie")
	public @ResponseBody Map<String, Object> delCookie(
			@CookieValue(value = "JSESSIONID", required = false) String sessionId,
			HttpServletRequest request, HttpServletResponse response,
			String name) {
		Map<String, Object> result = new HashMap<String, Object>();
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			System.out.println("没有cookie==============");
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					cookie.setPath("/");
					System.out.println("被删除的cookie名字为:" + cookie.getName()
							+ ";path:" + cookie.getPath());
					request.getSession().removeAttribute("sessionId");
					// request.getSession().invalidate();
					// response.addCookie(cookie);
					break;
				}
			}
		}

		result.put("JSESSIONID", sessionId);
		result.put("sessionId", request.getSession().getAttribute("sessionId"));
		return result;
	}

	/**
	 * 读取所有cookie 注意二、从客户端读取Cookie时，包括maxAge在内的其他属性都是不可读的，也不会被提交。
	 * 浏览器提交Cookie时只会提交name与value属性。maxAge属性只被浏览器用来判断Cookie是否过期
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/showCookies")
	public @ResponseBody Map<String, Object> showCookies(
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		String sessionId = (String) request.getSession().getAttribute(
				"sessionId");
		if (null == cookies) {
			System.out.println("没有cookie=========");
		} else {
			for (Cookie cookie : cookies) {
				System.out.println("name:" + cookie.getName() + ",value:"
						+ cookie.getValue());
				result.put(cookie.getName(), cookie.getValue());
			}
		}
		result.put("sessionId", sessionId);
		return result;

	}

	private String getIPAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	@RequestMapping(value = "/simple/save/{name}/{password}", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public String simpleSave(@PathVariable String name,
			@PathVariable String password) {
		User user = new User();
		user.setUsername(name);
		user.setPassword(password);
		userService.saveUser(user);
		User user2 = userService.findOneUser(name, password);

		News news = new News();
		news.setTitle("美丽新世界");
		news.setContent("大美青海");
		news.setUserId(user2.getId());
		// news.setUserId(user2);
		newsService.save(news);
		// "保存成功！查询接口  localhost:8080/spring-mvc-showcase/simple/find/"+user2.getId();
		return "redirect:/simple/find/" + user2.getId();
	}

	@RequestMapping(value = "/simple1/save/", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public Object simple1Save(@RequestParam String name,
			@RequestParam String password) {
		User user2 = new User();
		try {
			User user = new User();
			user.setUsername(name);
			user.setPassword(password);
			userService.saveUser(user);
			user2 = userService.findOneUser(name, password);

			News news = new News();
			news.setTitle("美丽新世界");
			news.setContent("大美青海");
			news.setUserId(user2.getId());
			// news.setUserId(user2);
			newsService.save(news);
		} catch (Exception e) {
			System.out.println("程序内部异常！");
		}
		// "保存成功！查询接口  localhost:8080/spring-mvc-showcase/simple/find/"+user2.getId();
		return "redirect:/simple/find/" + user2.getId();
	}

	@RequestMapping(value = "/simple/find/{id}", method = RequestMethod.POST)
	public @ResponseBody Object simpleFind(@PathVariable Long id) {
		User user = new User();
		try {
			user = userService.findOneUser(id);
		} catch (Exception e) {
			return "程序异常！";

		}
		return user;
	}

	@RequestMapping(value = "/simple", produces = "text/plain;charset=utf-8")
	public @ResponseBody String simple() {
		return "Hello world!";
	}

	@RequestMapping(value = "/simple/map")
	public @ResponseBody Map<String, Object> map() {
		Map<String, Object> map = new HashMap<>();
		List<User> users = userService.findAllUsers();
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getUsername());
			map.put("users", users);
			map.put("name", users.get(i).getUsername());
			map.put("password", users.get(i).getPassword());
		}

		return map;
	}

	@RequestMapping(value = "/index1", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("/user/index");
		modelAndView.addObject("name", "xxx");
		return modelAndView;
	}

	// 对于ModelAndView构造函数可以指定返回页面的名称，也可以通过setViewName方法来设置所需要跳转的页面；

	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public ModelAndView index2() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("name", "xxx");
		modelAndView.setViewName("/user/index");
		return modelAndView;
	}

	/**
	 * Model一个模型对象， 主要包含spring封装好的model和modelMap,以及java.util.Map，
	 * 当没有视图返回的时候视图名称将由requestToViewNameTranslator决定；
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index3", method = RequestMethod.GET)
	public Map<String, String> index3() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "1");
		// map.put相当于request.setAttribute方法
		return map;
	}

	// 返回String
	// 通过model进行使用
	@RequestMapping(value = "/index4", method = RequestMethod.GET)
	public @ResponseBody String index(Model model) {

		return "jsp";
	}

}
