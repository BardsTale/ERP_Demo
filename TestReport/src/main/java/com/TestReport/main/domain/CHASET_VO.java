package com.TestReport.main.domain;

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
public class CHASET_VO implements Serializable {
	private String start_tr_time;                //검사시간(시작)
	private String end_tr_time;                  //검사시간(종료)
	private String conusr;                       //검사자
	private String basetr;                       //검사항목
	private String message_warn;                 //경고창 메시지
	private String codmst_code;                  //공통코드 검색창 - 코드
	private String codmst_name;                  //공통코드 검색창 - 코드명
	private String codmst_title;                 //공통코드 검색창 타이틀
	private String kind;                         //구분
	private String standard;                     //규격
	private String basevalue;                    //기준치
	private String btn_exit;                     //나가기 버튼
	private String input_value;                  //납품량
	private String btn_insert;                   //등록 버튼
	private String message_logout_title;         //로그아웃 요청 메시지1
	private String message_logout_body;          //로그아웃 요청 메시지2
	private String message_relogin;              //로그인 갱신 창 메시지
	private String btn_login;                    //로그인 버튼
	private String message_ok_login;             //로그인 성공 메시지
	private String message_fail_login;           //로그인 실패 메시지
	private String message_id_pw;                //로그인 정보 미입력 메시지
	private String version;                      //버전
	private String message_version;              //버전 미입력 메시지
	private String message_passwd_cng1;          //변경할 비밀번호 미입력
	private String message_passwd_no_match;      //변경할 비밀번호 불일치
	private String message_passwd_cng2;          //변경할 비밀번호 확인 미입력
	private String manu_major_matr;              //부자재 - 대메뉴명
	private String manu_minor_matr;              //부자재 - 중메뉴명
	private String matr_left_title;              //부자재검사 성적서관리 - 좌측 영역 제목
	private String matcod;                       //부자재코드
	private String jpcode;                       //제품코드
	private String brand;                        //브랜드
	private String remark;                       //비고
	private String text_passwd_change;           //비밀번호 변경 안내
	private String text_passwd_cng1;             //비밀번호 변경 입력란 문구
	private String text_passwd_cng2;             //비밀번호 변경 확인 입력란 문구
	private String text_passwd;                  //비밀번호 입력란 문구
	private String message_auth_fail;            //사용자 인증 실패 메시지
	private String btn_delete;                   //삭제 버튼
	private String message_delete_title;         //삭제 요청 메시지1
	private String message_delete_body;          //삭제 요청 메시지2
	private String message_delete_none;          //삭제 자료 없음 메시지
	private String logo_title;                   //상단 로고 문자
	private String top_user_info;                //상단 사용자 안내
	private String message_disconnect;           //서버 연결 실패 메시지
	private String select;                       //선택
	private String title_delete_btn;             //성적서 삭제버튼 영역 제목
	private String tr_date;                      //성적서 작성일
	private String message_passgb_none;          //성적서 합부여부 내용 없음
	private String text_id;                      //아이디 입력란 문구
	private String message_error;                //에러 발생 메시지
	private String message_excel_down_none;      //엑셀 다운로드 실패 메시지
	private String btn_excel;                    //엑셀 버튼
	private String exup_search_no;               //엑셀 업로드 기간 조회 유무2
	private String exup_btn_next;                //엑셀 업로드 다음 버튼
	private String exup_btn_upload;              //엑셀 업로드 버튼
	private String message_exceed_file;          //엑셀 업로드 용량 초과 메시지
	private String message_exup_none1;           //엑셀 업로드 자재 및 버전 지정 오류
	private String message_count_data;           //엑셀 업로드 저장 자료 건수 메시지
	private String message_none_data;            //엑셀 업로드 저장 자료 미발견 메시지
	private String message_exup_none2;           //엑셀 업로드 조회기간 지정 오류
	private String text_select_file;             //엑셀 업로드 파일 업로드 문구
	private String message_exup_none3;           //엑셀 업로드 파일 지정 오류
	private String message_process1;             //엑셀 업로드 프로세스 메시지1
	private String message_process2;             //엑셀 업로드 프로세스 메시지2
	private String message_process3;             //엑셀 업로드 프로세스 메시지3
	private String message_process4;             //엑셀 업로드 프로세스 메시지4
	private String message_process5;             //엑셀 업로드 프로세스 메시지5
	private String exup_file_name;               //엑셀 업로드 확인창 - 엑셀 파일명
	private String exup_matcod;                  //엑셀 업로드 확인창 - 자재
	private String exup_date;                    //엑셀 업로드 확인창 - 조회기간
	private String message_auth_done;            //인증 만료 메시지
	private String btn_passgb;                   //일괄 합부 검사 버튼
	private String btn_conusr;                   //일괄 확인 처리 버튼
	private String inpdat;                       //입고일
	private String message_matcod_none;          //자재코드 미입력 메시지
	private String message_save_none;            //저장 내용 없음 메시지
	private String message_insert_title;         //저장 요청 메시지1
	private String message_insert_body;          //저장 요청 메시지2
	private String item_no;                      //제품견본등록No.
	private String btn_search;                   //조회 버튼
	private String message_strdat_none;          //조회 시작일자 미선택 메시지
	private String message_enddat_none;          //조회 종료일자 미선택 메시지
	private String period;                       //주기
	private String message_wait_title;           //처리 대기 메시지1
	private String message_wait_body;            //처리 대기 메시지2
	private String message_ok;                   //처리완료 메시지
	private String btn_reset;                    //초기화 버튼
	private String btn_cancle;                   //취소 버튼
	private String page;                         //페이지
	private String message_conusr;               //페이지 일괄 확인 처리 메시지
	private String message_program_none;         //프로그램 정보 소실 메시지
	private String passgb;                       //합부여부
	private String passgb_false;                 //합부여부-부
	private String passgb_true;                  //합부여부-합
	private String btn_confirm;                  //확인 버튼
	private String message_userid_notexist;      //존재하지 않는 계정입니다.
	private String message_passwd_incorrect;     //비밀번호가 틀렸습니다.
	private String message_process_fail;		 //실패하였습니다.<br>관리자에게 문의하세요.
	private String message_Lack_authority;		 //이용 권한이 부족합니다.<br>관리자만 이용 가능한 기능입니다.
	private String message_exception;			 //관리자에게 문의하세요.
	private String message_ex_duplicateKey;		 //이미 등록된 자료가 검출되었습니다.
	private String message_ex_invalid_format;	 //유효하지 않는 값을 입력하셨습니다.
	private String message_ex_illegal_argument;	 //부적절한 요청이 들어왔습니다.
	private String message_ex_crypt;		 	 //암호화 검증 중 문제가 발생하였습니다.<br>다시 시도 바랍니다.
	private String message_ex_mybatis;		 	 //데이터 작업 중 문제가 발생하였습니다.<br>관리자에게 문의하세요.
	private String message_ex_base_seq;		 	 //성적서 출력 순번은 중복될 수 없습니다.
	private String message_ex_excel_upload;		 //엑셀 파일을 읽는 중 오류가 발생하였습니다.
	private String A_BmtMst;                     //부자재 관리
	private String A_CharSetMng;                 //언어셋 관리
	private String A_ComCodeMng;                 //검사항목관리
	private String A_CorporMng;                  //법인 관리
	private String A_JepMst;                     //제품 관리
	private String A_PgmMst;                     //프로그램 관리
	private String A_UsrMst;                     //계정관리
	private String B_MdtrBase;                   //중간검사 기준치설정
	private String B_MdtrMng;                    //중간검사 성적서관리
	private String B_MdtrSelect;                 //중간검사 성적서조회
	private String B_MdtrTotal;                  //생산이력
	private String C_MatrBase;                   //부자재검사 기준치설정
	private String C_MatrMng;                    //부자재검사 성적서관리
	private String D_JptrBase;                   //제품검사 기준치설정
	private String D_JptrSelect;                 //제품검사 성적서조회
	private String E_TrScanList;                 //성적서스캔 등록
	private String A_Basic;                      //기본관리
	private String B_Intermediate;               //중간검사성적서
	private String C_Rawmaterial;                //부자재인수검사성적서
	private String D_Product;                    //제품검사성적서
	private String E_Scan;                       //스캔리스트
	private String btn_add;                      //신규 버튼
	private String btn_print;                    //출력 버튼
	private String btn_upload;                   //업로드 버튼
	private String btn_pw_change;                //비밀번호 변경 버튼
	private String text_code_unknown;            //성적서 코드 미지정
	private String exup_jpcode;                  //엑셀 업로드 확인창 - 제품
	private String spcgbn;                       //특별특성
	private String strdat;                       //생산일/검사일
	private String message_jpcode_none;          //제품코드 미입력 메시지
	private String message_exup_none1_jp;        //엑셀 업로드 제품 및 버전 지정 오류 메시지
	private String message_process1_jp;          //엑셀 업로드 프로세스 메시지1_제품
	private String message_scan_not_accept;      //스캔 파일 미지원 양식 메시지
	private String message_scan_dup;             //스캔 파일 명칭 중복 메시지
	private String message_fileup_fail;          //파일 업로드 실패 메시지
	private String input_product;          		 //생산량
	private String text_404;          		 	 //404에러 메시지
	private String text_500;          		 	 //500에러 메시지
	private String minimun_val;          		 //최소값
	private String maximum_val;          		 //최대값
	private String avg_val;          		 	 //평균값
	private String cpk;          		 	     //CpK
	private String message_avg_undefined;        //유요하지 않은 평균값
}
