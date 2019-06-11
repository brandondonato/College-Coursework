package lab07;
import java.util.List;

public class Buffer {
	
	protected List<String> lines;
	
	public int getNumLines(){
		int retCount = lines.size();
		return retCount;
	}
	
	public String getText(){
		String retString = String.join("\n", this.lines);
		return retString;
	}
	
	public void draw(){
		System.out.println(this.getText());
	}
	
	public void save() throws Exception{
		throw new UnsupportedOperationException();
	}
	
	public void type(String toType){
		if(lines.size() == 0){
			lines.add("");
		}
		char[] charArray = toType.toCharArray();
		for(int i = 0; i < charArray.length; i++){
			if(charArray[i] == '\n'){
				lines.add("");
			}else{
				String addedStr = lines.remove((lines.size() - 1));
				addedStr = addedStr + charArray[i];
				lines.add(addedStr);
			}
			
		}
		
	}
}