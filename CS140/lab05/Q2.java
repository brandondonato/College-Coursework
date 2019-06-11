package lab05;

import java.util.ArrayList;

public class Q2 {
    public int multiply(ArrayList<Integer> alist) {
        // compute and return the product of all of the non-zero elements
        // inside of `alist`. Assume `alist` is not null but if it is empty
        // return 0.
        // you may assume `alist` is either empty or has at least one non-zero element.
        // hence the return value can only be 0 if the array is empty.
        int productOfElements = 1;
      	int currentElement;

      	if(alist.size() > 0){
      		for(int i = 1; i < alist.size(); i++){
      			if(alist.get(i) != 0){
      				productOfElements = productOfElements * alist.get(i);
      			}
      		}
      	}
      	return productOfElements;
    }
}