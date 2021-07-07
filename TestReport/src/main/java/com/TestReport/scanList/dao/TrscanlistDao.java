package com.TestReport.scanList.dao;

import java.util.HashMap;
import java.util.List;



public interface TrscanlistDao {
	//주문정보 스캔본 조회
	public List<HashMap<String,Object>> selOrderList(HashMap<String, String> param); 
	//스캔본 조회
	public List<HashMap<String,Object>> selScanList(HashMap<String, String> param); 
	//계정 등록
	public int insScanList(HashMap<String, String> param); 
	//계정 수정
	public int updScanList(HashMap<String, String> param); 
	//계정 삭제
	public int delScanList(HashMap<String, String> param); 
}
