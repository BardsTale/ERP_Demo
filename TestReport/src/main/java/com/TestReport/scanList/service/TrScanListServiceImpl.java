package com.TestReport.scanList.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.common.FileHandler;
import com.TestReport.scanList.dao.TrscanlistDao;

@Service("TrScanListService")
public class TrScanListServiceImpl implements TrScanListService{
	@Autowired
	public TrscanlistDao trscanlistDao;

	//주문정보 스캔 리스트 조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectTrScanOrderList(HashMap<String, String> param){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataListOrder = null;
    	List<HashMap<String, Object>> dataListScan = null;
        	
    	dataListScan = trscanlistDao.selScanList(param);
    	dataListOrder = trscanlistDao.selOrderList(param);
    	String msg = "처리되었습니다.";
    	result_map.put("dataListScan", dataListScan);
    	result_map.put("dataListOrder", dataListOrder);
        result_map.put("msg",msg);
    	return ResponseEntity.status(HttpStatus.OK).body(result_map); 
	}
	
	//스캔 리스트 조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectTrScanList(HashMap<String, String> param){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		List<HashMap<String, Object>> dataList = null;
		
		dataList = trscanlistDao.selScanList(param);
		String msg = "처리되었습니다.";
		result_map.put("dataList", dataList);
		result_map.put("msg",msg);
		return ResponseEntity.status(HttpStatus.OK).body(result_map);
	}
	
	//저장
	@Override
	public ResponseEntity<HashMap<String, Object>> insertTrScanList(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		try {
			int result_value = trscanlistDao.insScanList(param);
			if(result_value > 0) {
				msg = "처리되었습니다.";
				status = HttpStatus.CREATED;
			}else {
				FileHandler.deleteHandler(param.get("SCNNAM"));
			}
		} catch (Exception e) {
			FileHandler.deleteHandler(param.get("SCNNAM"));
			throw e;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//수정
	@Override
	public ResponseEntity<HashMap<String, Object>> updateTrScanList(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		try {
			int result_value = trscanlistDao.updScanList(param);
			if(result_value > 0) {
				msg = "처리되었습니다.";
				status = HttpStatus.CREATED;
			}else {
				FileHandler.deleteHandler(param.get("SCNNAM"));
			}
		} catch (Exception e) {
			FileHandler.deleteHandler(param.get("SCNNAM"));
			throw e;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//삭제
	@Override
	public ResponseEntity<HashMap<String, Object>> deleteTrScanList(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = trscanlistDao.delScanList(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.OK;
			FileHandler.deleteHandler(param.get("SCNNAM"));
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
}
