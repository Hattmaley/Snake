import java.awt.event.*;

/**
 * UneFenetre.java
 * @author Gaston
 * Implements some (actually only one) of the window's useful methods.
 */

public class UneFenetre extends WindowAdapter
{
	/**
	 * Just so we can be neat about the close button.
	 */
	public void windowClosing(WindowEvent event)
	{
		System.exit(0);
	}
}