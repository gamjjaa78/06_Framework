package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //요청,응답 제어역할명시+'bean등록'
public class mainController {
	// "/"주소로 요청시 main.html 파일로 forward
	@RequestMapping("/")
	public String mainPage() {
		
		//forward:요청위임(결과페이지 생성까지)
		//thymeleaf:Spring Boot에서 사용하는 템플릿엔진(html파일사용)
		//thymeleaf 이용시 html로 forward시 사용되는 접두,접미사 존재		
		//접두사:classpath://templates/
		//접미사:.html
		return "common/main";
		
	}
	
	
	
}
