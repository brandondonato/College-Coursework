package assignment05;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Creates a background to see the Game of Life unfold
 * The dialog on top can be moved around and controls
 * the action
 */
public class GraphDialogSetup
{
	private JFrame graph = new JFrame();
	private Dimension screenSize;
	private JDialog dialog;
	private boolean running = false;
	private LifePanel lifePanel;
	private CellGrid cellGrid;
	private Timer t = new Timer(100, e -> {
		if (running) {
			cellGrid.applyUpdate();			
			graph.repaint();
		}});

	private final int NUM_BLOCKS_HORIZ = 200;
	private final int NUM_BLOCKS_VERT = 150;

	/**
	 * Actually creates the on screen objects and computes
	 * the number of blocks that give the best fit
	 */
	public GraphDialogSetup()
	{

		t.start();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//if the dialog in the bottom right corner closes the application
		// change the next line to graph.setSize(screenSize);
		graph.setSize(screenSize.width-100,screenSize.height-100);
		graph.setUndecorated(true);
		double blockWidth = screenSize.getWidth()/NUM_BLOCKS_HORIZ;
		double blockHeight = screenSize.getHeight()/NUM_BLOCKS_VERT;

		cellGrid = new CellGrid(NUM_BLOCKS_VERT, NUM_BLOCKS_HORIZ);
		lifePanel = new LifePanel(blockWidth, blockHeight,
				NUM_BLOCKS_HORIZ, NUM_BLOCKS_VERT,
				screenSize.getWidth(),screenSize.getHeight(),
				cellGrid);
		graph.add(lifePanel);
		graph.setVisible(true);
		dialog = new JDialog((Frame)null, "Control", true);
		dialog.setLayout(new GridLayout(0,2));
		JButton setupButton = new JButton("Setup");
		setupButton.addActionListener(new SetupListener());
		dialog.add(setupButton);
		JButton stepButton = new JButton("Step");
		/*
		 * NOTE On the screen (x,y) is a position where x measures the 
		 * horizontal distance from the left side of the screen and 
		 * y measures the vertical distance FROM THE TOP of the screen
		 * BUT-BUT-BUT
		 * In the block array blackSquares, I am following mathematical
		 * tradition where blackSquares[i][j] refers to the i-th row from
		 * the top and the j-th column from the left.
		 * 
		 * Hence i is used to determine where the y coordinate is in the
		 * graphic and j is used to determine the x coordinate.
		 * 
		 * The code provided makes all the adjustments.
		 * 
		 * If blackSquares[i][j] is true the corresponding square is
		 * black, otherwise it will be blank
		 */
		stepButton.addActionListener(e -> {
			cellGrid.applyUpdate();
			graph.repaint();
		});
		dialog.add(stepButton);
		JButton pauseButton = new JButton("Pause/Run");
		pauseButton.addActionListener(e -> running = !running);
		dialog.add(pauseButton);
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(e -> System.exit(0));
		dialog.add(exitButton);
		dialog.setTitle("Control: " + NUM_BLOCKS_HORIZ + "x" + NUM_BLOCKS_VERT);
		dialog.pack();
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.addWindowListener(new ExitWindowListener());
		dialog.setLocation((int)(screenSize.getWidth()-dialog.getWidth()),
				(int)(screenSize.getHeight()-dialog.getHeight()));
		dialog.setVisible(true);
	}

	public static void main(String[] args)
	{
		new GraphDialogSetup();
	}

	private class SetupListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0)
		{
			JDialog setupDialog = new JDialog(dialog, "Setup Options");
			setupDialog.setLayout(new GridLayout(0,1));
			JButton randomButton = new JButton("Random");
			randomButton.addActionListener(e -> {
				cellGrid.randomStart();
				setupDialog.dispose();
				graph.repaint();
			});
			setupDialog.add(randomButton);
			JButton gosperButton = new JButton("Gosper Gun");
			gosperButton.addActionListener(e -> {
				cellGrid.gosperStart();;
				setupDialog.dispose();
				graph.repaint();
			});
			setupDialog.add(gosperButton);
			JButton gliderButton = new JButton("Glider");
			gliderButton.addActionListener(e -> {
				cellGrid.gliderStart();;
				setupDialog.dispose();
				graph.repaint();
			});
			setupDialog.add(gliderButton);
			setupDialog.pack();
			setupDialog.setLocationRelativeTo(dialog);
			setupDialog.setVisible(true);			
		}
	}

	private class ExitWindowListener extends WindowAdapter {

		public void windowClosing(WindowEvent arg0)
		{
			System.exit(0);
		}

	}
}
