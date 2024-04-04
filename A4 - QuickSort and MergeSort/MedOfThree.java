/* Median of Three using Quicksort algorithm */
public class MedOfThree <T extends Comparable<? super T>> implements Partitionable<T> {

  @Override
  public int partition(T[] a, int first, int last) {
    // Choose the middle number in the array as pivot
    int mid = (first + last) / 2;
    sortThree(a, first, mid, last);
    swap(a, mid, last - 1);

    // Set pivot to the last value
    int pivotIndex = last - 1;
    T pivot = a[pivotIndex];
    int indexFromLeft = first + 1;
    int indexFromRight = last - 2;
    boolean done = false;

    while (!done) {
      // starting at beginning of array, leave elements that are < pivot;
      // locate first element that is >= pivot; you will find one,
      // since last element is >= pivot
      while (a[indexFromLeft].compareTo(pivot) < 0)
        indexFromLeft++;
      // starting at end of array, leave elements that are > pivot;
      // locate first element that is <= pivot; you will find one,
      // since first element is <= pivot
      while (a[indexFromRight].compareTo(pivot) > 0)
        indexFromRight--;
      assert a[indexFromLeft].compareTo(pivot) >= 0 &&
          a[indexFromRight].compareTo(pivot) <= 0;
      if (indexFromLeft < indexFromRight) {
        swap(a, indexFromLeft, indexFromRight);
        indexFromLeft++;
        indexFromRight--;
      } else
        done = true;
    } // end while
    // place pivot between Smaller and Larger subarrays
    swap(a, pivotIndex, indexFromLeft);
    pivotIndex = indexFromLeft;
    // Assertion:
    // Smaller = a[first..pivotIndex-1]
    // Pivot = a[pivotIndex]
    // Larger = a[pivotIndex+1..last]
    return pivotIndex;
  }

  private void sortThree(T[] a, int first, int mid, int last) {
    order(a, first, mid); // a[first] <= a[mid]
    order(a, mid, last);  // a[mid] <= a[last]
    order(a, first, mid); // a[first] <= a[last]
  }

  private  void order(T[] a, int i, int j) {
    if (a[i].compareTo(a[j]) > 0)
      swap(a, i, j);
  }

  private void swap(Object[] a, int i, int j) {
    Object temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}
