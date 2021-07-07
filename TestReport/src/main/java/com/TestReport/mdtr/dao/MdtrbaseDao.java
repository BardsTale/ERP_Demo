package com.TestReport.mdtr.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.TestReport.mdtr.domain.MDTRBS_VO;


public interface MdtrbaseDao {
	//중간검사 기준치 버전 조회
	public List<HashMap<String,Object>> selMdtrVersion(@Param("CORCOD") String CORCOD, @Param("JPCODE") String JPCODE); 
	//중간검사 기준치 조회
	public List<HashMap<String,Object>> selMdtrbase(@Param("CORCOD") String CORCOD, @Param("JPCODE") String JPCODE,@Param("VSDATE") String VSDATE); 
	//중간검사 기준치 등록
	public int insMdtrbase(MDTRBS_VO MDTRBS_VO); 
	//이미 성적서 자료가 등록된 버전에서 새로운 기준치를 추가할 경우 Body에 빈 데이터 값들 등록
	public int insMdtrBodyByMdtrbase(@Param("CORCOD") String CORCOD, @Param("JPCODE") String JPCODE, @Param("STRDAT") String STRDAT, @Param("CKITEM") String CKITEM); 
	//중간검사 기준치 수정
	public int updMdtrbase(MDTRBS_VO MDTRBS_VO); 
	//중간검사 기준치 삭제
	public int delMdtrbase(HashMap<String, String> param); 
}
