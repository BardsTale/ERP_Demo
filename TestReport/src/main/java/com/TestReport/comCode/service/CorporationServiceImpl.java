package com.TestReport.comCode.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.comCode.dao.CorporationDao;
import com.TestReport.configuration.aop.AuthorityCheck;

@Service("CorporationService")
public class CorporationServiceImpl implements CorporationService{
	@Autowired
	public CorporationDao corporationDao;

	//조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectCORPOR(String SEARCH, String SCHGBN){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;

    	dataList = corporationDao.selectCORPOR(SEARCH, SCHGBN);
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
    	return ResponseEntity.status(HttpStatus.OK).body(result_map);
	}
	
	//저장
	@Override
	public ResponseEntity<HashMap<String, Object>> insertCORPOR(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = corporationDao.insertCORPOR(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//수정
	@Override
	public ResponseEntity<HashMap<String, Object>> updateCORPOR(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = corporationDao.updateCORPOR(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//삭제
	@AuthorityCheck
	@Override
	public ResponseEntity<HashMap<String, Object>> deleteCORPOR(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = corporationDao.deleteCORPOR(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.OK;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
}
