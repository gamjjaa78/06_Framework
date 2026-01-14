package edu.kh.project.admin.model.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.admin.model.mapper.AdminMapper;
import edu.kh.project.common.util.Utility;
import edu.kh.project.member.model.dto.Member;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final AdminMapper mapper;
	private final BCryptPasswordEncoder bCrypt;

	// 관리자 로그인 서비스
	@Override
	public Member login(Member inputMember) {
		Member loginMember = mapper.login(inputMember.getMemberEmail());

		if (loginMember == null)
			return null;

		if (!bCrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw()))
			return null;

		loginMember.setMemberPw(null); // 비번전달안할거야
		return loginMember;
	}

	
	@Override
	public int checkEmail(String memberEmail) {
		// TODO Auto-generated method stub
		return mapper.checkEmail();

	@Override
	public String createAdminAccount(Member member) {
		//1. 영어(대소문자), 숫자 포함 6자리 난수로 만든 비밀번호를 평문/암호화한 값 구하기
		String rawPw=Utility.generatePassword();
		
		//2. 평문 비번 암호화하여 저장
		String encPw=bcrypt.encode(rawPw);
		
		//3.
		member.setMemberPw(encPw);
		
		//4.DB에 암호화된 비번
		
		
		return null;
	}

}
