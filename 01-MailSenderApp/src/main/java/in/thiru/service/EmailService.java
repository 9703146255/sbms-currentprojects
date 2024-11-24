package in.thiru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import in.thiru.model.StudentCourseDetails;

@Service
public class EmailService {
	
	 @Autowired
	    private JavaMailSender mailSender;

	    public void sendMail(StudentCourseDetails details) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(details.getEmail());
	        message.setSubject("Course Enrollment: " + details.getCourseName());

	        String text = String.format(
	                "Dear %s,\n\nWe are excited to inform you that you have been enrolled in the course \"%s\".\n\n" +
	                "Course Details:\n" +
	                "- Duration: %s\n" +
	                "- Cost: $%.2f\n" +
	                "- Start Date: %s\n\n" +
	                "Looking forward to a successful learning experience!\n\nBest regards,\nYour Institute",
	                details.getStudentName(), details.getCourseName(), details.getCourseDuration(),
	                details.getCourseCost(), details.getCourseStartDate()
	        );
	        message.setText(text);

	        mailSender.send(message);
	    }

}
