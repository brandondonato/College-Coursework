package assignment01;

public class Driver{

	public static void main(String[] args){

		Person frank = new Person("Frank", 11, 1, 1996);
		Person jimmy = new Person("Jimmy", 14, 2, 1998);
		Person jeremy = new Person("Jeremy", 15, 6, 1997);
		Person samir = new Person("Samir", 21, 11, 1998);

		HighSchool suffernHigh = new HighSchool("Suffern High School");
		HighSchool vestal = new HighSchool("Vestal High School");
		HighSchool maineEndwell = new HighSchool("Maine-Endwell High School");

		HighSchoolStudent jimmyHighSchool = new HighSchoolStudent(suffernHigh, jimmy);
		HighSchoolStudent jeremyHighSchool = new HighSchoolStudent(vestal, jeremy);
		HighSchoolStudent samirHighSchool = new HighSchoolStudent(maineEndwell, samir);

		University harvard = new University("Harvard University", 
														"Cambridge, MA");
		University mit = new University("Massachusetts Institute of Technology", 
												"Cambridge, MA");

		UniversityStudent jeremyUni = jeremyHighSchool.goToUniversity(harvard);
		UniversityStudent samirUni = samirHighSchool.goToUniversity(mit);

		Company google = new Company("Google" , "New York, NY");

		jeremyUni.getAJob(google, 135000.00);

		frank.printHistory();
		System.out.println("\n--------------------\n");
		jimmy.printHistory();
		System.out.println("\n--------------------\n");
		samir.printHistory();
		System.out.println("\n--------------------\n");
		jeremy.printHistory();
	}
}
