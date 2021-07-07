package com.TestReport.comCode.dao;

import java.util.HashMap;
import java.util.List;


public interface ComcodeDao {
	//검사항목 조회
	public List<HashMap<String,Object>> selBASETR(String TRKIND); 
	//검사항목 등록
	public void insBASETR(HashMap<String, String> map); 
	//검사항목 수정
	public int updBASETR(HashMap<String, String> map); 
	//검사항목 삭제
	public int delBASETR(HashMap<String, String> map); 
}
