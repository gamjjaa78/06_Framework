package edu.kh.project.myPage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.mapper.MyPageMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageMapper mapper;

	@Autowired // 의존성 주입(DI)
	private BCryptPasswordEncoder bcrypt;
	
	
	
	
	//회원 정보 수정
	@Override
	public int updateInfo(Member inputMember, String[] memberAddress) {
		
		//입력된 주소가 있을 경우
		//a^^^b^^^c 형태로 가공
		
		//주소가 입려됐을때
		if(!inputMember.getMemberAddress().equals(",,")) {
		String address=String.join("^^^", memberAddress);
		inputMember.setMemberAddress(address);
		
		} else { //주소 미입력
			inputMember.setMemberAddress(null);
			
		}
		
		//inputMember 수정닉넴,수정전화번호,수정주소, 회원번호
		
		return mapper.updateInfo(inputMember);
	}

	@Override
	public int checkPw(String currentPw, Member loginMember) {
		
		String pw=mapper.checkPw(loginMember.getMemberNo());
		
		//같으면 false
		if(!bcrypt.matches(currentPw, pw)) { //비번이 서로 틀릴때
			return 0;
		} 
		
		return 1;
	}
	
	@Override
	public int updatePw(String currentPw, Member loginMember) {
		String encPw=bcrypt.encode(currentPw);
		loginMember.setMemberPw(encPw);
		return mapper.updatePw(loginMember);
	}
}

