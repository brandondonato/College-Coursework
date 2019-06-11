package lab07;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShellBuffer extends Buffer{
	
	private final String PROMPT = "> ";
	public ShellBuffer(){
		super.lines = new ArrayList<>();
	}
	
	@Override
	public void save(){
		
	}
	
	@Override 
	public void type(String toType){
		super.type(PROMPT);
		super.type("Command Line");
		super.type("\n");
		this.execute(toType);
	}
	
	/**
	 * Has the OS execute the shell command `command`, appending its output to `lines`.
	 * If the command fails in any way, a failure message is instead appended to `lines`.
	 * @param command the command to execute as one space-separated string
	 */
	private void execute(String command) {
	    // split `command` into list of space-separated words
	    List<String> commandAsWordList = Arrays.asList(command.split(" +"));
	    ProcessBuilder pb = new ProcessBuilder(commandAsWordList);
	    File pipe = null;
	    try {
	        pipe = new File("pipe");
	        // capture the output of our `command` into the `pipe` file
	        pb.redirectOutput(pipe);
	        Process proc = pb.start();
	        proc.waitFor(); // wait for it to finish before reading from it
	        List<String> output = Files.readAllLines(Paths.get("pipe"));
	        super.lines.addAll(output);
	        super.type("\n");
	    } catch (Exception e) {
	        super.type("command '" + command + "' failed\n");
	    } finally {
	        if (pipe != null) {
	            pipe.delete(); // delete the pipe file
	        }
	    }
	}
}
