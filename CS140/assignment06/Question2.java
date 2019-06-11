package assignment06;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;

public class Question2 {
	public static Course[] courseArray;
	public static Student[] studentArray;
    static {
    	Random rand = new Random();
    	courseArray = new Course[600];
    	studentArray = new Student[6000];
    	/*
    	for(int i = 0; i < 400; i++){//Number of Undergrad courses
    		courseArray[i] = new UndergradCourse(Question1.name(rand.nextInt(308915776)));
    	}
    	for(int i = 400; i < 600; i++){//Number of Grad courses
    		courseArray[i] = new GraduateCourse(Question1.name(rand.nextInt(308915776)));
    	}
    	for(int i = 0; i < 5000; i++){//Number of Undergrad students
    		studentArray[i] = new UndergradStudent(Question1.name(rand.nextInt(308915776)));
    	}
    	for(int i = 5000; i < 6000; i++){//Number of Grad students
    		studentArray[i] = new GraduateStudent(Question1.name(rand.nextInt(308915776)), 
    				Question1.name(rand.nextInt(308915776)));
    	}
    	*/
   
    	ArrayList<Course> list = new ArrayList<Course>(Arrays.asList(courseArray));
        Collections.shuffle(list);
        courseArray = list.toArray(courseArray);
        
        ArrayList<Student> list2 = new ArrayList<Student>(Arrays.asList(studentArray));
        Collections.shuffle(list2);
        studentArray = list2.toArray(studentArray);
    }

    
    public static void main(String[] args){
    	int maxClass = 0;
        int minClass = 6001;
        
        double totalClasses = 0;
        double totalStudents = 0;
        double averageClass = 0;
        
        Random rand = new Random();
        
        //Giving Students courses
        for(int i = 0; i < 6000; i++){
        	for(int j = 0; j < 4; j++){
        		studentArray[i].addCourse(courseArray[rand.nextInt(600)]);
        	}
        }
        
        //Max Class Size
        for(int i = 0; i < courseArray.length; i++){
        	courseArray[i].tallyEnrollment(studentArray);
        	if(courseArray[i].getEnrollment() > maxClass){
        		maxClass = courseArray[i].getEnrollment();
        	}
        }
        //System.out.println("The maximum class size is: " + maxClass);
        
        
        //Min Class Size
        for(int j = 0; j < courseArray.length; j++){
        	if((courseArray[j].getEnrollment() < minClass) && (courseArray[j].getEnrollment() > 0)){
        		minClass = courseArray[j].getEnrollment();
        	}
        }
        //System.out.println("The minimum class size is: "+minClass);
        
        //Calculate the Average Class Size
        for(int k = 0; k < 600; k++){
        	if(courseArray[k].getEnrollment() > 0){
        		totalStudents += courseArray[k].getEnrollment();
        		totalClasses += 1.0;
        	}
        }
        averageClass = (totalStudents)/(totalClasses);
        //System.out.println("The average class size is: "+averageClass);
        
        //Percent of Grads taking Undergrad Classes
        double totalGrads = 1000;
        double gradsInUndergrads = 0;
        double percentageOfGrads = 0;
        for(int l = 0; l < studentArray.length; l++){
        	studentArray[l].adjustSchedule();
        	if(studentArray[l].isGrad()){
        		if(((GraduateStudent)studentArray[l]).hasUndergradClass()){
        			gradsInUndergrads = gradsInUndergrads + 1;
        		}
        	}
        }
        percentageOfGrads = ((gradsInUndergrads)/(totalGrads) * 100);
        //System.out.println("The percent of graduate students taking an "
        //		+ "undergraduate class is: "+ percentageOfGrads + "%");
    }
 
}
