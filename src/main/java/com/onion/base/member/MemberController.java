package com.onion.base.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
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
	
		session.invalidate();
		
		mv.setViewName("redirect:../");
		
		return mv;
	}
	
	@GetMapping("join")
	public ModelAndView setJoin(ModelAndView mv) throws Exception {
		mv.setViewName("member/join");
		return mv;
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(ModelAndView mv, MemberVO memberVO, RoleVO roleVO) throws Exception {
		
		
		int result = memberService.setJoin(memberVO, roleVO);
		
		mv.setViewName("redirect:./join");
		if(result > 0) {
			mv.setViewName("redirect:./login");
		}
		
		
		return mv;
	}
	
	
}
