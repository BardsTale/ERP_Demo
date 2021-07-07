package com.TestReport.comCode.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface BmtMstService {
	//부자재항목 조회
	public ResponseEntity<HashMap<String, Object>> selectBMTMST(String CORCOD, String MATCOD); 
	//부자재항목 등록
	public ResponseEntity<HashMap<String, Object>> insertBMTMST(HashMap<String, String> map);
	//부자재항목 엑셀 등록
	public ResponseEntity<HashMap<String, Object>> insertBMTMST_Excel(List<HashMap<String, String>> ListMap);
	//부자재항목 수정
	public ResponseEntity<HashMap<String, Object>> updateBMTMST(HashMap<String, String> map); 
	//부자재항목 삭제
	public ResponseEntity<HashMap<String, Object>> deleteBMTMST(HashMap<String, String> map); 
}
