package assignment03;
import java.util.ArrayList;

public class FirstCharAnalyzer{

	private char ch;
	private ArrayList<String> list;

	public FirstCharAnalyzer(char character, ArrayList<String> stringsList){
		ch = character;
		if(stringsList != null){
			list = stringsList;
		}
	}

	public String toString() {
		String[] names = {};
		names = list.toArray(names); // "list" is the ArrayList field
		return "" + NameUtilities.countFirstCharacters(ch, names); // ch is the char field
	}

}