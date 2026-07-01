package Day2;

public class Book {
	private String title;
	private String author;
	private boolean available;
	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.available = true;
	}

	public Book(String title, String author, boolean available) {
		this.title = title;
		this.author = author;
		this.available = available;
	}
	
	public void borrowBook() {
		if (available) {
			available = false;
		} else {
			System.out.println("Book is already borrowed.");
		}
	}
	
	public void returnBook() {
		this.available = true;
	}
	
	public void getInfo() {
		System.out.println(String.format("Title: %s", title));
		System.out.println(String.format("Author: %s", author));
		System.out.print("Availability: ");
		String availability = available ? "Available" : "Not Available";
		System.out.println(availability);
	}
	
	public String getTitle() {
		return title;
	}
}
