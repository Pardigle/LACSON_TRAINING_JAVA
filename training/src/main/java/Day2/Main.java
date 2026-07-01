package Day2;

public class Main {
	public static void main(String[] args) {
		Library library = new Library();
		
		library.addBook(new Book("Harry Potter", "J.K. Rowling"));
		library.showAllBooks();
		
		library.addBook(new Book("Heaven", "Mieko Kawakami"));
		library.showAllBooks();
		
		library.addBook(new Book("The Giver", "Lois Lowry"));
		library.showAllBooks();
		
		library.borrowBook("Harry Potter");
		library.showAllBooks();
		
		library.returnBook("Harry Potter");
		library.showAllBooks();
	}
}
