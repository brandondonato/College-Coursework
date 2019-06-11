package assignment04;
import java.time.LocalDate;
public class Person {
	private String name;
	private LocalDate birthdate;
	
	public Person(String aName, int day, int month, int year) {
		name = aName;
		birthdate = LocalDate.of(year, month, day);
	}
	public String getName() {
		return name;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
}
