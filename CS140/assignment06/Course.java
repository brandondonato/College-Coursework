package assignment06;
import java.util.Set;
import java.util.HashSet;

public class Course {
	
	private static int nextCrn = 10001;
	private String title;
	private int crn;
	private Set<Student> enrollment;
	/*
	public Course(String inputTitle){
		title = inputTitle;
		crn = nextCrn;
		nextCrn += 1;
		enrollment = new HashSet<Student>();
	}
	*/
	
	public boolean isUndergrad(){
		throw new UnsupportedOperationException("No information "
				+ "about level of the course");
	}

	public boolean isGrad() {
		throw new UnsupportedOperationException("No information "
				+ "about level of the course");
	}
	
	public int getCrn(){
		return crn; 
	}
	
	public void tallyEnrollment(Student[] allStudents){ 
		for(int i = 0; i < allStudents.length; i++){
			if(allStudents[i].hasCourse(crn)){
				enrollment.add(allStudents[i]);
			}
		}
	}
	
	public int getEnrollment(){
		return enrollment.size();
	}
	
	
	
	
	
	
	
	
}
