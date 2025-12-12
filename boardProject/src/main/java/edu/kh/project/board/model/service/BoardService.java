package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	/**게시판종류 조회서비스
	 * @return
	 */
	List<Map<String, Object>> selectBoardTypeList();

}
