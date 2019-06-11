package lab07;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileBuffer extends Buffer {
	private String filename;
	
	public FileBuffer(String name){
		filename = name;
		try{
			super.lines = java.nio.file.Files.readAllLines(Paths.get(filename));
		}catch(IOException e){
			super.lines = new ArrayList();
		}		
	}
	
	@Override
	public void save(){
		try{
			Files.write(Paths.get(filename), super.lines);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
