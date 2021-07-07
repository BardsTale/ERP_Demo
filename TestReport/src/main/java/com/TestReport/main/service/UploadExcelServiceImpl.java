package com.TestReport.main.service;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TestReport.common.FileHandler;
import com.TestReport.configuration.exception.ExcelUploadException;
import com.TestReport.matr.dao.MatrMngDao;
import com.TestReport.mdtr.dao.MdtrMngDao;

@Service("UploadExcelService")
public class UploadExcelServiceImpl implements UploadExcelService {
	@Autowired
	public MdtrMngDao mdtrMngDao;
	@Autowired
	public MatrMngDao matrMngDao;
	
	public String matchCellType(Cell cell) {
		String value="";
		//셀이 빈값일경우를 위한 널체크
		if(cell==null) return "";
		//타입별로 내용 읽기
		switch (cell.getCellType()){
		case FORMULA:
    		switch(cell.getCachedFormulaResultType()) {
            case NUMERIC:
            	value = cell.getNumericCellValue()+"";
                break;
			default:
				value = cell.getStringCellValue();
				break;
    		}
    		break;
		case STRING:
			value=cell.getStringCellValue();
			break;
		case NUMERIC:
			if(DateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				value=df.format(cell.getDateCellValue())+"";
			}else {
				value=cell.getNumericCellValue()+"";
			}
			break;
        case BOOLEAN:
	        value=cell.getBooleanCellValue()+"";
	        break;
		case BLANK:
			value="";
			break;
		case ERROR:
			value="";
			break;
		default:
			value="";
			break;
		}
		return value;
	}
	
	public ResponseEntity<SortedMap<String, Object>> excelToTR(HashMap<String, String> param, FileInputStream inputStream){
		SortedMap<String, Object> result_map = new TreeMap<String, Object>();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		DataFormatter df = new DataFormatter();
		df.addFormat("m/d/yy", new java.text.SimpleDateFormat("yyyy-MM-dd"));
		int base_property_count = 0;
		List<HashMap<String, Object>> dataListBase = null;
		//성적서 base 조회 후 저장.
		if(param.get("JPCODE")==null) {
			base_property_count = 15;
			dataListBase = matrMngDao.selMatrAdminLeft(param);
			result_map.put("dataListBase",dataListBase);
		}else {
			base_property_count = 12;
			dataListBase = mdtrMngDao.selMdtrAdminLeft(param);
			result_map.put("dataListBase",dataListBase);
		}
		
		try {
			FileInputStream fis= inputStream;
			Workbook workbook = WorkbookFactory.create(fis);
			try {
				int sheet_cnt = workbook.getNumberOfSheets();
				for(int sheetIndex = 0; sheetIndex<sheet_cnt; sheetIndex++) {
					Sheet sheet = workbook.getSheetAt(sheetIndex);
					List<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();
					
					//헤드가 되는 첫 행 이후의 row들을 읽음.
					int nRowEndIndex = sheet.getPhysicalNumberOfRows();
					//base_property_count 바디 요소를 제외한 요소 수.
					if(nRowEndIndex-base_property_count != dataListBase.size()) {
						result_map.put("msg","선택하신 제품 버전의 기준치 갯수와<br>엑셀의 기준치가 갯수가 다릅니다.");
						return ResponseEntity.status(status).body(result_map);
					}
					for(int rowIndex = 1; rowIndex<nRowEndIndex; rowIndex++){
						HashMap<String, Object> cell_Obj = new HashMap<String, Object>();
						Row row=sheet.getRow(rowIndex);
						
						//cell Object 만듦
						for(int cellIndex = 0; cellIndex<20; cellIndex++) {
							Cell cell = row.getCell(cellIndex);
							String value = df.formatCellValue(cell);
							cell_Obj.put(Integer.toString((cellIndex+1)), value);
						}
						//만든 cell을 추가.
						rows.add(cell_Obj);
					}
					
					result_map.put(sheetIndex+"",rows);
				}
			}finally {
				if(workbook!=null){
					workbook.close();
				}
				inputStream.close();
				FileHandler.deleteHandler(param.get("FILENM"));
			}
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelUploadException(e);
		}
		return ResponseEntity.status(status).body(result_map);
	}
	
	public ResponseEntity<SortedMap<String, Object>> excelToJEPMST(HashMap<String, String> param, FileInputStream inputStream){
		SortedMap<String, Object> result_map = new TreeMap<String, Object>();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		DataFormatter df = new DataFormatter();
		df.addFormat("m/d/yy", new java.text.SimpleDateFormat("yyyy-MM-dd"));
		
		try {
			FileInputStream fis= inputStream;
			Workbook workbook = WorkbookFactory.create(fis);
			try {
				//첫 번째 시트만 사용.
				Sheet sheet = workbook.getSheetAt(0);
				List<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();
				
				//헤드가 되는 첫 행 이후의 row들을 읽음.
				int nRowEndIndex = sheet.getPhysicalNumberOfRows();
				for(int rowIndex = 1; rowIndex<nRowEndIndex; rowIndex++){
					HashMap<String, Object> cell_Obj = new HashMap<String, Object>();
					Row row=sheet.getRow(rowIndex);
					
					//cell Object 만듦
					cell_Obj.put("CORCOD", param.get("CORCOD"));
					cell_Obj.put("CORNAM", param.get("CORNAM"));
					cell_Obj.put("JPCODE", df.formatCellValue(row.getCell(0)));
					cell_Obj.put("JPNAME", df.formatCellValue(row.getCell(1)));
					cell_Obj.put("INPSBN", param.get("INPSBN"));
					//만든 cell을 추가.
					rows.add(cell_Obj);
				}
				
				result_map.put("rows",rows);
			}finally {
				if(workbook!=null){
					workbook.close();
				}
				inputStream.close();
				FileHandler.deleteHandler(param.get("FILENM"));
			}
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelUploadException(e);
		}
		return ResponseEntity.status(status).body(result_map);
	}
	
	public ResponseEntity<SortedMap<String, Object>> excelToBMTMST(HashMap<String, String> param, FileInputStream inputStream){
		SortedMap<String, Object> result_map = new TreeMap<String, Object>();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		DataFormatter df = new DataFormatter();
		df.addFormat("m/d/yy", new java.text.SimpleDateFormat("yyyy-MM-dd"));
		
		try {
			FileInputStream fis= inputStream;
			Workbook workbook = WorkbookFactory.create(fis);
			try {
				//첫 번째 시트만 사용.
				Sheet sheet = workbook.getSheetAt(0);
				List<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();
				
				//헤드가 되는 첫 행 이후의 row들을 읽음.
				int nRowEndIndex = sheet.getPhysicalNumberOfRows();
				for(int rowIndex = 1; rowIndex<nRowEndIndex; rowIndex++){
					HashMap<String, Object> cell_Obj = new HashMap<String, Object>();
					Row row=sheet.getRow(rowIndex);
					
					//cell Object 만듦
					cell_Obj.put("CORCOD", param.get("CORCOD"));
					cell_Obj.put("CORNAM", param.get("CORNAM"));
					cell_Obj.put("MATCOD", df.formatCellValue(row.getCell(0)));
					cell_Obj.put("MATNAM", df.formatCellValue(row.getCell(1)));
					cell_Obj.put("INPSBN", param.get("INPSBN"));
					//만든 cell을 추가.
					rows.add(cell_Obj);
				}
				
				result_map.put("rows",rows);
			}finally {
				if(workbook!=null){
					workbook.close();
				}
				inputStream.close();
				FileHandler.deleteHandler(param.get("FILENM"));
			}
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelUploadException(e);
		}
		return ResponseEntity.status(status).body(result_map);
	}
}
