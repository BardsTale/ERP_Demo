package com.TestReport.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.TestReport.configuration.interceptor.LoginIntercepter;

//셋팅된 스프링Mvc에서 추가적인 조작을 위해 WebMvcConfigurer 정의
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LoginIntercepter loginIntercepter = new LoginIntercepter();
		registry.addInterceptor(loginIntercepter).addPathPatterns(loginIntercepter.loginEssential).excludePathPatterns(loginIntercepter.loginInessential);
	}
}
