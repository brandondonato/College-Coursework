package lab10;

public class Student implements Comparable<Student> {

	private Person person;
	private int GPA;
	
	public Student(Person person, int gPA) {
		this.person = person;
		GPA = gPA;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public int getGPA() {
		return GPA;
	}
	public void setGPA(int gPA) {
		GPA = gPA;
	}
	@Override
	public String toString(){
		return person.toString() + " (" + GPA + ")";
	}
	
	public int compareTo(Student other){
		return this.getPerson().compareTo(other.getPerson());
	}
	
	public static int compareByGPA(Student a, Student b) {
	    return a.GPA - b.GPA;
	}
	
	public static int compareByNameLength(Student a, Student b){
		int studentA = a.getPerson().getFirstname().length() + 
				       a.getPerson().getLastname().length();
		int studentB = b.getPerson().getFirstname().length() + 
			           b.getPerson().getLastname().length();
		return studentA - studentB;
	}
}
