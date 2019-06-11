package genericCheckpointing.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import genericCheckpointing.vistor.VisitorI;

public class MyAllTypesSecond extends SerializableObject {
	//Hardcoding and creating the member types for reflection
	public double myDoubleT;
	public float myFloatT;
	public short myShortT;
	public char myCharT;
	public int myIntT;
	public long myLongT;
	public String myStringT = "";
	public boolean myBoolT;
	
	public double myOtherDoubleT;
	public float myOtherFloatT;
	public short myOtherShortT;
	public char myOtherCharT;
	public int myOtherIntT;
	public long myOtherLongT;
	public String myOtherStringT = "";
	public boolean myOtherBoolT;
	
	public MyAllTypesSecond() {}
	
	public MyAllTypesSecond(int index) {
		//Create a Random Class reference to randomize values
		Random rand = new Random();
		
		//Set the string values equal to some number
		myStringT = "Index number: " + index;
		myOtherStringT = "Still index: " + index;
		
		//Set the char to some value between 0 and the length of the
		//	alphabet
		String alphabet = "0123456789qwertyuiop[]asdfghjkl;'zxcvbnm,."
				+ "!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKLZXCVBNM?";
		myCharT = alphabet.charAt( rand.nextInt( alphabet.length() ) );
		myOtherCharT = alphabet.charAt( rand.nextInt( alphabet.length() ) );
		
		//Set the values for short, int, float, long, double to random 
		//	values
		myIntT = rand.nextInt(1000);
		myOtherIntT = rand.nextInt(1000);
		
		myShortT = (short)rand.nextInt(1000);
		myOtherShortT = (short)rand.nextInt(1000);
		
		myFloatT = rand.nextFloat() * 1000;
		myOtherFloatT = rand.nextFloat() * 1000;
		
		myLongT = ThreadLocalRandom.current().nextLong(1000);
		myOtherLongT = ThreadLocalRandom.current().nextLong(1000);
		
		myDoubleT = rand.nextDouble() * 1000;
		myOtherDoubleT = rand.nextDouble() * 1000;
		
		//Set the boolean to some random value
		myBoolT = rand.nextBoolean();
		myOtherBoolT = rand.nextBoolean();
	}
	//Generating getters and setters for the member variables
	/**
	 * @return the myDoubleT
	 */
	public double getmyDoubleT() {
		return myDoubleT;
	}
	/**
	 * @param myDoubleT the myDoubleT to set
	 */
	public void setmyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}
	/**
	 * @return the myFloatT
	 */
	public float getmyFloatT() {
		return myFloatT;
	}
	/**
	 * @param myFloatT the myFloatT to set
	 */
	public void setmyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}
	/**
	 * @return the myShortT
	 */
	public short getmyShortT() {
		return myShortT;
	}
	/**
	 * @param myShortT the myShortT to set
	 */
	public void setmyShortT(short myShortT) {
		this.myShortT = myShortT;
	}
	/**
	 * @return the myCharT
	 */
	public char getmyCharT() {
		return myCharT;
	}
	/**
	 * @param myCharT the myCharT to set
	 */
	public void setmyCharT(char myCharT) {
		this.myCharT = myCharT;
	}
	/**
	 * @return the myOtherDouble
	 */
	public double getmyOtherDoubleT() {
		return myOtherDoubleT;
	}
	/**
	 * @param myOtherDouble the myOtherDouble to set
	 */
	public void setmyOtherDoubleT(double myOtherDouble) {
		this.myOtherDoubleT = myOtherDouble;
	}
	/**
	 * @return the myOtherFloatT
	 */
	public float getmyOtherFloatT() {
		return myOtherFloatT;
	}
	/**
	 * @param myOtherFloatT the myOtherFloatT to set
	 */
	public void setmyOtherFloatT(float myOtherFloatT) {
		this.myOtherFloatT = myOtherFloatT;
	}
	/**
	 * @return the myOtherShortT
	 */
	public short getmyOtherShortT() {
		return myOtherShortT;
	}
	/**
	 * @param myOtherShortT the myOtherShortT to set
	 */
	public void setmyOtherShortT(short myOtherShortT) {
		this.myOtherShortT = myOtherShortT;
	}
	/**
	 * @return the myOtherCharT
	 */
	public char getmyOtherCharT() {
		return myOtherCharT;
	}
	/**
	 * @param myOtherCharT the myOtherCharT to set
	 */
	public void setmyOtherCharT(char myOtherCharT) {
		this.myOtherCharT = myOtherCharT;
	}
	/**
	 * This is the toString method
	 */
	@Override
	public String toString() {
		String retString = "MyAllTypesSecond Object with values:\n";
		retString += "myDoubleT: " + myDoubleT + "\n";
		retString += "myFloatT: " + myFloatT + "\n";
		retString += "myShortT: " + myShortT + "\n";
		retString += "myCharT: " + myCharT + "\n";
		retString += "myIntT: " + myIntT + "\n";
		retString += "myLongT: " + myLongT + "\n";
		retString += "myStringT: " + myStringT + "\n";
		retString += "myBoolT: " + myBoolT + "\n";
		
		retString += "myOtherDoubleT: " + myOtherDoubleT + "\n";
		retString += "myOtherFloatT: " + myOtherFloatT + "\n";
		retString += "myOtherShortT: " + myOtherShortT + "\n";
		retString += "myOtherCharT: " + myOtherCharT + "\n";
		retString += "myOtherIntT: " + myOtherIntT + "\n";
		retString += "myOtherLongT: " + myOtherLongT + "\n";
		retString += "myOtherStringT: " + myOtherStringT + "\n";
		retString += "myOtherBoolT: " + myOtherBoolT + "\n";
		
		return(retString);
	}
	/**
	 * @return the myIntT
	 */
	public int getmyIntT() {
		return myIntT;
	}
	/**
	 * @param myIntT the myIntT to set
	 */
	public void setmyIntT(int myIntT) {
		this.myIntT = myIntT;
	}
	/**
	 * @return the myLongT
	 */
	public long getmyLongT() {
		return myLongT;
	}
	/**
	 * @param myLongT the myLongT to set
	 */
	public void setmyLongT(long myLongT) {
		this.myLongT = myLongT;
	}
	/**
	 * @return the myStringT
	 */
	public String getmyStringT() {
		return myStringT;
	}
	/**
	 * @param myStringT the myStringT to set
	 */
	public void setmyStringT(String myStringT) {
		this.myStringT = myStringT;
	}
	/**
	 * @return the myBoolT
	 */
	public boolean ismyBoolT() {
		return myBoolT;
	}
	/**
	 * @param myBoolT the myBoolT to set
	 */
	public void setmyBoolT(boolean myBoolT) {
		this.myBoolT = myBoolT;
	}
	/**
	 * @return the myOtherIntT
	 */
	public int getmyOtherIntT() {
		return myOtherIntT;
	}
	/**
	 * @param myOtherIntT the myOtherIntT to set
	 */
	public void setmyOtherIntT(int myOtherIntT) {
		this.myOtherIntT = myOtherIntT;
	}
	/**
	 * @return the myOtherLongT
	 */
	public long getmyOtherLongT() {
		return myOtherLongT;
	}
	/**
	 * @param myOtherLongT the myOtherLongT to set
	 */
	public void setmyOtherLongT(long myOtherLongT) {
		this.myOtherLongT = myOtherLongT;
	}
	/**
	 * @return the myOtherStringT
	 */
	public String getmyOtherStringT() {
		return myOtherStringT;
	}
	/**
	 * @param myOtherStringT the myOtherStringT to set
	 */
	public void setmyOtherStringT(String myOtherStringT) {
		this.myOtherStringT = myOtherStringT;
	}
	/**
	 * @return the myOtherBoolT
	 */
	public boolean ismyOtherBoolT() {
		return myOtherBoolT;
	}
	/**
	 * @param myOtherBoolT the myOtherBoolT to set
	 */
	public void setmyOtherBoolT(boolean myOtherBoolT) {
		this.myOtherBoolT = myOtherBoolT;
	}
	/**
	 * This is the overridden hashCode function that returns a unique
	 * 	hashCode for each instance of myAllTypesSecond
	 */
	@Override
	public int hashCode() {
		int result = 17;
		
		result = 31 * result + myStringT.hashCode();
		result = 31 * result + myOtherStringT.hashCode();
		
		result = 31 * result + myIntT;
		result = 31 * result + myOtherIntT;
		
		result = 31 * result + Float.floatToIntBits(myFloatT);
		result = 31 * result + Float.floatToIntBits(myOtherFloatT);
		
		result = 31 * result + (myBoolT ? 1:0);
		result = 31 * result + (myOtherBoolT ? 1:0);
		
		result = 31 * result + Double.valueOf(myDoubleT).hashCode();
		result = 31 * result + Double.valueOf(myOtherDoubleT).hashCode();
		
		result = 31 * result + myShortT;
		result = 31 * result + myOtherShortT;
		
		result = 31 * result + Character.hashCode(myCharT);
		result = 31 * result + Character.hashCode(myOtherCharT);
		
		result = 31 * result + Long.valueOf(myLongT).hashCode();
		result = 31 * result + Long.valueOf(myOtherLongT).hashCode();
		return result;
	}
	
	/**
	 * This is the overridden equals operator to compare two objects
	 */
	@Override
	public boolean equals(Object obj) {
		//Verify the objects types are the same
		if(!(obj instanceof MyAllTypesSecond) ) {
			return false;
		}
		
		MyAllTypesSecond compare = (MyAllTypesSecond)obj;
		
		//Compare all values to see if they are all the same
		return  (this.myStringT.equals(compare.myStringT) 
				&& this.myOtherStringT.equals(compare.myOtherStringT)
				
				&& this.myIntT == compare.myIntT
				&& this.myOtherIntT == compare.myOtherIntT
				
				&& this.myFloatT == compare.myFloatT
				&& this.myOtherFloatT == compare.myOtherFloatT
				
				&& this.myBoolT == compare.myBoolT
				&& this.myOtherBoolT == compare.myOtherBoolT
				
				&& this.myShortT == compare.myShortT
				&& this.myOtherShortT == compare.myOtherShortT
				
				&& this.myCharT == compare.myCharT 
				&& this.myOtherCharT == compare.myOtherCharT
				
				&& this.myLongT == compare.myLongT
				&& this.myOtherLongT == compare.myOtherLongT);
	}
	/**
	 * Accepting the visitor implementation
	 */
	public void accept(VisitorI visitor) {
		visitor.visit(this);
	}
	
}
