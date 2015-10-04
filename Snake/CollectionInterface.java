/**
 * CollectionInterface.java
 * @author Matt Haley
 * Interface to create a collection of a linked list for the game Snake
 */

public interface CollectionInterface 
{
	/**
	 * Determines if the linked list is empty.  Returns true if the list is empty
	 * Returns false if the list has one or more Nodes
	 * @return boolean - True if list is empty, false if array has at least
	 * one value
	 */
	public boolean isEmpty();
	
	/**
	 * Adds a square to the linked list.
	 *  @param someSquare - Square to be added
	 */
	public void add(Square someSquare);
	
	/**
	 * Removes all the nodes in the list.  
	 * Note - There is no need to remove each individual node, the game should reset them all at once
	 */
	public void remove();
}
