package com.onion.base.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO implements UserDetails, OAuth2User{
	@NotBlank
	private String username;
	@Size(min = 5)
	private String password;
	
	private String passwordCheck;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	private boolean enabled;
	
	@Past
	private Date birth;
	
	
	private List<RoleVO> roleVOs;
	
	//Oauth2User, token 정보 저장
	private Map<String, Object> attributes;
	

	//Role을 리턴
	//? extends GrantedAuthority : 무엇
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO : roleVOs) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
		return authorities;
	}

//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		username(id) 반환
//		return null;
//	}
	
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		password 반환
//		return null;
//	}

	// 계정 만료 여부
	// return true; : 만료 안됨 
	// return false; : 만료 됨, 로그인 안됨 
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정 잠김 여부
	// return true : 잠기지 않음
	// return false : 잠김, 로그인 안됨
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	// password 만료 여부
	// return true : 만료 안됨
	// return false : 만료 됨, 로그인 안됨
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	
	// 계정 사용 여부
	// return true : 계정 활성화
	// return false : 계정 비활성화, 로그인 안됨 
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}


	
	
	
	
}
