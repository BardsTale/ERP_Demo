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
import com.TestReport.mdtr.service.MdtrTotalService;

@RestController
@RequestMapping("/B_MdtrTotal")
public class MdtrTotalController {
	@Autowired
	public MdtrTotalService mdtrTotalService;
	
	//최초 페이지 접속
	@RequestMapping("")
	public ModelAndView mdtrTotal(@RequestParam("PGMCOD") String PGMCOD, @RequestParam("PGMNAM") String PGMNAM, HttpSession session){
		ModelAndView mv = new ModelAndView(PGMCOD);
		mv.addObject("PGMNAM", PGMNAM);
		mv.addObject("PGMCOD", PGMCOD);
		mv.addObject("CHASETS", CharsetHandler.getInstance().getMap());
		return mv;
	}
	
	//Select - get
	@RequestMapping(value="/MDTRTOTAL", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMdtrTotal(@RequestParam("JPCODE") String JPCODE, @RequestParam("STRDAT") String STRDAT, @RequestParam("ENDDAT") String ENDDAT, HttpSession session){
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("CORCOD", ((ZOPR01_VO)session.getAttribute("user_bean")).getCORCOD());
		param.put("JPCODE", JPCODE);
		param.put("STRDAT", STRDAT);
		param.put("ENDDAT", ENDDAT);
		return mdtrTotalService.selectMdtrTotal(param); 
	}
}
