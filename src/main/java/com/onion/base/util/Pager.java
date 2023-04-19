package com.onion.base.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pager {
	
	private String search;
	
	private String kind;
	
	//page에 번호 담을 변수
	private Long page;
	
	//한 페이지에 보여줄 글의 갯수
	private Long perPage;
	
	//시작 index 번호
	private Long startRow;
	
	//1개의 블럭의 시작 페이지 번호
	private Long startNum;
	
	//1개의 블럭의 마지막 페이지 번호
	private Long lastNum;
	
	//한 블럭에 보여줄 페이지 갯수
	private Long perBlock;
	
	//전체 페이지 갯수
	private Long totalPage;
	
	// 첫페이지가기 위한 before
	private boolean before;
	
	// 끝페이지가기 위한 after
	private boolean after;

	
	
	
	
	
	
	
	
	//시작 index 번호를 계산하는 메서드
	public void makeStartRow() {
		//page = 1, startRow = 0;
		//page = 2, startRow = 10;
		this.startRow = (this.getPage()-1)*this.getPerPage();
		
	}
	
	public void makeNum(Long totalCount) {
		// 1. 전체 row의 갯수 구하기
		
		// 2. 총 page의 갯수 구하기
		this.totalPage = totalCount / this.getPerPage();
		if(totalCount % this.getPerPage() != 0) {
			this.totalPage++;
		}
		
		// 3. 총 페이지가 넘어갔을 때를 처리
		if(this.getPage() > this.getTotalPage()) {
			this.setPage(totalPage);
		}
		// 4. 총 블럭의 수 구하기
		Long totalBlock = this.getTotalPage() / this.getPerBlock();
		if(this.getTotalPage() % this.getPerBlock() != 0) {
			totalBlock++;
		}
		// 5. page 번호로 현재 블럭 번호 구하기
		Long curBlock = this.getPage()/this.getPerBlock();
		if(this.getPage() % this.getPerBlock() != 0) {
			curBlock++;
		}
		
		// 6. curBlock의 시작번호와 끝번호를 계산
		this.startNum = (curBlock-1)*this.getPerBlock()+1;
		this.lastNum = curBlock*this.getPerBlock();
		
		// 7. 현재블럭 번호가 마지막 블럭 이라면 끝번호는 전체 페이지 번호와 같음
		if(curBlock == totalBlock) {
			this.after = true;
			lastNum = totalPage;
		}
		
		// 현재 블럭 번호가 첫번째 블럭이라면 첫 페이지 아래로 갈 수 없음
		if(curBlock == 1) {
			this.before = true;
		}
		
	}
	
	public Long getPage() {
		if(this.page == null || this.page == 0) {
			this.page = 1L;
		}
		return this.page;
	}
	
	public Long getPerPage() {
		if(this.perPage == null || this.perPage == 0) {
			this.perPage = 10L;
		}
		return this.perPage;
	}
	
	public String getSearch() {
		if(this.search == null) {
			this.search = "";
		}
		return this.search;
	}
	
	public Long getPerBlock() {
		if(this.perBlock == null || this.perBlock == 0) {
			this.perBlock = 5L;
		}
		return this.perBlock;
	}
	
	
	
	
}
