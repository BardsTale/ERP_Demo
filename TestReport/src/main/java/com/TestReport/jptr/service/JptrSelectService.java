package com.TestReport.jptr.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;



public interface JptrSelectService {
	public ResponseEntity<HashMap<String, Object>> selectJptrList(HashMap<String, String> param);
}
