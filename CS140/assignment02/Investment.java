package assignment02;
/**
   A class to monitor the growth of an investment that 
   accumulates interest at a fixed annual rate.
 */
public class Investment {
	private DollarsAndCents balance;
	private double rate;
	private int year;

	/**
	  Constructs an Investment object from a starting balance and
	  interest rate.
	  @param aBalance the starting balance
	  @param aRate the interest rate in percent
	 */
	public Investment(DollarsAndCents aBalance, double aRate) {
		balance = aBalance;
		rate = aRate;
	}

	/**
	  Keeps accumulating interest until a target balance has
	  been reached.
	  @param targetBalance the desired balance
	 */
	public void waitForBalance(DollarsAndCents targetBalance) {
		while(balance.lessThan(targetBalance)){
			year++;   
			DollarsAndCents interest = balance.upOrDown(rate / 100);
			balance = balance.add(interest);
		}
	}

	/**
	  Gets the current investment balance.
	  @return the current balance
	 */
	public DollarsAndCents getBalance() {
		return balance;
	}

	/**
	  Gets the number of years this investment has accumulated
	  interest.
	  @return the number of years since the start of the investment
	 */
	public int getYears() {
		return year;
	}
}