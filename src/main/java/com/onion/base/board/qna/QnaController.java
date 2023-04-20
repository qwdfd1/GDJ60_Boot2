package com.onion.base.board.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onion.base.board.BoardVO;
import com.onion.base.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	@GetMapping("list")
	public ModelAndView getList(ModelAndView mv, Pager pager)throws Exception {
		mv.setViewName("board/list");
		mv.addObject("list", qnaService.getList(pager));
		
		return mv;
	}
	
	@GetMapping("add")
	public ModelAndView setInsert(ModelAndView mv) throws Exception {	
		mv.setViewName("board/add");
		return mv;
	}
	
	@PostMapping("add")
	public ModelAndView setInsert(ModelAndView mv, BoardVO boardVO, MultipartFile [] boardFiles) throws Exception {
		
		int result = qnaService.setInsert(boardVO, boardFiles);
		
		mv.setViewName("redirect:./list");
		return mv;
	}
	
}
