package assignment03;
import java.util.ArrayList;

public class LengthAverageAnalyzer{

	private ArrayList<String> list;

	public LengthAverageAnalyzer(ArrayList<String> stringList){
		if(stringList != null){
			list = stringList;
		}
	}

	public String toString() {
		String[] names = {};
		names = list.toArray(names);
		return "" + NameUtilities.averageLength(names);
	}
}