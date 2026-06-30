package training;
import java.util.ArrayList;

public class Library {
	private ArrayList<Book> booklist = new ArrayList<Book>();
	
	public Library() {}
	
	public void addBook(Book b) {
		booklist.add(b);
	}
	
	public void showAllBooks() {
		for (Book b : booklist) {
			b.getInfo();
		}
	}
	
	public void borrowBook(String title) {
		for (Book b : booklist) {
			if (b.getTitle() == title) {
				b.borrowBook();
			}
		}
	}
	
	public void returnBook(String title) {
		for (Book b : booklist) {
			if (b.getTitle() == title) {
				b.returnBook();
			}
		}
	}
}
