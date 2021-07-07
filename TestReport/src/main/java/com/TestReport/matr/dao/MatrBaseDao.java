package com.TestReport.matr.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.TestReport.matr.domain.MATRBS_VO;


public interface MatrBaseDao {
	//중간검사 기준치 버전 조회
	public List<HashMap<String,Object>> selMatrVersion(@Param("CORCOD") String CORCOD, @Param("MATCOD") String MATCOD); 
	//중간검사 기준치 조회
	public List<HashMap<String,Object>> selMatrbase(@Param("CORCOD") String CORCOD, @Param("MATCOD") String MATCOD, @Param("VSDATE") String VSDATE); 
	//중간검사 기준치 등록
	public int insMatrbase(MATRBS_VO MATRBS_VO); 
	//이미 성적서 자료가 등록된 버전에서 새로운 기준치를 추가할 경우 Body에 빈 데이터 값들 등록
	public int insMatrBodyByMatrbase(@Param("CORCOD") String CORCOD, @Param("MATCOD") String MATCOD, @Param("STRDAT") String STRDAT, @Param("CKITEM") String CKITEM); 
	//중간검사 기준치 수정
	public int updMatrbase(MATRBS_VO MATRBS_VO); 
	//중간검사 기준치 삭제
	public int delMatrbase(HashMap<String, String> param); 
}
