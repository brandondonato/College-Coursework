/**
* The FileProcessor class implements an application that reads through
* 	a text file and parses the input line-by-line.
* @author Brandon Donato
*/

package coursesRegistration.scheduler;
import coursesRegistration.util.FileProcessor;
//import coursesRegistration.util.StdoutDisplayInterface;
import coursesRegistration.util.FileDisplayInterface;
import coursesRegistration.util.Results;

import java.util.ArrayList;


public class Scheduler {
	/* These are the instance variables of the class */
	private FileProcessor studentPref;
	private FileProcessor courseInfo;
	private Course[] courseList = new Course[6];
	
	public Scheduler(FileProcessor studentP, FileProcessor courseI) {
		//Set the files for the course preferences and information
		studentPref = studentP;
		courseInfo = courseI;
	}
	
	/**
	 * This method will perform all of the scheduling based on class
	 * 	preferences and class availability. Using multiple queues for
	 * 	each level of student and for each course. T]
	 * 
	 * This will allow the proper prioritization for each course.
	 * 
	 * All courses will abide by a first-come, first-serve basis, 
	 * 	therefore classes with no priority between first, second, and
	 * 	third years will take the first number of students to sign up.
	 *	
	 *@return Nothing!
	 */
	public void scheduleClasses() {
		//Define the course string before parsing the input file
		String course = courseInfo.readNextLine(); 
		int counter = 0;
		//List of all students
		ArrayList<String> studentList = new ArrayList<String>();
		//Results object for the very end
		FileDisplayInterface finishedResults = new Results();
		
		
		//This while loop will parse the courseInfo file and create the
		//	courses and add them to the courseList variable
		while(course != null) {
			/*The split string is in the following format:
			   [Class, 'CAPACITY', Capacity, *space*, 'CLASS_TIMING', +
			    *space*, classtime] 
			
				Class = 0, Capacity = 2, class time = 6
			*/
			String[] courseArray = course.split("[-:; ]");
			String name = courseArray[0];
			int capacity = Integer.parseInt(courseArray[2]);
			int classtime = Integer.parseInt(courseArray[6]);
			
			//Create the new course based on the parsed information
			Course newCourse = new Course(name, capacity, classtime);
			//Add the new course to the course list
			courseList[counter] = newCourse;
			//Get the next file line
			course = courseInfo.readNextLine();
			counter++;
		}
		//Begin the parse the student file the same way as the course
		//	file
		String student = studentPref.readNextLine();
		//Student temporary information
		String[] preferences = new String[6];
		Year year;
		
		while(student != null) {
			//Split the input string to get the tokens we will use
			String[] studentArray = student.split("[,;: ]");
			//Temporary Variables to give to the Results class to print 
			//	out the results in the proper format
			String studentID;
			int[] courseTimes = new int[3];
			Course currentCourse = null;
			int coursesFilled = 0;
			
			//Setting the temporary variables
			studentID = studentArray[0]; 
			//Add students to student list
			studentList.add(studentID);
			//Place the preferences for each student
			preferences[0] = studentArray[1];
			preferences[1] = studentArray[3];
			preferences[2] = studentArray[5];
			preferences[3] = studentArray[7];
			preferences[4] = studentArray[9];
			preferences[5] = studentArray[11];
			//System.out.println(preferences[0] + preferences[1] + preferences[2]+ preferences[3]+ preferences[4]+ preferences[5]);
			year = Year.valueOf(studentArray[15]);
			
			//For every preference for each student
			for(int i = 0; i < preferences.length; i++) {
				if(coursesFilled == 3) {
					break;
				}
				//Get the current preference
				String preference = preferences[i];
				//Set the current course we will be working with
				for(int j = 0; j < courseList.length; j++) {
					if(courseList[j].getCourseID().equals(preference)) {
						currentCourse = courseList[j];
					}
				}
				
				//Begin handling the course preference
				if(preference.equals("E") || preference.equals("F")) {
					//Handles the case for the E or F course
					if(year == Year.FIRST_YEAR && (currentCourse.firstYearPosition != currentCourse.capacity) ) {
						currentCourse.firstYears[currentCourse.firstYearPosition] = studentID;
						currentCourse.firstYearPosition++;
						courseTimes[coursesFilled] = currentCourse.getClassTime();
						coursesFilled++;
					}else if(year == Year.SECOND_YEAR && (currentCourse.secondYearPosition != currentCourse.capacity) ) {
						currentCourse.secondYears[currentCourse.secondYearPosition] = studentID;
						currentCourse.secondYearPosition++;
						courseTimes[coursesFilled] = currentCourse.getClassTime();
						coursesFilled++;
					}else if(year == Year.THIRD_YEAR && (currentCourse.thirdYearPosition != currentCourse.capacity) ){
						currentCourse.thirdYears[currentCourse.thirdYearPosition] = studentID;
						currentCourse.thirdYearPosition++;
						courseTimes[coursesFilled] = currentCourse.getClassTime();
						coursesFilled++;
					}
				}else{
					//System.out.print("Student: " + studentID + " Preference: ");
					//System.out.print(preference + '\n');
					
					//Verify if the class is not full
					if( !currentCourse.courseIsFull() ) {
						//Verify the class time does not conflict with
						//	another class time
						if(currentCourse.getClassTime() != courseTimes[0] && 
								currentCourse.getClassTime() != courseTimes[1]) {
							
							//Add the student to the class!
							currentCourse.classList[currentCourse.currentCapacity] = studentID;
							currentCourse.currentCapacity++;
							courseTimes[coursesFilled] = currentCourse.getClassTime();
							coursesFilled++;
						}
					}
					
				}//End else statement	
			}//End for loop of preferences
			
			//System.out.println(year);
			student = studentPref.readNextLine();
		
		}
		//Begin processing the queues for courses E and F
		for(int i = 0; i < courseList.length; i++) {
			Course currentCourse;
			if(courseList[i].getCourseID().equals("E") || courseList[i].getCourseID().equals("F") ) {
				currentCourse = courseList[i];
				//Process the third years
				for(int j = 0; j < currentCourse.thirdYears.length; j++) {
					String studentID  = currentCourse.thirdYears[j];
					if(studentID != null && !currentCourse.courseIsFull() ){
						currentCourse.classList[currentCourse.currentCapacity] = studentID;
						currentCourse.currentCapacity++;
					}
				}
				//Process the second years
				for(int j = 0; j < currentCourse.secondYears.length; j++) {
					String studentID  = currentCourse.secondYears[j];
					if(studentID != null && !currentCourse.courseIsFull() ){
						currentCourse.classList[currentCourse.currentCapacity] = studentID;
						currentCourse.currentCapacity++;
					}
				}
				//Process the first years
				for(int j = 0; j < currentCourse.firstYears.length; j++) {
					String studentID  = currentCourse.firstYears[j];
					if(studentID != null && !currentCourse.courseIsFull() ){
						currentCourse.classList[currentCourse.currentCapacity] = studentID;
						currentCourse.currentCapacity++;
					}
				}
				
			}
			
		}
		
		//Creating the return line to be printed to stdout or a file
		for(int i =0; i < studentList.size(); i++) {
			String studentID = studentList.get(i);
			String courseOne = "";
			String courseTwo = "";
			String courseThree = "";
			int coursesFilled = 0;
			String retLine;
			//For each course
			for(int j = 0; j < courseList.length; j++) {
				//For each student in the course
				for(int k = 0; k < courseList[j].classList.length; k++) {
					//Break if all courses are filled
					if(coursesFilled == 3) {
						break;
					}
					//if courses aren't null
					else if(courseList[j].classList[k] != null) {
						if(courseList[j].classList[k].equals(studentID)) {
							if(coursesFilled == 0) {
								courseOne = courseList[j].getCourseID();
								coursesFilled++;
							}else if(coursesFilled == 1) {
								courseTwo = courseList[j].getCourseID();
								coursesFilled++;
							}else if(coursesFilled == 2) {
								courseThree = courseList[j].getCourseID();
								coursesFilled++;
							}
							
						}
					}
				}
			}
			if(courseOne.equals("")) {
				retLine = studentID + ":";
			}else if(courseTwo.equals("")) {
				retLine = studentID + ":" + courseOne; 
			}else if(courseThree.equals("")) {
				retLine = studentID + ":" + courseOne + "," + courseTwo;
			}else {
				retLine = studentID + ":" + courseOne + "," + courseTwo + "," + courseThree;
			}
			//Now begin to print the output to either stdout or a file
			finishedResults.printLineToFile(retLine, "registration_results.txt");
		}//End iteration for each student
	}
	
	
	/*
	 ******************************************************************
	 *	Getters, Setters and toString methods
	 */
	@Override
	public String toString() {
		return "Course Information File: " + courseInfo.getFile() + '\n' +
				"Student Preferences File: " + studentPref.getFile();
	}
	/**
	 * @return the studentPref
	 */
	public FileProcessor getStudentPref() {
		return studentPref;
	}
	/**
	 * @param studentPref the studentPref to set
	 */
	public void setStudentPref(FileProcessor studentPref) {
		this.studentPref = studentPref;
	}
	/**
	 * @return the courseInfo
	 */
	public FileProcessor getCourseInfo() {
		return courseInfo;
	}
	/**
	 * @param courseInfo the courseInfo to set
	 */
	public void setCourseInfo(FileProcessor courseInfo) {
		this.courseInfo = courseInfo;
	}
	/**
	 * @return the courseList
	 */
	public Course[] getCourseList() {
		return courseList;
	}
	/**
	 * @param courseList the courseList to set
	 */
	public void setCourseList(Course[] courseList) {
		this.courseList = courseList;
	}
	
}
/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
enum Year{
	FIRST_YEAR, SECOND_YEAR, THIRD_YEAR
}

/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
class Course{
	//All of the identifiers for the given course
	public String courseID;
	public String[] classList;
	public int classTime; 
	public int capacity;
	public int currentCapacity = 0;
	//The given queues for each grade for the given class
	public String[] firstYears;
	public int firstYearPosition;
	public String[] secondYears;
	public int secondYearPosition;
	public String[] thirdYears;
	public int thirdYearPosition;
	//If the class isn't E or F, it's just first come, first serve
	
	public Course(String courseName, int capacity, int time) {
		courseID = courseName;
		this.capacity = capacity;
		classList = new String[capacity];
		classTime = time;
		
		firstYears = new String[capacity];
		secondYears = new String[capacity];
		thirdYears = new String[capacity];
	}
	
	/**
	 * 
	 * @param student
	 * @return Boolean (if student is in the course listing)
	 */
	public boolean joinedCourse(String student) {
		for(int i = 0; i < classList.length; i++) {
			if(classList[i].equals(student) ) {
				return true;
			}
		}
		return false;
	}
	
	public boolean courseIsFull() {
		return (currentCapacity == (capacity) );
	}
	/*
	 ******************************************************************
	 * 	Getters, Setters and toString methods
	 */
	@Override
	public String toString() {
		return courseID;
	}
	/**
	 * @return the courseID
	 */
	public String getCourseID() {
		return courseID;
	}
	/**
	 * @param courseID the courseID to set
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	/**
	 * @return the classList
	 */
	public String[] getClassList() {
		return classList;
	}
	/**
	 * @param classList the classList to set
	 */
	public void setClassList(String[] classList) {
		this.classList = classList;
	}
	/**
	 * @return the classTime
	 */
	public int getClassTime() {
		return classTime;
	}
	/**
	 * @param classTime the classTime to set
	 */
	public void setClassTime(int classTime) {
		this.classTime = classTime;
	}
	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	/**
	 * @return the currentCapacity
	 */
	public int getCurrentCapacity() {
		return currentCapacity;
	}
	/**
	 * @param currentCapacity the currentCapacity to set
	 */
	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}
	/**
	 * @return the firstYears
	 */
	public String[] getFirstYears() {
		return firstYears;
	}
	/**
	 * @param firstYears the firstYears to set
	 */
	public void setFirstYears(String[] firstYears) {
		this.firstYears = firstYears;
	}
	/**
	 * @return the firstYearPosition
	 */
	public int getFirstYearPosition() {
		return firstYearPosition;
	}
	/**
	 * @param firstYearPosition the firstYearPosition to set
	 */
	public void setFirstYearPosition(int firstYearPosition) {
		this.firstYearPosition = firstYearPosition;
	}
	/**
	 * @return the secondYears
	 */
	public String[] getSecondYears() {
		return secondYears;
	}
	/**
	 * @param secondYears the secondYears to set
	 */
	public void setSecondYears(String[] secondYears) {
		this.secondYears = secondYears;
	}
	/**
	 * @return the secondYearPosition
	 */
	public int getSecondYearPosition() {
		return secondYearPosition;
	}
	/**
	 * @param secondYearPosition the secondYearPosition to set
	 */
	public void setSecondYearPosition(int secondYearPosition) {
		this.secondYearPosition = secondYearPosition;
	}
	/**
	 * @return the thirdYears
	 */
	public String[] getThirdYears() {
		return thirdYears;
	}
	/**
	 * @param thirdYears the thirdYears to set
	 */
	public void setThirdYears(String[] thirdYears) {
		this.thirdYears = thirdYears;
	}
	/**
	 * @return the thirdYearPosition
	 */
	public int getThirdYearPosition() {
		return thirdYearPosition;
	}
	/**
	 * @param thirdYearPosition the thirdYearPosition to set
	 */
	public void setThirdYearPosition(int thirdYearPosition) {
		this.thirdYearPosition = thirdYearPosition;
	}
}
