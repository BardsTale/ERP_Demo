package com.TestReport.comCode.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.comCode.dao.PgmLstDao;
import com.TestReport.configuration.aop.AuthorityCheck;

@Service("PgmMstService")
public class PgmMstServiceImpl implements PgmMstService{
	@Autowired
	public PgmLstDao pgmLstDao;

	//조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectPGMLST(String SCHGBN){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;

    	dataList = pgmLstDao.selectPGMLST(SCHGBN);
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
    	return ResponseEntity.status(HttpStatus.OK).body(result_map);
	}
	
	//조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectPGMCHK(String PGMCOD){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		List<HashMap<String, Object>> dataList = null;
		
		dataList = pgmLstDao.selectPGMCHK(PGMCOD);
		String msg = "처리되었습니다.";
		result_map.put("dataList", dataList);
		result_map.put("msg",msg);
		return ResponseEntity.status(HttpStatus.OK).body(result_map);
	}
	
	//저장
	@Override
	public ResponseEntity<HashMap<String, Object>> insertPGMLST(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = pgmLstDao.insertPGMLST(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//수정
	@Override
	public ResponseEntity<HashMap<String, Object>> updatePGMLST(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = pgmLstDao.updatePGMLST(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//수정_그리드
	@Override
	public ResponseEntity<HashMap<String, Object>> updatePGMLST_Grid(List<HashMap<String, String>> ListMap) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		int result_value = 0;
		
		for(HashMap<String, String> param : ListMap) {
			result_value += pgmLstDao.updatePGMLST(param);
		}
		if(result_value == ListMap.size()) {
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//삭제
	@AuthorityCheck
	@Override
	public ResponseEntity<HashMap<String, Object>> deletePGMLST(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = pgmLstDao.deletePGMLST(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.OK;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
}
