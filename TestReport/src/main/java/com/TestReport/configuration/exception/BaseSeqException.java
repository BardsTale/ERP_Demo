package com.TestReport.configuration.exception;

public class BaseSeqException extends RuntimeException {
	
	/**
	 * 성적서 기준치 순번 중복 커스텀 예외 처리.
	 * serialVersionUID Default로 넣음
	 */
	private static final long serialVersionUID = 1L;
	private static final String message = "출력 순번은 중복될 수 없습니다.";
	
	public BaseSeqException() {
		super(message);
	}
}
