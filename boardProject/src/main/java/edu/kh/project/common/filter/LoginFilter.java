package edu.kh.project.common.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*filter 요청, 응답시 걸러내거나 추가할 수 있는 객체
 *[필터 클래스 생성 방법]
 *1.Jakarta.servlet.Filter 인터페이스 상속받기
 *2.doFilter() 메서드 오버라이딩 
 * 
 * 
 */
//로그인이 돼있지 않은 경우 특정페이지 접근불가하도록 필터링함
public class LoginFilter implements Filter {

	//필터 동작을 정의하는 메서드
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//ServletRequest request : HttpServletRequest의 부모타임
		//ServletResponse response : HttpServletResponse의 부모타입
		
		//session 이 필요함->loginMember가 session애 담김
		//HttpServletRequest 형태(자식)로 다운캐스팅
		HttpServletRequest req=(HttpServletRequest)request;
		//HttpServletResponse 형태(자식)로 다운캐스팅
		HttpServletResponse resp=(HttpServletResponse)response;
		
		//현재 요청의 URI를 가져옴
		String path=req.getRequestURI();//  /myPage/profile
		
		//요청 URI가 "/myPage/profile"로 시작하는지 확인
		if(path.startsWith("/myPage/profile/")) {
			//필터를 통과하도록 함
			//FilterChain 다음 필터 또는 DispatcherServlet과 연결된 객체
			chain.doFilter(request, response);
			//필터 통과 후 refurn
			return;
		}
		
		//session 객체 얻어오기
		HttpSession session = req.getSession();
		
		//세션에서 로그인한 회원 정보를 꺼내옴
		//loginMember 있는지 null인지 판단
		if(session.getAttribute("loginMember")==null) {
			//로그인 안돼있음
			
			// /loginError 재요청 (redirect)
			resp.sendRedirect("/loginError");
			
		} else { //로그인 돼있음
			//다음 필터로, 또는 없다면 DispatcherServlet으로 요청, 응답전달
			chain.doFilter(request, response);
			
		}
		
		
		
		
	}
}
