package com.TestReport.mdtr.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;



public interface MdtrMngService {
	public ResponseEntity<HashMap<String, Object>> selectMdtrMng(String params, String CORCOD) throws Exception;
	public ResponseEntity<HashMap<String, Object>> selectMdtrMngValue(String params, String CORCOD) throws Exception;
	public ResponseEntity<HashMap<String, Object>> insertMdtrMng(HashMap<String, Object> param);
	public ResponseEntity<HashMap<String, Object>> updateMdtrMng(HashMap<String, Object> param);
	public ResponseEntity<HashMap<String, Object>> deleteMdtrMng(HashMap<String, String> param);
}
