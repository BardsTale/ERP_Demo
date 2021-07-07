package com.TestReport.login.service;

import java.security.PrivateKey;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.common.CharsetHandler;
import com.TestReport.common.Common_SI2;
import com.TestReport.login.dao.LoginDao;
import com.TestReport.login.domain.ZOPR01_VO;
import com.TestReport.main.domain.CHASET_VO;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDao LoginDao;
	
	@Override
	public ResponseEntity<HashMap<String, Object>> doDecrypt(PrivateKey privateKey, String securedUSERID, String securedPASSWD) {
		CHASET_VO CHASET_VO = CharsetHandler.getInstance().getVO(); //캐릭터셋
		String userid = "", password = "";
		HashMap<String, Object> result_map = new HashMap<String, Object>();
		String msg = CHASET_VO.getMessage_ok_login();
		HttpStatus status = HttpStatus.OK;
    	
    	if (privateKey == null) {
        	System.out.println("암호화 비밀키 정보를 찾을 수 없습니다.");
        	msg = CHASET_VO.getMessage_auth_done();
        	status = HttpStatus.GONE;
        }else {
        	try {
        				
        		//복호화 프로세스
        		userid = Common_SI2.decryptRsa(privateKey, securedUSERID).toUpperCase();
        		password = Common_SI2.decryptRsa(privateKey, securedPASSWD);
        		//복호화 한 ID를 통해 계정 정보를 가져온다.
        		ZOPR01_VO ZOPR01_bean = LoginDao.getLoginInfo(userid);
        		//ID가 체크되면 비밀번호 검사
        		if(ZOPR01_bean == null) {
        			msg = CHASET_VO.getMessage_userid_notexist();
        			status = HttpStatus.BAD_REQUEST;
        		}else {
        			//PBKDF2 암호화 알고리즘을 돌린 password와 db값이 같으면 통과
        			if(!ZOPR01_bean.getPASSWD().equals(Common_SI2.encryptPBKDF(userid, password))) {
        				//암호 불일치
        				msg = CHASET_VO.getMessage_passwd_incorrect();
        				status = HttpStatus.BAD_REQUEST;
        			}else {
        				//암호 일치, 로그인 성공
        				result_map.put("user_bean",ZOPR01_bean);
        			}
        		}
        	} catch (Exception e) {
        		System.out.println(e.getMessage());
        		System.out.println(e);
        		msg =  CHASET_VO.getMessage_auth_fail();
        		status = HttpStatus.INTERNAL_SERVER_ERROR;
        	}
        }
    	
    	result_map.put("msg",msg);
    	return ResponseEntity.status(status).body(result_map);
	}
}
