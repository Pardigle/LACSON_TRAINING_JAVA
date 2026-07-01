package training;

public sealed abstract class OnlinePayment implements PaymentMethod 
	permits CreditCard, PayPal, BankTransfer {
	
	protected double balance;
	
	public OnlinePayment(double balance) {
		this.balance = balance;
	}
	
	public void pay(double amount) {
		if (balance > amount) {
			System.out.println("Payment successful!");
		}
	}
}
