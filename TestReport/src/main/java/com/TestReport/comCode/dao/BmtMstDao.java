package com.TestReport.comCode.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface BmtMstDao {
	//부자재 조회
	public List<HashMap<String,Object>> selBMTMST(@Param("CORCOD") String CORCOD,@Param("MATCOD") String MATCOD); 
	//부자재 등록
	public int insBMTMST(HashMap<String, String> map);
	//부자재 삭제 전 기준치 설정에 사용여부 확인
	public int chkUsedMAT(HashMap<String, String> map);
	//부자재 수정
	public int updBMTMST(HashMap<String, String> map); 
	//부자재 삭제
	public int delBMTMST(HashMap<String, String> map); 
}
