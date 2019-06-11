package lab09;
import java.util.List;
import java.util.ArrayList;

public class BinarySearchTree {

	private Integer value = null;
	private BinarySearchTree leftNode;
	private BinarySearchTree rightNode;
	
	public void insert(int i){
		if(value == null){
			value = i;
			rightNode = new BinarySearchTree();
			leftNode = new BinarySearchTree();
		}else if(i > value){
			rightNode.insert(i);
		}else if(value == i){
			value = i;
		}else{
			leftNode.insert(i);
		}
	}
	
	public Integer find(int i){
		Integer retValue = 0;
		if(value == null || value == i){
			retValue = value;
		}else if(value > i){
			return leftNode.find(i);
		}else{
			return rightNode.find(i);
		}
		return retValue;
	}
	
	public List<Integer> getElems(){
		ArrayList<Integer> retList = new ArrayList<Integer>();
		if(value != null){
			if(leftNode != null){
				retList.addAll(leftNode.getElems());
			}
			retList.add(value);
			if(rightNode != null){
				retList.addAll(rightNode.getElems());
			}
		}
		return retList;
	}
	
}
