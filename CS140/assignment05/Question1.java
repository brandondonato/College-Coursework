package assignment05;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Question1 {
	
	public static int[] counter(String s) {
		int[] retList = new int[26];
		char[] letterList = {'a','b','c','d','e','f','g','h','i','j','k',
							'l','m','n','o','p','q','r','s','t','u','v',
							'w','x','y','z'};
		if(s != null){
			s = s.toLowerCase();
			String compareString = s.toLowerCase();
			for(int i = 0; i < compareString.length(); i++ ){
				if((compareString.charAt(i) >= 'a') && (compareString.charAt(i) <= 'z')){
					for(int a = 0; a < letterList.length; a++){
						if(compareString.charAt(i) == letterList[a]){
							retList[a] += 1;
						}
					}
				}
			}
			
		}
		return retList;
	}	
	
	public static int count(char c, String s){
		int retCounter = 0;
		if(s != null){
			String compareString = s.toLowerCase();
			char compareChar = Character.toLowerCase(c);
			for(int i = 0; i < compareString.length(); i++){
				if(compareString.charAt(i) == compareChar){
					retCounter++;
				}
			}
		}

		return retCounter;
	}
	
	public static boolean permutation2(String s1, String s2){
		boolean retValue = true;
		if(s1 != null && s2 != null){
			String newString1 = s1.toLowerCase();
			String newString2 = s2.toLowerCase();
			for(char c = 'a'; c <= 'z'; c++ ){
				if((count(c, newString1)) != (count(c, newString2))){
					retValue = false;
				}
			}
		}
		return retValue;
	}

	
	public static boolean permutation1(String s1, String s2) {
	    return Arrays.equals(counter(s1), counter(s2));
	}
	
	static class MyChCounter{
	    char c;
	    int count = 1;
	    MyChCounter(char c1) {
	        c = c1;
	    }
	    void increment() {
	        count++;
	    }
	    
	    @Override
	    public boolean equals(Object obj) {
	        boolean retVal = false;
	        if(obj != null && getClass() == obj.getClass()) {
	            MyChCounter m = (MyChCounter)obj;
	            retVal = (c == m.c && count == m.count);
	        }
	        return retVal;
	    }        
	   
	    @Override
	    public int hashCode() {
	        return Character.hashCode(c)*31 + Integer.hashCode(count);
	    }
	}
	
	public static boolean permutation3(String s1, String s2){
		boolean retValue = false;
		if(s1 != null && s2 != null){
			s1 = s1.toLowerCase();
			s2 = s2.toLowerCase();
			Map<Character, MyChCounter> counter1 = new TreeMap<>();
			Map<Character, MyChCounter> counter2 = new TreeMap<>();
			char[] s1CharArray = s1.toCharArray();
			char[] s2CharArray = s2.toCharArray();
			for(int i = 0; i < s1CharArray.length; i++){
				char c = s1CharArray[i];
				if(Character.isLetter(c)){
					if(counter1.containsKey(c)){
						counter1.get(c).increment();
					}else{
						counter1.put(c, new MyChCounter(c));
					}
				}
			}
			for(int a = 0; a < s2CharArray.length; a++){
				char ch = s2CharArray[a];
				if(Character.isLetter(ch)){
					if(counter2.containsKey(ch)){
						counter2.get(ch).increment();
					}else{
						counter2.put(ch, new MyChCounter(ch));
					}
				}
			}
			retValue = counter1.equals(counter2);
		}
		return retValue;
	}
	
	
	
}

