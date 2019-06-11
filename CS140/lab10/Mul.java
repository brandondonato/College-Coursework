package lab10;

public class Mul extends Expr {
	private Expr num1;
	private Expr num2;
	public Mul(Expr n1, Expr n2){
		num1 = n1;
		num2 = n2;
	}
	
	public int eval(){
		return num1.eval() * num2.eval();
	}
	
	public String toString(){
		String retString = "(" + num1.toString() +
				" * " + num2.toString() + ")";
		return retString;
	}
	
}
