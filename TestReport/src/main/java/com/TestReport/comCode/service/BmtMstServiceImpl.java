package com.TestReport.comCode.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.comCode.dao.BmtMstDao;

@Service("BmtMstService")
public class BmtMstServiceImpl implements BmtMstService {
	
	@Autowired
	public BmtMstDao bmtmstDao;

	//조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectBMTMST(String CORCOD, String MATCOD) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;

    	dataList = bmtmstDao.selBMTMST(CORCOD, MATCOD);
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
    	return ResponseEntity.status(HttpStatus.OK).body(result_map);
	}

	//저장
	@Override
	public ResponseEntity<HashMap<String, Object>> insertBMTMST(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = bmtmstDao.insBMTMST(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
		
	}
	
	//엑셀 저장
	@Override
	public ResponseEntity<HashMap<String, Object>> insertBMTMST_Excel(List<HashMap<String, String>> ListMap) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		int result_value = 0;
		
		//헤드 등록
		for(HashMap<String, String> param : ListMap) {
			result_value += bmtmstDao.insBMTMST(param);
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
	public ResponseEntity<HashMap<String, Object>> updateBMTMST(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = bmtmstDao.updBMTMST(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}

	//삭제
	@Override
	public ResponseEntity<HashMap<String, Object>> deleteBMTMST(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		if(bmtmstDao.chkUsedMAT(param) > 0) {
			msg = "성적서에 사용중인 자재는 삭제하실 수 없습니다.";
		}else {
			int result_value = bmtmstDao.delBMTMST(param);
			if(result_value > 0) {
				msg = "처리되었습니다.";
				status = HttpStatus.OK;
			}
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}

	
	
}
