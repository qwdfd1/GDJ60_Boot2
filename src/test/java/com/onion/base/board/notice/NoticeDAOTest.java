package com.onion.base.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.onion.base.board.BoardVO;

@SpringBootTest
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;
	
//	@Test
	void setInsert() throws Exception {
		BoardVO boardVO = new NoticeVO();
		int result = 0;
		for(int i=0; i<120; i++) {
			boardVO.setWriter("qwdfd"+i);
			boardVO.setTitle("Insert Test"+i);
			boardVO.setContents("Contents Test"+i);
			
			result = noticeDAO.setInsert(boardVO);
			
			if(i%10 == 0) {
				Thread.sleep(500);
			}
			
			if(result == 0) {
				break;
			}
		}
		

		
		assertEquals(1, result);
		
	}
	
//	@Test
//	void setUpdate() throws Exception {
//		BoardVO boardVO = new NoticeVO();
//		
//		boardVO.setNum(1L);
//		boardVO.setTitle("Update Test1");
//		boardVO.setContents("Update Test1");
//		
//		int result = noticeDAO.setUpdate(boardVO);
//		
//		assertEquals(1, result);
//	}
	
//	@Test
//	void setDelete() throws Exception {
//		BoardVO boardVO = new NoticeVO();
//		
//		boardVO.setNum(7L);
//		
//		int result = noticeDAO.setDelete(boardVO);
//		
//		assertEquals(1, result);
//	}
	
	

}
