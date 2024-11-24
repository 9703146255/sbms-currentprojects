package in.thiru.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.thiru.model.StudentCourseDetails;
import in.thiru.service.EmailService;
import in.thiru.service.ExcelReaderService;

@RestController
@RequestMapping("/sendbulkemail")
public class BulkEmailController {
	  @Autowired
	    private EmailService emailService;

	    @Autowired
	    private ExcelReaderService excelReaderService;

	    @GetMapping
	    public String sendBulkEmail() {
	        try {
//	            List<StudentCourseDetails> studentDetailsList =  excelReaderService.readStudentDetailsFromExcel("path/to/your/student_course_details.xlsx");
	            
	        	List<StudentCourseDetails> studentDetailsList =  excelReaderService.readStudentDetailsFromExcel("C:\\Users\\hp\\Desktop\\SBMS-PROJECTS\\01-MailSenderApp\\src\\main\\resources\\student_course_details.xlsx");
	            //C:\Users\hp\Desktop\SBMS-PROJECTS\01-MailSenderApp\src\main\resources
	            for (StudentCourseDetails details : studentDetailsList) {
	                emailService.sendMail(details);
	            }
	            return "Emails sent successfully!";
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "Failed to send emails.";
	        }
	    }
}
