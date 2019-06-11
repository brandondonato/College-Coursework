package assignment06;

import java.util.Iterator;
import java.util.Set;
import java.util.Random;

public class UndergradStudent extends Student {

	/*
	public UndergradStudent(String inputMajor) {
		super(inputMajor);
	}
	*/

	@Override
	public boolean isUndergrad(){
		return true;
	}
	
	@Override
	public void adjustSchedule(){
		Random rand = new Random();
		int gradCount = 0;
		Course[] courseList = getSchedule().toArray(new Course[getSchedule().size()]);
		for(int i = 0; i < courseList.length; i++){
			if(courseList[i].isGrad()){
				gradCount += 1;
			}
		}
		while(gradCount > 2){
			for(int i = 0; i < courseList.length; i++){
				if(courseList[i].isGrad()){
					getSchedule().remove(courseList[i]);
					Course newClass = Question2.courseArray[rand.nextInt(600)];
					while(newClass.isGrad()){
						newClass = Question2.courseArray[rand.nextInt(600)];
					}
					getSchedule().add(newClass);
					gradCount -= 1;
				}
			}
		}
	}

	
}
