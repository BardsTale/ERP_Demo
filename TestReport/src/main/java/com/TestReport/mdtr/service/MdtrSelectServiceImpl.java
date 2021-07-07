package com.TestReport.mdtr.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.mdtr.dao.MdtrSelectDao;

@Service("MdtrSelectService")
public class MdtrSelectServiceImpl implements MdtrSelectService{
	@Autowired
	public MdtrSelectDao mdtrSelectDao;

	//조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectMdtrList(HashMap<String, String> param){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataListBase = null;
    	List<HashMap<String, Object>> dataListHead = null;
    	List<HashMap<String, Object>> dataListBody = null;

    	dataListBase = mdtrSelectDao.selMdtrListBase(param);
    	dataListHead = mdtrSelectDao.selMdtrListHead(param);
    	dataListBody = mdtrSelectDao.selMdtrListBody(param);
    	String msg = "처리되었습니다.";
    	
    	result_map.put("dataListBase", dataListBase);
    	result_map.put("dataListHead", dataListHead);
    	result_map.put("dataListBody", dataListBody);
        result_map.put("msg",msg);
        return ResponseEntity.status(HttpStatus.OK).body(result_map);
	}
}
