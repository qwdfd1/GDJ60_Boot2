package com.onion.base.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onion.base.board.BoardFileVO;
import com.onion.base.board.BoardVO;
import com.onion.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {
	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@Autowired
	private NoticeService noticeService;
	
	
	@GetMapping("list")
	public ModelAndView getList(ModelAndView mv, Pager pager) throws Exception {
		
		log.info("search : {}", pager.getSearch());
		log.info("kind : {}", pager.getKind());
		
		List<BoardVO> ar = noticeService.getList(pager);
		
		mv.addObject("list", ar);
		mv.setViewName("board/list");
		
		return mv;
	}

	
	@GetMapping("detail")
	public ModelAndView getDetail(ModelAndView mv, BoardVO boardVO) throws Exception {
		mv.addObject("boardVO", noticeService.getDetail(boardVO));
		mv.setViewName("board/detail");
		
		return mv;
	}
	
	@GetMapping("add")
	public ModelAndView setInsert() throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("board/add");
		
		return mv;
	}
	
	@PostMapping("add")
	public ModelAndView setInsert(BoardVO boardVO, MultipartFile [] boardFiles) throws Exception {
		
		for (MultipartFile multipartFile : boardFiles) {
			log.info("OrinalName : {} Size: {}", multipartFile.getOriginalFilename(), multipartFile.getSize());
		}
		
		ModelAndView mv = new ModelAndView();
		
		int result = noticeService.setInsert(boardVO, boardFiles);
		
		mv.setViewName("redirect:./list");
		
		return mv;
	}
	
	@GetMapping("fileDown")
	public ModelAndView getFileDown(BoardFileVO boardFileVO) throws Exception {
		boardFileVO = noticeService.getFileDetail(boardFileVO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardFileVO", boardFileVO);
		mv.setViewName("fileManager");
		return mv;
		
		
	}
	
}
