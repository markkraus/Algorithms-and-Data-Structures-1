public interface Indexable<T>
{
	// Get and return the value located at logical location i in the implementing
	// collection, where location 0 is the logical beginning of the collection.
	// If the collection has fewer than (i+1) items, throw an IndexOutOfBoundsException
	public T get(int i);

	// Assign item to logical location i in the implementing collection, where location 		
	// 0 is the logical beginning of the collection.  If the collection has fewer than
	// (i+1) items, throw an IndexOutOfBoundsException
	public void set(int i, T item);

	// Return the number of items currently in the Indexable. Note that this is the
	// same method specified in the MyQ<T> interface.  It is fine for a single method
	// to be part of more than one interface
	public int size();
}
