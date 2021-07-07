package com.TestReport.common;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.TestReport.main.domain.CHASET_VO;
import com.TestReport.main.service.UploadExcelService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FileHandler {
	@Autowired 
	UploadExcelService uploadExcelService;
	
	final static String UPLOAD_DIRECTORY = "excel_TestReport/";
    public ResponseEntity<String> uploadHandler(List<MultipartFile> multiparts) {
    	CHASET_VO CHASET_VO = CharsetHandler.getInstance().getVO(); //캐릭터셋
    	ObjectMapper objectMapper = new ObjectMapper(); 
    	HashMap<String, Object> result_map = new HashMap<String, Object>();
    	HttpStatus status = HttpStatus.CREATED;
    	String msg = ""; // 오류 시에만 사용.
    	String result = "";
        try {
        	for(MultipartFile item : multiparts){
                if(!item.isEmpty()){
                	String name = item.getOriginalFilename();
                    if (!(name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".gif") || 
                    	name.toLowerCase().endsWith(".bmp") || name.toLowerCase().endsWith(".jpeg") || 
                    	name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".pdf")))
                    {
                        // check if file extension is allowed
                    	//.gif .jpg .jpeg .png .bmp .pdf 확장자만<br>업로드가 가능합니다!
                    	msg = CHASET_VO.getMessage_scan_not_accept();
                    	status = HttpStatus.BAD_REQUEST;
                        break;
                    } else if (new File(UPLOAD_DIRECTORY + File.separator + name).exists()) {
                        // check if file already exists.
                    	//같은 이름의 파일이 존재합니다.
                    	msg = CHASET_VO.getMessage_scan_dup();
                    	status = HttpStatus.BAD_REQUEST;
                    	break;
                    } else {
                	    item.transferTo( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }
            }
        	result_map.put("msg", msg);
        	result_map.put("status", status.value());
        	//jqxFileUpload를 사용하기 때문에 String형으로 만들어서 배출.
        	result = objectMapper.writeValueAsString(result_map);
        } catch (Exception ex) {
        	//파일 등록에 실패하였습니다.<br>
        	msg = CHASET_VO.getMessage_fileup_fail()+"[Error Type : "+ex.getClass().getSimpleName()+"]";
        	ex.getStackTrace();
        }
        return ResponseEntity.status(status).body(result);
    }
    
    public FileInputStream uploadHandler_excel(MultipartHttpServletRequest filelist) throws FileNotFoundException, IOException{
		Iterator<String> filenames = filelist.getFileNames(); 
		
		//MultipartFile을 poi에서 쓸 수 있도록 일반 FileInputStream으로 변경.
		MultipartFile mfile = filelist.getFile((String)filenames.next());
		String name = mfile.getOriginalFilename();
		File file = new File(UPLOAD_DIRECTORY + File.separator + name);
		mfile.transferTo(file);
		FileInputStream inputStream = new FileInputStream(file);
    	return inputStream;
    }
    
    public static void deleteHandler(String fileName) {
    	File file = new File(UPLOAD_DIRECTORY+fileName);
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("파일 : "+UPLOAD_DIRECTORY+fileName+" 삭제완료.");
			}else {
				System.out.println("파일 : "+UPLOAD_DIRECTORY+fileName+" 삭제실패.");
			}
		}
    }
  
}