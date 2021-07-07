package com.TestReport.comCode.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.comCode.dao.JepMstDao;

@Service("InsJepMstService")
public class JepMstServiceImpl implements JepMstService {
	
	@Autowired
	public JepMstDao jepmstDao;

	//조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectJEPMST(String CORCOD, String JPCODE) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;

    	dataList = jepmstDao.selJEPMST(CORCOD, JPCODE);
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
    	return ResponseEntity.status(HttpStatus.OK).body(result_map);
	}

	//저장
	@Override
	public ResponseEntity<HashMap<String, Object>> insertJEPMST(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = jepmstDao.insJEPMST(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
		
	}
	
	//엑셀 저장
	@Override
	public ResponseEntity<HashMap<String, Object>> insertJEPMST_Excel(List<HashMap<String, String>> ListMap) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		int result_value = 0;
		
		//헤드 등록
		for(HashMap<String, String> param : ListMap) {
			result_value += jepmstDao.insJEPMST(param);
		}
		if(result_value == ListMap.size()) {
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
		
	}

	//수정
	@Override
	public ResponseEntity<HashMap<String, Object>> updateJEPMST(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = jepmstDao.updJEPMST(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}

	//삭제
	@Override
	public ResponseEntity<HashMap<String, Object>> deleteJEPMST(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		if(jepmstDao.chkUsedJEP(param) > 0) {
			msg = "성적서에 사용중인 제품은 삭제하실 수 없습니다.";
		}else {
			int result_value = jepmstDao.delJEPMST(param);
			if(result_value > 0) {
				msg = "처리되었습니다.";
				status = HttpStatus.OK;
			}
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}

	
	
}
