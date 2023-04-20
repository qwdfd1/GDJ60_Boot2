package com.onion.base.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.onion.base.board.BoardDAO;
import com.onion.base.board.BoardVO;


@Mapper
public interface QnaDAO extends BoardDAO {
	public int setRefInsert(BoardVO boardVO) throws Exception;
}
