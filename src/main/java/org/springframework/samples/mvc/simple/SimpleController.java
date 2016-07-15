package org.springframework.samples.mvc.simple;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.samples.mvc.jpa.entity.News;
import org.springframework.samples.mvc.jpa.entity.User;
import org.springframework.samples.mvc.jpa.service.NewsService;
import org.springframework.samples.mvc.jpa.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.HttpRequestDeviceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller("SimpleController")
public class SimpleController {
	@Resource
	private UserService userService;
	
	@Resource
	private NewsService newsService;
	
	/**
	 * 获取客户的请求头信息
	 * ip地址,请求头信息,参数,缓存等等
	 */
	@RequestMapping(value="/client/info")
	public @ResponseBody Map<String,Object> getClientBaseInfo(
			HttpServletRequest request,
			@CookieValue(value="JSESSIONID",required=false) String sessionId) {
        Map<String,Object> result=new HashMap<String, Object>();
        Map<String,Object> resultBody=new HashMap<String, Object>();
        String ipAddress=getIPAddress(request);//获取客户端ip地址
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            result.put(key, value);
        }
        boolean isMobile=HttpRequestDeviceUtils.isMobileDevice(request);
        resultBody.put("isMoblie", isMobile);
        resultBody.put("headers",result);
        resultBody.put("sessionId", sessionId);
        resultBody.put("ip", ipAddress);
        HttpSession httpSession=request.getSession();
        httpSession.setAttribute("sessionId", sessionId);
		return resultBody;
	}
	
	private String getIPAddress(HttpServletRequest request){
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
	

	
	@RequestMapping(value="/simple/save/{name}/{password}", 
			produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public  String simpleSave(@PathVariable String name,@PathVariable String password) {
		User user=new User();
		user.setUsername(name);
		user.setPassword(password);
		userService.saveUser(user);
	    User user2=userService.findOneUser(name, password);
	    
	    News news=new News();
	    news.setTitle("美丽新世界");
	    news.setContent("大美青海");
	    news.setUserId(user2.getId());
//	    news.setUserId(user2);
	    newsService.save(news);
//	     "保存成功！查询接口  localhost:8080/spring-mvc-showcase/simple/find/"+user2.getId();
		return  "redirect:/simple/find/"+user2.getId();
	}
	
	@RequestMapping(value="/simple1/save/", 
			produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public  Object simple1Save(@RequestParam String name,@RequestParam String password) {
		User user2=new User();
		try {
			User user=new User();
			user.setUsername(name);
			user.setPassword(password);
			userService.saveUser(user);
			user2 = userService.findOneUser(name, password);
			
			News news=new News();
			news.setTitle("美丽新世界");
			news.setContent("大美青海");
			news.setUserId(user2.getId());
//	    news.setUserId(user2);
			newsService.save(news);
		} catch (Exception e) {
			System.out.println("程序内部异常！");
		}
//	     "保存成功！查询接口  localhost:8080/spring-mvc-showcase/simple/find/"+user2.getId();
		return  "redirect:/simple/find/"+user2.getId();
	}
	
	
	@RequestMapping(value="/simple/find/{id}",method=RequestMethod.POST)
	public @ResponseBody Object simpleFind(@PathVariable Long id) {
		User user=new User();
		try {
			user = userService.findOneUser(id);
		} catch (Exception e) {
			return "程序异常！";
					
		}
		return user;
	}

	@RequestMapping(value="/simple",produces="text/plain;charset=utf-8")
	public @ResponseBody String simple() {
		return "Hello world!";
	}
	
	@RequestMapping(value="/simple/map")
	public @ResponseBody Map<String,Object> map() {
		Map<String,Object> map=new HashMap<>();
		List<User> users=userService.findAllUsers();
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getUsername());
			map.put("users", users);
			map.put("name", users.get(i).getUsername());
			map.put("password", users.get(i).getPassword());
		}
		
		return map;
	}
	
	   @RequestMapping(value="/index1",method=RequestMethod.GET)  
	    public ModelAndView index(){  
	        ModelAndView modelAndView = new ModelAndView("/user/index");  
	        modelAndView.addObject("name", "xxx");  
	        return modelAndView;  
	    }  
	   
	   //对于ModelAndView构造函数可以指定返回页面的名称，也可以通过setViewName方法来设置所需要跳转的页面；  
	      
	    @RequestMapping(value="/index2",method=RequestMethod.GET)  
	    public ModelAndView index2(){  
	        ModelAndView modelAndView = new ModelAndView();  
	        modelAndView.addObject("name", "xxx");  
	        modelAndView.setViewName("/user/index");  
	        return modelAndView;  
	    }  
	    
	    /** 
	     * Model一个模型对象， 
	     * 主要包含spring封装好的model和modelMap,以及java.util.Map， 
	     * 当没有视图返回的时候视图名称将由requestToViewNameTranslator决定；  
	     * @return 
	     */  
	    @RequestMapping(value="/index3",method=RequestMethod.GET)  
	    public Map<String, String> index3(){  
	        Map<String, String> map = new HashMap<String, String>();  
	        map.put("1", "1");  
	        //map.put相当于request.setAttribute方法  
	        return map;  
	    }  
	    
	    //返回String  
	    //通过model进行使用  
	    @RequestMapping(value="/index4",method = RequestMethod.GET)  
	    public @ResponseBody String index(Model model) {  
	     
	        return "jsp";  
	    }  
	      

}
