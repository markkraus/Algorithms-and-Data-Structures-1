import java.util.Iterator;

public class PrimQ1WithIterator<T> implements QueueWithIteratorInterface<T> {

  private T[] queue; // Array of queue entries
  private int frontIndex; // Index of front entry
  private int backIndex; // Index of back entry
  private static final int DEFAULT_INITIAL_CAPACITY = 10;

  public PrimQ1WithIterator() {
    this(DEFAULT_INITIAL_CAPACITY);
  }

  public PrimQ1WithIterator(int initialCapacity) {
    @SuppressWarnings("unchecked")
    T[] tempQueue = (T[]) new Object[initialCapacity];
    queue = tempQueue;
    frontIndex = 0;
    backIndex = initialCapacity - 1;
  }

  @Override
  public Iterator<T> iterator() {
    return new QueueIterator();
  }

  private class QueueIterator implements Iterator<T> {
    private int currentIndex;

    public QueueIterator() {
      currentIndex = frontIndex;
    }

    @Override
    public boolean hasNext() {
      return currentIndex != (backIndex + 1) % queue.length;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException();
      }
      T result = queue[currentIndex];
      currentIndex = (currentIndex + 1) % queue.length;
      return result;
    }
  }

  @Override
  public void enqueue(T newEntry) {
    if (!isFull()) {
      backIndex = (backIndex + 1) % queue.length;
      queue[backIndex] = newEntry;
    } else {
      throw new IllegalStateException("Queue is full");
    }
  }

  @Override
  public T dequeue() {
    T front = null;
    if (!isEmpty()) {
      front = queue[frontIndex];
      queue[frontIndex] = null;
      frontIndex = (frontIndex + 1) % queue.length;
    }
    return front;
  }

  @Override
  public T getFront() {
    T front = null;
    if (!isEmpty()) {
      front = queue[frontIndex];
    }
    return front;
  }

  @Override
  public boolean isEmpty() {
    return frontIndex == (backIndex + 1) % queue.length;
  }

  @Override
  public void clear() {
    while (!isEmpty()) {
      dequeue();
    }
  }

  private boolean isFull() {
    return frontIndex == (backIndex + 2) % queue.length;
  }
}
