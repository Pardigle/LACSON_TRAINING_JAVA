package training;

public class Main {
	public static void main(String[] args) {
		Library library = new Library();
		
		library.showAllBooks();
		
		library.addBook(new Book("Harry Potter", "J.K. Rowling"));
		library.addBook(new Book("Heaven", "Mieko Kawakami"));
		library.addBook(new Book("The Giver", "Lois Lowry"));
		
		library.borrowBook("Harry Potter");
		library.returnBook("Harry Potter");
		
		library.showAllBooks();
	}
}
