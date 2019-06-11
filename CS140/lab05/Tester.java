package lab05;

import java.util.ArrayList;
import java.util.Arrays;

public class Tester {

    public static void testQ1() {
        Converter c = new Converter(.5);
        System.out.println(.5 + " " + c.getConversionFactor());
        System.out.println(.5 + " " + c.convert(1));
        System.out.println(1.0 + " " + c.convert(2));
        System.out.println(5.0 + " " + c.convert(10));
    }

    public static void testQ2() {
        ArrayList<Integer> aList = new ArrayList<Integer>(Arrays.asList(0,1,2,4,0));
        Q2 q = new Q2();
        System.out.println(8 + " " + q.multiply(aList));
    }

    public static void testQ3() {
        Q3 q = new Q3();
        int[] is = {1, 1, 1, 1, 0, 2, 2, 2};
        System.out.println(3 + " " + q.countMax(is));
    }

    public static void testQ4() {
        Q4 q = new Q4();
        int[] is = {1, 2, 3, 1, 0};
        int[] reversed = q.reverse(is);
        System.out.println(1 + " " + reversed[4]);
        System.out.println(2 + " " + reversed[3]);
        System.out.println(3 + " " + reversed[2]);
        System.out.println(1 + " " + reversed[1]);
        System.out.println(0 + " " + reversed[0]);
    }

    public static void main(String[] args) {
         Tester.testQ1();
         System.out.println("--------------------");
         Tester.testQ2();
         System.out.println("--------------------");
         Tester.testQ3();
         System.out.println("--------------------");
         Tester.testQ4();
    }
}