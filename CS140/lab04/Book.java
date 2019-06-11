package lab04;

public class Book{

	private String title;
	private int numPages;

	public Book(String str, int anInt){
		if(anInt <= 0) throw new IllegalArgumentException("The number of pages must" +
															" be more than 0");
		title = str;
		numPages = anInt;
	}

	public String getTitle(){
		return title;
	}

	public int getNumPages(){
		return numPages;
	}

	public String toString(){
		return (title + " (" + numPages + ")");
	}

}