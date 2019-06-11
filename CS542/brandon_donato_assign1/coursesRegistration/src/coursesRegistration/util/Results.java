package coursesRegistration.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	String filename;
	@Override
	public void printLineToStdout(String line) {
		//Print to stdout
		filename = "stdout";
		System.out.println(line);
	}

	@Override
	public void printLineToFile(String line, String filename) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
			//Create the bufferedWriter and the FileWriter and pass
			//	"true" to append to the file so that it does not 
			//	overwrite what was previously written
			this.filename = filename;
			//Do the actual writing
			writer.write(line);
			writer.write('\n');
			writer.close();
		}catch(IOException e) {
			System.out.println("There was an error with writing to the file.");
			e.printStackTrace();
			
		}finally{}
		
	}

	@Override
	public String toString() {
		return "Last wrote to: " + filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	
}
