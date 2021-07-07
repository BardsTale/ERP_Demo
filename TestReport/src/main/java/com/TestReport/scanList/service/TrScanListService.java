package com.TestReport.scanList.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;



public interface TrScanListService {
	public ResponseEntity<HashMap<String, Object>> selectTrScanOrderList(HashMap<String, String> param);
	public ResponseEntity<HashMap<String, Object>> selectTrScanList(HashMap<String, String> param);
	public ResponseEntity<HashMap<String, Object>> insertTrScanList(HashMap<String, String> param);
	public ResponseEntity<HashMap<String, Object>> updateTrScanList(HashMap<String, String> param);
	public ResponseEntity<HashMap<String, Object>> deleteTrScanList(HashMap<String, String> param);
}
