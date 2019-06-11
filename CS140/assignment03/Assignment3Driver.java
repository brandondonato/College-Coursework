package assignment03;
import java.util.ArrayList;

public class Assignment3Driver{

	public static void main(String[] args){
		
		ArrayList<String> names = new ArrayList<>();
		for(int i = 0; i < 1000; i++){
			names.add(resources.NamesResource.getRandomFirstName());
		}


		ArrayList<Object> test = new ArrayList<>();
		test.add(new FirstCharAnalyzer('b', names));
		test.add(new LengthAverageAnalyzer(names)); 
		test.add(new SyllablesAverageAnalyzer(names));

		System.out.println(test);

	}
}