package lab05;


public class Converter{

	private double conversionFactor;

	public Converter(double factor){
		conversionFactor = factor;
	}

	public double convert(double val){
		return val * conversionFactor;
	}

	public double getConversionFactor(){
		return conversionFactor;
	}
}