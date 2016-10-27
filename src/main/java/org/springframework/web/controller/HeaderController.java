package org.springframework.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value="HeaderController")
public class HeaderController {
	  
		
		@RequestMapping(value = "/header/show")
		public String index(HttpServletRequest request) {
			return "me/headers";
		}
		
		@RequestMapping(value = "/header/show_html")
		public String indexHtml(HttpServletRequest request) {
			return "html/header";
		}
}
