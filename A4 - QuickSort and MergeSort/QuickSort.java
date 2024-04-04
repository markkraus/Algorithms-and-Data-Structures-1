public class QuickSort<T extends Comparable<? super T>> implements Sorter<T> {

  private Partitionable<T> partAlgo;
  private int MIN_SIZE; // min size to recurse, use InsertionSort for smaller sizes to complete sort
  private int count;

  public QuickSort(Partitionable<T> part) {
    partAlgo = part;
    MIN_SIZE = 5;
    count = 0;
  }

  @Override
  public void sort(T[] a, int size) {
    quickSort(a, 0, size - 1);
  }

  @Override
  public void setMin(int minSize) {
    MIN_SIZE = minSize;
  }

  private void quickSort(T[] a, int first, int last) {
    if (last - first + 1 < MIN_SIZE) {
      // Array is small enough to use Insertion Sort
      insertionSort(a, first, last);
    } else {
      // Not small enough - use Quick Sort
      int pivotIndex = partAlgo.partition(a, first, last);
      count++; // counting minRecurse
      quickSort(a, first, pivotIndex - 1);
      quickSort(a, pivotIndex + 1, last);
    }
  }

  private void insertionSort(T[] a, int first, int last) {
    int unsorted;
    for (unsorted = first + 1; unsorted <= last; unsorted++) {
      T firstUnsorted = a[unsorted];
      insertInOrder(firstUnsorted, a, first, unsorted - 1);
    }
  }

  private void insertInOrder(T element, T[] a, int begin, int end) {
    int index;
    for (index = end; (index >= begin) && (element.compareTo(a[index]) < 0); index--) {
      a[index + 1] = a[index]; // make room
    }
    a[index + 1] = element; // insert
  }

  public int getCount() {
    return count;
  }
}
