package com.TestReport.configuration.exception;

public class ExcelUploadException extends RuntimeException {
	
	/**
	 * 엑셀 업로드 커스텀 예외 처리.
	 * serialVersionUID Default로 넣음
	 */
	private static final long serialVersionUID = 2L;
	private static final String message = "엑셀 파일을 읽는 중 오류가 발생하였습니다.";
	
	public ExcelUploadException() {
		super(message);
	}
	public ExcelUploadException(Throwable cause) {
	    super(cause);
	}
}
