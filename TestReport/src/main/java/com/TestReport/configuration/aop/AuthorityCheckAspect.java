package com.TestReport.configuration.aop;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.TestReport.common.CharsetHandler;
import com.TestReport.main.domain.CHASET_VO;

/* 사용 권한 Check AOP 
 * 특정 메소드의 사용 권한이 부족할 경우 이용 불가 메시지.
 * */
@Aspect
@Component
public class AuthorityCheckAspect {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Around는 요청 전후 실행하며, 조인포인트를 proceed 해줘야 이후 진행이 된다.
	//@annotation을 사용하면 해당 경로의 해당 어노테이션 사용 시 발동. 포인트 컷에서 자유로워짐.
	@Around("@annotation(com.TestReport.configuration.aop.AuthorityCheck)")
	public ResponseEntity<HashMap<String, Object>> authorityCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		CHASET_VO CHASET_VO = CharsetHandler.getInstance().getVO(); //캐릭터셋
		String method_name = joinPoint.getSignature().toShortString();
		try {
			System.out.println(method_name+"start");
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			HashMap<String, Object> result_map = new HashMap<String, Object>();
			
			if(!"manager".equals(session.getAttribute("GRADE"))) {
				String userid = (String)session.getAttribute("USER_ID");
				String signatureString = joinPoint.getSignature().getName();
				
				logger.warn("사용자 ["+userid+"] <" + signatureString + "(..)> Method 이용 권한 부족");
				
				//message_Lack_authority
				result_map.put("msg",CHASET_VO.getMessage_Lack_authority());
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result_map);
			}else {
				return (ResponseEntity<HashMap<String, Object>>)joinPoint.proceed();
			}
		} finally {
			System.out.println(method_name+"end");
		}
	}
}
