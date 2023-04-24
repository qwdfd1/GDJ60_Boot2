package com.onion.base.member;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

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
	
	//password 검증 메서드
	public boolean memberCheck(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean result = false;
		//false : error가 없음, 검증 성공
		//true : error가 실패, 검증 실패
		
		//1. annotation 검증 결과를 받음
		result = bindingResult.hasErrors();
		
		//2. password 일치하는지 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			result = true;
			//에러메시지
			//bindingResult.rejectvalue("멤버변수명(path)", "message.property(키 값)")
			bindingResult.rejectValue("passwordCheck", "member.password.notEqual");
		}
		
		//3. ID 중복검사
		
			MemberVO checkMember = memberDAO.idDuplicateCheck(memberVO);
			if(checkMember != null) {
				result = true;
				//에러메시지
				//bindingResult.rejectvalue("멤버변수명(path)", "message.property(키 값)")
				bindingResult.rejectValue("userName", "member.userName.duplicate");					
			}
			
		//4. 생년월일 유효성 검사
			if(memberVO.getBirth() == null) {
				result = true;
				bindingResult.rejectValue("birth", "member.birth.notBlank");
			}
		
		
		
		
		return result;
		
	}
}
