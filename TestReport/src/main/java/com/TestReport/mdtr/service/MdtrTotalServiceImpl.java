package com.TestReport.mdtr.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.mdtr.dao.MdtrTotalDao;

@Service("MdtrTotalService")
public class MdtrTotalServiceImpl implements MdtrTotalService{
	@Autowired
	public MdtrTotalDao mdtrTotalDao;

	//조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectMdtrTotal(HashMap<String, String> param){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;

    	dataList = mdtrTotalDao.selMdtrTotal(param);
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
        return ResponseEntity.status(HttpStatus.OK).body(result_map);
	}
}
