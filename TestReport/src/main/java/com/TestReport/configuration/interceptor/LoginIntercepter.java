package com.TestReport.configuration.interceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.TestReport.login.domain.ZOPR01_VO;

public class LoginIntercepter implements HandlerInterceptor {
	//인터셉터를 태울 요청리스트
    public List<String> loginEssential
    = Arrays.asList("/**");

    //인터셉터를 태우지 않을 요청리스트
    public List<String> loginInessential
    = Arrays.asList("/favicon.ico", "/error/**", "/login/**", "/logout/**", "/LANG/**", "/index/**", "/index", "/", "/vendor_js/**", "/upload/**", "/js/**", "/img/**", "/fonts/**", "/css/**", "/common_js/**");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		ZOPR01_VO ZOPR01_VO = (ZOPR01_VO)session.getAttribute("user_bean");
		if(ZOPR01_VO != null) {return true;}
		else {
			String req_URI = request.getRequestURI();
			String req_Query = request.getQueryString();
			String req_Method = request.getMethod();
			String rest_Resource = (req_Query == null)?req_URI:req_URI+"?"+req_Query;
			//로그인 갱신을 위해 리다이렉트
			try { 
				//Ajax일 경우 헤더에 x-requested-with 키에 XMLHttpRequest 값을 지니고 있음.
				if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))) {
					//AJAX 요청일 경우 string response 객체
					response.sendRedirect("/login/renewal_ajax"); 
				}else {
					//페이지 연결 요청일 경우에만 REST 요청 리소스를 기록한다.
					session.setAttribute("rest_Resource", rest_Resource);
					//페이지 연결 요청일 경우 mv 객체
					response.sendRedirect("/login/renewal"); 
				}
			}
            catch (IOException ie ) {System.out.println(ie);} //만약 리다이렉션 도중 에러가 난 경우
			return false;
		}
	}
	
}
