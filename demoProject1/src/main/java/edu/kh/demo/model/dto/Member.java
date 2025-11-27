package edu.kh.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter,setter,toSting
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든필드 초기화용 매개변수생성자
public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private int memberAge;
}
