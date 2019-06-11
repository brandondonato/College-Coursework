package assignment01;

public class Employee{

	private Company company;
	private double salary;
	private Person person;

	public Employee(Company work, double money, Person employee){
		company = work;
		salary = money;
		person = employee;
		person.getHistory()[2] = this;
	}

	public void setSalary(double newSalary){
		salary = newSalary;
	}

	public Company getCompany(){
		return company;
	}

	public double getSalary(){
		return salary;
	}

	public Person getEmployee(){
		return person;
	}

}