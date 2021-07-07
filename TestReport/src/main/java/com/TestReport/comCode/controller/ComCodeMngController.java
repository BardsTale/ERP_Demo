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

import com.TestReport.comCode.service.ComCodeMngService;
import com.TestReport.common.CharsetHandler;

@RestController
@RequestMapping("/A_ComCodeMng")
public class ComCodeMngController {
	@Autowired
	public ComCodeMngService comCodeMngService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView comCodeMng(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//Select - get
	@RequestMapping(value="/BASTER", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getComCodeMng(@RequestParam("TRKIND") String TRKIND){
		return comCodeMngService.selectBaster(TRKIND);
	}
	
	//Insert - post
	@RequestMapping(value="/BASTER", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postComCodeMng(@RequestBody HashMap<String, String> param){
		return comCodeMngService.insertBaster(param);
	}
	
	//Update - put
	@RequestMapping(value="/BASTER", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putComCodeMng(@RequestBody HashMap<String, String> param){
		return comCodeMngService.updateBaster(param);
	}
	
	//Delete - delete
	@RequestMapping(value="/BASTER", method=RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Object>> deleteComCodeMng(@RequestBody HashMap<String, String> param){
		return comCodeMngService.deleteBaster(param);
	}
}
