package com.duyanh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	// TRA VE TRANG INDEX
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// TRA VE TRANG ADMIN
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	// TRA VE TRANG 403
	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
	
	// TRA VE TRANG LOGIN
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
}
