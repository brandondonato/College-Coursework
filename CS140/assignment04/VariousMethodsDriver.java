package assignment04;
import java.util.ArrayList;
import java.util.Arrays;

public class VariousMethodsDriver{

	public static void main(String[] args){
		long[] t1 = {0,2,4,6,8,10};
		long[] t2 = {0,2,3,0};
		long[] t3 = null;
		long[] t4 = {};
		long[] t5 = {2};
		long[] t6 = {0,2,3,4,4,5,22};

		System.out.println(VariousMethods.oddEvenCount(t1));
		System.out.println("Expected Evens: 3\n");
		System.out.println(VariousMethods.oddEvenCount(t2));
		System.out.println("Expected Evens: 1\n");
		System.out.println(VariousMethods.oddEvenCount(t3));
		System.out.println("Expected Evens: -1\n");
		System.out.println(VariousMethods.oddEvenCount(t4));
		System.out.println("Expected Evens: 0\n");
		System.out.println(VariousMethods.oddEvenCount(t5));
		System.out.println("Expected Evens: 0\n");
		System.out.println(VariousMethods.oddEvenCount(t6));
		System.out.println("Expected Evens: 2\n");

		BankAccount t7 = new BankAccount(10);
		BankAccount t8 = new BankAccount(100);
		BankAccount t9 = new BankAccount(1000);
		BankAccount t10 = new BankAccount(1);
		Person frank = new Person("Frank", 11, 1, 1996);
		t7.setCustomer(frank);
		Person jimmy = new Person("Jimmy", 14, 2, 1998);
		t8.setCustomer(jimmy);
		Person jeremy = new Person("Jeremy", 15, 6, 1997);
		t9.setCustomer(jeremy);
		Person samir = new Person("Samir", 21, 11, 1998);
		t10.setCustomer(samir);


		ArrayList<BankAccount> list1 = new ArrayList<>(Arrays.asList(t7,t8,t9,t10));
		ArrayList<BankAccount> list2 = new ArrayList<>(Arrays.asList(t7,t8));
		ArrayList<BankAccount> list3 = null;
		ArrayList<BankAccount> list11 = new ArrayList<>(Arrays.asList(null,t7,null,t8,null));


		System.out.println(VariousMethods.minCount(50,list1));
		System.out.println("Expected Count: 2\n");
		System.out.println(VariousMethods.minCount(99, list2));
		System.out.println("Expected Count: 1\n");
		System.out.println(VariousMethods.minCount(100,list3));
		System.out.println("Expected Count: -1\n");

		BankAccount[] list4 = {t7,t8,t9,t10}; //t8,t10 even ids
		BankAccount[] list5 = {t8,t9,t10};
		BankAccount[] list6 = {t7};
		BankAccount[] list7 = null;
		BankAccount[] list8 = {t8,t7,t10,t9,t9,t9};
		BankAccount[] list9 = {t8,t8,t8,t8};
		BankAccount[] list10 = {};


		System.out.println(VariousMethods.justAnExercise(100, 'k', list4));
		System.out.println("Expected Balance: 100.0\n");
		System.out.println(VariousMethods.justAnExercise(100, 'J', list4));
		System.out.println("Expected Balance: 1000.0\n");
		System.out.println(VariousMethods.justAnExercise(100, 'j', list4));
		System.out.println("Expected Balance: 1000.0\n");
		System.out.println(VariousMethods.justAnExercise(100, 'k', list5));
		System.out.println("Expected Balance: -1.0 \n");
		System.out.println(VariousMethods.justAnExercise(100, 'k', list6));
		System.out.println("Expected Balance: 10.0 \n");
		System.out.println(VariousMethods.justAnExercise(100, 'k', list7));
		System.out.println("Expected Balance: -1.0\n");
		System.out.println(VariousMethods.justAnExercise(5, 'J', list8));
		System.out.println("Expected Balance: 1000.0 \n");
		System.out.println(VariousMethods.justAnExercise(12, 'l', list9));
		System.out.println("Expected Balance: 100.0 \n");
		System.out.println(VariousMethods.justAnExercise(100, 'k', list10));
		System.out.println("Expected Balance: -1.0 \n");
		System.out.println(VariousMethods.justAnExercise(100, 'J', list6));
		System.out.println("Expected Balance: 10.0 \n");





		System.out.println(VariousMethods.anotherExercise(list1));
		System.out.println("Expected Index: 2\n");
		System.out.println(VariousMethods.anotherExercise(list2));
		System.out.println("Expected Index: 1\n");
		System.out.println(VariousMethods.anotherExercise(list3));
		System.out.println("Expected Index: -1\n");
		System.out.println(VariousMethods.anotherExercise(list11));
		System.out.println("Expected Index: 3\n");


	}
}