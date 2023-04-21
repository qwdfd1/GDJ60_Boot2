package com.onion.base.interceptors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.onion.base.member.MemberVO;
import com.onion.base.member.RoleVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		if(memberVO != null) {
			for (RoleVO roleVO : memberVO.getRoleVOs()) {
				if(roleVO.getNum() == 1) {
					return true;
				}
			}
		}
		
		
			request.setAttribute("msg", "권한이 없습니다");
			request.setAttribute("url", "../../../");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
			
			view.forward(request, response);
			return false;
		
		

	}
	
	
}
