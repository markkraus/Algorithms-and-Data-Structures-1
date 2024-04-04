import java.util.Random;


public class RandIndexQueue<T> implements MyQ<T>, Shufflable, Indexable<T> {
  //Initialize a q, the front, back, size of the q, along with the moves variable.
  private T[] q;
  private int front;
  private int back;
  private int size;
  private int moves;

  //Copy constructor
  @SuppressWarnings("unchecked")
  public RandIndexQueue(int sz) {
    size = 0;
    q = (T[]) new Object[sz];
    back = 0;
    front = 0;
    moves = 0;
  }


  public T get(int i) {
    //If the size is less than or equal to the next index, throw an out of bounds except
    if (size < i + 1) {
      throw new IndexOutOfBoundsException();

    }else if(front + i >= q.length){
      return q[(front + i) - q.length];
    }else{
      return q[front + i];
    }



  }

  public void set(int i, T item) {

    if (size < i + 1) {
      throw new IndexOutOfBoundsException();

    }else if(front + i >= q.length){
      q[(front + i) - q.length] = item;
    }else{
      q[front + i] = item;
    }

  }

  //return the size of the array
  public int size() {
    return size;
  }

  public void shuffle() {

  Random random = new Random();

  for(int i = 0; i< size; i++){
    int randIndex = random.nextInt(q.length);
    T temp = q[randIndex];
    if(temp != null){
      q[randIndex] = q[front];
      q[front] = temp;
    }



    }
   // System.out.print("---");

  }


  public int capacity() {
    //size is equal to the length of the q, thus returning it
    int size = q.length;
    return size;
  }

  public int getMoves() {
    //getter method; return the moves #
    return moves;
  }

  public void setMoves(int mv) {
    //setter method
    moves = mv;
  }


  @Override
  public void enqueue(T newEntry) {
    //if one less than capacity is less than the size of array, create new q and double the size
    if (capacity() - 1 < size) {
      @SuppressWarnings("unchecked")
      T[] tempQ = (T[]) new Object[capacity() * 2];

      int newBack = 0;
      //if the size and capacity are equal, loop through the new q at index i and assign it to the front mod capacity # in the q - increment front
      if(size == capacity()){
        for(int i = 0; i < size; i++){
          tempQ[i] = q[front % capacity()];
          front++;
        }
      }else{
        //else - while front and back do not equal, assign the location of newBack variable in new q to the front index of old q
        while (front != back) {
          tempQ[newBack] = q[front];
          //if one more than front equals back then front++
          if (front + 1 == back) {
            front = front + 1;
          } else {
            //else - make for circular nature of array with use of mod method
            front = (front + 1) % capacity();
            newBack++;
          }

        }

      }
      //the new q location of size now equals new entry, increment size, assign q to new q, assign back to size, front will equal 0, increment moves
      tempQ[size] = newEntry;
      size++;
      q = tempQ;
      back = size;
      front = 0;
      moves++;
    }else{
      //if it is empty, then front will equal 0
      if (isEmpty()) {
        front = 0;
      }
      back = back % capacity();
      q[back] = newEntry;
      back++;
      size++;

      moves++;
    }


  }



  @Override
  public T dequeue() {
    @SuppressWarnings("unused")
    T ind;
    //if q is empty, throw exception
    if (isEmpty()) {
      throw new EmptyQueueException();
    }
    //temp is now front of q
    T temp = q[front];
    //front of q is null
    q[front] = null;

    size--;
    //   Mod - front = front + 1 % capacity()
    front = (front + 1) % capacity();
    //increment moves++;
    moves++;
    // q[front]
    return temp;

  }


  @Override
  public T getFront() {
    //if q is empty throw exception
    if (isEmpty()) {
      throw new EmptyQueueException();
    }
    //also, return the front of the q
    return q[front];
  }

  @Override
  public boolean isEmpty() {
    //will return true if size is equal to 0
    return size == 0;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void clear() {
    q = (T []) new Object[q.length];
    back= 0;
    size = 0;
    front = 0;
  }


  public RandIndexQueue(RandIndexQueue<T> old) {
    //Create new Q that copies the length of the old Q
    @SuppressWarnings("unchecked")
    T[] copyarray = (T[]) new Object[old.q.length];
    for (int i = 0; i < old.q.length; i++) {
      copyarray[i] = old.q[i];
    }
    this.q = copyarray;
    this.size = old.size;
    this.front = old.front;
    this.back = old.back;
  }

  public boolean equalsX(RandIndexQueue<T> rhs) {
    //check sizes
  if(size != rhs.size){
    //if not equal - break
    return false;
  }
    int rhsFront = rhs.front;
    int tempFront = front;
    for(int i = 0 ; i < size; i++){
      T temp1 = q[tempFront];
      T temp2 = rhs.q[rhsFront];
      if(temp1.equals(temp2)){
        rhsFront = (rhsFront + 1) % rhs.capacity();
        tempFront = (tempFront + 1) % capacity();
      }else{
        return false;
      }

    }

    return true;
 }

  public String toString() {

    //start at logical front
    int tempFront = front;
    StringBuilder str = new StringBuilder();
    //iterate through q thru all indices
    while (tempFront != back) {
      str.append(q[tempFront] + " ");
      if (tempFront + 1 == back) {
        tempFront = tempFront + 1;
      } else {
        tempFront = (tempFront + 1) % capacity();
      }

    }

    return "Contents: " + str.toString();
  }




}
