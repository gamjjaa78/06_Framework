package edu.kh.project.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.project.admin.model.service.AdminService;
import edu.kh.project.member.model.dto.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController // 비동기
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("admin")
@RequiredArgsConstructor
@SessionAttributes({ "loginMember" })
public class AdminController {

	private final AdminService service;

	@PostMapping("login")
	public Member login(@RequestBody Member inputMember, Model model) {
		Member loginMember = service.login(inputMember);
		if (loginMember == null)
			return null;

		model.addAttribute("loginMember", loginMember);
		return loginMember;
	}

	@GetMapping("logout")
	public ResponseEntity<String> logout(HttpSession session) {
		//ResponseEntity
		//Spring에서 제공하는 Http 응답 데이터를 커스터마이징 할 수 있또록 지원하는 클래스
		//Http 상태코드, 헤더, 응답 본문(body)을 모두 설정 가능
		
		try {
			session.invalidate(); //세션 무효화 처리
			return ResponseEntity.status(HttpStatus.OK)
					.body("로그아웃 완료");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //500
					.body("로그아웃 중 예외발생"+e.getMessage());
		}
	}
	
	/**관리자 계정 발급
	 * @return
	 */
	@PostMapping("createAdminAccount")
	public ResponseEntity<String> createAdminAccount(@RequestBody Member member) {
		try {
			//1. 이메일 중복검사
			int checkEmail=service.checkEmail(member.getMemberEmail());
			
			//2. 중복시 발급안함
			if(checkEmail>0) {
				//HttpStatus.CONFLICT 요청이 서버의 현재 상태와 충돌시 사용
				//==이미 존재하는 리소스(email) 때문에 새 리소스 못 만듦
				return ResponseEntity.status(HttpStatus.CONFLICT) //409
						.body("이미 사용중인 이메일");
			}
			
			//3. 없음 새로 발급->비번 반환
			String accountPw=service.createAdminAccount(member);
			
			//HttpStatus.OK (200) 요청이 정상적으로 처리됐으나 기존 리소스에 대한 단순 처리
			//HttpStatus.CREATED(201) 자원이 성공적으로 생성됐음을 나타냄
			return ResponseEntity.status(HttpStatus.CREATED).body(accountPw);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //500
					.body("관리자 계정 생성 중 문제발생, 서버문의요청");
		}
	}
	
	
}
