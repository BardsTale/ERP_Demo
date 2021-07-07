package com.TestReport.main.controller;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.SortedMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.TestReport.common.FileHandler;
import com.TestReport.common.JqxGridExcelStreamingView;
import com.TestReport.main.service.UploadExcelService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class ExcelController {
	@Autowired
	FileHandler fileHandler;
	
	@Autowired 
	UploadExcelService uploadExcelService;
	
	@RequestMapping(value="EXCEL/DOWNLOADING", method=RequestMethod.POST)
	public ModelAndView postEXCEL(@RequestParam("param") String params, HttpSession session) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> param_map = new HashMap<String, Object>();
		param_map = mapper.readValue(params, new TypeReference<HashMap<String, Object>>(){});
		return new ModelAndView(new JqxGridExcelStreamingView(), "model", param_map);
	}
	
	
	@RequestMapping(value="EXCEL/UPLOADING/TR", method = RequestMethod.POST)
	public ResponseEntity<SortedMap<String, Object>> uploadFile_Excel_TR(@RequestParam HashMap<String, String> param, MultipartHttpServletRequest filelist, HttpSession session) throws Exception {
		FileInputStream inputStream = fileHandler.uploadHandler_excel(filelist);
		return uploadExcelService.excelToTR(param, inputStream);
	}
	
	@RequestMapping(value="EXCEL/UPLOADING/JEPMST", method = RequestMethod.POST)
	public ResponseEntity<SortedMap<String, Object>> uploadFile_Excel_JEPMST(@RequestParam HashMap<String, String> param, MultipartHttpServletRequest filelist, HttpSession session) throws Exception {
		FileInputStream inputStream = fileHandler.uploadHandler_excel(filelist);
		return uploadExcelService.excelToJEPMST(param, inputStream);
	}
	
	@RequestMapping(value="EXCEL/UPLOADING/BMTMST", method = RequestMethod.POST)
	public ResponseEntity<SortedMap<String, Object>> uploadFile_Excel_BMTMST(@RequestParam HashMap<String, String> param, MultipartHttpServletRequest filelist, HttpSession session) throws Exception {
		FileInputStream inputStream = fileHandler.uploadHandler_excel(filelist);
		return uploadExcelService.excelToBMTMST(param, inputStream);
	}
}
