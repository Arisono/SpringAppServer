package org.springframework.samples.mvc.simple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.samples.mvc.jpa.entity.News;
import org.springframework.samples.mvc.jpa.entity.User;
import org.springframework.samples.mvc.jpa.service.NewsService;
import org.springframework.samples.mvc.jpa.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("SimpleController")
public class SimpleController {
	@Resource
	private UserService userService;
	
	@Resource
	private NewsService newsService;
	
	@RequestMapping(value="/simple/save/{name}/{password}", produces = "application/json; charset=utf-8")
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
	
	
	
	@RequestMapping(value="/simple/find/{id}")
	public @ResponseBody User simpleFind(@PathVariable Long id) {
		User user=userService.findOneUser(id);
		return user;
	}

	@RequestMapping(value="/simple",produces="text/plain;charset=utf-8")
	public @ResponseBody String simple() {
		List<User> users=userService.findAllUsers();
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getUsername());
		}
		return "Hello world!刘杰";
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
	    public String index(Model model) {  
	     
	        return "专业万岁";  
	    }  
	      

}
