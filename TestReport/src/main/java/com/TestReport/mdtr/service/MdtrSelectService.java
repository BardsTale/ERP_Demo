package com.TestReport.mdtr.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;



public interface MdtrSelectService {
	public ResponseEntity<HashMap<String, Object>> selectMdtrList(HashMap<String, String> param);
}
