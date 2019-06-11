package assignment06;
import java.lang.Math;

public class Question1 {
	   public static String name(int n) {
	      if (n >= 26*26*26*26*26*26) return "ZZZZZZ";
	      if (n < 0) return "AAAAAA";
	      char[] chs = Integer.toString(n, 26).toCharArray();
	      char[] chs2 = new char[6];
	      System.arraycopy(chs, 0, chs2, 6-chs.length, chs.length);
	      for(int i = 0; i < 6; i++) {
	         if(chs2[i] == 0) chs2[i] = 'A';
	         if(chs2[i] >= '0' && chs2[i] <= '9') chs2[i] = (char)('A' + chs2[i]-'0');
	         if(chs2[i] >= 'a' && chs2[i] <= 'p') chs2[i] = (char)('A' + 10 + chs2[i]-'a');
	      }		
	      return new String(chs2);
	   }
	   public static void main(String[] args) {
	      for(int n = 10000; n < 11000; n++) {
	         System.out.println(name(n));
	      }
	      System.out.println(name(0));
	      System.out.println(name(26*26*26*26*26*26 - 2));
	      System.out.println(name(26*26*26*26*26*26 - 1));
	      System.out.println(name(26));
	      System.out.println(name(1));
	      System.out.println(26*26*26*26*26*26); //308,915,776
	      
	      System.out.println(next("AAAAAZ"));
	      System.out.println(next("ZZZZZZ"));
	      System.out.println(next("AAAAAA"));
	      
	      System.out.println(previous("AAAABA"));
	      System.out.println(previous("AAAAAA"));
	      System.out.println(previous("ZZZZZZ"));
	   }
	   
	   public static int nameToInt(String str){
		   int retValue = 0;
		   double positionPower = 0.0;
		   double positionValue = 0.0;
		   if(str.length() == 6){
			   str = str.toUpperCase();
			   char[] strArray = str.toCharArray();
			   for(int i = (strArray.length - 1); i >= 0; i-- ){
				   positionValue += (strArray[i] - 'A') * (Math.pow(26, positionPower));
				   positionPower += 1.0;
			   }
			   retValue = (int)positionValue;
		  }else{
			   retValue = -1;
		   }
		  return retValue;
	   }
	   
	   public static String next(String str){
		   String retString = "";
		   if(str != null){
			   if(str == "ZZZZZZ"){
				   retString = "AAAAAA";
			   }else{
				   retString = name((nameToInt(str)) + 1);
			   }
		   }
		   return retString;
	   }
	   
	   public static String previous(String str){
		   String retString = "";
		   if(str != null){
			   if(str == "AAAAAA"){
				   retString = "ZZZZZZ";
			   }else{
				   retString = name(nameToInt(str) - 1);
			   }
		   }
		   return retString;
	   }   
	   
}
