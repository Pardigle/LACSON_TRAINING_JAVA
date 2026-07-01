package training;

public final class PayPal extends OnlinePayment {
	private String email;
	
	public PayPal(double balance, String email) {
		super(balance);
		this.email = email;
	}
	
	@Override
	public void pay(double amount) {
		if (balance > amount) {
			System.out.println("PayPal payment successful for " + email);
		}
	}
}
