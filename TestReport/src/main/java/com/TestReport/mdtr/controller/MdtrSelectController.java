package com.TestReport.mdtr.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TestReport.common.CharsetHandler;
import com.TestReport.login.domain.ZOPR01_VO;
import com.TestReport.mdtr.service.MdtrSelectService;
import com.TestReport.mdtr.service.MdtrbaseService;

@RestController
@RequestMapping("/B_MdtrSelect")
public class MdtrSelectController {
	@Autowired
	public MdtrSelectService mdtrSelectService;
	@Autowired
	public MdtrbaseService mdtrbaseService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView mdtrList(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	
	//Version Select - get
	@RequestMapping(value="/MDTRLIST/VERSION", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMdtrList(@RequestParam("JPCODE") String JPCODE, HttpSession session){
		String CORCOD = ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD();
    	return mdtrbaseService.selectMdtrVersion(CORCOD, JPCODE); 
	}
	
	//Select - get
	@RequestMapping(value="/MDTRLIST", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMdtrList(@RequestParam("JPCODE") String JPCODE, @RequestParam("VSDATE") String VSDATE, @RequestParam("PASSGB") String PASSGB, @RequestParam("STRDAT") String STRDAT, @RequestParam("ENDDAT") String ENDDAT, HttpSession session){
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("CORCOD", ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD());
		param.put("JPCODE", JPCODE);
		param.put("VSDATE", VSDATE);
		param.put("PASSGB", PASSGB);
		param.put("STRDAT", STRDAT);
		param.put("ENDDAT", ENDDAT);
		return mdtrSelectService.selectMdtrList(param); 
	}
}
