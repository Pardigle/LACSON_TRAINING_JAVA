package training;

public class Main {
	public static void main(String[] args) {
		PaymentMethod[] basket = {
			new CreditCard(1000, 123456789),
			new PayPal(1000, "Patrick.Lacson@ibm.com"),
			new BankTransfer(1000, 123456789, "Patrick Lacson")
		};
		
		for (PaymentMethod payment : basket) {
			payment.pay(500);
		}
	}
	
	public static void DayTwoMain() {
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
