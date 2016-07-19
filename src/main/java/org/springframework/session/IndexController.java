package org.springframework.session;

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
	
}
