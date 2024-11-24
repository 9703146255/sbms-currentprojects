package in.thiru.model;

public class StudentCourseDetails {

	private String studentName;
	private String email;
	private String courseName;
	private String courseDuration;
	private double courseCost;
	private String courseStartDate;

	public StudentCourseDetails() {
		// TODO Auto-generated constructor stub
	}

	public StudentCourseDetails(String studentName, String email, String courseName, String courseDuration,
			double courseCost, String courseStartDate) {
		super();
		this.studentName = studentName;
		this.email = email;
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseCost = courseCost;
		this.courseStartDate = courseStartDate;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public double getCourseCost() {
		return courseCost;
	}

	public void setCourseCost(double courseCost) {
		this.courseCost = courseCost;
	}

	public String getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(String courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	@Override
	public String toString() {
		return "StudentCourseDetails [studentName=" + studentName + ", email=" + email + ", courseName=" + courseName
				+ ", courseDuration=" + courseDuration + ", courseCost=" + courseCost + ", courseStartDate="
				+ courseStartDate + "]";
	}
	
	

}
