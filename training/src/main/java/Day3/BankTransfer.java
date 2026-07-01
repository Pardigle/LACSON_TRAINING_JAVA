package Day3;

public non-sealed class BankTransfer extends OnlinePayment{
	private int accountNumber;
	private String accountName;
	
	public BankTransfer(double balance, int accountNumber, String accountName) {
		super(balance);
		this.accountNumber = accountNumber;
		this.accountName = accountName;
	}
	
	@Override
	public void pay(double amount) {
		if (balance > amount) {
			System.out.println("Bank payment successful for " + accountName + " " + Integer.toString(accountNumber));
		}
	}
}
