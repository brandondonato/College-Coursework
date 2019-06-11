package lab10;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Driver {
	public static void main(String[] args){
		Person test1 = new Person("John","Smith");
		Person test2 = new Person("Abby","Smith");
		Person test3 = new Person("Lokiana","Guy");
		Person test4 = new Person("Evil","Butts");
		Person test5 = new Person("John","Guy");
		Person test6 = new Person("Senor","Caonia");
		
		List<Person> people = new ArrayList<Person>();
		people.add(test1);
		people.add(test2);
		people.add(test3);
		people.add(test4);
		people.add(test5);
		people.add(test6);
		System.out.println("Sorting by name of people: \n");
		sortCopy(people);
		System.out.println("\n");
		
		Student test7 = new Student(test1, 1);
		Student test8 = new Student(test2, 2);
		Student test9 = new Student(test3, 3);
		Student test10 = new Student(test4, 4);
		Student test11 = new Student(test5, 4);
		Student test12 = new Student(test6, 2);
		
		List<Student> students = new ArrayList<Student>();
		students.add(test7);
		students.add(test8);
		students.add(test9);
		students.add(test10);
		students.add(test11);
		students.add(test12);
		System.out.println("Sorting by name by student: \n");
		sortCopy(students);
		System.out.println("\n");
		
		Comparator<Student> byStudents = Student::compareByGPA;
		Comparator<Student> byNameLength = Student::compareByNameLength;
		System.out.println("Sorting by GPA: \n");
		sortCopyBy(students, byStudents);
		System.out.println("\n");
		System.out.println("Sorting by name length: \n");
		sortCopyBy(students, byNameLength);
		System.out.println("\n");
		
		System.out.println("Sorting by decending GPA "
						+ "and ascending name length: \n");
		sortCopyBy(students, 
				byStudents.reversed().thenComparing(byNameLength));
		
	}
	public static <T extends Comparable<T>> void sortCopy(List<T> list){
		List<T> copy = new ArrayList<>(list);
		System.out.println(copy);
		Collections.sort(copy);
		System.out.println(copy);
	}
	
	public static <T> void sortCopyBy(List<T> list, Comparator<? super T> comp) {
	    List<T> copy = new ArrayList<>(list);
	    System.out.println(copy);
	    Collections.sort(copy, comp);
	    System.out.println(copy);
	}

		
	
}
