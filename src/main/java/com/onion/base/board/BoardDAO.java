package com.onion.base.board;

import java.util.List;

import com.onion.base.util.Pager;

public interface BoardDAO {
	
	//글 갯수
	public Long getTotalCount(Pager pager) throws Exception;
	
	//글 상세 조회
	public BoardVO getDetail(BoardVO boardVO) throws Exception;
	
	//글 목록 조회
	public List<BoardVO> getList(Pager pager) throws Exception;
	
	//글 쓰기
	public int setInsert(BoardVO boardVO) throws Exception;
	
	//글 수정
	public int setUpdate(BoardVO boardVO) throws Exception;
	
	//글 삭제
	public int setDelete(BoardVO boardVO) throws Exception;
	
	//File 추가
	public int setFileInsert(BoardFileVO boardFileVO) throws Exception;
	
	//File 조회
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception;
	

	
}
