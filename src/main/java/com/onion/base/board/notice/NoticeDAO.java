package com.onion.base.board.notice;

import org.apache.ibatis.annotations.Mapper;

import com.onion.base.board.BoardDAO;
import com.onion.base.board.BoardVO;

@Mapper
public interface NoticeDAO extends BoardDAO {
	public int setDelete(BoardVO boardVO) throws Exception;
	
	public int setFileListDelete(BoardVO boardVO) throws Exception;
	
	public int setBirthUserInsert() throws Exception;
}
