package com.TestReport.comCode.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;



public interface CorporationService {
	public ResponseEntity<HashMap<String, Object>> selectCORPOR(String SEARCH, String SCHGBN);
	public ResponseEntity<HashMap<String, Object>> insertCORPOR(HashMap<String, String> param);
	public ResponseEntity<HashMap<String, Object>> updateCORPOR(HashMap<String, String> param);
	public ResponseEntity<HashMap<String, Object>> deleteCORPOR(HashMap<String, String> param);
}
