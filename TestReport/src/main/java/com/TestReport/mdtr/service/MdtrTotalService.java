package com.TestReport.mdtr.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;



public interface MdtrTotalService {
	public ResponseEntity<HashMap<String, Object>> selectMdtrTotal(HashMap<String, String> param);
}
