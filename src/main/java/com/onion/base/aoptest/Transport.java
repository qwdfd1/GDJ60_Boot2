package com.onion.base.aoptest;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.onion.base.board.BoardFileVO;
import com.onion.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Transport {
	
	public int useSubway(BoardFileVO boardFileVO) throws Exception {
//		Random random = new Random();
//		int num = random.nextInt(2);
//		if(num==0) {
//			throw new Exception();
//		}
		log.error("지하철 이용");
		
		return 5;
		
	}
	
	public String useBus(Pager pager) {
		log.error("버스 이용");
		return "버스";
	}
	
	public void takeWalk() {
		log.error("걸어가기");
	}
}
