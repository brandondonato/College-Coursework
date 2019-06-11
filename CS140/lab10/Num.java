package lab10;


public class Num extends Expr{
	private int value;
	public Num(int inputVal){
		value = inputVal;
	}
	public int eval(){
		return value;
	}
	
	public String toString(){
		String retString = value + "";
		return retString.toString();
	}
}
