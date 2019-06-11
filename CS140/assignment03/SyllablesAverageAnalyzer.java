package assignment03;
import java.util.ArrayList;

public class SyllablesAverageAnalyzer{

	private ArrayList<String> list;

	public SyllablesAverageAnalyzer(ArrayList<String> stringList){
		if(stringList != null){
			list = stringList;
		}
	}

	public String toString() {
		String[] names = {};
		names = list.toArray(names); 
		return "" + NameUtilities.averageNumberOfSyllables(names); 
	}
}