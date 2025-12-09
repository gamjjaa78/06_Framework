package edu.kh.project.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String mainPage() {
		return "common/main"; //forward
	}

	//LoginFilter에서 로그인하지 않았을때 리다이렉트로 요청
	@GetMapping("loginError")
	public String loginError(RedirectAttributes ra) {
		ra.addAttribute("message", "로긴 후 이용해");
		return "redirect:/";
	}
	
}
