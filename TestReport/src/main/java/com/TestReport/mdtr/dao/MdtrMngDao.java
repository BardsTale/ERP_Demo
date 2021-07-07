package com.TestReport.mdtr.dao;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public interface MdtrMngDao {
	//중간검사 기준치 조회
	public List<HashMap<String,Object>> selMdtrAdminLeft(HashMap<String, String> param); 
	//중간검사 헤드 탑 조회
	public List<TreeMap<String,Object>> selMdtrAdminHeadTop(HashMap<String, String> param); 
	//중간검사 헤드 바텀 조회
	public List<TreeMap<String,Object>> selMdtrAdminHeadBottom(HashMap<String, String> param); 
	//중간검사 바디 조회
	public List<TreeMap<String,Object>> selMdtrAdminBody(HashMap<String, String> param); 
	//중간검사 기간별 통계값 조회
	public List<TreeMap<String,Object>> selMdtrPeriodVal(HashMap<String, String> param); 
	//중간검사 기준치값 조회
	public List<HashMap<String,Object>> selMdtrItemVal(HashMap<String, String> param); 
	//중간검사 헤드 등록
	public int insMdtrAdminHead(HashMap<String, String> param);  
	//중간검사 헤드 수정
	public int updMdtrAdminHead(HashMap<String, String> param); 
	//중간검사 헤드 합부여부 수정
	public int updPassMdtrAdminHead(HashMap<String, String> param); 
	//중간검사 바디 등록
	public int insMdtrAdminBody(HashMap<String, String> param); 
	//중간검사 바디 수정
	public int updMdtrAdminBody(HashMap<String, String> param); 
	//검사항목 헤드 삭제
	public int delMdtrAdminHead(HashMap<String, String> param); 
	//검사항목 바디 삭제
	public int delMdtrAdminBody(HashMap<String, String> param); 
}
