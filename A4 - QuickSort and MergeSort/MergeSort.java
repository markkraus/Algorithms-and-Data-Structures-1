public class MergeSort<T extends Comparable<? super T>>
    implements Sorter<T> {
  @SuppressWarnings("unused")
  private int MIN_SIZE; // min size to recurse, use InsertionSort for smaller sizes to complete sort

  public MergeSort() {
    MIN_SIZE = 5;
  }
  // remaining code in MergeSort class not shown
  // You must complete this class – in particular the methods
  // sort() and setMin().

  @Override
  public void sort(T[] a, int size) {
    mergeSort(a, 0, size - 1);
  }

  @Override
  public void setMin(int minSize) {
    MIN_SIZE = minSize;
  }

  private void mergeSort(T[] a, int first, int last) {
    @SuppressWarnings("unchecked")
    T[] tempArray = (T[]) new Comparable<?>[a.length];
    mergeSort(a, tempArray, first, last);
  }

  private void mergeSort(T[] a, T[] tempArray, int first, int last) {
    if (first < last) {
      // Sort each half
      int mid = (first + last) / 2;
      mergeSort(a, tempArray, first, mid);
      mergeSort(a, tempArray, mid + 1, last);
      if (a[mid].compareTo(a[mid + 1]) > 0){
        merge(a, tempArray, first, mid, last);
      }
    }
  }

  private void merge(T[] a, T[] tempArray, int first, int mid, int last) {
    // Two adjacent subarrays are a[beginHalf1..endHalf1] anda[beginHalf2..endHalf2]
    int beginHalf1 = first;
    int endHalf1 = mid;
    int beginHalf2 = mid + 1;
    int endHalf2 = last;

    // Copy the smaller item into the temporary array
    int index = beginHalf1;
    for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++) {
      if (a[beginHalf1].compareTo(a[beginHalf2]) <= 0) {
        tempArray[index] = a[beginHalf1];
        beginHalf1++;
      } else {
        tempArray[index] = a[beginHalf2];
        beginHalf2++;
      }
    }

    // Finish off the nonempty subarray
    for (; beginHalf1 <= endHalf1; beginHalf1++, index++) {
      tempArray[index] = a[beginHalf1];
    }
    for (; beginHalf2 <= endHalf2; beginHalf2++, index++) {
      tempArray[index] = a[beginHalf2];
    }

    // Copy the result back into the original array
    for (index = first; index <= last; index++) {
      a[index] = tempArray[index];
    }
  }
}