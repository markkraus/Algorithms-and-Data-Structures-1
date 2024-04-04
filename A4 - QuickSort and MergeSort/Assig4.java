import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Assig4 {
  public static void main(String args[]) {
    // Make a single Random Object
    Random R = new Random();

    ArrayList<Sorter<Integer>> sorts;
    ArrayList<Double> totalTimes = new ArrayList<>();

    // Data to sort will be an array of Integer
    Integer[] A, temp;
    int size;
    int trials;
    @SuppressWarnings("unused")
    double avg;

    size = Integer.parseInt(args[0]); // array size
    trials = Integer.parseInt(args[1]); // number of trials

    // Add sorting algorithms to the list
    sorts = new ArrayList<>();
    sorts.add(new QuickSort<Integer>(new SimplePivot<Integer>()));
    sorts.add(new QuickSort<Integer>(new MedOfThree<Integer>()));
    sorts.add(new QuickSort<Integer>(new RandomPivot<Integer>()));
    sorts.add(new QuickSort<Integer>(new MedOfFive<Integer>()));
    sorts.add(new MergeSort<Integer>());

    temp = new Integer[size];
    A = new Integer[size];

    sequenceArray(temp, size);

    try {
      FileWriter writer = new FileWriter("output.txt");

      writer.write("Initialization information:\n");
      writer.write("Array size: " + size + "\n");
      writer.write("Number of runs per test: " + trials + "\n\n");

      // Test each sorting algorithm
      for (Sorter<Integer> sort : sorts) {
        totalTimes.clear(); // Clear previous times for each sort

        for (int j = 0; j < trials; j++) {
          copyArray(temp, A);

          R.setSeed(1234);

          sort.setMin(5);

          double times = 0;
          shuffle(A, R);

          long start = System.nanoTime();
          sort.sort(A, A.length);
          long stop = System.nanoTime();
          long diff = stop - start;
          double sec = (double) diff / 1000000000;
          times += sec;

          totalTimes.add(times);
        }

        double minTime = Double.MAX_VALUE;
        double maxTime = Double.MIN_VALUE;
        double total = 0;
        for (double time : totalTimes) {
          minTime = Math.min(minTime, time);
          maxTime = Math.max(maxTime, time);
          total += time;
        }
        avg = total / trials;

        writer.write("Algorithm: " + sort.getClass().getSimpleName() + "\n");
        writer.write("Best Result:\n");
        if (sort instanceof QuickSort) {
          writer.write("  Min Recurse: " + ((QuickSort<Integer>) sort).getCount() + "\n");
        }
        writer.write("  Average: " + minTime + " sec\n");
        writer.write("Worst Result:\n");
        if (sort instanceof QuickSort) {
          writer.write("  Min Recurse: " + ((QuickSort<Integer>) sort).getCount() + "\n");
        }
        writer.write("  Average: " + maxTime + " sec\n\n");
      }

      // Print best and worst setups
      printBestAndWorst(sorts, totalTimes, writer);

      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void printBestAndWorst(ArrayList<Sorter<Integer>> sorts, ArrayList<Double> totalTimes,
      FileWriter writer) throws IOException {
    double minTime = Double.MAX_VALUE;
    double maxTime = Double.MIN_VALUE;
    int minIndex = -1;
    int maxIndex = -1;

    for (int i = 0; i < sorts.size(); i++) {
      double totalTime = totalTimes.get(i);
      if (totalTime < minTime) {
        minTime = totalTime;
        minIndex = i;
      }
      if (totalTime > maxTime) {
        maxTime = totalTime;
        maxIndex = i;
      }
    }

    if (minIndex != -1 && maxIndex != -1) {
      writer.write("After the tests, here is the best setup:\n");
      writer.write("Algorithm: " + sorts.get(minIndex).getClass().getSimpleName() + "\n");
      writer.write("  Min Recurse: " + ((QuickSort<Integer>) sorts.get(minIndex)).getCount() + "\n");
      writer.write("  Average: " + minTime + " sec\n\n");

      writer.write("After the tests, here is the worst setup:\n");
      writer.write("Algorithm: " + sorts.get(maxIndex).getClass().getSimpleName() + "\n");
      writer.write("  Min Recurse: " + ((QuickSort<Integer>) sorts.get(maxIndex)).getCount() + "\n");
      writer.write("  Average: " + maxTime + " sec\n\n");
    }
  }

  // Fill array with random data
  public void fillArray(Integer[] arr) {
    Random R = new Random();
    for (int i = 0; i < arr.length; i++) {
      // Values will be 0 <= X < 2 billion
      arr[i] = Integer.valueOf(R.nextInt(2000000000));
    }
  }

  public static void sequenceArray(Integer[] a, Integer N) {
    for (int i = 1; i <= N; i++) {
      a[i - 1] = i;
    }
  }

  public static void shuffle(Integer[] a, Random r) {
    for (int i = 0; i < a.length; i++) {
      int randIndex = r.nextInt(a.length);
      Integer temp = a[randIndex];
      if (temp != null) {
        a[randIndex] = a[0];
        a[0] = temp;
      }
    }
  }

  public static void copyArray(Integer[] orig, Integer[] copy) {
    for (int i = 0; i < orig.length; i++)
      copy[i] = orig[i];
  }

  public static void showArray(Integer[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println("\n");
  }
}
