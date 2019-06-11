package assignment06;

public class GraduateCourse extends Course{
	
	/*public GraduateCourse(String inputTitle){
		super(inputTitle);
	}
	*/
	
	@Override
	public boolean isUndergrad(){
		return false;
	}
	
	@Override
	public boolean isGrad(){
		return true;
	}
}
