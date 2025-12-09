package edu.kh.project.myPage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.service.MyPageService;
import lombok.extern.slf4j.Slf4j;

/*@SessionAttributes({"loginMember"})
 *Model에 추가된 속성 중 key값이 일치하는 속성을 sesion scope로 변경하는 어노테이션
 *클래스 상단에 @SessionAttributes({"loginMember"}) 
 * 
 * @SessionAttribute
 * @SessionAttributes를 통해 session에 등록된 속성꺼낼떄 사용하는 어노테이션
 * 메서드이 매개변수에 @SessionAttribute("loginMember") Member loginMember 작성
 */
@Controller
@RequestMapping("myPage")
@Slf4j
public class MyPageController {
	
	@Autowired
	private MyPageService service;
	
	/**내정보 조회
	 * @param loginMember 세션에 존재하는 loginMember를 얻어와 Member타입 매개변수 대입
	 * @return
	 */
	@GetMapping("info") //  /myPage/info GET 방식 요청 배핑
	public String info(@SessionAttribute("loginMember") Member loginMember, Model model) {
		
		//현재 로그인한 회원의 주소를 꺼내옴
		//현재 로그인한 회원 정보->session scope에 등록된 상태(loginMember)
		//logMember(memberAddress도 포함)
		//만약 회원가입 당시 주소를 입력했다면 주소값 문자열(^^^ 구분자로 만들어진 문자열)
		//					 입력하지 않았다면 null
		String memberAddress=loginMember.getMemberAddress();
		//03189^^^서울 종로구 우정국로2길 21^^^3층, 302클래스 (대왕빌딩)
		//or null
		
		if(memberAddress !=null) { //주소가 있을 경우에만 동작
			//구분자 ^^^ 기준으로 memberAddress 값을 쪼개 String[]로 반환
			String[] arr=memberAddress.split("\\^\\^\\^");
			//[03189, 서울 종로구 우정국로2길 21, 3층, 302클래스 (대왕빌딩)]
			model.addAttribute("postcode", arr[0]);
			model.addAttribute("address", arr[1]);
			model.addAttribute("detailAddress", arr[2]);
		}
		
		return "myPage/myPage-info";
	}
	
	//프로필 이미지 변경 화면 이동
	@GetMapping("profile") // /myPage/porfile GET 방식 요청 배핑 
	public String porfile() {
		return "myPage/myPage-profile";
	}
	
	//비밀번호 변경화면 이동
	@PostMapping("changePw")
	public String changePw(@RequestParam ("currentPw") String currentPw,
			@SessionAttribute("loginMember") Member loginMember, 
			RedirectAttributes ra) {
		
		int result=service.checkPw(currentPw, loginMember);
		
		String message=null;
		
		if(result == 0) {
			message="입력한 비번과 현재 비번이 서로 다릅니다.";
			ra.addFlashAttribute("message", message);
			return "redirect:changePw";
		
		}  
		
		
		result=service.updatePw(currentPw, loginMember);
			
			
		
		return "redirect:changePw";		
	}
	
	@GetMapping("changePw")
	public String changePw2() {
		return "myPage/myPage-changePw";		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//회원탈퇴 화면이동
	@GetMapping("secession") // /myPage/secession GET 방식 요청 배핑 
	public String secession() {
		return "myPage/myPage-secession";
	}
	
	//파일테스트 화면으로 이동
	@GetMapping("fileTest") // /myPage/fileTest GET 방식 요청 배핑 
	public String feileTest() {
		return "myPage/myPage-fileTest";
	}
	
	//파일목록조회 화면으로 이동
	@GetMapping("fileList") // /myPage/fileList GET 방식 요청 배핑 
	public String feileList() {
		return "myPage/myPage-fileList";
	}
	
	
	/**회원 정보 수정
	 * @param inputMember 커맨드객체(@ModelAttribute 가 생략)
	 * 						제출된 memberNickname, memberTel 세팅된 상태
	 * @param memberAddress 주소만 따로 배열형태로 얻어옴
	 * @param loginMember 로그인한 회원정보(혀재 로그인한 회원의 회원번호(PK) 사용할 예정)
	 * @return
	 */
	@PostMapping("info") // /myPage/info POST 방식 요청 배핑 
	public String updateInfo(Member inputMember /*수정해야될 정보*/,
			@RequestParam("memberAddress") String[] memberAddress,
			@SessionAttribute("loginMember") Member loginMember,
			RedirectAttributes ra) {
		
		//inputMember에 현재 로그인한 회원 번호 추가
		inputMember.setMemberNo(loginMember.getMemberNo());
		//inputMember 수정된 회원의 닉네임, 전화번호, [주소]와 회원번호
		
		//회원정보 수정서비스 호출
		int result=service.updateInfo(inputMember, memberAddress);
		
		String message=null;
		
		if(result > 0) {
			message="정보수정성공!!";
			
			//loginMember에 DB상 업데이트된 내용으로 세팅
			//loginMember는 세션에 저장된 로그인한 회원 정보가 저장돼있음 (로그인 할 당시 기존 데이터)
			//loginMember를 수정하면 세션에 저장된 로그인한 회원의 정보가 업데이트됨
			//Session에 있는 회원 정보와 DB 데이터 동기화
			loginMember.setMemberNickname(inputMember.getMemberNickname());
			loginMember.setMemberTel(inputMember.getMemberTel());
			loginMember.setMemberAddress(inputMember.getMemberAddress());
			
		} else {
			
			message="수정실패여,,";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:info"; //재요청경로  /myPage/info GET요청
	}
	
}
