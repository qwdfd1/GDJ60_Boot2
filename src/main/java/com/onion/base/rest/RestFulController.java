package com.onion.base.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.onion.base.board.BoardVO;
import com.onion.base.board.notice.NoticeService;
import com.onion.base.util.Pager;

import lombok.extern.slf4j.Slf4j;


//RestController 내의 모든 메서드가 @ResponseBody가 필요하다면 대신에 클래스 선언부에 @RestController 선언
@RestController
@Slf4j
public class RestFulController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/rest/detail")
	@ResponseBody
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		boardVO = noticeService.getDetail(boardVO);
		return boardVO;
	}
	
	@GetMapping("/rest/list/{page}")
	@ResponseBody
	public List<BoardVO> getList(@PathVariable Long page, Pager pager) throws Exception {
		
		log.error("===================== PAGE : {} ===============", page);
		
		List<BoardVO> ar = noticeService.getList(pager);
		
		return ar;
	}
}
