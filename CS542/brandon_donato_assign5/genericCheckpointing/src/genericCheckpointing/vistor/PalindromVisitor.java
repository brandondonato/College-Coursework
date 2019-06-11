package genericCheckpointing.vistor;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

public class PalindromVisitor implements VisitorI {
	//This is the variable that stores the number of palindromes
	//	seen while traversing the objects
	public int numPalindromes = 0;
	
	/**
	 * Go through each of the String fields in MyAllTypesFirst and
	 * determine whether or not they are palindromes
	 */
	@Override
	public void visit(MyAllTypesFirst first) {
		//Check each string to see if they are palindromes
		int myString = isPalindrome(first.myString);
		int myOtherString = isPalindrome(first.myOtherString);
		
		//Determine the number of palindromes
		int ret = myString + myOtherString;
		
		//Add the total number to the sum
		numPalindromes += ret;
	}

	/**
	 * Go through each of the String fields in MyAllTypesSecond and
	 * determine whether or not they are palindromes
	 */
	@Override
	public void visit(MyAllTypesSecond second) {
		//Check each string to see if they are palindromes
		int myString = isPalindrome(second.myStringT);
		int myOtherString = isPalindrome(second.myOtherStringT);
		
		//Determine the number of palindromes
		int ret = myString + myOtherString;
		
		//Add the total number to the sum
		numPalindromes += ret;
	}
	
	public int isPalindrome(String str) {
		//Use these as the left and right indicies
		int indexL = 0;
		int indexR = str.length() - 1;
		
		if( !(str.length() > 0 )) {
			return 0;
		}
		//While the indicies are in the right place
		while(indexL <= indexR) {
			if( str.charAt(indexL) != str.charAt(indexR) ) {
				return 0;
			}
			indexR--;
			indexL++;
		}
		
		//At this point, the number is a palindrome
		System.out.println("Palindrome: " + str);
		return 1;
	}
	
	public int getPalindromes() {
		return numPalindromes;
	}

}
