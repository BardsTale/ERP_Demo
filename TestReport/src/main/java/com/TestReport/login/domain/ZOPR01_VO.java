package com.TestReport.login.domain;

import java.io.Serializable;

import lombok.Data;

/* 롬북 어노테이션 선언
 * @Data를 선언 시 아래의 어노테이션들이 한 번에 선언된다.
 * 
 * @Getter, @Setter : 클래스 생성자 getter, setter를 생략.
 * @RequiredArgsConstructor : final이나 @NonNull 인 필드 값만 파라미터로 받는 생성자를 만들어 준다.
 * @EqualsAndHashCode : 객체끼리 비교를 위한 오버라이딩 생략.
 * @ToString : toString 시 해쉬코드가 아닌 인스턴스 값 오버라이딩 생략.
 */
@Data
@SuppressWarnings("serial")
public class ZOPR01_VO implements Serializable {
	private String CORCOD;
	private String CHASET;
	private String USER_ID;
	private String USER_NM;
	private String PASSWD;
	private String GRADE;
	private String DEPTCD;
	private String ACTIVE;
	private int	   TRYCNT;
	private String INSDAT;
	private String UPTUSR;
	private String UPTDAT;
	private String INSUSR;
}
