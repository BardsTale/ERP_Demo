package com.TestReport.comCode.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface CorporationDao {
	//법인 조회
	public List<HashMap<String,Object>> selectCORPOR(@Param("SEARCH") String SEARCH,@Param("SCHGBN") String SCHGBN); 
	//법인 등록
	public int insertCORPOR(HashMap<String, String> map);
	//법인 수정
	public int updateCORPOR(HashMap<String, String> map); 
	//법인 삭제
	public int deleteCORPOR(HashMap<String, String> map); 
}
