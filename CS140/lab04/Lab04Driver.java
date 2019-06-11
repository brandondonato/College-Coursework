package lab04;

public class Lab04Driver{

	public static void main(String[] args){

		Library newLibrary = new Library();
		Book harryPotter = new Book("Harry Potter", 671);
		Book stephenKing = new Book("It", 892);
		Book doctorSeuss = new Book("The Cat in the Hat", 32);

		newLibrary.addBook(harryPotter);
		newLibrary.addBook(stephenKing);
		newLibrary.addBook(doctorSeuss);

		newLibrary.printLibrary();
		System.out.println("\n");

		System.out.println(newLibrary.averageLengthOfBooks());
		System.out.println("Expected average: 531.0\n");

		Book checkedOutBook = newLibrary.checkoutBook(0);

		newLibrary.printLibrary();
		System.out.println("The book checked out was " + checkedOutBook + "\n");


	}
}