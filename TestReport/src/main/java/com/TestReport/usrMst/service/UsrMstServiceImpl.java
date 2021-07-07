package com.TestReport.usrMst.service;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;

import javax.crypto.BadPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.common.CharsetHandler;
import com.TestReport.common.Common_SI2;
import com.TestReport.configuration.aop.AuthorityCheck;
import com.TestReport.main.domain.CHASET_VO;
import com.TestReport.usrMst.dao.UsrmstDao;

@Service("UsrMstService")
public class UsrMstServiceImpl implements UsrMstService{
	@Autowired
	public UsrmstDao usrmstDao;

	//조회
	@Override
	public ResponseEntity<HashMap<String, Object>> selectUsrMst(String TRKIND){
		HashMap<String, Object> result_map = new HashMap<String, Object>();
    	List<HashMap<String, Object>> dataList = null;
    	
    	dataList = usrmstDao.selUSRMST(TRKIND);
    	String msg = "처리되었습니다.";
    	result_map.put("dataList", dataList);
        result_map.put("msg",msg);
    	return ResponseEntity.status(HttpStatus.OK).body(result_map);
	}
	
	//저장
	@Override
	public ResponseEntity<HashMap<String, Object>> insertUsrMst(PrivateKey privateKey, HashMap<String, String> param) throws Exception {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		try {
			//복호화 프로세스
            String userid = Common_SI2.decryptRsa(privateKey, param.get("USER_ID")).toUpperCase();
            
            //ID 복호화 및 패스워드 PBKDF 암호화 후 치환
            param.put("USER_ID", userid);
            if(!param.getOrDefault("PASSWD", "").equals("")) {
            	String passwd = Common_SI2.decryptRsa(privateKey, param.get("PASSWD"));
            	param.put("PASSWD", Common_SI2.encryptPBKDF(userid, passwd));
            }
			int result_value = usrmstDao.insUSRMST(param);
			if(result_value > 0) {
				msg = "처리되었습니다.";
				status = HttpStatus.CREATED;
			}
		} catch (InvalidKeyException ie) {
			throw ie;
		} catch (BadPaddingException be) {
			throw be;
		} catch (Exception e) {
			throw e;
		} 
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map); 
	}
	
	//수정
	@Override
	public ResponseEntity<HashMap<String, Object>> updateUsrMst(PrivateKey privateKey, HashMap<String, String> param) throws Exception {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		try {
			//복호화 프로세스
            String userid = Common_SI2.decryptRsa(privateKey, param.get("USER_ID")).toUpperCase();
            
            //ID 복호화 및 패스워드 PBKDF 암호화 후 치환
            param.put("USER_ID", userid);
            if(!param.getOrDefault("PASSWD", "").equals("")) {
            	String passwd = Common_SI2.decryptRsa(privateKey, param.get("PASSWD"));
            	param.put("PASSWD", Common_SI2.encryptPBKDF(userid, passwd));
            }
			int result_value = usrmstDao.updUSRMST(param);
			if(result_value > 0) {
				msg = "처리되었습니다.";
				status = HttpStatus.CREATED;
			}
		} catch (InvalidKeyException ie) {
			throw ie;
		} catch (BadPaddingException be) {
			throw be;
		} catch (Exception e) {
			throw e;
		} 
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//비밀번호 수정
	@Override
	public ResponseEntity<HashMap<String, Object>> updateUsrMstPassword(PrivateKey privateKey, HashMap<String, String> param) throws Exception {
		CHASET_VO CHASET_VO = CharsetHandler.getInstance().getVO(); //캐릭터셋
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = CHASET_VO.getMessage_process_fail();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		try {
			//복호화 프로세스
			//ID 복호화 및 패스워드 PBKDF 암호화 후 치환
			String userid = Common_SI2.decryptRsa(privateKey, param.get("securedUSERID")).toUpperCase();
			param.put("USER_ID", userid);
			String passwd = Common_SI2.decryptRsa(privateKey, param.get("securedPASSWD"));
			param.put("PASSWD", Common_SI2.encryptPBKDF(userid, passwd));
			int result_value = usrmstDao.updUSRMST_Password(param);
			if(result_value > 0) {
				msg = CHASET_VO.getMessage_ok();
				status = HttpStatus.CREATED;
			}
		} catch (InvalidKeyException ie) {
			throw ie;
		} catch (BadPaddingException be) {
			throw be;
		} catch (Exception e) {
			throw e;
		} 
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
	//삭제
	@AuthorityCheck
	@Override
	public ResponseEntity<HashMap<String, Object>> deleteUsrMst(HashMap<String, String> param) {
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = "실패하였습니다.<br>관리자에게 문의하세요.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		int result_value = usrmstDao.delUSRMST(param);
		if(result_value > 0) {
			msg = "처리되었습니다.";
			status = HttpStatus.OK;
		}
		result_map.put("msg",msg);
		return ResponseEntity.status(status).body(result_map);
	}
	
}
