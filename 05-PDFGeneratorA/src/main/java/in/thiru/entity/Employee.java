package in.thiru.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {
	
	 	@Id
	    private Long id;
	    private String name;
	    private String department;
	    private String designation;
	    private double salary;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getDesignation() {
			return designation;
		}
		public void setDesignation(String designation) {
			this.designation = designation;
		}
		public double getSalary() {
			return salary;
		}
		public void setSalary(double salary) {
			this.salary = salary;
		}
	    
		public Employee() {
			// TODO Auto-generated constructor stub
		}
		public Employee(Long id, String name, String department, String designation, double salary) {
			super();
			this.id = id;
			this.name = name;
			this.department = department;
			this.designation = designation;
			this.salary = salary;
		}
		@Override
		public String toString() {
			return "Employee [id=" + id + ", name=" + name + ", department=" + department + ", designation="
					+ designation + ", salary=" + salary + "]";
		}
		
		
	    

}
