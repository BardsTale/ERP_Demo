package com.TestReport.usrMst.service;

import java.security.PrivateKey;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;



public interface UsrMstService {
	public ResponseEntity<HashMap<String, Object>> selectUsrMst(String USERID);
	//저장과 수정은 GlobalException에 예외를 보내기 위해 theows 함.
	public ResponseEntity<HashMap<String, Object>> insertUsrMst(PrivateKey privateKey, HashMap<String, String> param) throws Exception;
	public ResponseEntity<HashMap<String, Object>> updateUsrMst(PrivateKey privateKey, HashMap<String, String> param) throws Exception;
	public ResponseEntity<HashMap<String, Object>> updateUsrMstPassword(PrivateKey privateKey, HashMap<String, String> param) throws Exception;
	public ResponseEntity<HashMap<String, Object>> deleteUsrMst(HashMap<String, String> param);
}
