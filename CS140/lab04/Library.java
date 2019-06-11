package lab04;
import java.util.ArrayList;

public class Library{

	private ArrayList<Book> bookList;

	public Library(){
		bookList = new ArrayList<>();
	}

	public void addBook(Book b){
		if(b != null){
			bookList.add(b);
		}
	}

	public int numBooks(){
		return bookList.size();
	}

	public void printLibrary(){
		System.out.println(bookList);
	}

	public double averageLengthOfBooks(){
		int bookPages = 0;
		double averagePages = -1.0;
		if(bookList.size() > 0){
			for(Book book : bookList){
				bookPages += book.getNumPages();
			}
			averagePages = (bookPages/bookList.size());
			return averagePages;
		}else{
			return averagePages;
		}
	}

	public Book checkoutBook(int index){
		Book tempBook = bookList.get(index);
		bookList.remove(index);
		return tempBook;
	}


}