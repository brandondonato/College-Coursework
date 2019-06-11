package assignment01;

public class University{

	private String name;
	private String city; 

	public University(String university, String location){
		name = university;
		city = location; 
	}

	public String getUniversityName(){
		return name;
	}

	public String getUniversityCity(){
		return city;
	}
}