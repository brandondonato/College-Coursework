package assignment01;

public class Company{

	private String name;
	private String city;

	public Company(String companyName, String workLocation){
		name = companyName;
		city = workLocation;
	}

	public String getName(){
		return name;
	}

	public String getCity(){
		return city;
	}
}