package com.TestReport.mdtr.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import com.TestReport.mdtr.domain.MDTRBS_VO;



public interface MdtrbaseService {
	public ResponseEntity<HashMap<String, Object>> selectMdtrVersion(String CORCOD, String JPCODE);
	public ResponseEntity<HashMap<String, Object>> selectMdtrbase(String CORCOD, String JPCODE, String VSDATE);
	public ResponseEntity<HashMap<String, Object>> insertMdtrbase(MDTRBS_VO MDTRBS_VO);
	public ResponseEntity<HashMap<String, Object>> updateMdtrbase(MDTRBS_VO MDTRBS_VO);
	public ResponseEntity<HashMap<String, Object>> deleteMdtrbase(HashMap<String, String> map);
}
