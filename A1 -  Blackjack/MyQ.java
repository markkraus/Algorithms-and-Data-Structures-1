public interface MyQ<T> extends QueueInterface<T>
{

	// Return the number of items currently in the MyQ.  Determining the
	// length of a queue can sometimes very useful.
	public int size();

	// Return the length of the underlying data structure which is storing the
	// data.  In an array implementation, this will be the length of the array.
	// This method would not normally be part of a queue but is included here to
	// enable testing of your resizing operation.
	public int capacity();

	// Methods to get and set the value for the moves variable.  The idea for
	// this is the same as shown in Recitation Exercise 2 -- but now instead
	// of a separate interface these are incorporated into the MyQ<T>
	// interface.  The value of your moves variable should be updated during
	// an enqueue() or dequeue() method call.  However, any data movement required
	// to resize the underlying array should not be counted in the moves.
	public int getMoves();
	public void setMoves(int moves);
}
