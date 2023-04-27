package com.onion.base.member;

import java.lang.reflect.Member;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.onion.base.util.MailManager;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MemberService implements UserDetailsService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MailManager mailManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception {
		return memberDAO.getLogin(memberVO);
	}
	
	public int setLastTimeUpdate(HttpSession session) throws Exception {
		MemberVO memberVO = (MemberVO)session.getAttribute("member");

		return memberDAO.setLastTimeUpdate(memberVO);
	}
	
	public int setJoin(MemberVO memberVO) throws Exception {
//		memberVO.setEnabled(true);
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.setJoin(memberVO);
		if (result == 0) {
			return result;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
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
		
		if(memberVO2 == null || !memberVO.getUsername().equals(memberVO2.getUsername())) {
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
				bindingResult.rejectValue("username", "member.username.duplicate");					
			}
			
		//4. 생년월일 유효성 검사
			if(memberVO.getBirth() == null) {
				result = true;
				bindingResult.rejectValue("birth", "member.birth.notBlank");
			}
		
		return result;
		
	}
	
	public boolean findPasswordCheck(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean result = false;
		
		MemberVO tempUser = memberDAO.getFindPassword();
		
		log.error("======== USERNAME  : {} ========", tempUser.getUsername());
		log.error("======== USEREMAIL  : {} ========", tempUser.getEmail());
		
		if(tempUser == null) {
			
			result = true;
			
			bindingResult.rejectValue("username", "member.findPassword.notExists");

		}
		
		return result;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		log.error("======================== Spring Security Login =====================");
		log.error("=============== {} =============", username);
		try {
			memberVO = memberDAO.getLogin(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberVO;
	}
	
	public int findPassword(MemberVO memberVO) throws Exception {
		

		
		String randomString = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Calendar calendar = Calendar.getInstance();
		
		Random random = new Random(calendar.getTimeInMillis());
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<6; i++) {
			int rNum =  random.nextInt(randomString.length());
			sb.append(randomString.charAt(rNum));
			
		}
		
		log.error("======== Temp Password : {} ========", sb.toString());
		
		return 0;
		
	}
	
	
}
