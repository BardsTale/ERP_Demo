package com.TestReport.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TestReport.common.FileHandler;


@RestController
public class FileUploadController {
	@Autowired
	FileHandler fileHandler;
	
	@RequestMapping(value="UPLOADING", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile(@RequestParam("fileToUpload") List<MultipartFile> files, HttpSession session) throws Exception, IOException {
		return fileHandler.uploadHandler(files);
	}
}
