package genericCheckpointing.vistor;

import java.util.HashSet;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

public class PrimeVisitorImpl implements VisitorI {
	//This will be the hashSet populated by the visitor when visiting
	//	an object. If you come across a unique prime, map it into the
	//	hashSet.
	public HashSet<Long> hashSetL = new HashSet<Long>();
	public HashSet<Integer> hashSetI = new HashSet<Integer>();
	public HashSet<Short> hashSetS = new HashSet<Short>();
	public int numUniquePrimes = 0;
	
	/**
	 * This is the visit function that determines the number of prime
	 * values within a MATF instance
	 */
	@Override
	public void visit(MyAllTypesFirst first) {
		//Determine if the integer values are prime and unique
		int myInt = determineIsPrime(first.myInt);
		int myOtherInt = determineIsPrime(first.myOtherInt);
		
		int myShort = determineIsPrime(first.myShort);
		int myOtherShort = determineIsPrime(first.myOtherShort);
		
		int myLong = determineIsPrime(first.myLong);
		int myOtherLong = determineIsPrime(first.myOtherLong);
		
		//Calculate the number of unique primes
		int retSum = myInt + myOtherInt
					+ myShort + myOtherShort
					+ myLong + myOtherLong;
		
		//Add that number to the current number
		numUniquePrimes += retSum;
	}
	
	/**
	 * This is the visit function that determines the number of prime
	 * values within a MATS instance
	 */
	@Override
	public void visit(MyAllTypesSecond second) {
		//Determine if the integer values are prime and unique
		int myInt = determineIsPrime(second.myIntT);
		int myOtherInt = determineIsPrime(second.myOtherIntT);
		
		int myShort = determineIsPrime(second.myShortT);
		int myOtherShort = determineIsPrime(second.myOtherShortT);
		
		int myLong = determineIsPrime(second.myLongT);
		int myOtherLong = determineIsPrime(second.myOtherLongT);
		
		//Calculate the number of unique primes
		int retSum = myInt + myOtherInt
					+ myShort + myOtherShort
					+ myLong + myOtherLong;
		
		//Add that number to the current number
		numUniquePrimes += retSum;
	}
	
	//Determine if prime for ints
	public int determineIsPrime(int value) {
		if(value == 0) {
			return 0;
		}
		for(int i = 2; i < value/2; i++) {
			if( (value%i) == 0 ) {
				return 0;
			}
		}
		
		if(hashSetI.contains(value) ) {
			return 0;
		}else {
			hashSetI.add(value);
			//System.out.println("Int Prime Value: " + value);
			return 1;
		}
	}
	
	//Determine if prime for shorts
	public int determineIsPrime(short value) {
		if(value == 0) {
			return 0;
		}
		for(int i = 2; i < value/2; i++) {
			if( (value%i) == 0 ) {
				return 0;
			}
		}
		
		if(hashSetS.contains(value) ) {
			return 0;
		}else {
			hashSetS.add(value);
			//System.out.println("Short Prime Value: " + value);
			return 1;
		}
	}
	
	//Determine if primes for longs
	public int determineIsPrime(long value) {
		if(value == 0) {
			return 0;
		}
		for(int i = 2; i < value/2; i++) {
			if( (value%i) == 0 ) {
				return 0;
			}
		}
		
		if(hashSetL.contains(value) ) {
			return 0;
		}else {
			hashSetL.add(value);
			//System.out.println("Long Prime Value: " + value);
			return 1;
		}
	}
	
	public int getNumPrimes() {
		return numUniquePrimes;
	}
	
}
