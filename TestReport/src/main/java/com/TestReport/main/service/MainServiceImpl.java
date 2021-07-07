package com.TestReport.main.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.main.dao.CharSetDao;
import com.TestReport.main.dao.CodmstDao;
import com.TestReport.main.dao.MainDao;
import com.TestReport.main.dao.MenuDao;

@Service("MainService")
public class MainServiceImpl implements MainService{
	@Autowired
	public MainDao mainDao;
	@Autowired
	public MenuDao menuDao;
	@Autowired
	public CodmstDao codmstDao;
	@Autowired
	public CharSetDao charSetDao;
	
	
	@Override
	public HashMap<String, Object> getMainData() {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String today, version = "";
		
		try {
			//날짜 YYYY-MM-DD 가져오는용
			today = mainDao.getDate2();
			
			//CSS, Javscript 버전관리용
			version = mainDao.getDateLog();
			
			result_map.put("today", today);
			result_map.put("version", version);
		} catch (Exception e) {
			System.out.println(e);
		}
		return result_map;
	}
	
	@Override
	public HashMap<String, Object> getCharSet(String CHASET) {
		List<HashMap<String, String>> chaset_map = null;
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		
		try {
			//언어셋
			chaset_map = charSetDao.selectCHAMST_Local(CHASET);
			chaset_map.forEach(map->{
				result_map.put(map.get("ENGRMK"), map.get("FORSTR"));
			});
		} catch (Exception e) {
			System.out.println(e);
		}
		return result_map;
	}
	
	@Override
	public List<HashMap<String, String>> getCharSetList() {
		return charSetDao.selectCHAMSTList();
	}
	
	@Override
	public HashMap<String, Object> getMenus(String CHASET) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		List<HashMap<String, Object>> menu_Head = null;
		List<HashMap<String, Object>> menu_Body = null;
		try {
			menu_Head = menuDao.getMenuHead(CHASET);
			menu_Body = menuDao.getMenuBody(CHASET);
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(menu_Head);
		result_map.put("menu_Head", menu_Head);
		result_map.put("menu_Body", menu_Body);
		return result_map;
	}
	
	@Override
	public ResponseEntity<HashMap<String, Object>> selectCODMST(String proc_kind,String search, String MAJOCD, String CORCOD) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;

    	if(proc_kind.equals("JPCODE")) {
    		dataList = codmstDao.selJEPMST(search, CORCOD);
    	}else if(proc_kind.equals("CKITEM")) {
			dataList = codmstDao.selBASETRMST(search, MAJOCD);
    	}else if(proc_kind.equals("MATCOD")) {
    		dataList = codmstDao.selMATCOD(search, CORCOD);
    	}else if(proc_kind.equals("CORCOD")) {
    		dataList = codmstDao.selCORCOD(search);
    	}
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
        return ResponseEntity.status(HttpStatus.OK).body(result_map); 
	}
}
