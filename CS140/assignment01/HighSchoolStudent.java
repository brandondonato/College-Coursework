package assignment01;

public class HighSchoolStudent{

	private HighSchool highSchool;
	private Person person;

	public HighSchoolStudent(HighSchool school, Person student){
		highSchool = school;
		person = student;
		person.getHistory()[0] = this;
	}

	public UniversityStudent goToUniversity(University univ){
		UniversityStudent newStudent = new UniversityStudent(univ, person);
		return newStudent;
	}

	public HighSchool getHighSchool(){
		return highSchool;
	}

	public Person getName(){
		return person;
	}
}
