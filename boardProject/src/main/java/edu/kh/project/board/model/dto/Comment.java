package edu.kh.project.board.model.dto;

public class Comment {
	private int commentNo;
	private String commenContent;
	private String commentWriteDate;
	private String commentDelFl;
	private int boardNo;
	private int memberNo;
	private int parentCommentNo;
	
	//댓글 조회시 MEMBER 테이블과 JOIN해서 가져올 데이터 담을 필드
	//회원 프로필
	private String profileImg;
	private String memberNickname;
	
}
