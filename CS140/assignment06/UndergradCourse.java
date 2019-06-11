package assignment06;

public class UndergradCourse extends Course{

	/*
	public UndergradCourse(String inputTitle) {
		super(inputTitle);
	}
	*/

	@Override
	public boolean isUndergrad(){
		return true;
	}
	
	@Override
	public boolean isGrad(){
		return false;
	}
}
