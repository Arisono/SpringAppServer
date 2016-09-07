package org.springframework.web.session;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**负责处理首页转发的控制器
 * @author Arison
 *
 */
@Controller(value="IndexController")
public class IndexController {
   
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		return "me/index";
	}
	

	
	@RequestMapping(value = "/freez")
	public String freez(HttpServletRequest request) {
		return "me/freez-table-demo";
	}
	
	@RequestMapping(value = "/freez1")
	public String freez1(HttpServletRequest request) {
		return "me/freez-table-demo1";
	}
}
