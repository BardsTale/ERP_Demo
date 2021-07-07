package com.TestReport.mdtr.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.configuration.exception.BaseSeqException;
import com.TestReport.mdtr.dao.MdtrbaseDao;
import com.TestReport.mdtr.domain.MDTRBS_VO;

@Service("MdtrbaseService")
public class MdtrbaseServiceImpl implements MdtrbaseService{
	@Autowired
	public MdtrbaseDao mdtrbaseDao;

	@Override
	public ResponseEntity<HashMap<String, Object>> selectMdtrVersion(String CORCOD, String JPCODE) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;

    	dataList = mdtrbaseDao.selMdtrVersion(CORCOD, JPCODE);
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
        return ResponseEntity.status(HttpStatus.OK).body(result_map); 
	}
	
	//조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectMdtrbase(String CORCOD, String JPCODE, String VSDATE){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;

    	dataList = mdtrbaseDao.selMdtrbase(CORCOD, JPCODE, VSDATE);
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
    	return ResponseEntity.status(HttpStatus.OK).body(result_map); 
	}
	
	//저장
	@Override
	public ResponseEntity<HashMap<String, Object>> insertMdtrbase(MDTRBS_VO MDTRBS_VO){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = mdtrbaseDao.insMdtrbase(MDTRBS_VO);
		if(result_value > 0) {
			//순번이 중복될 경우 예외처리한다.
			if(MDTRBS_VO.getJEPSEQ() > 1) {
				throw new BaseSeqException();
			}
			msg = "처리되었습니다.";
			String CORCOD = MDTRBS_VO.getCORCOD();
			String JPCODE = MDTRBS_VO.getJPCODE();
			String STRDAT = MDTRBS_VO.getSTRDAT();
			String CKITEM = MDTRBS_VO.getCKITEM();
			//성적서 공백 Body 처리
			mdtrbaseDao.insMdtrBodyByMdtrbase(CORCOD, JPCODE, STRDAT, CKITEM);
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//수정
	@Override
	public ResponseEntity<HashMap<String, Object>> updateMdtrbase(MDTRBS_VO MDTRBS_VO){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = mdtrbaseDao.updMdtrbase(MDTRBS_VO);
		if(result_value > 0) {
			//순번이 중복될 경우 예외처리한다.
			if(MDTRBS_VO.getJEPSEQ() > 1) {
				throw new BaseSeqException();
			}
			msg = "처리되었습니다.";
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//삭제
	@Override
	public ResponseEntity<HashMap<String, Object>> deleteMdtrbase(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = mdtrbaseDao.delMdtrbase(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.OK;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
}
