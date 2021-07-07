package com.TestReport.usrMst.dao;

import java.util.HashMap;
import java.util.List;


public interface UsrmstDao {
	//계정 조회
	public List<HashMap<String,Object>> selUSRMST(String USERID); 
	//계정 등록
	public int insUSRMST(HashMap<String, String> map); 
	//계정 수정
	public int updUSRMST(HashMap<String, String> map); 
	//비밀번호 수정
	public int updUSRMST_Password(HashMap<String, String> map); 
	//계정 삭제
	public int delUSRMST(HashMap<String, String> map); 
}
