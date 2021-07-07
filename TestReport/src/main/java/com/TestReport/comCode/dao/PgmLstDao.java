package com.TestReport.comCode.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface PgmLstDao {
	//프로그램 리스트 조회
	public List<HashMap<String,Object>> selectPGMLST(@Param("SCHGBN") String SCHGBN); 
	//프로그램 기능 조회
	public List<HashMap<String,Object>> selectPGMCHK(@Param("PGMCOD") String PGMCOD); 
	//프로그램 리스트 등록
	public int insertPGMLST(HashMap<String, String> map);
	//프로그램 리스트 수정
	public int updatePGMLST(HashMap<String, String> map); 
	//프로그램 리스트 삭제
	public int deletePGMLST(HashMap<String, String> map); 
}
