// CS 0445 Spring 2022
// Recitation Exercise 5

// Read over the partial implementation of the DoubleRecList below.
// Run the main program (CS445Rec5.java) and trace the constructor and
// toString() methods so that you fully understand how the recursion is 
// working.

// Note that even though the Node<T> class is a generic class, the
// DoubleRecList class is not generic -- it is fixed with a Node<String>
// as its data.  Note also that Node<T> is a separate, public class so
// we don't have access to its private data.  Thus, we need to use the
// accessor and mutator methods within the Node<T> class for access.
// See Node<T> for details.

// Add the reverse() method and test it using the same main program
// (after removing the comments)

public class DoubleRecList
{
	private Node<String> front;  // instance variable for front
	
	// Non-recursive method to meet the spec of the constructor.  Since the
	// recursive method needs more parameters we will call it from this
	// non-recursive "stub".
	public DoubleRecList(String [] data)
	{
		front = null;
		if (data.length > 0)   // if there is some data, call the recursive
			rec_init(data, 0); // method to initialize.  The int here is the
							   // current position in the data array.
	}
	
	// Recursive method to initialize the list with data.  This will build
	// the list from back to front, putting each new Node at the front of
	// the list.  See more comments below.
	private void rec_init(String [] data, int loc)
	{
		// If we are not at the end of the array, recurse before doing
		// anything else.  The idea is that we will add the last value
		// at the end of the list in the final recursive call, and then
		// add each earlier item at the front of the list when the call
		// resumes.
		if (loc < data.length-1) // up to the 2nd last value
		{
			// Recursively initialize the rest of the list
			rec_init(data, loc+1);
			
			// Create a node for the new front with current data
			Node<String> temp = new Node<String>(data[loc]);
			
			// Get back of list as prev of old front
			Node<String> back = front.getPrevNode();
			
			// Connect new front to the rest of the list and set the
			// front to the new node.
			temp.setPrevNode(back);
			back.setNextNode(temp);
			temp.setNextNode(front);
			front.setPrevNode(temp);
			front = temp;
		}
		else if (loc == data.length-1)  // last value (or only value)
		{
			// The end Node in the list will also be the first Node created
			// and is thus a special case -- we must link it back to itself
			// for next and previous.
			front = new Node<String>(data[loc]);
			front.setPrevNode(front);
			front.setNextNode(front);
		}
	}
	
	// Similar to the constructor, we have a non-recursive method to meet
	// the spec of the toString() method.  This then calls / returns a
	// recursive method to actually generate the string.
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		Node<String> curr = front;
		
		// Since our list is circular we need a way to detect that all Nodes
		// have been processed.  We can do this by counting (if we know the
		// size of the list).  We can also do this by checking to see when we 
		// have circled all the way around the list back to the beginning.  For
		// this approach we have a special case to process the front node BEFORE
		// calling the recursive method.
		if (curr != null)  // make sure list is not empty
		{
			str.append(curr.getData());  // Append data from first Node
			str.append(" ");
			return rec_toString(curr.getNextNode(), str);  // Recurse to process
										 // the rest of the list.
		}
		else return str.toString();
	}
	
	// Recursive method to convert the list into a String.  This is using
	// a StringBuilder.  Note that the StringBuilder was created above in the
	// non-recursive method, and then it is passed into the recursive method
	// as an argument.
	private String rec_toString(Node<String> curr, StringBuilder B)
	{
		// Base case is now getting back to the front node rather than getting
		// to null.  Be careful if implementing in this way not to miss the front
		// Node or to count it twice.  Note also that we mut return the recursive
		// call since it returns a String.  Make sure to properly return a value
		// whenever the method is not void.
		if (curr != front)
		{
			B.append(curr.getData());
			B.append(" ");
			return rec_toString(curr.getNextNode(), B);
		}
		// When we get back to the front all of the data has been added so just
		// return the String.
		else
			return B.toString();
	}
	
	// Method to reverse the data in the list.
	// You must implement this method
	// There are several ways to do this.  In the approach I am taking I first extract
	// the last node and assign it to the front of the "reversed" list.  Then I 
	// recursively traverse backward through the original list while moving forward
	// through the new list.  At each call I am removing a node from the old list and 
	// adding it to end of the new list.  Note that no new Node objects are being 
	// created -- the existing Nodes are simply being moved around.
	public void reverse()
	{
		if (front != null)  // if front == null do nothing
		{
			// Front of reversed list will be last node in the list, which is the prev
			// of the front.
			Node<String> revList = front.getPrevNode();
			
			// Get the node before that to start going "backward" through original list
			Node<String> curr = revList.getPrevNode();
			
			// Set prev of front to null -- this will allow us to check for null in
			// our recursive method
			front.setPrevNode(null);
			
			// Set up new front Node as the old last Node
			front = revList;
			
			// Make recursive call.  Note that this method is void, so we just call
			// it without any return.  Compare this to the toString() method above.
			rec_reverse(revList, curr);
		}
	}
	
	// In the recursive method, newList is the list we are building from front to
	// back and oldList is the previous list we are traversing from back to front.
	private void rec_reverse(Node<String> newList, Node<String> oldList)
	{
		// We are not at end of list (in reverse).  Above we set front.prev to null
		// to be able to detect this base case.
		if (oldList != null)
		{
			// temp will be prev node of old list - to keep moving down old list
			Node<String> temp = oldList.getPrevNode();
			
			// Connect current node of old list to end of new list
			newList.setNextNode(oldList);
			oldList.setPrevNode(newList);
			
			// Recurse forward through new list and backward through old list
			rec_reverse(newList.getNextNode(), temp);
		}
		else  // old list is null -- all nodes have been processed
		{
			// Base case -- we have reversed all of the nodes, but need to make
			// it circular.  Connect front to end and vice versa.
			newList.setNextNode(front);
			front.setPrevNode(newList);
		}
	}

}
