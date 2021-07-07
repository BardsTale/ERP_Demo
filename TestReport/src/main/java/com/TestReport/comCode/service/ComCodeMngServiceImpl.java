package com.TestReport.comCode.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.comCode.dao.ComcodeDao;
import com.TestReport.configuration.aop.AuthorityCheck;

@Service("ComCodeMngService")
public class ComCodeMngServiceImpl implements ComCodeMngService{
	@Autowired
	public ComcodeDao comcodeDao;

	//조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectBaster(String TRKIND){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;

    	dataList = comcodeDao.selBASETR(TRKIND);
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
    	return ResponseEntity.status(HttpStatus.OK).body(result_map);
	}
	
	//저장
	@Override
	public ResponseEntity<HashMap<String, Object>> insertBaster(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		comcodeDao.insBASETR(param);
		//Mybatis의 SelectKey 옵션에 의해 파라미터로 전달한 HashMap에 SelectKey가 들어가 사용가능.
		if(!param.getOrDefault("DAECOD2","").equals("")){
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//수정
	@Override
	public ResponseEntity<HashMap<String, Object>> updateBaster(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = comcodeDao.updBASETR(param);
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
	public ResponseEntity<HashMap<String, Object>> deleteBaster(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = comcodeDao.delBASETR(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.OK;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
}
