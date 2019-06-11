package lab10;

public class Person implements Comparable<Person>{

	private String firstname;
	private String lastname;
	
	public Person(String firstname, String lastname){
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString(){
		return lastname + ", " + firstname;
	}
	public int compareTo(Person other){
		int retValue = 0;
		if(lastname.equals(other.getLastname()) && firstname.equals(other.getFirstname())){
			System.out.println(1);
			retValue = 0;
		}else if(this.lastname.equals(other.getLastname())){
			retValue = firstname.compareTo(other.getFirstname());
		}else{
			retValue = lastname.compareTo(other.getLastname());
		}
		return retValue;
	}
	
	
}
