package lab10;

public class Add extends Expr{
	private Expr num1;
	private Expr num2;
	public Add(Expr number1, Expr number2){
		num2 = number1;
		num1 = number2;
	}
	
	public int eval(){
		return num2.eval() + num1.eval();
	}
	
	public String toString(){
		String retString = "(" + num2.toString() +
				" + " + num1.toString() + ")";
		return retString;	
	}
}