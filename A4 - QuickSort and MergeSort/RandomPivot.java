import java.util.Random;
public class RandomPivot <T extends Comparable<? super T>> implements Partitionable<T>{

  public int partition(T[] a, int first, int last) {
    // Random pivot is assigned
    Random rand = new Random();
    int pivotIndex = rand.nextInt(last-first+1)+first;
    swap(a, pivotIndex, last);

    // Set the pibot to the last
    pivotIndex = last;
    T pivot = a[pivotIndex];
    int indexFromLeft = first;
    int indexFromRight = last - 1;
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
      while (a[indexFromRight].compareTo(pivot) > 0 && indexFromRight > first)
        indexFromRight--;

      assert a[indexFromLeft].compareTo(pivot) >= 0 &&
        a[indexFromRight].compareTo(pivot) <= 0;

      if (indexFromLeft < indexFromRight) {
        swap(a, indexFromLeft, indexFromRight);
        indexFromLeft++;
        indexFromRight--;
      }
      else
        done = true;
    }

    // Place pivot between Smaller and Larger subarrays
    swap(a, pivotIndex, indexFromLeft);
    pivotIndex = indexFromLeft;

    return pivotIndex;
  }


  private void swap(Object[] a, int i, int j) {
    Object temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public void quickSort(T[] a, int left, int right) {
    int index = partition(a, left, right);
    if (left < index - 1)
      quickSort(a, left, index - 1);
    if (index < right)
      quickSort(a, index, right);
  }
}
