package com.TestReport.common;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.TestReport.main.domain.CHASET_VO;


//캐릭터셋 싱글톤 인스턴스
//Initinalization on Demand Holder Idiom 방식
public class CharsetHandler {
	private static HashMap<String, HashMap<String, Object>> CHASET_MAP = new HashMap<>();
	private static HashMap<String, CHASET_VO> CHASET_VO_MAP = new HashMap<>();
	private CharsetHandler () {}
	private static class LazyHolder {
		private static final CharsetHandler instance = new CharsetHandler();
	}
	
	public static CharsetHandler getInstance () {
		return LazyHolder.instance;
	}
	
	public void setVO(String chaset, CHASET_VO chaset_VO) {
		CHASET_VO_MAP.put(chaset, chaset_VO);
	}
	
	public void setMap(String chaset, HashMap<String, Object> chamap) {
		CHASET_MAP.put(chaset, chamap);
	}
	
	public CHASET_VO getVO() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String chaset = null;
		
		//CHASET이 저장된 쿠키 가져오기.
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("CHASET")) {
					chaset = cookie.getValue();
				}
			}
		}
		return CHASET_VO_MAP.get(chaset==null?"korean":chaset);
	}
	
	public HashMap<String, Object> getMap() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String chaset = null;
		
		//CHASET이 저장된 쿠키 가져오기.
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("CHASET")) {
					chaset = cookie.getValue();
				}
			}
		}
		return CHASET_MAP.get(chaset==null?"korean":chaset);
	}
}
