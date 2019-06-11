package studentCoursesBackup.util;
//Use this to get the nodes to represent the tree
import studentCoursesBackup.myTree.Node; 
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.statusEnum.Status;
import studentCoursesBackup.util.Results;

import java.lang.CloneNotSupportedException;

public class TreeBuilder {
	//Contains the root node of the BST
	public Node root;
	public Node rootCopy1;
	public Node rootCopy2;
	
	//Constructor creates an empty tree first
	public TreeBuilder() {
		root = null;
	}
	/**
	 * 
	 * @param bNumIn: The 3-digit b-number 
	 * @return Whether or not the b-number exists as a node within the
	 * 	tree
	 */
	public boolean doesExist(Node node, int bNumIn) {
		//If the node matches, return true
		if(node == null) {
			return false;
		}
		if(node.getbNum() == bNumIn) {
			return true;
		}
		//If the number is too small, search the left subtree
		if(node.getbNum() > bNumIn) {
			return doesExist(node.leftNode, bNumIn);
		}
		
		//If the number is too large, search the right subtree
		return doesExist(node.rightNode, bNumIn);
	}
	/**
	 * 
	 * @param bNumIn: The unique B-number that will be inserted into
	 * 			the tree
	 * @param course: The course name associated with the unique
	 * 			B-number
	 * @return: The root of the tree with the new node 
	 */
	public Node insert(Node node, Node insertedNode) {
		//The node is empty
		if(node == null) {
			//node = new Node(insebNumIn, course);
			node = insertedNode;
			//System.out.println("inserted: " + bNumIn + ", " + course);
			return node;
		}
		//Insert the node in the proper spot
		if(node.getbNum() > insertedNode.getbNum() ) {
			node.leftNode = insert(node.leftNode, insertedNode);
		}else if(node.getbNum() < insertedNode.getbNum() ){
			node.rightNode = insert(node.rightNode, insertedNode);
		}	
		return node;
	}
	/**
	 * 
	 * @param findNode: The node we want to find in the tree
	 * @return the Node we wanted to find, or null if it does
	 * 			not exist
	 */
	public Node findNode(int nodeBnum, Node node) {
		//If the node does not exist or the node matches, return
		if(node.getbNum() == nodeBnum || node == null) {
			return node;
		}
		//If the value is too small, search the left subtree
		if(node.getbNum() > nodeBnum) {
			return( findNode(nodeBnum, node.leftNode) );
		}
		//If the value is too large, search the right subtree
		return( findNode(nodeBnum, node.rightNode) );
	}
	/**
	 * 
	 * @param nodeToDelete: This is the node we want to delete
	 * 						information from
	 * @return Nothing, but the tree is now updated
	 */
	public void deleteNodes(FileProcessor inputFile) {
		String input = inputFile.readNextLine();
		while(input != null) {
			//Split into: index0 = bNum, index1 = Course
			String[] inputArray = input.split(":");
			int bNumIn = Integer.parseInt(inputArray[0]);
			String courseIn = inputArray[1];
			
			//If the node exists within the tree
			if( doesExist(root, bNumIn) ) {
				//find the node, delete the information
				Node removeNode = findNode(bNumIn, root);
				removeNode.deleteNode(courseIn);
				//Notify the observers and delete their information
				removeNode.notifyAllObservers(courseIn, Status.DELETE);
				
				//Node removeNode1 = findNode(bNumIn, rootCopy1);
				//removeNode1.deleteNode(courseIn);
			}
			
			input = inputFile.readNextLine();
		}
		//System.out.println("Original tree: ");
		//printNodes(root, resultsRoot);
		//System.out.println("\n" + "Copy tree 1:");
		//printNodes(rootCopy1, resultsRoot);
		//System.out.println("\n" + "Copy tree 2:");
		//printNodes(rootCopy2, resultsRoot);
	}
	/**
	 * @return Nothing, but it creates the tree 
	 */
	public void buildTree(FileProcessor inputFile) {
		//Read each file and create a node for each bNum and course
		String input = inputFile.readNextLine();
		while(input != null) {
			//Split into: index0 = bNum, index1 = Course
			String[] inputArray = input.split(":");
			int bNumIn = Integer.parseInt(inputArray[0]);
			String courseIn = inputArray[1];
			
			//If the given node does not exist
			if( !doesExist(root, bNumIn) ) {
				Node nodeOrig = new Node(bNumIn, courseIn);
				//Insert the node into the first tree
				root = insert(root, nodeOrig);
				try {
					//Create the cloned nodes
					Node backup1 = (Node)nodeOrig.clone();
					Node backup2 = (Node)nodeOrig.clone();
					
					//Add them as listeners to the original node
					nodeOrig.listeners.add(backup1);
					nodeOrig.listeners.add(backup2);
					
					//Insert the clones into their respective trees
					rootCopy1 = insert(rootCopy1, backup1);
					rootCopy2 = insert(rootCopy2, backup2);
				}catch(CloneNotSupportedException e) {
					System.err.println("Error: The clone function did not work.");
					//Print the stack trace to stderr
					e.printStackTrace();
					//Exit with 0
					System.err.println("Exiting the program now.");
					System.exit(0);
				}finally{}				
				
				//System.out.println("bNum is: " + bNumIn + " course is: " + courseIn);
			}else if( doesExist(root, bNumIn) ) {
				//So long as the node does not already have this course
				//	it will be added. Get the node in question, add the
				//	course and then update the listeners
				
				Node existingNode = findNode(bNumIn, root);
				//Check to see if the node has the course already
				boolean hasCourse = false;
				for(int i = 0; i < existingNode.getCourses().size(); i++) {
					if(existingNode.getCourses().get(i).equals(courseIn) ) {
						hasCourse = true;
					}
				}
				//Only if the node does not have this course, then add
				//	the course to the list of courses
				if( !hasCourse) {
					existingNode.addCourse(courseIn);
					existingNode.notifyAllObservers(courseIn, Status.ADD);
					
					//System.out.println(existingNode.getCourses());
				}
				
			}
			
			input = inputFile.readNextLine();
		}
		//System.out.println("Original tree: ");
		//printNodes(root, resultsRoot);
		//System.out.println("\n" + "Copy tree 1:");
		//printNodes(rootCopy1, resultsRoot);
		//System.out.println("\n" + "Copy tree 2:");
		//printNodes(rootCopy2, resultsRoot);
	}
	/**
	 * @return A string that describes each node in the tree, but goes 
	 * 			through the tree inorder
	 */
	public void printNodes(Node rootIn, Results resultsIn) {
		if(rootIn == null) {
			return;
		}
		printNodes(rootIn.leftNode, resultsIn);
		//System.out.print(rootIn.toString() );
		resultsIn.addToWriteLine(rootIn.toString());
		//resultsIn.addToWriteLine("\n");
		printNodes(rootIn.rightNode, resultsIn);	
	}
	

/******************Getters, Setters and toString********************/
	/**
	 * @return The root of the tree
	 */
	@Override
	public String toString() {
		String retString = "";
		if(root == null) {
			retString = "The tree is empty.";
		}else {
			
		}
		return retString;
	}
	/**
	 * @return the root
	 */
	public Node getRoot() {
		return root;
	}
	/**
	 * @param root the root to set
	 */
	public void setRoot(Node root) {
		this.root = root;
	}
	/**
	 * @return the rootCopy1
	 */
	public Node getRootCopy1() {
		return rootCopy1;
	}
	/**
	 * @param rootCopy1 the rootCopy1 to set
	 */
	public void setRootCopy1(Node rootCopy1) {
		this.rootCopy1 = rootCopy1;
	}
	/**
	 * @return the rootCopy2
	 */
	public Node getRootCopy2() {
		return rootCopy2;
	}
	/**
	 * @param rootCopy2 the rootCopy2 to set
	 */
	public void setRootCopy2(Node rootCopy2) {
		this.rootCopy2 = rootCopy2;
	}
	
}
