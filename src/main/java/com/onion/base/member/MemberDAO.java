package com.onion.base.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception;
	
	public int setJoin(MemberVO memberVO) throws Exception;
	
	public RoleVO getRoleNum(RoleVO roleVO) throws Exception;
	
	public int setMemberRoleInsert(MemberRoleVO memberRoleVO)throws Exception;
}
