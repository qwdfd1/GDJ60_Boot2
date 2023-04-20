package com.onion.base.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.onion.base.aoptest.Card;
import com.onion.base.aoptest.Transport;
import com.onion.base.board.BoardFileVO;
import com.onion.base.util.Pager;

@Controller
public class HomeController {
	
	@Autowired
	private Transport transport;
	
	@Autowired
	private Card card;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/use")
	public void use() throws Exception{
		Pager pager = new Pager();
		pager.setKind("Bus Title");
		transport.useBus(pager);
		
		
		BoardFileVO boardFileVO = new BoardFileVO();
		boardFileVO.setFileName("Subway File");
		transport.useSubway(boardFileVO);

		transport.takeWalk();
	
	}
	
}
