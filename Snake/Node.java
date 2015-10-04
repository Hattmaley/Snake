/**
 * Node.java
 * @author Matt Haley
 * This class creates a node for the linked list.  A node has a reference and Item.
 */

public class Node 
{
	/**
	 * An square that is to be held in a node
	 */
	private Square square;
	
	/**
	 * Node used to determine the next node in the linked list
	 */
	private Node next;
	
	/**
	 * Default Constructor
	 */
	public Node()
	{
		//Default Constructor
	}
	
	/**
	 * Constructor that creates a node with a square
	 * @param someSquare - Square that is to be added
	 */
	public Node(Square someSquare)
	{
		this.square = someSquare;
	}
	
	/**
	 * Sets the Node to have a certain square  
	 * @param someSquare - Square to be contained in the Node
	 */
	public void setSquare(Square someSquare)
	{
		this.square = someSquare;
	}
	
	/**
	 * Gets the square that is in the Node
	 * @return - Square that is in the Node
	 */
	public Square getSquare()
	{
		return square;
	}

	/**
	 * Sets the next node after the current node
	 * @param someNode - Node that is to be set next
	 */
	public void setNext(Node someNode)
	{
		this.next = someNode;
	}
	
	/**
	 * Gets the next node after the current node
	 * @return - The next Node
	 */
	public Node getNext()
	{
		return next;
	}
}
