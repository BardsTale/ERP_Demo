package com.TestReport.login.dao;

import com.TestReport.login.domain.ZOPR01_VO;

public interface LoginDao {
	public ZOPR01_VO getLoginInfo(String USER_ID);
}

