package com.TestReport.main.service;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.SortedMap;

import org.springframework.http.ResponseEntity;

public interface UploadExcelService {
	//성적서 엑셀 업로드
	public ResponseEntity<SortedMap<String, Object>> excelToTR(HashMap<String, String> param, FileInputStream inputStream);
	
	//제품 마스터 엑셀 업로드
	public ResponseEntity<SortedMap<String, Object>> excelToJEPMST(HashMap<String, String> param, FileInputStream inputStream);
	
	//부자재 마스터 엑셀 업로드
	public ResponseEntity<SortedMap<String, Object>> excelToBMTMST(HashMap<String, String> param, FileInputStream inputStream);
}
