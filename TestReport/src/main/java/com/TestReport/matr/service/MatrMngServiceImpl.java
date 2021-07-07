package com.TestReport.matr.service;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.common.CharsetHandler;
import com.TestReport.main.domain.CHASET_VO;
import com.TestReport.matr.dao.MatrMngDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("MatrMngService")
public class MatrMngServiceImpl implements MatrMngService{
	@Autowired
	public MatrMngDao matrMngDao;
	
	//조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectMatrMng(String params, String CORCOD) throws Exception{
		CHASET_VO CHASET_VO = CharsetHandler.getInstance().getVO(); //캐릭터셋
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		HashMap<String, String> param_map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		
    	List<HashMap<String, Object>> dataListBase = null;
    	List<TreeMap<String, Object>> dataListHeadTop = null;
    	List<TreeMap<String, Object>> dataListHeadBottom = null;
    	List<TreeMap<String, Object>> dataListBody = null;
    	List<TreeMap<String, Object>> dataListPeriod = null;

    	try {
    		param_map = mapper.readValue(params, new TypeReference<HashMap<String, String>>(){});
		} catch (JsonProcessingException jpe) {
			throw jpe;
		}
    	param_map.put("CORCOD",CORCOD);
    	
    	dataListBase = matrMngDao.selMatrAdminLeft(param_map);
    	dataListHeadTop = matrMngDao.selMatrAdminHeadTop(param_map);
    	dataListHeadBottom = matrMngDao.selMatrAdminHeadBottom(param_map);
    	dataListBody = matrMngDao.selMatrAdminBody(param_map);
    	dataListPeriod = matrMngDao.selMatrPeriodVal(param_map);
    	String msg = CHASET_VO.getMessage_ok();
    	
    	result_map.put("dataListBase", dataListBase);
    	result_map.put("dataListHeadTop", dataListHeadTop);
    	result_map.put("dataListHeadBottom", dataListHeadBottom);
    	result_map.put("dataListBody", dataListBody);
    	result_map.put("dataListPeriod", dataListPeriod);
        result_map.put("msg",msg);
    	return ResponseEntity.status(HttpStatus.OK).body(result_map); 
	}
	
	//기준치 값 조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectMatrMngValue(String params, String CORCOD) throws Exception{
		CHASET_VO CHASET_VO = CharsetHandler.getInstance().getVO(); //캐릭터셋
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		HashMap<String, String> param_map = new HashMap<String, String>();
    	List<HashMap<String, Object>> dataList = null;
    	ObjectMapper mapper = new ObjectMapper();
    	
    	try {
    		param_map = mapper.readValue(params, new TypeReference<HashMap<String, String>>(){});
		} catch (JsonProcessingException jpe) {
			throw jpe;
		}
    	param_map.put("CORCOD",CORCOD);
    	
    	dataList = matrMngDao.selMatrItemVal(param_map);
    	String msg = CHASET_VO.getMessage_ok();
    	
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
		return ResponseEntity.status(HttpStatus.OK).body(result_map); 
	}
	
	//저장
	@Override
	@SuppressWarnings("unchecked")
	public ResponseEntity<HashMap<String, Object>> insertMatrMng(HashMap<String, Object> param) {
		CHASET_VO CHASET_VO = CharsetHandler.getInstance().getVO(); //캐릭터셋
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = CHASET_VO.getMessage_process_fail();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		List<HashMap<String,String>> headList = (List<HashMap<String,String>>) param.get("head_row"); 
		List<HashMap<String,String>> bodyList = (List<HashMap<String,String>>) param.get("body_row"); 
		//헤드 등록
		for(HashMap<String,String> head_param : headList) {
			int result_value = matrMngDao.updMatrAdminHead(head_param);
			if(result_value > 0) {
				msg = CHASET_VO.getMessage_ok();
				status = HttpStatus.CREATED;
			}else {
				result_value = matrMngDao.insMatrAdminHead(head_param);
				if(result_value > 0) {
					msg = CHASET_VO.getMessage_ok();
					status = HttpStatus.CREATED;
				}
			}
		}
		//바디 등록
		for(HashMap<String,String> body_param : bodyList) {
			int result_value = matrMngDao.updMatrAdminBody(body_param);
			if(result_value > 0) {
				msg = CHASET_VO.getMessage_ok();
				status = HttpStatus.CREATED;
			}else {
				result_value = matrMngDao.insMatrAdminBody(body_param);
				if(result_value > 0) {
					msg = CHASET_VO.getMessage_ok();
					status = HttpStatus.CREATED;
				}
			}
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//수정
	@Override
	@SuppressWarnings("unchecked")
	public ResponseEntity<HashMap<String, Object>> updateMatrMng(HashMap<String, Object> param) {
		CHASET_VO CHASET_VO = CharsetHandler.getInstance().getVO(); //캐릭터셋
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = CHASET_VO.getMessage_process_fail();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		List<HashMap<String,String>> headList = (List<HashMap<String,String>>) param.get("head_row"); 
		//헤드 합부여부 수정
		for(HashMap<String,String> head_param : headList) {
			int result_value = matrMngDao.updPassMatrAdminHead(head_param);
			if(result_value > 0) {
				msg = CHASET_VO.getMessage_ok();
				status = HttpStatus.CREATED;
			}
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//삭제
	@Override
	public ResponseEntity<HashMap<String, Object>> deleteMatrMng(HashMap<String, String> param) {
		CHASET_VO CHASET_VO = CharsetHandler.getInstance().getVO(); //캐릭터셋
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = CHASET_VO.getMessage_process_fail();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = matrMngDao.delMatrAdminHead(param);
		if(result_value > 0) {
			matrMngDao.delMatrAdminBody(param);
			msg = CHASET_VO.getMessage_ok();
			status = HttpStatus.OK;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
}
