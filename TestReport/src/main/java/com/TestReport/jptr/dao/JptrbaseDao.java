package com.TestReport.jptr.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.TestReport.jptr.domain.JPTRBS_VO;


public interface JptrbaseDao {
	//제품검사 기준치 버전 조회
	public List<HashMap<String,Object>> selJptrVersion(@Param("CORCOD") String CORCOD, @Param("JPCODE") String JPCODE); 
	//제품검사 기준치 조회
	public List<HashMap<String,Object>> selJptrbase(@Param("CORCOD") String CORCOD, @Param("JPCODE") String JPCODE, @Param("VSDATE") String VSDATE); 
	//제품검사 기준치 등록
	public int insJptrbase(JPTRBS_VO JPTRBS_VO); 
	//제품항목 수정
	public int updJptrbase(JPTRBS_VO JPTRBS_VO); 
	//제품항목 삭제
	public int delJptrbase(HashMap<String, String> param); 
}
