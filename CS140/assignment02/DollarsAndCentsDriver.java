package assignment02;

public class DollarsAndCentsDriver{
	
	public static void main(String [] args){
	
	DollarsAndCents test1 = new DollarsAndCents(10, 0);
	DollarsAndCents test2 = new DollarsAndCents(4, 101);
	DollarsAndCents test3 = new DollarsAndCents(0,0);
	DollarsAndCents test4 = null;
	DollarsAndCents test5 = new DollarsAndCents(4, 101);

		System.out.println(test1.add(test2));
		System.out.println("Expected: $15.01\n");
		System.out.println(test1.add(test3, test2, test2));
		System.out.println("Expected: $20.02\n");
		System.out.println(test1.lessThan(test2));
		System.out.println("Expected: False\n");
		System.out.println(test2.lessThan(test5));
		System.out.println("Expected: True\n");
		System.out.println(test2.subtract(test5));
		System.out.println("Expected: $4.99\n");
		System.out.println(test2.subtract(test3));
		System.out.println("Expected: $5.01\n");
		System.out.println(test1.upOrDown(2));
		System.out.println("Expected: $20.00\n");
	}
}