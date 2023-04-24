package com.onion.base.member;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	@NotBlank
	private String userName;
	@Size(min = 6)
	private String password;
	
	private String passwordCheck;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	
	@Past
	private Date birth;
	
	private boolean enabled;
	
	private List<RoleVO> roleVOs;
}
