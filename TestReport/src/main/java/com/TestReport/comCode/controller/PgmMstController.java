package com.TestReport.comCode.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TestReport.comCode.service.PgmMstService;
import com.TestReport.common.CharsetHandler;

@RestController
@RequestMapping("/A_PgmMst")
public class PgmMstController {
	@Autowired
	public PgmMstService pgmMstService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView PgmMstMaster(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//Select - get
	@RequestMapping(value="/PGMLST", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getPgmLst(@RequestParam("SCHGBN") String SCHGBN){
		return pgmMstService.selectPGMLST(SCHGBN);
	}
	
	//Select - get
	@RequestMapping(value="/PGMCHK", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getPgmChk(@RequestParam("PGMCOD") String PGMCOD){
		return pgmMstService.selectPGMCHK(PGMCOD);
	}
	
	//Insert - post
	@RequestMapping(value="/PGMLST", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> postPgmLst(@RequestBody HashMap<String, String> param, HttpSession session){
		return pgmMstService.insertPGMLST(param);
	}
	
	//Update - put
	@RequestMapping(value="/PGMLST", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putPgmLst(@RequestBody HashMap<String, String> param){
		return pgmMstService.updatePGMLST(param);
	}
	
	//Update - put
	@RequestMapping(value="/PGMLST/GRID", method=RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Object>> putPgmLst_Grid(@RequestBody List<HashMap<String, String>> params){
		return pgmMstService.updatePGMLST_Grid(params);
	}
	
	//Delete - delete
	@RequestMapping(value="/PGMLST", method=RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Object>> deletePgmLst(@RequestBody HashMap<String, String> param){
		return pgmMstService.deletePGMLST(param);
	}
}
