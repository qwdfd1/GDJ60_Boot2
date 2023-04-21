package com.onion.base.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception {
		return memberDAO.getLogin(memberVO);
	}
	
	public int setJoin(MemberVO memberVO) throws Exception {
		memberVO.setEnabled(true);
		int result = memberDAO.setJoin(memberVO);
		
		if (result == 0) {
			return result;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("userName", memberVO.getUserName());
		map.put("num", 3);
		
		result = memberDAO.setMemberRole(map);
		
		if(result == 0) {
			throw new Exception();
		}
		
		return result;
	}
	
	public boolean idDuplicateCheck(MemberVO memberVO) throws Exception {
		MemberVO memberVO2 = memberDAO.idDuplicateCheck(memberVO);
		
		boolean check = true;
		
		if(memberVO2 == null || !memberVO.getUserName().equals(memberVO2.getUserName())) {
			check = false;
		}
		
		return check;
	}
}
