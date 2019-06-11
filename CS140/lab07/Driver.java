package lab07;

public class Driver {

	public static void main(String[] args) throws Exception{
		ScratchBuffer b1 = new ScratchBuffer();
		FileBuffer b2 = new FileBuffer("Rawr");
		ShellBuffer b3 = new ShellBuffer();
		
		Editor e1 = new Editor(b1);
		Editor e2 = new Editor(b2);
		Editor e3 = new Editor(b3);
		
		
		e2.save();
		e3.type("ls");
		e3.draw();
	
		
		
		

	}

}
