package assignment04;
public class BankAccount {
	private double balance;
	private int id;
	private Person customer;
	private static int nextID;

	public BankAccount(double initialBalance) {
		balance = initialBalance;
		id = ++nextID;
	}
	public void deposit(double amount) {  
		balance += amount;
	}
	public void withdraw(double amount) { 
		balance -= amount;
	}
	public double getBalance() {   
		return balance;
	}

	public void setCustomer(Person p){
		customer = p;
	}

	public Person getCustomer(){
		return customer;
	}

	public int getId(){
		return id;
	}
}
