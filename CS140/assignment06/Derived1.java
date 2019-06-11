package assignment06;

public class Derived1 extends BaseClass{

	private String field1;
	
	public String Dervied1(){
		field1 = (super.toString()) + " " + field1;
		return field1;
	}
	
	@Override
	public String toString(){
		return field1;
	}
}
