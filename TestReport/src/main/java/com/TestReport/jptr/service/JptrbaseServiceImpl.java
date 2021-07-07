package com.TestReport.jptr.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.configuration.exception.BaseSeqException;
import com.TestReport.jptr.dao.JptrbaseDao;
import com.TestReport.jptr.domain.JPTRBS_VO;

@Service("JptrbaseService")
public class JptrbaseServiceImpl implements JptrbaseService{
	@Autowired
	public JptrbaseDao jptrbaseDao;

	@Override
	public ResponseEntity<HashMap<String, Object>> selectJptrVersion(String CORCOD, String JPCODE) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;

    	dataList = jptrbaseDao.selJptrVersion(CORCOD, JPCODE);
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
        return ResponseEntity.status(HttpStatus.OK).body(result_map); 
	}
	
	//조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectJptrbase(String CORCOD, String JPCODE, String VSDATE){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;

    	dataList = jptrbaseDao.selJptrbase(CORCOD, JPCODE, VSDATE);
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
    	return ResponseEntity.status(HttpStatus.OK).body(result_map); 
	}
	
	//저장
	@Override
	public ResponseEntity<HashMap<String, Object>> insertJptrbase(JPTRBS_VO JPTRBS_VO) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = jptrbaseDao.insJptrbase(JPTRBS_VO);
		if(result_value > 0) {
			//순번이 중복될 경우 예외처리한다.
			if(JPTRBS_VO.getJEPSEQ() > 1) {
				throw new BaseSeqException();
			}
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//수정
	@Override
	public ResponseEntity<HashMap<String, Object>> updateJptrbase(JPTRBS_VO JPTRBS_VO) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = jptrbaseDao.updJptrbase(JPTRBS_VO);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map); 
	}
	
	//삭제
	@Override
	public ResponseEntity<HashMap<String, Object>> deleteJptrbase(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = jptrbaseDao.delJptrbase(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.OK;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map); 
	}
	
}
