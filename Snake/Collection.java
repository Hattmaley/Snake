import java.awt.Color;
import java.awt.Graphics;

/**
 * Collection.java
 * @author Matt Haley
 * The collection class contains the linked list that the snake is made up of.  This class can add Nodes
 * to the snake, remove Nodes from the snake, and check if the linked list is empty.  Moreover, sorting
 * operations done to the snake will be handled in this class.
 */

public class Collection implements CollectionInterface
{
	/**
	 * Holds the first reference of the linked list
	 */
	private Node head;
	
	/**
	 * Keeps how many elements are in the linked list
	 */
	private int listcount;
	
	/**
	 * Keeps track of the "selected" item
	 */
	private Node selected;
	
	/**
	 * Stores the previous x value of the head node
	 */
	private int prevX = 0;
	
	/**
	 * Stores the previous y values of the head node
	 */
	private int prevY = 0;
	
	/**
	 * Distances found on the game board, these are basically 1 unit shorter than the normal gameboard
	 * These will be used for out of bounds errors 
	 */
	private final int X = 19; 
	private final int Y = 39; 
	private final int WIDTH = 601;
	private final int LENGTH = 441;
	
	/**
	 * Default constructor
	 */
	public Collection()
	{
		selected = null;
	}
	
	/**
	 * Determines if the linked list is empty.  Returns true if the list is empty
	 * Returns false if the list has one or more Nodes
	 */
	public boolean isEmpty() 
	{
		//If selected and head are null then it is empty
		if(selected != null && head != null)
		{
			return true;
		}
		//Otherwise it has elements in it
		else
		{
			return false;
		}
	}
	
	/**
	 * Adds a square to the linked list.
	 * @param someSquare - Square to be added
	 */
	public void add(Square someSquare) 
	{
		//Check if the linked list is empty
		if(isEmpty() == false) //Could do (head == null)
		{
			//Add the first square
			someSquare.setLocation(100, 100);
			someSquare.setColor(randomColor());
			head = new Node(someSquare);
			selected = head;
		}
		//List already has a node
		else
		{
			//Add a node based on the first one
			someSquare.setLocation(someSquare.getXLocation(), someSquare.getYLocation());
			someSquare.setColor(randomColor());
			selected.setNext(new Node(someSquare));
			selected = selected.getNext();
		}
		//Increase the size of the list by one
		listcount++;
	}

	/**
	 * Removes all the nodes in the list.  
	 * Note - There is no need to remove each individual node, the game should reset them all at once
	 */
	public void remove() 
	{
		//Lots of Garbage Collection
		//Head is now null
		head = null;
		//The selected node is now null
		selected = null;
		//The list is reset to 0
		listcount = 0;
	}
	
	/**
	 * Updates the location of all the following square in reference to the head square.
	 */
	public void updateSnake()
	{
		//The list must be greater than one
		if(listcount > 1)
		{
			//Set current to head
			Node current = head;
			//Used to set the next location to the previous location
			int setX = current.getSquare().getXLocation();
			int setY = current.getSquare().getYLocation();
			//Goes through the entire list
			for(int i = 0; i < listcount - 1; i++)
			{
				//Gets the next location
				int getX = current.getNext().getSquare().getXLocation();
				int getY = current.getNext().getSquare().getYLocation();
				//Sets the current node to the previous location
				current.getNext().getSquare().setLocation(setX, setY);
				//Get the next Node in the list
				current = current.getNext();
				//The next location now becomes the current location
				setX = getX;
				setY = getY;
			}
		}
	}
	
	/**
	 * Sorts the snake. The snake is sorted by the colors of the rainbow (ROYGBIV)
	 */
	public void sortSnake()
	{
		//Sort the snake
		bubbleSort();
		//or
		//head = mergeSort(head);
		
	}
	
	/**
	 * Bubble sorts the list
	 */
	private void bubbleSort()
	{
		Node current;
		//If the collection is empty, it is sorted
		if (isEmpty() == false)
		{
			System.out.println("Sorted!"); 
		}
		else if (head.getNext() == null)
		{
			System.out.println("Sorted!");
		}
		//If the collection has multiple items in it
		else 
		{
			current = head;			//Current begins as head
			boolean done = true;	//Boolean to know when the loop should end
			while (done) 
			{
				done = false;
				for(int i = 0; i < listcount - 1; i++)	//Goes through all the elements
				{
					//Defines the colors as number so it is easy to compare
					int C1 = (colorToNumber(current.getSquare().getColor()));
					int C2 = (colorToNumber(current.getNext().getSquare().getColor()));
					//If color 1 is greater than color 2, then swap
					if (C1 > C2) 
					{
						Color temp = current.getSquare().getColor();
						current.getSquare().setColor(current.getNext().getSquare().getColor());
						current.getNext().getSquare().setColor(temp);
						done = true;
					}
					//Get the next node
					current = current.getNext();
				}
				//Reset current to head
				current = head;
			} 
		}
	}
	
	/**
	 * Finds the size of the linked list
	 * @param node - Node to find the size of
	 * @return - The size of the linked list
	 */
	private int count(Node node)
	{
		//Size is initially 0
		int size = 0;
		//If the list is empty, there are 0 elements in it
		if(isEmpty() == false)
		{
			return 0;
		}
		//Go through each node, increment the size
		else
		{
			while(node != null)
			{
				size++;
				node = node.getNext();
			}
		}
		//Return the size of the linked list
		return size;
	}
	
	/**
	 * Merge Sorts the linked list
	 * @param list - List that needs to be sorted
	 * @return - The sorted linked list
	 */
	private Node mergeSort(Node list)
	{
		//Break into halves
		Node firstHalf = list;
		Node secondHalf;
		
		Node sortedList;
		
		int count = count(list);
		if(count <= 1)
		{
			//Collection is 1 or lower, this means its already sorted
			return list;
		}
		else
		{
			//Divide in two
			//Find the middle
			for(int i = 0; i < (count/2); i++)
			{
				firstHalf = firstHalf.getNext();
			}
			//The start of the second half is the next element in the first half
			secondHalf = firstHalf.getNext();
			//The first half can be made by breaking the second half off
			firstHalf.setNext(null);
			
			//Merge sorts until each number is separate
			firstHalf = mergeSort(firstHalf);
			secondHalf = mergeSort(secondHalf);
			
			//Merge the sorted list into one list
			sortedList = merge(firstHalf, secondHalf);
			
			//Returns the sorted list
			return sortedList;
		}
	}
	
	/**
	 * Sorts and merges the lists together from merge sort -- This method is not working
	 * 															I can't figure out what I did wrong
	 * @param a - The first list to be sorted and merged
	 * @param b - The second list to be sorted and merged
	 * @return - The sorted and merged linked list
	 */
	private Node merge(Node a, Node b)
	{
		//Creates the data collection the result of the merge is held
		Node c = new Node();

		//Both a or b must not be null
		while(a != null || b != null)
		{
			//If they are both not null
			if(a != null && b != null)
			{
				//i is smaller
				if(colorToNumber(a.getSquare().getColor()) <= colorToNumber(b.getSquare().getColor()))
				{
					c.setNext(a);
					c.getNext().setNext(null);
					a = a.getNext();
				}
				//j is smaller
				else
				{
					c.setNext(b);
					c.getNext().setNext(null);
					b = b.getNext();
				}
			}

			else if(a == null && b != null)	//Can just add the elements because they are sorted
			{
				c.setNext(b);
			}

			else if(b == null && a != null)  //Can just add the elements because they are sorted
			{
				c.setNext(a);
			}
		}
		//Return the result
		return c;
	}
	
	/**
	 * Paints the collection in the frame.  Paints all the elements of the collection to get the
	 * snake like appearance
	 * @param pane - The location to be painted
	 */
	public void paint(Graphics pane)
	{
		Node current = head;
		//Paints the items
		while(current != null)
		{
			//Paints that item
			current.getSquare().paint(pane);
			//Gets the next Item
			current = current.getNext();
		}
	}
	
	/**
	 * Checks if the snake collides with itself.  The game will end if the collision occurs
	 * @return - Boolean if the collision is true or false.  If there is a collision it
	 * returns true.  Otherwise false if returned for no collision.
	 */
	public boolean checkForCollisionSquare()
	{
		Node current = head;
		//If there are more than 2 elements the head node has the potential to hit another 
		if(count(current) > 2)
		{
			//Current is initially set to be the third node
			current = current.getNext().getNext();
			//Check if each node has the same location
			while(current != null)
			{
				if(head.getSquare().getXLocation() == current.getSquare().getXLocation() && 
						head.getSquare().getYLocation() == current.getSquare().getYLocation())
				{
					//If the location is the same return true
					return true;
				}
				//Get the next node
				current = current.getNext();
			}
		}
		//If no squares have the same location return false
		return false;
	}
	
	/**
	 * Checks for a "collision" or swapping with only the second square in the snake.  The square
	 * located directly after the head.  An error was occurring where the snake could eat itself if
	 * multiple keys were pressed in the delay.  
	 * @return - Boolean, if true the snake would have eaten itself and the correction needs to be 
	 * delt in the SnakeGame class. False if the error did not occur.
	 */
	public boolean checkForCollisionNext()
	{
		Node current = head;
		//Run if there are at least two elements
		if(count(current) >= 2)
		{
			//Get the next node
			current = current.getNext();
			//If they are the same, return true
			if(head.getSquare().getXLocation() == prevX && 
					head.getSquare().getYLocation() == prevY)
			{
				return true;
			}
			
			//Store the current x and y locations, will be used next time for the next locations
			prevX = current.getSquare().getXLocation();
			prevY = current.getSquare().getYLocation();
		
		}
		//If there are less than two nodes, return false
		return false;
	}
	
	/**
	 * Determines if the snake collides with the sides that it is contained in.  If the snake touches
	 * one of the sides the game should end
	 * @return -Boolean if the collision is true or false.  If there is a collision it
	 * returns true.  Otherwise false if returned for no collision.
	 */
	public boolean checkForCollisionBoundary()
	{
		if(head != null)
		{
			//The game boundary
			//If the head square goes out of these locations, return true
			if(head.getSquare().getXLocation() <= X || head.getSquare().getXLocation() >= WIDTH+X)
			{
				return true;
			}
			if(head.getSquare().getYLocation() <= Y || head.getSquare().getYLocation() >= LENGTH+Y)
			{
				return true;
			}
		}
		//If the square is within these boundaries, return false
		return false;
	}
	
	/**
	 * Determines if the snake collides with the item.  If the snake touches the item
	 * the item should move to a new location and the score should increase
	 * The new item location should not be under the snake.  It can only be in open space where
	 * the snake is not located
	 * @param someSquare - Item that the collision should be checked with
	 * @return - Return true if there is a collision with the item.  Return false if the snake is not
	 * where the item is.
	 */
	public boolean checkForCollisionItem(SquareItem someSquare)
	{
		//Checks for a collision with an item so that an item wont be painted under the snake
		Node current = head;
		//Goes through each node and checks if it is the same as the item
		while(current != null)
		{
			if(current.getSquare().getXLocation() == someSquare.getXLocation() &&
					current.getSquare().getYLocation() == someSquare.getYLocation())
			{
				//If the item will be printed under the snake, correct it in the other class
				return true;
			}
			//Get the next node
			current = current.getNext();
		}
		return false;
	}
	
	/**
	 * Generates a random color
	 * @return - Returns the color generated
	 */
	private Color randomColor()
	{
		//Gets one of 7 colors
		int colorNumber = (int) (Math.random() * 7) + 1;
		//Returns that color
		return numberToColor(colorNumber);
	}
	
	/**
	 * For each number there is an assigned color.  The numbers 1-7 have a color associated with them
	 * @param colorNumber - The number 1-7 of the color that is wanted
	 * @return - The color based on the number
	 */
	private Color numberToColor(int colorNumber)
	{
		//Each color has a numeric value, easier for sorting
		if(colorNumber == 1)
		{
			return Color.MAGENTA;
		}
		else if(colorNumber == 2)
		{
			return Color.RED;
		}
		else if(colorNumber == 3)
		{
			return Color.ORANGE;
		}
		else if(colorNumber == 4)
		{
			return Color.YELLOW;
		}
		else if(colorNumber == 5)
		{
			return Color.GREEN;
		}
		else if(colorNumber == 6)
		{
			return Color.CYAN;
		}
		else if(colorNumber == 7)
		{
			return Color.BLUE;
		}
		else
		{
			return Color.BLUE;
		}

	}
	
	/**
	 * Converts a color into a number, integer comparison is easier than color comparison when sorting.
	 * The seven colors that can be converted are magenta, red, orange, yellow, green, cyan, and blue.
	 * If there is an invalid color inputted, -1 will be returned.
	 * @param color - Color that needs to be converted
	 * @return - An integer based on the color value
	 */
	private int colorToNumber(Color color)
	{
		//Each color has a numeric value, easier for sorting
		if(color == Color.MAGENTA)
		{
			return 1;
		}
		if(color == Color.RED)
		{
			return 2;
		}
		else if(color == Color.ORANGE)
		{
			return 3;
		}
		else if(color == Color.YELLOW)
		{
			return 4;
		}
		else if(color == Color.GREEN)
		{
			return 5;
		}
		else if(color == Color.CYAN)
		{
			return 6;
		}
		else if(color == Color.BLUE)
		{
			return 7;
		}
		else
		{
			return -1;
		}
	}
} // End of class

//Merge Sort Attempts
//I could not get my merge function to work
/*
private Node merge(Node i, Node j)
{
	
	Node next = null;
	Node result = null;
	while(i != null || j != null)
	{
		//If they are both not null
		if(i != null && j != null)
		{
			//If all values of i are used
			if(i == null && j != null)	//Can just add the elements because they are sorted
			{
				result.setNext(j);
			}
			//If all values of j are used
			if(j == null && i != null)  //Can just add the elements because they are sorted
			{
				result.setNext(i);
			}
			
			
			//i is smaller
			if(colorToNumber(i.getSquare().getColor()) < colorToNumber(j.getSquare().getColor()))
			{
				
				Node itemp = i;
				itemp.getNext();
				itemp.setNext(null);
				result.setNext(itemp);
				i = i.getNext();
				
			}
			//j is smaller
			else
			{
				Node jtemp = j;
				jtemp.getNext();
				jtemp.setNext(null);
				result.setNext(jtemp);
				j = j.getNext();
			}
		}
		
	
	}
	//Return the result
	return result;
	
}
*/


//Yet another
/*
Node c = new Node();

while ((a != null) && (b != null)) 
{
	if (colorToNumber(a.getSquare().getColor()) <= colorToNumber(b.getSquare().getColor())) 
	{
		Color temp = a.getSquare().getColor();
		a.getSquare().setColor(b.getSquare().getColor());
		b.getSquare().setColor(temp);
		a = a.getNext();
	}
	else 
	{
		Color temp = b.getSquare().getColor();
		b.getSquare().setColor(a.getSquare().getColor());
		a.getSquare().setColor(temp);
		b = b.getNext();
	}
}
while(a != null)
{
	a.getNext();
}
a.setNext(b);
return a;
*/