package com.TestReport.matr.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import com.TestReport.matr.domain.MATRBS_VO;

public interface MatrBaseService {
	//버전 조회
	public ResponseEntity<HashMap<String, Object>> selectMatrVersion(String CORCOD, String MATCOD) ; 
	//부자재항목 조회
	public ResponseEntity<HashMap<String, Object>> selectMatrbase(String CORCOD, String MATCOD, String VSDATE) ; 
	//부자재항목 등록
	public ResponseEntity<HashMap<String, Object>> insertMatrbase(MATRBS_VO MATRBS_VO) ; 
	//부자재항목 수정
	public ResponseEntity<HashMap<String, Object>> updateMatrbase(MATRBS_VO MATRBS_VO) ; 
	//부자재항목 삭제
	public ResponseEntity<HashMap<String, Object>> deleteMatrbase(HashMap<String, String> map) ; 
}
