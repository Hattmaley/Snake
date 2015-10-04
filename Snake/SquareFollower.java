import java.awt.Color;

/**
 * SquareFollower.java
 * @author Matt Haley
 * This class contains the information for a following square.  It has no special implementation
 * other than being created and following the head. The class sounds so sad.  I wanted to have
 * the head be a distinctly different class because it has implementation the follower should not.
 */

public class SquareFollower extends Square
{
	/**
	 * Default Constructor
	 */
	public SquareFollower()
	{
		//Default Constructor
	}
	
	/**
	 * Constructor that receives the color, x, and y location
	 * @param x - x location of the square
	 * @param y - y location of the square
	 * @param color - The color of the square
	 */
	public SquareFollower(int x, int y, Color color)
	{
		super(x, y, color);
	}
	
	/**
	 * Constructor that receives the x and y location
	 * @param x - x location of the square
	 * @param y - y location of the square
	 */
	public SquareFollower(int x, int y)
	{
		super(x, y);
	}
	
	/**
	 * Used to determine if the square is a following square or head square
	 * @return - If the square is a follower return true, otherwise return false
	 * Since this is the SquareFollower class true will always be returned.
	 */
	public boolean isFollower()
	{
		return  true;
	}
}
