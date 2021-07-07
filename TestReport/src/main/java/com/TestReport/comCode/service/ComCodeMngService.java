package com.TestReport.comCode.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;



public interface ComCodeMngService {
	public ResponseEntity<HashMap<String, Object>> selectBaster(String TRKIND);
	public ResponseEntity<HashMap<String, Object>> insertBaster(HashMap<String, String> param);
	public ResponseEntity<HashMap<String, Object>> updateBaster(HashMap<String, String> param);
	public ResponseEntity<HashMap<String, Object>> deleteBaster(HashMap<String, String> param);
}
