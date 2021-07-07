package com.TestReport.comCode.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface JepMstDao {
	//제품 조회
	public List<HashMap<String,Object>> selJEPMST(@Param("CORCOD") String CORCOD,@Param("JPCODE") String JPCODE); 
	//제품 등록
	public int insJEPMST(HashMap<String, String> map);
	//제품 삭제 전 기준치 설정에 사용여부 확인
	public int chkUsedJEP(HashMap<String, String> map);
	//제품 수정
	public int updJEPMST(HashMap<String, String> map); 
	//제품 삭제
	public int delJEPMST(HashMap<String, String> map); 
}
