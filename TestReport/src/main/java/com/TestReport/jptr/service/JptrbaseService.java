package com.TestReport.jptr.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import com.TestReport.jptr.domain.JPTRBS_VO;



public interface JptrbaseService {
	public ResponseEntity<HashMap<String, Object>> selectJptrVersion(String CORCOD, String JPCODE);
	public ResponseEntity<HashMap<String, Object>> selectJptrbase(String CORCOD, String JPCODE, String VSDATE);
	public ResponseEntity<HashMap<String, Object>> insertJptrbase(JPTRBS_VO JPTRBS_VO);
	public ResponseEntity<HashMap<String, Object>> updateJptrbase(JPTRBS_VO JPTRBS_VO);
	public ResponseEntity<HashMap<String, Object>> deleteJptrbase(HashMap<String, String> map);
}
