package assignment06;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class Student {

	private static int nextID = 10001;
	private String name;
	private int id;
	private Set<Course> schedule;
	private String major;
	private ArrayList<Course> allCourses = new ArrayList<>();
	/*
	public Student(String inputMajor){
		major = inputMajor;
		id = nextID;
		nextID += 1;
		schedule = new HashSet<>();
		name = Question1.name(id);
	}
	*/
	
	protected Set<Course> getSchedule(){
		return schedule;
	}
	
	public boolean isUndergrad(){
		return false;
	}
	
	public boolean isGrad(){
		return false;
	}

	public void addCourse(Course newCourse){
		schedule.add(newCourse);
	}
	
	public void adjustSchedule(){};
	
	public boolean hasCourse(int crn){
		boolean retValue = false;
		Course[] courseList = this.getSchedule().toArray(new Course[getSchedule().size()]);
		for(int i = 0; i < courseList.length; i++){
			if(courseList[i].getCrn() == crn){
				retValue = true;
			}
		}
		return retValue;
	}
	
	public ArrayList<Course> getAllCourses(){
		return allCourses;
	}
	
}
