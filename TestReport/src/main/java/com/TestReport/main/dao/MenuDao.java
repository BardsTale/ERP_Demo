package com.TestReport.main.dao;

import java.util.HashMap;
import java.util.List;

public interface MenuDao {
	//메뉴 헤드 조회
	public List<HashMap<String,Object>> getMenuHead(String CHASET); 
	//메뉴 바디 조회
	public List<HashMap<String,Object>> getMenuBody(String CHASET); 
}
