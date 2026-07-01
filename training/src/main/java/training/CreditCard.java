package training;

public final class CreditCard extends OnlinePayment {
	private int cardNumber;
	
	public CreditCard(double balance, int accountNumber) {
		super(balance);
		this.cardNumber = accountNumber;
	}
	
	@Override
	public void pay(double amount) {
		if (balance > amount) {
			System.out.println("Card payment successful for " + Integer.toString(cardNumber));
		}
	}
}
