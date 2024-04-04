public class MedOfFive <T extends Comparable<? super T>> implements Partitionable<T> {

  public int partition(T[] a, int first, int last) {
    // Middle, left/right middle elements
    int mid = (first + last) / 2;
    int fmid = (first + mid) / 2;
    int lmid = (mid + last) / 2;
    sortFive(a, first, fmid, mid, lmid, last);
    swap(a, mid, last - 1);

    int pivotIndex = last - 1;
    T pivot = a[pivotIndex];

    //Pointers on both ends of the list to sort
    int indexFromLeft = first + 1;
    int indexFromRight = last - 2;

    boolean done = false;
    while (!done) {
      // starting at beginning of array, leave elements that are < pivot;
      // locate first element that is >= pivot
      while (a[indexFromLeft].compareTo(pivot) < 0)
        indexFromLeft++;

      // starting at end of array, leave elements that are > pivot;
      // locate first element that is <= pivot

      while (a[indexFromRight].compareTo(pivot) > 0 && indexFromRight > first)
        indexFromRight--;

      // Assertion: a[indexFromLeft] >= pivot and
      //            a[indexFromRight] <= pivot.

      if (indexFromLeft < indexFromRight) {
        swap(a, indexFromLeft, indexFromRight);
        indexFromLeft++;
        indexFromRight--;
      }
      else
        done = true;
    }

    // place pivot between Smaller and Larger subarrays
    swap(a, pivotIndex, indexFromLeft);
    pivotIndex = indexFromLeft;

    // Assertion:
    // Smaller = a[first..pivotIndex-1]
    // Pivot = a[pivotIndex]
    // Larger  = a[pivotIndex + 1..last]

    return pivotIndex;
  }

    private void sortFive(T[] a, int first, int fmid, int mid, int lmid, int last) {
      order(a, first, fmid);
      order(a, first, mid);
      order(a, first, lmid);
      order(a, first, last); 
      order(a, fmid, mid);
      order(a, fmid, lmid);
      order(a, fmid, last);
      order(a, mid, lmid);
      order(a, mid, last);
      order(a, lmid, last);
    } 

    /** Task: Orders two given array elements into ascending order
     *        so that a[i] <= a[j].
     *  @param a  an array of Comparable objects
     *  @param i  an integer >= 0 and < array.length
     *  @param j  an integer >= 0 and < array.length */
    private void order(T[] a, int i, int j) {
      if (a[i].compareTo(a[j]) > 0)
        swap(a, i, j);
    }

    private void swap(Object[] a, int i, int j) {
      Object temp = a[i];
      a[i] = a[j];
      a[j] = temp;
    } 
}
