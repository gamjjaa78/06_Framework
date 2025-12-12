package edu.kh.project.common.interceptor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.kh.project.board.model.service.BoardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/* Interceptor 요청,응답,뷰 완성 후 가로채는 객체(Spring 지원)
 * HandlerInterceptor 인터페이스를 상속받아 구현
 * preHandle(전처리) DispatcherServlet->Controller 사이 수행
 * postHandle(후처리) Controller->DispatcherServlet 사이 수행
 * afterCompletion(뷰 완성 후) ViewResolver->DispatcherServlet 사이 수행
 */
@Slf4j
public class BoardTypeInterceptor implements HandlerInterceptor {
	
	@Autowired //의존성주입(DI)->BoardService 타입이거나 상속관계인 객체(Bean) 주입
	private BoardService service;
	
	//전처리:요청이 Controller로 들어오기전 실행되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//boardTpye을 DB에서 얻어오기
		//boardTypeList 형태로 가져오기
		//application scope:
		//서저 시작~종료시까지 유지되는 Servlet 내장 객체
		//서버 내 딱 한개만 존재!! ->모든 클라가 공용으로 사용
		
		//application scope 객체 얻어오기
		ServletContext application= request.getServletContext();
		
		if(application.getAttribute("boardTypeList")==null) {
		
			//boardTypeList 조회서비스호출
			List<Map<String, Object>> boardTypeList=service.selectBoardTypeList();
			
			log.debug("boardTypeList"+boardTypeList);
			
			//조회결과를 applicaion scope에 추가
			application.setAttribute("boardTypeList", boardTypeList);
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	//후처리:요청이 처리된 후, 뷰가 렌더링되기전 실행되는 메서드
	//응답가지고 DispatcherServlet 에게 돌아가기전 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}	
	
	//뷰 완성 후:뷰 렌더링이 끝난 후 실행되는 메서드
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
		throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
}