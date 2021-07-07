package com.TestReport.main.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CodmstDao {
	//제품코드 조회
	public List<HashMap<String,Object>> selJEPMST(@Param("search") String search, @Param("CORCOD") String CORCOD); 
	//부자재코드 조회
	public List<HashMap<String,Object>> selMATCOD(@Param("search") String search, @Param("CORCOD") String CORCOD);	
	//법인코드 조회
	public List<HashMap<String,Object>> selCORCOD(String search); 
	//검사항목 조회
	public List<HashMap<String,Object>> selBASETRMST(@Param("search") String search, @Param("MAJOCD") String MAJOCD); 
}
