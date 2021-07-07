package com.TestReport.main.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface MainService {
	public HashMap<String, Object> getMainData();
	public HashMap<String, Object> getCharSet(String CHASET);
	public List<HashMap<String, String>> getCharSetList();
	public HashMap<String, Object> getMenus(String CHASET);
	public ResponseEntity<HashMap<String, Object>> selectCODMST(String proc_kind, String search, String MAJOCD, String CORCOD);
}
