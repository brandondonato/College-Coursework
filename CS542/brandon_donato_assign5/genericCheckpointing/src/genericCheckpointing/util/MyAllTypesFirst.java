package genericCheckpointing.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import genericCheckpointing.vistor.VisitorI;

public class MyAllTypesFirst extends SerializableObject {
	//Hardcoding and creating the member types for reflection
	public int myInt;
	public long myLong;
	public String myString = "";
	public boolean myBool;
	public double myDouble;
	public float myFloat;
	public short myShort;
	public char myChar;
	
	public int myOtherInt;
	public long myOtherLong;
	public String myOtherString = "";
	public boolean myOtherBool;
	public double myOtherDouble;
	public float myOtherFloat;
	public short myOtherShort;
	public char myOtherChar;
	
	/**
	 * This is the default constructor used to generate the instance of 
	 * each class
	 */
	public MyAllTypesFirst() {
		myInt = 167;
		myShort = 181;
		
		myString = "abba";
	}
	/**
	 * 
	 * @param index: Used for a random seed when creating random values
	 * 				for each variable
	 * 
	 * This constructor takes a value and uses it as a seed and creates 
	 * a random value from
	 */
	public MyAllTypesFirst(int index) {
		//Create a Random Class reference to randomize values
		Random rand = new Random();
		
		//Set the string values equal to some number
		myString = "Index number: " + index;
		myOtherString = "Still index: " + index;
		
		//Set the char to some value between 0 and the length of the
		//	alphabet
		String alphabet = "0123456789qwertyuiop[]asdfghjkl;'zxcvbnm,."
				+ "!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKLZXCVBNM?";
		myChar = alphabet.charAt( rand.nextInt( alphabet.length() ) );
		myOtherChar = alphabet.charAt( rand.nextInt( alphabet.length() ) );
		
		//Set the values for short, int, float, long, double to random 
		//	values
		myInt = rand.nextInt(1000);
		myOtherInt = rand.nextInt(1000);
		
		myShort = (short)rand.nextInt(1000);
		myOtherShort = (short)rand.nextInt(1000);
		
		myFloat = rand.nextFloat() * 1000;
		myOtherFloat = rand.nextFloat() * 1000;
		
		myLong = ThreadLocalRandom.current().nextLong(1000);
		myOtherLong = ThreadLocalRandom.current().nextLong(1000);
		
		myDouble = rand.nextDouble() * 1000;
		myOtherDouble = rand.nextDouble() * 1000;
		
		//Set the boolean to some random value
		myBool = rand.nextBoolean();
		myOtherBool = rand.nextBoolean();
	}
	
	//Generated the getters and setters for the member variables
	/**
	 * @return the myInt
	 */
	public int getmyInt() {
		return myInt;
	}
	/**
	 * @param myInt the myInt to set
	 */
	public void setmyInt(int myInt) {
		this.myInt = myInt;
	}
	/**
	 * @return the myLong
	 */
	public long getmyLong() {
		return myLong;
	}
	/**
	 * @param myLong the myLong to set
	 */
	public void setmyLong(long myLong) {
		this.myLong = myLong;
	}
	/**
	 * @return the myString
	 */
	public String getmyString() {
		return myString;
	}
	/**
	 * @param myString the myString to set
	 */
	public void setmyString(String myString) {
		this.myString = myString;
	}
	/**
	 * @return the myBool
	 */
	public boolean ismyBool() {
		return myBool;
	}
	/**
	 * @param myBool the myBool to set
	 */
	public void setmyBool(boolean myBool) {
		this.myBool = myBool;
	}
	/**
	 * @return the myOtherInt
	 */
	public int getmyOtherInt() {
		return myOtherInt;
	}
	/**
	 * @param myOtherInt the myOtherInt to set
	 */
	public void setmyOtherInt(int myOtherInt) {
		this.myOtherInt = myOtherInt;
	}
	/**
	 * @return the myOtherLong
	 */
	public long getmyOtherLong() {
		return myOtherLong;
	}
	/**
	 * @param myOtherLong the myOtherLong to set
	 */
	public void setmyOtherLong(long myOtherLong) {
		this.myOtherLong = myOtherLong;
	}
	/**
	 * @return the myOtherString
	 */
	public String getmyOtherString() {
		return myOtherString;
	}

	/**
	 * @param myOtherString the myOtherString to set
	 */
	public void setmyOtherString(String myOtherString) {
		this.myOtherString = myOtherString;
	}

	/**
	 * @return the myOtherBool
	 */
	public boolean ismyOtherBool() {
		return myOtherBool;
	}

	/**
	 * @param myOtherBool the myOtherBool to set
	 */
	public void setmyOtherBool(boolean myOtherBool) {
		this.myOtherBool = myOtherBool;
	}
	
	/**
	 * This is the toString method
	 */
	@Override
	public String toString() {
		String retString = "MyAllTypesFirst Object with values:\n";
		retString += "myInt: " + myInt + "\n";
		retString += "myLong: " + myLong + "\n";
		retString += "myString: " + myString + "\n";
		retString += "myBool: " + myBool + "\n";
		retString += "myDouble: " + myDouble + "\n";
		retString += "myShort: " + myShort + "\n";
		retString += "myFloat: " + myFloat + "\n";
		retString += "myChar: " + myChar + "\n";
		
		
		retString += "myOtherInt: " + myOtherInt + "\n";
		retString += "myOtherLong: " + myOtherLong + "\n";
		retString += "myOtherString: " + myOtherString + "\n";
		retString += "myOtherBool: " + myOtherBool + "\n";
		retString += "myOtherDouble: " + myOtherDouble + "\n";
		retString += "myOtherShort: " + myOtherShort + "\n";
		retString += "myOtherFloat: " + myOtherFloat + "\n";
		retString += "myOtherChar: " + myOtherChar + "\n";
		
		return(retString);
	}
	/**
	 * @return the myDouble
	 */
	public double getmyDouble() {
		return myDouble;
	}
	/**
	 * @param myDouble the myDouble to set
	 */
	public void setmyDouble(double myDouble) {
		this.myDouble = myDouble;
	}
	/**
	 * @return the myFloat
	 */
	public float getmyFloat() {
		return myFloat;
	}
	/**
	 * @param myFloat the myFloat to set
	 */
	public void setmyFloat(float myFloat) {
		this.myFloat = myFloat;
	}
	/**
	 * @return the myShort
	 */
	public short getmyShort() {
		return myShort;
	}
	/**
	 * @param myShort the myShort to set
	 */
	public void setmyShort(short myShort) {
		this.myShort = myShort;
	}
	/**
	 * @return the myChar
	 */
	public char getmyChar() {
		return myChar;
	}
	/**
	 * @param myChar the myChar to set
	 */
	public void setmyChar(char myChar) {
		this.myChar = myChar;
	}
	/**
	 * @return the myOtherDouble
	 */
	public double getmyOtherDouble() {
		return myOtherDouble;
	}
	/**
	 * @param myOtherDouble the myOtherDouble to set
	 */
	public void setmyOtherDouble(double myOtherDouble) {
		this.myOtherDouble = myOtherDouble;
	}
	/**
	 * @return the myOtherFloat
	 */
	public float getmyOtherFloat() {
		return myOtherFloat;
	}
	/**
	 * @param myOtherFloat the myOtherFloat to set
	 */
	public void setmyOtherFloat(float myOtherFloat) {
		this.myOtherFloat = myOtherFloat;
	}
	/**
	 * @return the myOtherShort
	 */
	public short getmyOtherShort() {
		return myOtherShort;
	}
	/**
	 * @param myOtherShort the myOtherShort to set
	 */
	public void setmyOtherShort(short myOtherShort) {
		this.myOtherShort = myOtherShort;
	}
	/**
	 * @return the myOtherChar
	 */
	public char getmyOtherChar() {
		return myOtherChar;
	}
	/**
	 * @param myOtherChar the myOtherChar to set
	 */
	public void setmyOtherChar(char myOtherChar) {
		this.myOtherChar = myOtherChar;
	}
	/**
	 * This is the overridden hashCode implementation which returns
	 * a unique hashCode for each MyAllTypesFirst
	 */
	@Override
	public int hashCode() {
		int result = 17;
		
		result = 31 * result + myString.hashCode();
		result = 31 * result + myOtherString.hashCode();
		
		result = 31 * result + myInt;
		result = 31 * result + myOtherInt;
		
		result = 31 * result + Float.floatToIntBits(myFloat);
		result = 31 * result + Float.floatToIntBits(myOtherFloat);
		
		result = 31 * result + (myBool ? 1:0);
		result = 31 * result + (myOtherBool ? 1:0);
		
		result = 31 * result + Double.valueOf(myDouble).hashCode();
		result = 31 * result + Double.valueOf(myOtherDouble).hashCode();
		
		result = 31 * result + myShort;
		result = 31 * result + myOtherShort;
		
		result = 31 * result + Character.hashCode(myChar);
		result = 31 * result + Character.hashCode(myOtherChar);
		
		result = 31 * result + Long.valueOf(myLong).hashCode();
		result = 31 * result + Long.valueOf(myOtherLong).hashCode();
		return result;
	}
	
	/**
	 * This is the overridden equals operator to compare two objects
	 */
	@Override
	public boolean equals(Object obj) {
		//Verify the objects types are the same
		if(!(obj instanceof MyAllTypesFirst) ) {
			return false;
		}
		
		//Cast the object for comparison
		MyAllTypesFirst compare = (MyAllTypesFirst)obj;
		
		//Compare all values to see if they are all the same
		return  (this.myString.equals(compare.myString) 
				&& this.myOtherString.equals(compare.myOtherString)
				
				&& this.myInt == compare.myInt
				&& this.myOtherInt == compare.myOtherInt
				
				&& this.myFloat == compare.myFloat
				&& this.myOtherFloat == compare.myOtherFloat
				
				&& this.myBool == compare.myBool
				&& this.myOtherBool == compare.myOtherBool
				
				&& this.myShort == compare.myShort
				&& this.myOtherShort == compare.myOtherShort
				
				&& this.myChar == compare.myChar
				&& this.myOtherChar == compare.myOtherChar
				
				&& this.myLong == compare.myLong
				&& this.myOtherLong == compare.myOtherLong);
	}
	
	/**
	 * Accepting the visitor implementation
	 */
	public void accept(VisitorI visitor) {
		visitor.visit(this);
	}
}
