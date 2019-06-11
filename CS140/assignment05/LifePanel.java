package assignment05;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class LifePanel extends JPanel
{
	private double blockWidth;
	private double blockHeight;
	private double screenWidth;
	private double screenHeight;
	private int numBlocksHoriz;
	private int numBlocksVert;
	private CellGrid cellGrid;
	
	public LifePanel(double blockW, double blockH, 
			int nBlocksHoriz, int nBlocksVert, 
			double scrWidth, double scrHeight,
			CellGrid grid)
	{
		blockWidth = blockW;
		blockHeight = blockH;
		screenWidth = scrWidth;
		screenHeight = scrHeight;
		numBlocksHoriz = nBlocksHoriz;
		numBlocksVert = nBlocksVert;		
		cellGrid = grid;
	}

	/**
	 * Provided to remove  a compiler warning.
	 * No need for serialization is expected.  
	 */
	private static final long serialVersionUID = 5288191279115685984L;

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		boolean[][] cells = cellGrid.getCells();
		for (int i = 0; i < cells.length; i++)
			for (int j = 0; j < cells[i].length; j++)
				if(cells[i][j])
					g2.fill(new Rectangle2D.Double(j*blockWidth, i*blockHeight, 
							blockWidth, blockHeight));
		g2.setColor(Color.BLACK);
		for (int i = 1; i < numBlocksVert; i++)
			g2.draw(new Line2D.Double(0,i*blockHeight, screenWidth, i*blockHeight));
		
		for (int j = 1; j < numBlocksHoriz; j++)
			g2.draw(new Line2D.Double(j*blockWidth, 0, j*blockWidth, screenHeight));
		
	}
}
