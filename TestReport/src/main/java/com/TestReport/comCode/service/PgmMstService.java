package com.TestReport.comCode.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;



public interface PgmMstService {
	public ResponseEntity<HashMap<String, Object>> selectPGMLST(String SCHGBN);
	public ResponseEntity<HashMap<String, Object>> selectPGMCHK(String PGMCOD);
	public ResponseEntity<HashMap<String, Object>> insertPGMLST(HashMap<String, String> param);
	public ResponseEntity<HashMap<String, Object>> updatePGMLST(HashMap<String, String> param);
	public ResponseEntity<HashMap<String, Object>> updatePGMLST_Grid(List<HashMap<String, String>> params);
	public ResponseEntity<HashMap<String, Object>> deletePGMLST(HashMap<String, String> param);
}
