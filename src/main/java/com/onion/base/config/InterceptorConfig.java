package com.onion.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.onion.base.interceptors.AdminCheckInterceptor;
import com.onion.base.interceptors.MemberCheckInterceptor;

// 인터셉터 설정 파일
// servlet-context.xml 파일에 등록하는 대신 사용
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private MemberCheckInterceptor memberCheckInterceptor;
	
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//인터셉터 등록시 순서도 중요하다 위에서부터 아래로
		
		registry.addInterceptor(memberCheckInterceptor)
				.addPathPatterns("/member/mypage")
				.addPathPatterns("/qna/*")
				.excludePathPatterns("/qna/list")
				.excludePathPatterns("/qna/detail")
				.addPathPatterns("/member/admin")
				.addPathPatterns("/notice/*")
				.excludePathPatterns("/notice/list")
				.excludePathPatterns("/notice/detail")
				
				;
				
		registry.addInterceptor(adminCheckInterceptor)
				.addPathPatterns("/member/admin")
				.addPathPatterns("/notice/*")
				.excludePathPatterns("/notice/list")
				.excludePathPatterns("/notice/detail")
				;
	}
	
	
	
}
