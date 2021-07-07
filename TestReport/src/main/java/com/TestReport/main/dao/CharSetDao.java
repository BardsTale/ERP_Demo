package com.TestReport.main.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface CharSetDao {
	//언어셋 조회
	public List<HashMap<String,Object>> selectCHAMST(@Param("SEARCH") String SEARCH,@Param("SCHGBN") String SCHGBN); 
	//로컬스토리지용 언어셋 조회
	public List<HashMap<String,String>> selectCHAMST_Local(@Param("CHASET") String CHASET); 
	//운영중인 언어셋 리스트 조회
	@Select("SELECT CHASET FROM CHAMST GROUP BY CHASET")
	public List<HashMap<String,String>> selectCHAMSTList(); 
	//언어셋 등록
	public int insertCHAMST(HashMap<String, String> map);
	//언어셋 수정
	public int updateCHAMST(HashMap<String, String> map); 
	//언어셋 삭제
	public int deleteCHAMST(HashMap<String, String> map); 
}
