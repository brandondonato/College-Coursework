package assignment06;
import assignment06.Question2;
import java.util.Random;

public class GraduateStudent extends Student {

	private String undergradMajor;
	
	/*
	public GraduateStudent(String inputMajor, String underMajor) {
		super(inputMajor);
		undergradMajor = underMajor;
	}
	*/
	
	@Override
	public boolean isGrad(){
		return true;
	}
	
	@Override
	public void adjustSchedule(){
		Random rand = new Random();
		int undergradCount = 0;
		Course[] courseList = getSchedule().toArray(new Course[getSchedule().size()]);
		for(int i = 0; i < courseList.length; i++){
			if(courseList[i].isUndergrad()){
				undergradCount += 1;
			}
		}
		while(undergradCount > 1){
			for(int i = 0; i < courseList.length; i++){
				if(undergradCount > 1){
					if(courseList[i].isUndergrad()){
						getSchedule().remove(courseList[i]);
						Course newClass = Question2.courseArray[rand.nextInt(600)];
						while(newClass.isUndergrad()){
							newClass = Question2.courseArray[rand.nextInt(600)];
						}
						getSchedule().add(newClass);
						undergradCount -= 1;
					}
				}
			}
		}
	}

	public boolean hasUndergradClass(){
		boolean retValue = false;
		Course[] courseList = getSchedule().toArray(new Course[getSchedule().size()]);
		for(int i = 0; i < courseList.length; i++){
			if(courseList[i].isUndergrad()){
				retValue = true;
			}
		}
		return retValue;
	}
}
