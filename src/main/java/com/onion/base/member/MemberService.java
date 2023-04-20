package com.onion.base.member;

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
	
	public int setJoin(MemberVO memberVO, RoleVO roleVO) throws Exception {
		int result = memberDAO.setJoin(memberVO);
		
		if (result == 0) {
			return result;
		}
		
		
		MemberRoleVO memberRoleVO = new MemberRoleVO();
		memberRoleVO.setUserName(memberVO.getUserName());
		memberRoleVO.setNum(memberDAO.getRoleNum(roleVO).getNum());
		result = memberDAO.setMemberRoleInsert(memberRoleVO);
		
		if(result == 0) {
			throw new Exception();
		}
		
		return result;
	}
}
