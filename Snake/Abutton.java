import java.awt.*;

/**
 * Abutton.java
 * @author Fleury
 * This class creates a button, each button has a color and a string written on it.
 */

public class Abutton
{
	/**
	 * To define the size of our buttons
	 */
	public final static int						
				BUTTON_WIDTH = 64,
				BUTTON_HEIGHT = BUTTON_WIDTH / 2;

	/**
	 * Default constructor. By default, we create a button with a questionable label in plain black
	 * at an arbitrary location and with an arbitrary size
	 */
	public Abutton()
	{										
		this("?????",						
			 Color.black,					
			 43, 47,						
			 143, 147);						
	}

	/**
	 * Most specific constructor.  We create a button with a given label, in a given color, at
	 * a given location, and with a given size.
	 * @param someLabel - The label
	 * @param someColor - The color
	 * @param someX - The x location
	 * @param someY - The y location
	 * @param someWidth - The width
	 * @param someHeight - The height
	 */
	public Abutton(String someLabel,
				   Color someColor,
				   int someX, int someY,
				   int someWidth, int someHeight)
	{										
		setup(someLabel,					
			  someColor,					
			  someX, someY,					
			  someWidth, someHeight);		
	}

	/**
	 * Initializes (or re-sets) the components of a button.
	 * @param someLabel - The label
	 * @param someColor - The color
	 * @param someX - The x location
	 * @param someY - The y location
	 * @param someWidth - The width
	 * @param someHeight - The height
	 */
	public void setup(String someLabel,
					  Color someColor,
					  int someX, int someY,
					  int someWidth, int someHeight)
	{
		label = someLabel;
		color = someColor;
		x = someX;
		y = someY;
		width = someWidth;
		height = someHeight;
		up = true;							//	Initially, the button is up
	}

	/**
	 * Flips the up/down state of a button 
	 */
	public void flip()
	{
		up = ! up;
	}

	/**
	 * Determines if a given point is inside a button.
	 * Note:  The boundary is considered within (inside) the button.
	 * @param someX - x location that the mouse would be inside
	 * @param someY - y location that the mouse would be inside
	 * @return - boolean, if true the mouse is inside the button, if false
	 * the mouse is not inside the button
	 */
	public boolean isInside(int someX, int someY)
	{
		return ((someX >= x) && (someX <= x + width)
				&& (someY >= y) && (someY <= y + height));
	}

	/**
	 * Draws/paints the button
	 * @param pane - The graphics pane
	 */
	public void paint(Graphics pane)
	{
		final int DELTA = 2;				//	To define the space between the
											//		frame and the inside of a button

		pane.setColor(Color.black);			//	Drawing the button frame
		pane.drawRect(x, y, width, height);	//		in black,

		pane.setColor(color);				//		and the inside of the button
		pane.fill3DRect(x + DELTA, y + DELTA,	//	in the given color
						width - (2*DELTA - 1),
						height - (2*DELTA - 1),
						up);

		pane.setColor(Color.black);			//	Finally, we put the label in black
											//		... and nicely centered
		int labelWidth = pane.getFontMetrics().stringWidth(label);
		int labelHeight = pane.getFontMetrics().getAscent();
		pane.drawString(label,
						x + (width - labelWidth)/2,
						y + (height + labelHeight)/2);
	}
	
	/**
	 * To hold the label
	 */
	private String label;					
	
	/**
	 * The color
	 */
	private Color color;
	
	/**
	 * The location, and the size of the button
	 */
	private int x, y, width, height;						
	
	/**
	 * To know if the button is up or not
	 */
	private boolean up;						
}