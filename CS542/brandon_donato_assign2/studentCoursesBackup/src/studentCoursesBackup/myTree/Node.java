package studentCoursesBackup.myTree;
import studentCoursesBackup.myTree.ObserverI;
import studentCoursesBackup.statusEnum.Status;

import java.lang.CloneNotSupportedException;

import java.util.ArrayList;

/**
 * @author Brandon Donato
 *
 */
/**
 * Create an enum to determine if you are deleting the current course
 * 	or adding a new course to the node
 *
 */
public class Node implements ObserverI, SubjectI, Cloneable{
	private Integer bNum; //3-digit int 
	public ArrayList<String> courses = new ArrayList<String>(); //The list of courses
	//These will be the two child nodes in the tree
	//	Implementing Binary Search Tree
	public Node leftNode = null; 
	public Node rightNode = null; 
	//This is the list of listeners each node has
	public ArrayList<Node> listeners = new ArrayList<Node>(); 
	
	public Node(int bNumIn, String course) {
		bNum = bNumIn; //Set the proper B-Num node
		courses.add(course); //Add the course to the given B-Num node
	}
	//The Constructor utilized for the clone method
	public Node(int bNumIn, ArrayList<String> coursesIn) {
		bNum = bNumIn;
		courses = coursesIn;
	}
	/**
	 * 
	 * @param courseIn: String of the newly added course
	 */
	public void addCourse(String courseIn) {
		courses.add(courseIn);
	}
	/**
	 * @return nothing, but empties the courses arrayList
	 */
	public void deleteNode(String course) {
		courses.remove(course);
	}
/*******************Subject Methods Implemented**********************/
	@Override
	/**
	 * @return Nothing, but add the listener to the list
	 */
	public void register(Node listener) {
		listeners.add(listener);
	}

	@Override
	/**
	 * @return Nothing, but remove this listener from the list
	 */
	public void unregister(Node listener) {
		listeners.remove(listener);
	}

	@Override
	/**
	 * @return Nothing, but call update on all listeners
	 */
	public void notifyAllObservers(String course, Status status) {
		for(int i = 0; i < listeners.size(); i++) {
			//Send the subject as the object that is the basis for the
			//	update
			listeners.get(i).update(course, status);
		}
	}
/*******************Observer Methods Implemented**********************/
	/**
	 * @return Nothing, but ensures the listeners information matches
	 * 			the subject's information
	 */
	@Override
	public void update(String courseIn, Status statusIn) {
		if(statusIn == Status.DELETE) {
			//Delete the incoming course
			courses.remove(courseIn);
		}else if(statusIn == Status.ADD) {
			//Add the incoming course
			courses.add(courseIn);
		}
	}
/**********************Implementing Clone*****************************/
	/**
	 * @return A Clone of the Node called by this function
	 */
	@Override
	public Object clone() throws CloneNotSupportedException{
		//Create a new object from the values of the current object
		Node retNode = new Node(bNum, courses.get(0));
		return retNode;	
	}
/*********************Implementing hashCode***************************/
	/**
	 * @return int: The unique hashCode associated with each node
	 * 
	 */
	@Override
	public int hashCode() {
		int retHash = 15;
		int constant = bNum + courses.get(0).hashCode();
		retHash = 37 * retHash + constant;
		
		return retHash;
	}
/*****************toString method, getters and setters****************/
	/**
	 * @return The b-number of the node and the course associated
	 */
	@Override
	public String toString() {
		if(!courses.isEmpty() ) {
			String retString = "[";
			for(int i = 0; i < courses.size(); i++) {
				if(i == courses.size() - 1) {
					retString = retString + courses.get(i);
				}else {
					retString = retString + courses.get(i) + ",";
				}
			}
			retString = retString + "]" + "\n";
			if(bNum < 10) {
				return "00" + bNum + ":" + retString;
			}else if(bNum < 100) {
				return "0" + bNum + ":" + retString;
			}else {
				return bNum + ":" + retString;
			}
		}else {
			if(bNum < 10) {
				return "00" + bNum + ":" + "\n";
			}else if(bNum < 100) {
				return "0" + bNum + ":" + "\n";
			}else {
				return bNum + ":" + "\n";
			}
		}
		
	}
	/**
	 * 
	 * @return The b-Number for the course
	 */
	public int getbNum() {
		return bNum;
	}
	/**
	 * 
	 * @param bNumIn: Set the old b-number to the inputed one
	 */
	public void setbNum(int bNumIn) {
		this.bNum = bNumIn;
	}
	/**
	 * 
	 * @return The list of courses the node has
	 */
	public ArrayList<String> getCourses() {
		return courses;
	}
	
	/**
	 * @return the leftNode
	 */
	public Node getLeftNode() {
		return leftNode;
	}
	/**
	 * @param leftNode the leftNode to set
	 */
	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}
	/**
	 * @return the rightNode
	 */
	public Node getRightNode() {
		return rightNode;
	}
	/**
	 * @param rightNode the rightNode to set
	 */
	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}
	/**
	 * @return the listeners
	 */
	public ArrayList<Node> getListeners() {
		return listeners;
	}
	/**
	 * @param listeners the listeners to set
	 */
	public void setListeners(ArrayList<Node> listeners) {
		this.listeners = listeners;
	}
	/**
	 * @param courses the courses to set
	 */
	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}
}
