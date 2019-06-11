package studentCoursesBackup.driver;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.TreeBuilder;
import studentCoursesBackup.util.Results;



/**
 * @author Brandon Donato
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */

		if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") ||
			args[2].equals("${arg2}") || args[3].equals("${arg3}") || args[4].equals("${arg4}") ) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");

			System.err.println("The files are as follows: args0 = input, args1 = delete, args2 = output1" +
				", args3 = output2, args4 = output3");
			System.exit(0);
		}

		//String courseInputs = args[0]; //First argument = input.txt
		//String updates = args[1]; //Second argument = output.txt

		FileProcessor inputFile = new FileProcessor(args[0]);
		FileProcessor deleteFile = new FileProcessor(args[1]);

		//Get the output filenames
		String output1 = args[2];
		String output2 = args[3];
		String output3 = args[4];
		//Create the tree
		TreeBuilder tree = new TreeBuilder();

		//Build the tree from the input file
		tree.buildTree(inputFile);

		//Delete nodes from the tree based upon the delete file
		tree.deleteNodes(deleteFile);

		//Create the results objects
		Results rootResults = new Results();
		Results rootResultsCopy1 = new Results();
		Results rootResultsCopy2 = new Results();

		//Print the nodes, in order, to the results object and
		//	have the results objects write to a file
		tree.printNodes(tree.root, rootResults);
		rootResults.printLineToFile(output1);

		tree.printNodes(tree.rootCopy1, rootResultsCopy1);
		rootResultsCopy1.printLineToFile(output2);

		tree.printNodes(tree.rootCopy2, rootResultsCopy2);
		rootResultsCopy2.printLineToFile(output3);
	}
}
