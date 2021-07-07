package com.TestReport.jptr.dao;

import java.util.HashMap;
import java.util.List;


public interface JptrSelectDao {
	public List<HashMap<String,Object>> selJptrListBase(HashMap<String, String> param); 
	public List<HashMap<String,Object>> selJptrListHead(HashMap<String, String> param); 
	public List<HashMap<String,Object>> selJptrListBody(HashMap<String, String> param); 
}
