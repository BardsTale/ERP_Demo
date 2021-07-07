package com.TestReport.comCode.controller;

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

import com.TestReport.comCode.service.CharSetService;
import com.TestReport.common.CharsetHandler;

@RestController
@RequestMapping("/A_CharSetMng")
public class CharSetController {
	@Autowired
	public CharSetService charSetService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView CharSetMaster(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//Select - get
	@RequestMapping(value="/CHASET", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getCharSet(@RequestParam("SEARCH") String SEARCH, @RequestParam("SCHGBN") String SCHGBN){
		return charSetService.selectChaMst(SEARCH, SCHGBN);
	}
	
	//Insert - post
	@RequestMapping(value="/CHASET", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postCharSet(@RequestBody HashMap<String, String> param, HttpSession session){
		return charSetService.insertChaMst(param);
	}
	
	//Update - put
	@RequestMapping(value="/CHASET", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putCharSet(@RequestBody HashMap<String, String> param){
		return charSetService.updateChaMst(param);
	}
	
	//Delete - delete
	@RequestMapping(value="/CHASET", method=RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Object>> deleteCharSet(@RequestBody HashMap<String, String> param){
		return charSetService.deleteChaMst(param);
	}
}
