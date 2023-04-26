package com.onion.base.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("login")
	public ModelAndView getLogin(ModelAndView mv) throws Exception {
		mv.setViewName("member/login");
		return mv;
	}
	
	@PostMapping("login")
	public ModelAndView getLogin(ModelAndView mv, MemberVO memberVO, HttpSession session) throws Exception {
		
		memberVO = memberService.getLogin(memberVO);
		
		mv.setViewName("redirect:./login");
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}
		
		return mv;
	}
	
	@GetMapping("logout")
	public ModelAndView getLogout(ModelAndView mv, HttpSession session) throws Exception {
			
		int result = memberService.setLastTimeUpdate(session);
		if(result > 0) {
			session.invalidate();
		}

		mv.setViewName("redirect:../");
		
		return mv;
	}
	
	@GetMapping("join")
	public ModelAndView setJoin(ModelAndView mv, MemberVO memberVO) throws Exception {
		mv.setViewName("member/join");
		return mv;
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(ModelAndView mv, @Valid MemberVO memberVO, BindingResult bindingResult) throws Exception {
		
		boolean check = memberService.memberCheck(memberVO, bindingResult);
		
		if(check) {
			log.warn("==============검증 실패==========");
			mv.setViewName("member/join");
			return mv;
			
		}
		
		
		int result = memberService.setJoin(memberVO);
		
		mv.setViewName("redirect:./join");
		if(result > 0) {
			mv.setViewName("redirect:./login");
		}
		
		
		return mv;
	}
	
	@GetMapping("admin")
	public void getAdmin()throws Exception {}
	
	@GetMapping("mypage")
	public void getMyPage()throws Exception {}
	
	@GetMapping("idDuplicateCheck")
	@ResponseBody
	public boolean IdDuplicateCheck(MemberVO memberVO)throws Exception {
		
		return memberService.idDuplicateCheck(memberVO);
		
	}
	

	
	
	
	
}
