import java.awt.Color;

/**
 * SquareItem.java
 * @author Matt Haley
 * SquareItem creates a square that is an item.  This item can be collected by the snake to get the score.
 * The value located in the SquareItem will be put towards the score
 */

public class SquareItem extends Square
{
	/**
	 * Value that the square holds. Used for the score
	 */
	private int value;
	
	/**
	 * Default Constructor
	 */
	public SquareItem()
	{
		//Default Constructor
	}
	
	/**
	 * Constructor that has the x and y location, the color, and value of the SquareItem
	 * @param x - x location of the square
	 * @param y - y location of the square
	 * @param color - color of the square
	 * @param value - value of the square
	 */
	public SquareItem(int x, int y, Color color, int value)
	{
		super(x, y, color);
		this.value = value;
	}
	
	/**
	 * Constructor that has the x and y location, and value of the SquareItem
	 * @param x - x location of the square
	 * @param y - y location of the square
	 * @param value - value of the square
	 */
	public SquareItem(int x, int y, int value)
	{
		super(x, y);
		this.value = value;
	}
	
	/**
	 * Sets the value of the item
	 * @param someValue - Values of the item
	 */
	public void setValue(int someValue)
	{
		this.value = someValue;
	}
	
	/**
	 * Gets the value of the SquareItem
	 * @return - The value of the square
	 */
	public int getValue()
	{
		return value;
	}
	
}
