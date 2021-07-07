package com.TestReport.matr.dao;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;


public interface MatrMngDao {
	//부자재검사 기준치 조회
	public List<HashMap<String,Object>> selMatrAdminLeft(HashMap<String, String> param); 
	//부자재검사 헤드 탑 조회
	public List<TreeMap<String,Object>> selMatrAdminHeadTop(HashMap<String, String> param); 
	//부자재검사 헤드 바텀 조회
	public List<TreeMap<String,Object>> selMatrAdminHeadBottom(HashMap<String, String> param); 
	//부자재검사 바디 조회
	public List<TreeMap<String,Object>> selMatrAdminBody(HashMap<String, String> param); 
	//부자재검사 기간별 통계값 조회
	public List<TreeMap<String,Object>> selMatrPeriodVal(HashMap<String, String> param); 
	//부자재검사 기준치값 조회
	public List<HashMap<String,Object>> selMatrItemVal(HashMap<String, String> param); 
	//부자재검사 헤드 등록
	public int insMatrAdminHead(HashMap<String, String> param); 
	//부자재검사 헤드 수정
	public int updMatrAdminHead(HashMap<String, String> param); 
	//부자재검사 헤드 합부여부 수정
	public int updPassMatrAdminHead(HashMap<String, String> param); 
	//부자재검사 바디 등록
	public int insMatrAdminBody(HashMap<String, String> param); 
	//부자재검사 바디 수정
	public int updMatrAdminBody(HashMap<String, String> param); 
	//검사항목 헤드 삭제
	public int delMatrAdminHead(HashMap<String, String> param); 
	//검사항목 바디 삭제
	public int delMatrAdminBody(HashMap<String, String> param); 
}
