package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller // 요청/응답 제어하는 역할 명시+ Bean 등록
@RequestMapping("param") // /param으로 시작하는 요청을 현재 컨트롤러로 매핑
@Slf4j //log를 이용한 메시지 콘솔창에 출력할때 사용(lombok 제공)
public class parameterController {
	@GetMapping("main")
	public String paramMain() { // /param/main  Get 방식 요청 매핑
		// src/main/resources/template/param/param-main.html로 forward
		//접두사 classpath"/templates/
		//접미사 .html
		return "param/param-main"; //접두,접미사 뺀 나머지
	}
	
	//HttpServletRequest
	//요청 클라이언트의 정보, 제출된 파라미터 등을 저장한 객채
	//클라이언트 요청시 상성
	
	//Spring 의 Controller 단 메서드 작성시
	//매개변수에 원하는 객체 작성시
	//존재하는 객체를 바인딩 또는 없으면 생성해서 바인딩
	//->ArgumentResolver (전달 인자 해결사)
	
	@PostMapping("test1") // /param/test POST 방식 요청 매핑
	public String paramTest(HttpServletRequest req) {
		String inputName=req.getParameter("inputName");
		String inputAddress=req.getParameter("inputAddress");
		int inputAge=Integer.parseInt(req.getParameter("inputAge"));
		
		log.debug("inputName : "+ inputName);
		log.debug("inputAddress : "+ inputAddress);
		log.debug("inputAge : "+ inputAge);
		
		//Spring에서 Redirect(재요청) 하는 방법!
		//Controller 메서드 반환값에
		//"redirect:재요청주소"; 작성
		
		return "redirect:/param/main"; 
	}
	
}
