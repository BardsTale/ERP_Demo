package com.TestReport.matr.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.configuration.exception.BaseSeqException;
import com.TestReport.matr.dao.MatrBaseDao;
import com.TestReport.matr.domain.MATRBS_VO;

@Service("MatrBaseService")
public class MatrBaseServiceImpl implements MatrBaseService{
	@Autowired
	public MatrBaseDao matrBaseDao;	

	@Override
	public ResponseEntity<HashMap<String, Object>> selectMatrVersion(String CORCOD, String MATCOD) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;

    	dataList = matrBaseDao.selMatrVersion(CORCOD, MATCOD);
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
        return ResponseEntity.status(HttpStatus.OK).body(result_map); 
	}
	
	//조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectMatrbase(String CORCOD, String MATCOD, String VSDATE) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;

    	dataList = matrBaseDao.selMatrbase(CORCOD, MATCOD , VSDATE);
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
    	return ResponseEntity.status(HttpStatus.OK).body(result_map); 
	}

	//저장
	@Override
	public ResponseEntity<HashMap<String, Object>> insertMatrbase(MATRBS_VO MATRBS_VO) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = matrBaseDao.insMatrbase(MATRBS_VO);
		if(result_value > 0) {
			//순번이 중복될 경우 예외처리한다.
			if(MATRBS_VO.getMATSEQ() > 1) {
				throw new BaseSeqException();
			}
			msg = "처리되었습니다.";
			String CORCOD = MATRBS_VO.getCORCOD();
			String MATCOD = MATRBS_VO.getMATCOD();
			String STRDAT = MATRBS_VO.getSTRDAT();
			String CKITEM = MATRBS_VO.getCKITEM();
			//성적서 공백 Body 처리
			matrBaseDao.insMatrBodyByMatrbase(CORCOD, MATCOD, STRDAT, CKITEM);
			status = HttpStatus.CREATED;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}

	//수정
	@Override
	public ResponseEntity<HashMap<String, Object>> updateMatrbase(MATRBS_VO MATRBS_VO) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = matrBaseDao.updMatrbase(MATRBS_VO);
		if(result_value > 0) {
			//순번이 중복될 경우 예외처리한다.
			if(MATRBS_VO.getMATSEQ() > 1) {
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
	public ResponseEntity<HashMap<String, Object>> deleteMatrbase(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = matrBaseDao.delMatrbase(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.OK;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}

}
