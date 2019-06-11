package lab07;

public class Editor {

	private Buffer buffer;
	private StatusBar status;
	
	public Editor(Buffer bufferIn){
		buffer = bufferIn;
		status = new StatusBar(bufferIn);
	}
	
	public void draw(){
		buffer.draw();
		status.draw();
	}

	public void save() throws Exception {
		buffer.save();
	}

	public void type(String toType) {
		buffer.type(toType);
	}
}
