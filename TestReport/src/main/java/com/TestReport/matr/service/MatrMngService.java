package com.TestReport.matr.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;



public interface MatrMngService {
	public ResponseEntity<HashMap<String, Object>> selectMatrMng(String params, String CORCOD)  throws Exception;
	public ResponseEntity<HashMap<String, Object>> selectMatrMngValue(String params, String CORCOD) throws Exception;
	public ResponseEntity<HashMap<String, Object>> insertMatrMng(HashMap<String, Object> param);
	public ResponseEntity<HashMap<String, Object>> updateMatrMng(HashMap<String, Object> param);
	public ResponseEntity<HashMap<String, Object>> deleteMatrMng(HashMap<String, String> param);
}
