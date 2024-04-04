/* Simple Pivot using Quick Sort algorithm */
public class SimplePivot<T extends Comparable<? super T>> implements Partitionable<T> {

  public int partition(T[] a, int first, int last) {
    // Simply pick pivot as rightmost element
    int pivotIndex = last;  
    T pivot = a[pivotIndex];
    int indexFromLeft = first;
    int indexFromRight = last - 1;
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
      } else
        done = true;
    } 

    // Place pivot between Smaller and Larger subarrays
    swap(a, pivotIndex, indexFromLeft);
    pivotIndex = indexFromLeft;

    // Assertion:
    // Smaller = a[first..pivotIndex-1]
    // Pivot = a[pivotIndex]
    // Larger  = a[pivotIndex + 1..last]

    return pivotIndex;
  }

  private void swap(Object[] a, int i, int j) {
      Object temp = a[i];
      a[i] = a[j];
      a[j] = temp;
  }
}
