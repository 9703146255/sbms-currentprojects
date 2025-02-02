package in.thiru.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import in.thiru.model.StudentCourseDetails;
@Service
public class ExcelReaderService {
	
	public List<StudentCourseDetails> readStudentDetailsFromExcel(String filePath) throws IOException {
        List<StudentCourseDetails> studentDetailsList = new ArrayList<>();
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row

            StudentCourseDetails details = new StudentCourseDetails();
            details.setStudentName(row.getCell(0).getStringCellValue());
            details.setEmail(row.getCell(1).getStringCellValue());
            details.setCourseName(row.getCell(2).getStringCellValue());
            details.setCourseDuration(row.getCell(3).getStringCellValue());
            details.setCourseCost(row.getCell(4).getNumericCellValue());
            details.setCourseStartDate(getCellValueAsDate(row.getCell(5)));
           

            studentDetailsList.add(details);
        }
        workbook.close();
        return studentDetailsList;
    }
	
	 private String getCellValueAsDate(Cell cell) {
	        if (cell == null) return "";
	        
	        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
	            // Get date value from the cell
	            Date date = cell.getDateCellValue();
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format the date as needed
	            return dateFormat.format(date);
	        } else if (cell.getCellType() == CellType.STRING) {
	            return cell.getStringCellValue(); // Return as string if it's already a string
	        }
	        return ""; // Default return for other types or null
	    }

}
