package assignment02;
import java.util.Arrays;

public class ArrayStatsDriver{

	public static void main(String[] args){
		ArrayStats test1 = new ArrayStats(); // this equivalent passing an empty array {} as the argument
		ArrayStats test2 = new ArrayStats(null);
		ArrayStats test3 = new ArrayStats(-7, -2, -3, -6, -8, -2, -1, -3, -6, -3, -4, 0, -5, -8);

		System.out.println("Testing test2.getData(), expect null");
		System.out.println(Arrays.toString(test2.getData()));
		System.out.println("Testing test2.getMax(), expect 0.0");
		System.out.println(test3.getMax());
		System.out.println("Testing test2.getMin(), expect 0.0");
		System.out.println(test3.getMin());
		System.out.println("Testing test2.getMaxIdx(), expect -1");
		System.out.println(test3.getMaxIdx());
		System.out.println("Testing test2.getMinIdx(), expect -1");
		System.out.println(test3.getMinIdx());
		System.out.println("Testing test2.getAvg(), expect 0.0");
		System.out.println(test3.getAvg());
	}
}