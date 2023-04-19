package com.onion.base.board;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardVO {
	private Long num;
	private String writer;
	private String title;
	private String contents;
	private Date regDate;
	private Long hit;
	private List<BoardFileVO> boardFileVOs;
	
}
