package assignment07;
import assignment06.UndergradCourse;

public class CSGenedCourse extends UndergradCourse {
	private CSGened[] geneds;
	
	public CSGenedCourse(CSGened[] array){
		geneds = array;
		
	}
	
	public CSGened[] getGeneds(){
		return geneds;
	}
}

