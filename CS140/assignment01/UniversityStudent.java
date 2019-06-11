package assignment01;

public class UniversityStudent{

	private University university;
	private Person person;

	public UniversityStudent(University uni, Person student){
		university = uni;
		person = student;
		person.getHistory()[1] = this;
	}

	public Employee getAJob(Company comp, double startingSalary){
		Employee newEmployee = new Employee(comp, startingSalary, person);
		return newEmployee;
	}

	public University getUniversity(){
		return university;
	}

	public Person getName(){
		return person;
	}

}

