package edu.kh.myapp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin : Spring에서 제공하는 어노테이션으로 CORS 설정위해 사용됨
//CORS(Cross-Origin Resource Sharing)
//클라와 서버가 다른 출처(origin)에서 요청주고받을때 발생하는 보안정책
//브라우저에서는 기본적으로 다른 출처(도메인, 프로토콜, 포트 등) 요청차단
//->클라와 서버의 출처가 다를땐 CORS 설정 적절히 해야
//	정상적인 http 통신가능

@CrossOrigin(origins = "http://localhost:5173") // http://localhost:5173와 자원(데이터)공유
@RestController // 비동기요청 (controller는 동기식)
public class MyAppController {

	@GetMapping("getPortNumber")
	public List<String> getPortNumber() {
		return Arrays.asList("서버포트는 80", "클라포트는 5173");
	}

	@PostMapping("getUserInfo")
	public String getUserInfo(@RequestBody Map<String, Object> map) {
		// message 리턴
		// 만약 요청데이터 중 name값이 홍길동이고, age값이 20이면
		// "홍길동 님은 20세" 리턴
		// 같지 않다면 "데이터없음" 리턴
		String message = "데이터없음";

		if (map.get("name").equals("홍길동") && (int) map.get("age") == 20) {
			message = "홍길동 님은 20세";
		}
		return message;

	}
}
