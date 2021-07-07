package com.TestReport.comCode.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;



public interface CharSetService {
	public ResponseEntity<HashMap<String, Object>> selectChaMst(String SEARCH, String SCHGBN);
	public ResponseEntity<HashMap<String, Object>> insertChaMst(HashMap<String, String> param);
	public ResponseEntity<HashMap<String, Object>> updateChaMst(HashMap<String, String> param);
	public ResponseEntity<HashMap<String, Object>> deleteChaMst(HashMap<String, String> param);
}
