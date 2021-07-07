package com.TestReport.mdtr.dao;

import java.util.HashMap;
import java.util.List;

public interface MdtrSelectDao {
	//중간검사 성적서 조회
	public List<HashMap<String,Object>> selMdtrListBase(HashMap<String, String> param); 
	public List<HashMap<String,Object>> selMdtrListHead(HashMap<String, String> param); 
	public List<HashMap<String,Object>> selMdtrListBody(HashMap<String, String> param); 
}
