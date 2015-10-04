import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * SnakeGameWithSidePanel.java
 * @author Matt Haley
 * Extension of SnakeGame, this class creates the side panel in the game.  There are a lot of constants so
 * I generated a new class to keep track of them.
 */

public class SnakeGameWithSidePanel extends SnakeGame
{
	/**
	 * Side panel x and y locations
	 */
	private final int SIDE_PANEL_X = 640; 
	private final int SIDE_PANEL_Y =  39;
	
	/**
	 * Side panel width and length
	 */
	private final int SIDE_PANEL_WIDTH = 330;
	private final int SIDE_PANEL_LEGNTH = 441;
	
	/**
	 * Location of the title, SNAKE
	 */
	private final int SNAKE_LOCATION_X = 710;
	private final int SNAKE_LOCATION_Y = 100;
	
	/**
	 * Location of the title Snake Speed
	 */
	private final int SNAKE_SPEED_LOCATION_X = 680;
	private final int SNAKE_SPEED_LOCATION_Y = 220;
	
	/**
	 * Location of the words, CURRENT SCORE
	 */
	private final int CURRENT_SCORE_LOCATION_X = SNAKE_SPEED_LOCATION_X;
	private final int CURRENT_SCORE_LOCATION_Y = SNAKE_SPEED_LOCATION_Y + 70;
	
	/**
	 * Location of the words, SCORE
	 * Similar to the top one, but this has the value written next to it
	 */
	private final int SCORE_LOCATION_X = CURRENT_SCORE_LOCATION_X;
	private final int SCORE_LOCATION_Y = CURRENT_SCORE_LOCATION_Y + 20;
	
	/**
	 * Location of the value of the current score
	 */
	private final int SCORE_LOCATION_VALUE_X = SCORE_LOCATION_X + 70;
	private final int SCORE_LOCATION_VALUE_Y = SCORE_LOCATION_Y;
	
	/**
	 * Location of the words, FRUIT EATEN
	 */
	private final int FRUIT_LOCATION_X = SCORE_LOCATION_X;
	private final int FRUIT_LOCATION_Y = SCORE_LOCATION_Y + 20;
	
	/**
	 * Location of the value of the amount of fruit eaten
	 */
	private final int FRUIT_LOCATION_VALUE_X = FRUIT_LOCATION_X + 135;
	private final int FRUIT_LOCATION_VALUE_Y = FRUIT_LOCATION_Y;
	
	/**
	 * Location of the words, HIGH SCORE
	 */
	private final int HIGH_SCORE_LOCATION_X_TEXT = FRUIT_LOCATION_X;
	private final int HIGH_SCORE_LOCATION_Y_TEXT = FRUIT_LOCATION_Y + 40;
	
	/**
	 * Location of the words, SCORE
	 * Similar to the top one, but this has the value written next to it
	 */
	private final int HIGH_SCORE_LOCATION_X = HIGH_SCORE_LOCATION_X_TEXT;
	private final int HIGH_SCORE_LOCATION_Y = HIGH_SCORE_LOCATION_Y_TEXT + 20;
	
	/**
	 * Location of the value of the high score
	 */
	private final int HIGH_SCORE_LOCATION_VALUE_X = HIGH_SCORE_LOCATION_X + 70;
	private final int HIGH_SCORE_LOCATION_VALUE_Y = HIGH_SCORE_LOCATION_Y;
	
	/**
	 * Location of the words, FRUIT EATEN
	 */
	private final int HIGH_FRUIT_LOCATION_X = HIGH_SCORE_LOCATION_X;
	private final int HIGH_FRUIT_LOCATION_Y = HIGH_SCORE_LOCATION_Y + 20;
	
	/**
	 * Location of the value for the amount of fruit eaten
	 */
	private final int HIGH_FRUIT_LOCATION_VALUE_X = HIGH_FRUIT_LOCATION_X + 135;
	private final int HIGH_FRUIT_LOCATION_VALUE_Y = HIGH_FRUIT_LOCATION_Y;
	
	/**
	 * Location of the word, PAUSE, used when the game is paused
	 */
	private final int PAUSE_X = 270;
	private final int PAUSE_Y = 200;
	
	/**
	 * Font sizes used throughout the program
	 */
	private final int FONT_SIZE = 60;
	private final int FONT_SIZE_2 = 20;
	private final int FONT_SIZE_3 = 28;
	
	/**
	 * Default Constructor
	 */
	public SnakeGameWithSidePanel()
	{
		super();
	}
	
	/**
	 * Paints all the buttons and elements in the game of snake
	 */
	public void paint(Graphics pane)
	{
		super.paint(pane);
		
		//The rectangles in the game
		pane.setColor(Color.WHITE);
		pane.drawRect(SIDE_PANEL_X, SIDE_PANEL_Y, SIDE_PANEL_WIDTH, SIDE_PANEL_LEGNTH);
		
		//New Font!
		Font myFont = new Font("MV Boli", Font.BOLD, FONT_SIZE);
		pane.setColor(new Color(0,0,200));
		pane.setFont(myFont);
		
		//Draws the title
		pane.drawString("Snake", SNAKE_LOCATION_X, SNAKE_LOCATION_Y);
		
		//New Font!
		Font myFont2 = new Font("MV Boli", Font.BOLD, FONT_SIZE_3);
		pane.setColor(new Color(0,0,200));
		pane.setFont(myFont2);
		
		//Draws the minor titles
		//Current Score
		pane.drawString("Current Score", CURRENT_SCORE_LOCATION_X, CURRENT_SCORE_LOCATION_Y);
		//High Score
		pane.drawString("High Score", HIGH_SCORE_LOCATION_X_TEXT, HIGH_SCORE_LOCATION_Y_TEXT);
		//Different Speed Buttons Title
		pane.drawString("Snake Speed", SNAKE_SPEED_LOCATION_X, SNAKE_SPEED_LOCATION_Y);
		
		//PAUSE
				if(suspend == true)
				{
					//If paused, Pause is shown on the screen
					pane.drawString("PAUSE", PAUSE_X, PAUSE_Y);
				}
		
		//New Font!
		Font myFont3 = new Font("MV Boli", Font.BOLD, FONT_SIZE_2);
		pane.setColor(Color.WHITE);
		pane.setFont(myFont3);
		
		//Draws the current score
		pane.drawString("Score: ", SCORE_LOCATION_X, SCORE_LOCATION_Y);
		pane.drawString(String.valueOf(currentScore), SCORE_LOCATION_VALUE_X, SCORE_LOCATION_VALUE_Y);
		
		//Draws the current fruit
		pane.drawString("Fruit Eaten: ", FRUIT_LOCATION_X, FRUIT_LOCATION_Y);
		pane.drawString(String.valueOf(currentFruit), FRUIT_LOCATION_VALUE_X, FRUIT_LOCATION_VALUE_Y);
		
		//Draws the high score
		pane.drawString("Score: ", HIGH_SCORE_LOCATION_X, HIGH_SCORE_LOCATION_Y);
		pane.drawString(String.valueOf(highScore), HIGH_SCORE_LOCATION_VALUE_X, 
																				HIGH_SCORE_LOCATION_VALUE_Y);
		//Draws the current fruit high score
		pane.drawString("Fruit Eaten: ", HIGH_FRUIT_LOCATION_X, HIGH_FRUIT_LOCATION_Y);
		pane.drawString(String.valueOf(highFruit), HIGH_FRUIT_LOCATION_VALUE_X, 
																				HIGH_FRUIT_LOCATION_VALUE_Y);
	}
}
	

	
	

