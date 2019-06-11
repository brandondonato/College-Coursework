package checkPrimesMultithreading.util;

public class IsPrime {


	/**
	 *
	 * @param input: The number we are seeing is prime or not
	 * @return Whether or not the number is prime
	 */

	public boolean isPrime(int input) {
		//For this assignment, if the number is not divisible by 2, it
		//	is a prime
		return( !(input%2 == 0) );
	}
}
