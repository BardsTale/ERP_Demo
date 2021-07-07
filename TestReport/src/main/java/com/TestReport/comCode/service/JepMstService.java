package com.TestReport.comCode.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface JepMstService {
	//제품항목 조회
	public ResponseEntity<HashMap<String, Object>> selectJEPMST(String CORCOD, String JPCODE); 
	//제품항목 등록
	public ResponseEntity<HashMap<String, Object>> insertJEPMST(HashMap<String, String> map); 
	//제품 엑셀 등록
	public ResponseEntity<HashMap<String, Object>> insertJEPMST_Excel(List<HashMap<String, String>> ListMap); 
	//제품항목 수정
	public ResponseEntity<HashMap<String, Object>> updateJEPMST(HashMap<String, String> map); 
	//제품항목 삭제
	public ResponseEntity<HashMap<String, Object>> deleteJEPMST(HashMap<String, String> map); 
}
