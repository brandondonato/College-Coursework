package assignment07;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Human implements Play {

	@Override
	public int takeTurn(int currentState) {
		int numberToTake = -1;
		while(numberToTake < 0) {
			String in = JOptionPane.showInputDialog(null,
			"Number to remove:");
			Scanner input = new Scanner(in);
			if(input.hasNextInt()){
				numberToTake = input.nextInt();
				if ((currentState != 1 && (numberToTake < 1 || numberToTake > currentState/2))
						|| (currentState == 1 && numberToTake != 1)) {
					JOptionPane.showMessageDialog(null, 
							"You can only take between\n" +
							"1 and " + (currentState == 1 ? 1 : currentState/2) + " sticks from the pile");
					numberToTake = -1;
				}
			}
			else{
				JOptionPane.showMessageDialog(null, 
						"You did not enter an integer");
			}
			input.close();
		}
		return numberToTake;
	}

}
