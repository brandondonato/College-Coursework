package lab10;


public class Neg extends Expr{
	private Expr num1;
	public Neg(Expr number1){
		num1 = number1;
	}	
	
	public int eval(){
		if(num1.eval() == 0){
			return num1.eval() + 1;
		}else{
			return num1.eval() - num1.eval();
		}
	}
	
	public String toString(){
		return "neg" + "(" + num1.toString() + ")";
	}
}
