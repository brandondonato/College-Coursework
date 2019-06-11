package project;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Assembler {

	public static String assemble(File input, File output) {
		String returnVal = "success";
		ArrayList<String> code = new ArrayList<>();
		ArrayList<String> data = new ArrayList<>();
		boolean incode = true;
		int lineCount = 1;
		try (Scanner inp = new Scanner(input)) {
			while(inp.hasNextLine()) {
				String line = inp.nextLine(); 
				if(incode && line.trim().equals("DATA")){
					if(line.trim().equals("DATA")){
						incode = false;
					}else{
						returnVal = "Error: DATA not capitalized on line " 
								+ lineCount;
						return returnVal;
					}
				}
				else if(incode) code.add(line);
				else data.add(line);
				lineCount++;
			}
		} catch (FileNotFoundException e) {
			returnVal = "Error: the source file " + input + " is missing";
		}

		ArrayList<String> outText = new ArrayList<>();

		int blankCount = 0;
		for(int line = 0; line < code.size(); line ++) {
			String[] parts = code.get(line).split("\\s+");
			if(parts.length == 0){
				if(blankCount == 0){
					blankCount = 1;
				}
			}
			else if(parts.length == 1) {
				//System.out.println(line+1);
				//System.out.println(parts[0]);
				if(InstructionMap.opcode.containsKey(parts[0])){
					if(!InstructionMap.noArgument.contains(parts[0])){
						returnVal = "Error: Missing argument on line " + line;
						return returnVal;
					}
					int opcode = InstructionMap.opcode.get(parts[0]);
					outText.add(Integer.toHexString(opcode).toUpperCase() + " 0");
					if(blankCount == 1){
						returnVal = "Error: Blank space on line " + (line - 1);
						return returnVal;
					}
				}else if(parts[0].equals("")) {
					returnVal = "Error: Bad Mnemonic on line " + line;
					return returnVal;
				}
			}else{
				if(InstructionMap.opcode.containsKey(parts[0])){
					if(parts.length > 2){
						returnVal = "Error: Too many arguments on line "+line; 
						return returnVal;
					}
					if(parts[1].startsWith("#")) {
						if(!InstructionMap.immediateOK.contains(parts[0])) {
							returnVal = "Error: Illegal immediate argument on line " + line;
							return returnVal;
						}
						parts[0] = parts[0] + "I";
						parts[1] = parts[1].substring(1);
						if(parts[0].equals("JUMPI")) parts[0] = "JMPI"; 
						if(parts[0].equals("JMPZI")) parts[0] = "JMZI"; 
					} else if(parts[1].startsWith("&")) {
						if(!InstructionMap.indirectOK.contains(parts[0])) {
							returnVal = "Error: Illegal indirect argument on line " + line;
							return returnVal;
						}
						parts[0] = parts[0] + "N";
						parts[1] = parts[1].substring(1);
						if(parts[0].equals("JUMPN")) parts[0] = "JMPN"; 
					}else if(InstructionMap.noArgument.contains(parts[0])) {
						returnVal = "Error: Illegal Argument on line " + line;
						return returnVal;
					}
					int isValid = 0;
					char[] ch = parts[1].toCharArray();
					for(int i = 0; i < ch.length; i++) {
						if(ch[i] == '-') {
							isValid++;
						}
						else if(Character.isDigit(ch[i])) {
							int value = ch[i] - '0';
							for(int j = 0; j < 10; j++) {
								if(value == j) {
									isValid++;
								}
							}
						}
						else {
							for(char k = 'A'; k <= 'F'; k++) {
								if(ch[i] == k) {
									isValid++;
								}
							}
						}
					}
					if(isValid == ch.length) {
						int opcode = InstructionMap.opcode.get(parts[0]);
						outText.add(Integer.toHexString(opcode).toUpperCase() 
								+ " " + parts[1]);
					}
					else {
						returnVal = "Error: Illegal Number Format on line " + line;
						return returnVal;
					}
					
				} else if(parts[0].equals("")) {
					if(!parts[1].equals("")) {
						returnVal = "Error: Blank space found in front of code on line " + line;
						return returnVal;
					}
					
				}
				else {
					returnVal = "Error: Bad Mnemonic on line " + line;
					return returnVal;
				}
				if(blankCount == 1) {
					returnVal = "Error: Blank space on line " + (line - 1);
					return returnVal;
				}
				line++;
			}
		}
		outText.add("-1");
		outText.addAll(data);

		if(returnVal.equals("success")) {
			try (PrintWriter outp = new PrintWriter(output)){
				for(String str : outText) {
					outp.println(str);
				}
				outp.close();
			} catch (FileNotFoundException e) {
				returnVal = "Error: unable to open " + output; 
			}

		}
		return returnVal;
	}

	public static void main(String[] args) {
		System.out.println("Enter the name of the file without extension: ");
		try (Scanner keyboard = new Scanner(System.in)) {
			String filename = keyboard.nextLine();
			String i = assemble(new File(filename + ".pasm"), 
					new File(filename + ".pexe"));
			System.out.println(i );
		}
	}

}
