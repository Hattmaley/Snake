import java.awt.Color;

/**
 * SquareHead.java
 * @author Matt Haley
 * This class creates a head square.  This square has several movement options.  Following squares
 * will follow this head.
 */

public class SquareHead extends Square
{
	/**
	 * Default Constructor
	 */
	public SquareHead()
	{
		//Default Constructor
	}
	
	/**
	 * Constructor that receives the color, x, and y location
	 * @param x - x location of the square
	 * @param y - y location of the square
	 * @param color - The color of the square
	 */
	public SquareHead(int x, int y, Color color)
	{
		super(x, y, color);
	}
	
	/**
	 * Constructor that receives the x and y location
	 * @param x - x location of the square
	 * @param y - y location of the square
	 */
	public SquareHead(int x, int y)
	{
		super(x, y);
	}
	
	/**
	 * Moves the square in the upwards direction
	 */
	public void moveUp()
	{
		y = y - LENGTH;
	}
	
	/**
	 * Moves the square in the downwards direction 
	 */
	public void moveDown()
	{
		y = y + LENGTH;
	}
	
	/**
	 * Moves the square in the leftwards direction
	 */
	public void moveLeft()
	{
		x = x - WIDTH;
	}
	
	/**
	 * Moves the square in the rightwards direction
	 */
	public void moveRight()
	{
		x = x + WIDTH;
	}
	
	/**
	 * Used to determine if the square is a following square or head square
	 * @return - If the square is a follower return true, otherwise return false
	 * Since this is the SquareHead class false will always be returned.
	 */
	public boolean isFollower()
	{
		return false;
	}
}
