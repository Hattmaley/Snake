import java.awt.Color;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

/**
 * SnakeGame.java
 * @author Matt Haley
 * 
 * This class creates the game environment for the game snake
 */


public class SnakeGame extends Frame implements KeyListener, AlarmInterface, MouseListener
{
	//Current Score
	/**
	 * Contains the current score of the game
	 */
	protected int currentScore;
	
	/**
	 * Contains the current amount of fruit eaten
	 */
	protected int currentFruit;
	
	//High Score
	/**
	 * Contains the high score of the game
	 */
	protected int highScore;
	
	/**
	 * Contains the high score for the amount of fruit eaten
	 */
	protected int highFruit;
	
	//Mouse Locations
	/**
	 * Contains the x mouse location
	 */
	private int xMouse;
	
	/**
	 * Contains the y mouse location
	 */
	private int yMouse;
	
	//Objects
	/**
	 * Contains the window options, so that the application can be closed
	 */
	private UneFenetre myWindow = new UneFenetre();
	
	/**
	 * Contains the alarm, so that the computer thread can be used
	 */
	private Alarm alarm;
	
	//Directional booleans
	/**
	 * If true the direction will be up 
	 */
	private boolean up = false;
	
	/**
	 * If true the direction will be down
	 */
	private boolean down = false; 
	
	/**
	 * If true the direction will be left
	 */
	private boolean	left = false;
							
	/**
	* If true the direction will be right
	*/
	private boolean right = false;
	
	//Game Window Properties
	/**
	 * Contains the x location of the box in which snake is played
	 */
	private final int GAME_X = 19; 
	
	/**
	 * Contains the y location of the box in which snake is played
	 */
	private final int GAME_Y = 39;
	
	/**
	 * Contains the width of the box in which snake is played
	 */
	private final int GAME_WIDTH = 602;
	
	/**
	 * Contains the length of the box in which snake is played
	 */
	private final int GAME_LEGNTH = 442;
	
	//Button Properties
	/**
	 * The width of each button
	 */
	private final int BUTTON_WIDTH = 60;
	
	/**
	 * The height of each button
	 */
	private final int BUTTON_HEIGHT = 25;
	
	/**
	 * Contains the location of each Button (Button name followed by x or y coordinate)
	 */
	private final int PLAYBUTTON_X = 655;
	private final int PLAYBUTTON_Y = 145;
	private final int RESETBUTTON_X = PLAYBUTTON_X + BUTTON_WIDTH;
	private final int RESETBUTTON_Y = PLAYBUTTON_Y;
	private final int PAUSEBUTTON_X = RESETBUTTON_X + BUTTON_WIDTH;
	private final int PAUSEBUTTON_Y = RESETBUTTON_Y;
	private final int SORTBUTTON_X = PAUSEBUTTON_X + BUTTON_WIDTH;
	private final int SORTBUTTON_Y = PAUSEBUTTON_Y;
	private final int INSTRUCTION_BUTTON_X = SORTBUTTON_X + BUTTON_WIDTH;
	private final int INSTRUCTION_BUTTON_Y = SORTBUTTON_Y;
	private final int SLOW_BUTTON_X = 680;
	private final int SLOW_BUTTON_Y = 230;
	private final int NORMAL_BUTTON_X = SLOW_BUTTON_X + BUTTON_WIDTH;
	private final int NORMAL_BUTTON_Y = SLOW_BUTTON_Y;
	private final int FAST_BUTTON_X = NORMAL_BUTTON_X + BUTTON_WIDTH;;
	private final int FAST_BUTTON_Y = SLOW_BUTTON_Y;
	
	//Buttons
	/**
	 * Buttons that can be used while playing the game
	 */
	private Abutton playButton;
	private Abutton pauseButton;
	private Abutton resetButton;
	private Abutton sortButton;
	private Abutton instructionButton;
	private Abutton slowButton, normalButton, fastButton;
	
	/**
	 * Boolean used if the pause button is pressed or not.
	 */
	protected boolean suspend = false;
	
	/**
	 * Creates the collection
	 */
	Collection collection = new Collection();
	
	/**
	 * Creates the head of the snake
	 */
	SquareHead square = new SquareHead(100, 100, Color.BLUE);
	
	/**
	 * Creates the item that the snake collects
	 */
	SquareItem item = new SquareItem(200, 200, Color.BLUE, 10);
	
	/**
	 * Sets the delay of the alarm, it is initially set at 100
	 */
	private int delay = 100;
	
	/**
	 * Store the previous direction of the snake
	 */
	private int prev;
	
	/**
	 * Store the current direction of the snake
	 */
	private int current = 0;
	
	/**
	 * Determines if the item is visible or not
	 */
	private boolean itemVisible = false;
	
	/**
	 * SnakeGame constructor, This constructor calls the game.  
	 * It contains the buttons that are created when the game is instantiated
	 */
	public SnakeGame()
	{
		//Create Frame
		//Title of the Game
		setTitle("Snake");
		//Location of the frame on the screen
		setLocation(10, 10);
		//Size of the Frame
		setSize(1000, 500);
		//Background Color
		//setBackground(new Color(95,200,230));
		setBackground(new Color(51,153,255));

		//Button Creation
		resetButton = new Abutton("Reset",Color.WHITE, RESETBUTTON_X, RESETBUTTON_Y, 
				BUTTON_WIDTH, BUTTON_HEIGHT);
		playButton = new Abutton("Play",Color.WHITE, PLAYBUTTON_X, PLAYBUTTON_Y, 
				BUTTON_WIDTH, BUTTON_HEIGHT);
		pauseButton = new Abutton("Pause",Color.WHITE, PAUSEBUTTON_X, PAUSEBUTTON_Y, 
				BUTTON_WIDTH, BUTTON_HEIGHT);
		sortButton = new Abutton("Sort",Color.WHITE, SORTBUTTON_X, SORTBUTTON_Y, 
				BUTTON_WIDTH, BUTTON_HEIGHT);
		instructionButton = new Abutton("Rules",Color.WHITE, INSTRUCTION_BUTTON_X, 
				INSTRUCTION_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		slowButton = new Abutton("Slow",Color.WHITE, SLOW_BUTTON_X, 
				SLOW_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		normalButton = new Abutton("Normal",Color.CYAN, NORMAL_BUTTON_X, 
				NORMAL_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		fastButton = new Abutton("Fast",Color.BLUE, FAST_BUTTON_X, 
				FAST_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

		//Allows the mouse to be used
		addMouseListener(this);
		//Allows the window to be closed
		addWindowListener(myWindow);
		//Allows the the frame to be seen
		setVisible(true);
		//Allows for the keyboard keys to be used
		this.addKeyListener(this);

		//Alarm stuff
		alarm = new Alarm(this);	//Creates alarm in this class						
		alarm.setPeriod(100);		//Period of the alarm, speed						 
		alarm.start();				//Starts the alarm
	}

	/**
	 * Paints all the buttons and elements in the game of snake
	 */
	public void paint(Graphics pane)
	{
		//The dimension rectangles in the game
		pane.setColor(Color.WHITE);
		pane.drawRect(GAME_X, GAME_Y, GAME_WIDTH, GAME_LEGNTH);
		
		//Paints the snake and item
		if(itemVisible == true)
		{
			item.paint(pane);
		}
		collection.paint(pane);
		
		//Buttons
		resetButton.paint(pane);
		pauseButton.paint(pane);
		sortButton.paint(pane);
		playButton.paint(pane);
		instructionButton.paint(pane);
		slowButton.paint(pane);
		normalButton.paint(pane);
		fastButton.paint(pane);
	}
	
	//Unused key events
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * Events that will occur if a particular key is pressed
	 * Used for moving the snake by pressing the arrow keys or WASD
	 * Uses updateKey in order to update the inputed keys
	 */
	public void keyPressed(KeyEvent e) 
	{
		updateKey(e);
	}
	
	/**
	 * Events that will occur if a particular key is pressed
	 * Used for moving the snake by pressing the arrow keys or WASD
	 */
	private boolean updateKey(KeyEvent e)
	{
		//If the snake has no following blocks
		if(currentScore < 10)
		{
			//Down and S
			if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
			{
				up = false;
				down = true;
				right = false;
				left = false;
			}
			//Left and A
			else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
			{
				left = true;
				up = false;
				down = false;
				right = false;
			}
			//Right and D
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
			{
				right = true;
				up = false;
				down = false;
				left = false;
			}
			//Up and W
			else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
			{
				up = true;
				down = false;
				right = false;
				left = false;
			}
		}
		//If the snake has one or more followers
		else
		{
			//Down and S
			if((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
																			&& up == false)
			{
				//up = false;
				down = true;
				right = false;
				left = false;
				//Store previous state
				previousState();
			}
			//Left and A
			else if((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
																			&& right == false)
			{
				left = true;
				up = false;
				down = false;
				//right = false;
				//Store previous state
				previousState();
			}
			//Right and D
			else if((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
																				&& left == false) 
			{
				right = true;
				up = false;
				down = false;
				//left = false;
				//Store previous state
				previousState();
			}
			//Up and W
			else if((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
																			&& down == false)
			{
				up = true;
				//down = false;
				right = false;
				left = false;
				//Store previous state
				previousState();
			}
		}
		//Space keybinding used for pause
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			pauseButton();
		}
		//Space keybinding used for pause
		if(e.getKeyCode() == KeyEvent.VK_P)
		{
			playButton();
		}
		repaint();
		return true;
	}
	
	/**
	 * TakeNotice contains the events that happen based on the thread of the computer.
	 * This contains the updated values for each x and y location of the snake.  This
	 * causes the following effect. This contains what happens as a result of the directional 
	 * booleans.  Also, this checks for all the collision events
	 * 
	 */
	public void takeNotice() 
	{
		if(suspend == false)
		{
			//Have to update the location of the squares here
			collection.updateSnake();
			
			//What direction is the snake going.  This updates based on key inputs
			if(down == true)
			{
				square.moveDown();
			}
			else if(left == true)
			{
				square.moveLeft();
			}
			else if(right == true)
			{
				square.moveRight();
			}
			else if(up == true)
			{
				square.moveUp();
			}
			
			//Fix weird key pressed event where when you press the key too fast the snake ate itself
			//Look at method for more details
			snakeEatsItselfFix();
			//Collision event with the Item
			collisionItem();
			//Collision event with elements of the snake
			collisionSquare();
			//Collision event with the boundary
			collisionBoundary();
			
			//Repaint
			repaint();
		}
	}
	
	/**
	 * Contains what will happen if there is a collision event with the item.  This method
	 * will increase the score of the game.  Moreover it will change the location of the item.
	 * Finally it will add an additional square to the end of the snake
	 */
	private void collisionItem()
	{
		//If the head of the snake collides with an object
		if(square.getXLocation() == item.getXLocation() && square.getYLocation() == item.getYLocation())
		{
			//Increment Score
			incrementScore();
			//Change the color and value
			changeItem();
			//Add an extra square to the end of the snake
			addSquare();
			//Repaint
			repaint();
		}
	}
	
	/**
	 * Contains what will happen if the snake collides with any of it followers. If the snake collides
	 * with itself the game will end.
	 */
	private void collisionSquare()
	{
		//Check if the snake collides with itself
		if(collection.checkForCollisionSquare() == true)
		{
			//Print the game is over
			System.out.println("Game Over! -- Collision with Snake");
			JOptionPane.showMessageDialog(null, "Game Over! -- Collision with Snake");
			//Reset the game
			resetButton();
		}
	}
	
	/**
	 * Contains what will happen if the snake collides into the side of the boundary.  If the snake
	 * collides with the edge the game will end.
	 */
	private void collisionBoundary()
	{
		//If the snake collides with the sides
		if(collection.checkForCollisionBoundary() == true)
		{
			//Print the game is over
			System.out.println("Game Over! -- Collision with Side");
			JOptionPane.showMessageDialog(null, "Game Over! -- Collision with Side");
			//Reset the game
			resetButton();
		}
	}
	
	/**
	 * The item needs to change its location every time there is a collision.  This method generates that
	 * new location.  It also takes into consideration where the snake is so that it will not paint the 
	 * item under the Snake.
	 */
	private void changeItemLocation()
	{
		//Generate a newX and newY value for the items location
		int newX, newY;
		//Get the location
		newX = getNewXLocation();
		newY = getNewYLocation();
		//If the location is painted where the snake is choose a new location
		while(collection.checkForCollisionItem(item) == true)
		{
			//Get new locations
			newX = getNewXLocation();
			newY = getNewYLocation();
			//Set those locations
			item.setLocation(newX, newY);
		}
		//Set the new x and y locations
		item.setLocation(newX, newY);
	}
	
	/**
	 * Generates a new x location for the item.  This location must be inside the boundary.
	 * @return - New x location for an item
	 */
	private int getNewXLocation()
	{
		//The new x value of the item
		int newX;
		//Range of acceptable x values if from 20 to 600 (boundary x dimensions)
		newX = (int) (Math.random() * 580) + 20;
		//Need a grid effect for the snake, the rectangle must be printed on a number divisible by 20
		if(newX % 20 != 0)
		{
			//Generates a location that is divisible by 20
			int temp = 20 - (newX % 20);
			newX = newX + temp;
		}
		//Returns the new X location
		return newX;
	}
	
	/**
	 * Generates a new y location for the item.  This location must be inside the boundary.
	 * @return - New y location for an item
	 */
	private int getNewYLocation()
	{
		//The new y value of the item
		int newY;
		//Range of acceptable x values if from 40 to 460 (boundary y dimensions)
		newY = (int) (Math.random() * 420) + 40;
		//Need a grid effect for the snake, the rectangle must be printed on a number divisible by 20
		if(newY % 20 != 0)
		{
			//Generates a location that is divisible by 20
			int temp = 20 - (newY % 20);
			newY = newY + temp;
		}
		//Returns the new X location
		return newY;
	}
	
	/**
	 * Adds a new square to the end of the snake.  The new square has a random color.
	 */
	private void addSquare()
	{
		//Creates a new follower
		//Note the plus 1 (on the x location) makes it so no collision events occur
		SquareFollower follower = new SquareFollower(square.getXLocation()+1, square.getYLocation());
		//Adds that new follower to the collection
		collection.add(follower);
	}
	
	/**
	 * Events that happen when the mouse is clicked
	 */
	public void mouseClicked(MouseEvent event)
	{
		//Checks if there is a user click
		check();
	}
	
	/**
	 * Gets the x and y mouse locations when a button is pressed
	 * Flips the button to know that it is pressed
	 */
	public void mousePressed(MouseEvent e)
	{
		//Gets the Mouse x and y locations
		xMouse = e.getX();
		yMouse = e.getY();
		flipWhenInside();
	}
	
	/**
	 * Flips the button to know that it is pressed
	 */
	public void mouseReleased(MouseEvent event) 
	{
		flipWhenInside();
	}
	
	//Unused Mouse methods
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
	
	/**
	 * Each of the buttons are flipped to show that they have been clicked
	 */
	private void flipWhenInside()
	{
		//Flips each of the buttons when the mouse is inside them
		if (resetButton.isInside(xMouse, yMouse))
		{
			resetButton.flip();
		}
		else if (sortButton.isInside(xMouse, yMouse))
		{
			sortButton.flip();
		}
		else if (playButton.isInside(xMouse, yMouse))
		{
			playButton.flip();
		}
		else if (instructionButton.isInside(xMouse, yMouse))
		{
			instructionButton.flip();
		}
		else if (pauseButton.isInside(xMouse, yMouse))
		{
			pauseButton.flip();
		}
		else if (slowButton.isInside(xMouse, yMouse))
		{
			slowButton.flip();
		}
		else if (normalButton.isInside(xMouse, yMouse))
		{
			normalButton.flip();
		}
		else if (fastButton.isInside(xMouse, yMouse))
		{
			fastButton.flip();
		}
	}
	
	/**
	 * Checks if a button has been pressed
	 */
	private void check()
	{
		//Reset
		if(resetButton.isInside(xMouse, yMouse))
		{
			resetButton();
		}
		
		//Play
		if(playButton.isInside(xMouse, yMouse))
		{
			playButton();
		}
				
		//Pause
		if(pauseButton.isInside(xMouse, yMouse))
		{
			pauseButton();
		}
				
		//Sort
		if(sortButton.isInside(xMouse, yMouse))
		{
			sortButton();
		}
		
		//Instruction
		if(instructionButton.isInside(xMouse, yMouse))
		{
			instructionButton();
		}
		
		//Slow
		if(slowButton.isInside(xMouse, yMouse))
		{
			slowButton();
		}
		
		//Normal
		if(normalButton.isInside(xMouse, yMouse))
		{
			normalButton();
		}
		
		//Fast
		if(fastButton.isInside(xMouse, yMouse))
		{
			fastButton();
		}
	}
	
	/**
	 * Resets the game
	 */
	private void resetButton()
	{
		//The pause button cannot be down for this to be used
		if(suspend == false)
		{
			//Removes all elements of the collection
			collection.remove();
			//Current Scores a reset
			currentScore = 0;
			currentFruit = 0;
			//Set the delete the item
		}
	}
	
	/**
	 * Starts the game
	 */
	private void playButton()
	{
		itemVisible = true;
		//The pause button cannot be down for this to be used
		if(suspend == false)
		{
			//Reset the game if another game is currently running
			resetButton();
			//Adds the initial head square
			collection.add(square);
			//Makes sure the head square is not moving
			resetMovement();
		}
	}
	
	/**
	 * Pauses the game.  If this button is pressed again the game unpauses
	 */
	private void pauseButton()
	{
		//Suspend is changed
		suspend = !suspend;
		//Repaints the game - To display PAUSE on the screen
		repaint();
	}
	
	/**
	 * Sorts the Snake
	 */
	private void sortButton()
	{
		//Sorts the snake
		collection.sortSnake();
		//Repaints the sorted snake
		repaint();
	}
	
	/**
	 * Contains the instructions of the game
	 */
	private void instructionButton()
	{
		JOptionPane.showMessageDialog(null, "Controls (Arrow Keys/WASD):" +  
				"\nUp Directional = Up/W" + 
				"\nDown Directional = Down/S" + 
				"\nLeft Directional = Left/A" + 
				"\nRight Directional = Right/D" +
				"\n\nAdditional Keys:" +
				"\nPause = Space" +
				"\nPlay = P Key" +
				"\n" + "\nInstructions:" +
				"\nCollect items by colliding into them with the head of the snake" +
				"\nAvoid colliding into the sides and the following snake" +
				"\n\nScore:" +
				"\nBlue items = 10 points, \nWhite items = 30 points" +
				"\n"
												);
	}
	
	/**
	 * Changes the delay speed to make the Snake move slower
	 */
	private void slowButton()
	{
		//Set the delay to be slower
		delay = 125;
		alarm.setPeriod(delay);
	}
	
	/**
	 * Sets the delay to have the original speed at the start of the game
	 */
	private void normalButton()
	{
		//Set the delay to be normal
		delay = 100;
		alarm.setPeriod(delay);
	}
	
	/**
	 * Changes the delay speed to make the Snake move faster
	 */
	private void fastButton()
	{
		//Set the delay to be faster
		delay = 75;
		alarm.setPeriod(delay);
	}
	
	/**
	 * Increments the score in the game.  This method should be called every time there is a
	 * collision with the item.  This game also checks if the high score should be updated
	 */
	private void incrementScore()
	{
		//Current Score
		currentScore = currentScore + item.getValue();
		currentFruit++;
		
		//Game Over!
		if(currentFruit == (22*30)) //(22*30) = the size of the gameboard
		{
			//Print the game is over
			System.out.println("You Win");
			JOptionPane.showMessageDialog(null, "You Win, You are the Snake Champion");
			//Reset the game
			resetButton();
			//If you want to test this and you cannot beat the game, the easiest way is to
				//block out all code under the movement directions in the takeNotice method 
				//and replace the code with the incrementScore() method. This will increment
				//the score based on the delay.  The game should be won when the current fruit
				//is equal to 660 (22*30).  This means that the entire game will be filled with
				//the snake and there will be no place else to go.  Thus, you win!!
		}
		
		//High Score
		if(currentScore >= highScore)
		{
			highScore = currentScore;
		}
		if(currentFruit >= highFruit)
		{
			highFruit = currentFruit;
		}
		
	}
	
	/**
	 * Resets the movement of the snake to make it stand still.  Useful at the beginning of the game
	 */
	private void resetMovement()
	{
		//Resets all movement
		up = false;
		down = false;
		right = false;
		left = false;
	}
	
	/**
	 * Changes the Item, new location, color, and value
	 */
	private void changeItem()
	{
		//(1/10) of the time a white item will appear
		//Generates a number 1 - 10
		int number = ((int) (Math.random() * 10) + 1);
		
		//If the number is 1, generate a white item
		if(number == 1)
		{
			Color whiteColor = Color.WHITE;
			int whiteValue = 30;
			item.setColor(whiteColor);
			item.setValue(whiteValue);
		}
		//If the number is other than 1, generate a blue item
		else
		{
			Color blueColor = Color.BLUE;
			int blueValue = 10;
			item.setColor(blueColor);
			item.setValue(blueValue);
		}
				
		//Changes the location of the item
		changeItemLocation();
	}
	
	/**
	 * This method store the current state/direction and the previous state/direction of
	 * the snake.  It used integers 1-4 to store the direction.
	 * @return - The previous state of the snake, in an integer based direction
	 * 				This is mainly used in the method below (snakeEatsItselfFix)
	 */
	private int previousState()
	{
		//If there is no current value, set the previous value to 0
		if(current == 0)
		{
			prev = 0;
		}
		//Set the previous state to the current state
		prev = current;
		//Update the current state
		if(up == true)
			current = 1;
		if(down == true)
			current = 2;
		if(left == true)
			current = 3;
		if(right == true)
			current = 4;
		//Return the previous state
		return prev;
	}
	
	/**
	 * In short this method stops the snake from eating itself when multiple keys are pressed within
	 * the delay.  
	 * 
	 * For example, if you wanted the snake to take a U turn you would press two keys
	 * in order to accomplish this.  However, if those two keys were pressed within the delay it would
	 * cause the second key pressed to designate the snake's direction.  The snake would "eat itself"
	 * because it would go directly in the opposite direction it is traveling.
	 * 
	 * This method edits the direction the snake moves if the user clicks the delay too fast.
	 * It relocates the head of the snake so that it updates where it should have and 
	 * it looks at the previous direction the snake was going and sets it in that direction.
	 * 
	 * This was the most difficult part of the program by far, even though it appears simple.
	 */
	private void snakeEatsItselfFix()
	{
		//Different States
		int upState = 1, downState = 2, leftState = 3, rightState = 4;
		
		if(current != 0)
		{
			if(collection.checkForCollisionNext() == true)
			{
				//Know when the update occurs
				System.out.println("true");
				//Suspend the system
				suspend = true;
				//Depending on the direction implement the correction
				//The snake is going left
				if(left == true)
				{
					if(prev == upState)
					{
						//Sets the location of the square to the proper place
						square.setLocation(square.getXLocation()+40, square.getYLocation());
						//Have left be false
						left = false;
						//Have the snake move in the updated direction
						//The next events have the same thought as this
						up = true;
					}
					if(prev == downState)
					{
						square.setLocation(square.getXLocation()+40, square.getYLocation());
						left = false;
						down = true;
					}
				}
				//The snake is going right
				if(right == true)
				{
					if(prev == upState)
					{
						square.setLocation(square.getXLocation()-40, square.getYLocation());
						right = false;
						up = true;
					}
					if(prev == downState)
					{
						square.setLocation(square.getXLocation()-40, square.getYLocation());
						right = false;
						down = true;
					}
				}
				//If the snake is moving up
				if(up == true)
				{
					if(prev == rightState)
					{
						square.setLocation(square.getXLocation(), square.getYLocation()+40);
						up = false;
						right = true;
					}
					if(prev == leftState)
					{
						square.setLocation(square.getXLocation(), square.getYLocation()+40);
						up = false;
						left = true;
					}
				}	
				//If the snake is moving down
				if(down == true)
				{
					if(prev == rightState)
					{
						square.setLocation(square.getXLocation(), square.getYLocation()-40);
						down = false;
						right = true;
					}
					if(prev == leftState)
					{
						square.setLocation(square.getXLocation(), square.getYLocation()-40);
						down = false;
						left = true;
					}
				}
				//Resume the game
				suspend = false;
			}	
		}
	}
}
