import java.awt.Color;
import java.awt.Graphics;

/**
 * Square.java
 * @author Matt Haley
 * This class creates a square. A square has a location and a color.
 */

public class Square
{
	/**
	 * x and y locations of the square
	 */
	protected int x, y;
	
	/**
	 * Color of the square
	 */
	private Color color;
	
	/**
	 * Length of the square
	 */
	protected final int LENGTH = 20;
	
	/**
	 * Width of the square
	 */
	protected final int WIDTH = 20;
	
	/**
	 * Default constructor
	 */
	public Square()
	{
		//Default Constructor!
	}
	
	/**
	 * Constructor that receives the x and y location
	 * @param x - x location of the square
	 * @param y - y location of the square
	 */
	public Square(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor that receives the color, x, and y location
	 * @param x - x location of the square
	 * @param y - y location of the square
	 * @param color - The color of the square
	 */
	public Square(int x, int y, Color color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	/**
	 * Sets the x and y location of the square
	 * @param x - x location of the square
	 * @param y - y location of the square
	 */
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the x location of the square
	 * @return - x location of the square
	 */
	public int getXLocation()
	{
		return x;
	}
	
	/**
	 * Gets the y location of the square
	 * @return - y location of the square
	 */
	public int getYLocation()
	{
		return y;
	}
	
	/**
	 * Sets the color of the square
	 * @param color - Color that the square should be
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	/**
	 * Gets the color of the square
	 * return - The color of the square
	 * @return Color - the color of the square
	 */
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * Paints the square in the pane
	 * @param pane - location the square is to be painted
	 */
	public void paint(Graphics pane)
	{
		//Draw the square with its color
		pane.setColor(color);
		pane.fillRect(x, y, WIDTH, LENGTH);
		//Draw the black outline around the square
		pane.setColor(new Color(51,153,255));
		pane.drawRect(x, y, WIDTH, LENGTH);
	}
}
