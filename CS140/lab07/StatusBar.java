package lab07;

public class StatusBar extends Buffer {
	
	private Buffer attachedTo;
	
	public StatusBar(Buffer enteredBuffer){
		attachedTo = enteredBuffer;
	}
	
	@Override
	public String getText(){
		return "----- lines "  + attachedTo.getNumLines() + " -----";
	}
}
