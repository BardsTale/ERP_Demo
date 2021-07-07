package com.TestReport.mdtr.dao;

import java.util.HashMap;
import java.util.List;

public interface MdtrTotalDao {
	//중간검사 성적서 조회
	public List<HashMap<String,Object>> selMdtrTotal(HashMap<String, String> param); 
}
