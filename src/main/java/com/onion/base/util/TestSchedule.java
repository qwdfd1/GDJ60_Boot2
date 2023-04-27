package com.onion.base.util;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.onion.base.board.BoardVO;
import com.onion.base.board.notice.NoticeDAO;
import com.onion.base.member.MemberDAO;
import com.onion.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private MailManager mailManager; 
	
	
	private int idx;

	@Scheduled(cron = "10 2 * * * *")
	public void test() throws Exception {
		
//		List<MemberVO> memberVOs = memberDAO.getMemberList();
//			
//		log.error("MemberName : {}", memberVOs.get(idx).getUserName());		
//		log.error("======================== 반복 중 =====================");
//		
//		idx++;
//		if(memberVOs.size() == idx) {
//			idx = 0;
//		}
		
		int result = memberDAO.setEnabledUpdate();
		log.error("실행 결과  : {}", result);	
		
		
	}
	
//	@Scheduled(cron = "10 * * * * *")
	public void BirthUser() throws Exception {
		
		//생일자리스트 정보
		List<MemberVO> list = memberDAO.getBirth();
		
		
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i<list.size(); i++) {
			
			//생일축하글 내용 삽입
			sb.append(list.get(i).getName());
			if(list.size()-1 != i) {
				sb.append("님, ");
			}
			
			//메일 발송
			mailManager.send(list.get(i).getEmail(), "생일 축하", "<h1>생일 축하 드립니다!" + list.get(i).getName() + "님!");
			
		}
		
		// 생일 축하글 작성
		sb.append("님의 생일을 축하합니다");
		log.error("Contents  : {}", sb);
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("생축");
		boardVO.setWriter("qwdfd1");
		boardVO.setContents(sb.toString());
		
		int result = noticeDAO.setInsert(boardVO);
		log.error("실행 결과  : {}", result);
		


		
		 
//		int result = noticeDAO.setBirthUserInsert();
	}
	
}
