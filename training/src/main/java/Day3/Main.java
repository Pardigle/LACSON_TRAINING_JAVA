package Day3;

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
}
