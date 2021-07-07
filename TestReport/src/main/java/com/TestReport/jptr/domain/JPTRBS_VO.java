package com.TestReport.jptr.domain;

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
public class JPTRBS_VO implements Serializable {
	private String CORCOD;
	private String JPCODE;
	private String STRDAT;
	private String CKITEM;
	private String ENDGBN;
	private String APUNIT;
	private String SPCGBN;
	private int	   DSPSEQ;
	private String BASTXT;
	private String BASTYP;
	private float  STRVAL;
	private float  ENDVAL;
	private String PASSGB;
	private String PERIOD;
	private String JARISU;
	private String REGUSR;
	private String REGDAT;
	private String MODUSR;
	private String MODDAT;
	private int	   JEPSEQ;
}
