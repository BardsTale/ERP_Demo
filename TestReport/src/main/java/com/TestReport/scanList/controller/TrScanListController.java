package com.TestReport.scanList.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TestReport.common.CharsetHandler;
import com.TestReport.scanList.service.TrScanListService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/E_TrScanList")
public class TrScanListController {
	@Autowired
	public TrScanListService trScanListService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView trScanList(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//Select - get
	@RequestMapping(value="/TRSCAN", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getTrScanList(@RequestParam("params") String params) throws Exception {
		//직렬화된 string hashmap형으로 변환. 예외처리는 추후에 수정.
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> param_map = new HashMap<String, String>();
		param_map = mapper.readValue(params, new TypeReference<HashMap<String, String>>(){});
    	return trScanListService.selectTrScanList(param_map); 
	}
	
	//Select Order - get
	@RequestMapping(value="/TRSCAN/ORDER", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getTrScanOrderList(@RequestParam("params") String params) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> param_map = new HashMap<String, String>();
		param_map = mapper.readValue(params, new TypeReference<HashMap<String, String>>(){});
		return trScanListService.selectTrScanOrderList(param_map); 
	}
	
	//Insert - post
	@RequestMapping(value="/TRSCAN", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postTrScanList(@RequestBody HashMap<String, String> param){
		return trScanListService.insertTrScanList(param); 
	}
	
	//Update - put
	@RequestMapping(value="/TRSCAN", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putTrScanList(@RequestBody HashMap<String, String> param){
		return trScanListService.updateTrScanList(param); 
	}
	
	//Delete - delete
	@RequestMapping(value="/TRSCAN", method=RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Object>> deleteTrScanList(@RequestBody HashMap<String, String> param){
		return trScanListService.deleteTrScanList(param); 
	}
}
