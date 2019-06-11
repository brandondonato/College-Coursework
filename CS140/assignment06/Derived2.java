package assignment06;

public class Derived2 extends BaseClass {
	
	private String field2;
	
	public String Dervied2(){
		field2 =  (field2 + " " + super.toString());
		return field2;
	}
	
	@Override
	public String toString(){
		return field2;
	}
}
